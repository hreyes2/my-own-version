package edu.odu.cs.cs350;

import java.io.File;
import java.io.*;
import java.nio.file.*;
/**
 * Classifier class that can retrieve file paths from command-line arguments.
 * Story 49: Classifier class to retrieve file path from the CLI.
 */


public class Classifier {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java -jar classifier.jar <file1> <file2> ...");
            System.exit(1);
        }

        // Load embedded model from the JAR's resources
        InputStream modelStream = Classifier.class.getResourceAsStream("/model.dat");
        if (modelStream == null) {
            System.err.println("❌ Embedded model not found inside classifier.jar");
            System.exit(1);
        }

        LearningMachine machine = LearningMachine.loadFromStream(modelStream);

        for (String filePath : args) {
            try {
                String text = Files.readString(Paths.get(filePath));
                String fileName = Paths.get(filePath).getFileName().toString();

                // Create Document (no category since it’s to be predicted)
                Document doc = new Document("", fileName, text);

                // Classify and print result
                String predictedClass = machine.classify(doc);
                System.out.println(fileName + ": " + predictedClass);
            } catch (IOException e) {
                logError("Error reading file " + filePath + ": " + e.getMessage());
            }
        }
    }

    private static void logError(String message) {
        try (FileWriter fw = new FileWriter("classifier.log", true)) {
            fw.write(message + "\n");
        } catch (IOException ignored) {
        }
    }
}
