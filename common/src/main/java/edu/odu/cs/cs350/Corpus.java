package edu.odu.cs.cs350;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Description of what this class or method does.
 */



public class Corpus {

    private List<Document> documents = new ArrayList<>();

    /**
     * Default constructor for backward compatibility.
     * Assumes the input file is labeled (used for training).
     */
    public Corpus(String csvFilePath) {
        loadLabeledDescriptor(csvFilePath);
    }

    /**
     * Overloaded constructor that explicitly distinguishes
     * between labeled (training) and unlabeled (classification) data.
     *
     * @param descriptorPath path to the file containing data
     * @param labeled true if training data (has category, filepath)
     */
    public Corpus(String descriptorPath, boolean labeled) {
        if (labeled) {
            loadLabeledDescriptor(descriptorPath);
        } else {
            loadUnlabeledDescriptor(descriptorPath);
        }
    }

    /**
     * Load corpus from a labeled CSV file.
     * Each line: category, filepath
     */
    private void loadLabeledDescriptor(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                String category = parts[0].trim();
                String filePath = parts[1].trim();

                // Read the text from the document file
                String content = safeReadFile(filePath);

                // Extract just the filename (not full path)
                String fileName = Paths.get(filePath).getFileName().toString();

                // Create a Document object
                Document doc = new Document(category, fileName, content);
                documents.add(doc);
            }
        } catch (IOException e) {
            System.err.println("Error loading labeled corpus: " + e.getMessage());
        }
    }

    /**
     * Load corpus from an unlabeled descriptor file.
     * Each line: filepath (used during classification)
     */
    private void loadUnlabeledDescriptor(String descriptorPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(descriptorPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String filePath = line;
                String content = safeReadFile(filePath);
                String fileName = Paths.get(filePath).getFileName().toString();

                // Create a Document object without a category
                Document doc = new Document(fileName, content);
                documents.add(doc);
            }
        } catch (IOException e) {
            System.err.println("Error loading unlabeled corpus: " + e.getMessage());
        }
    }

    /**
     * Safely read the contents of a file, returning an empty string if not found.
     */
    private String safeReadFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " (" + e.getMessage() + ")");
            return "";
        }
    }

    public List<Document> getDocuments() {
        return documents;
    }
}
