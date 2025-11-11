package edu.odu.cs.cs350;

import java.io.*;
import java.util.*;

/**
 * Description of what this class or method does.
 */


public class LearningMachine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Double> vocabulary = new HashMap<>();
    private Set<String> categories = new HashSet<>();

    public void train(Corpus corpus) {
        for (Document doc : corpus.getDocuments()) {
            categories.add(doc.getCategory());
            String[] tokens = doc.getText().split("\\W+");
            for (String token : tokens) {
                if (!token.isBlank()) {
                    vocabulary.merge(token.toLowerCase(), 1.0, Double::sum);
                }
            }
        }
    }

    public void save(String modelFile) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(modelFile))) {
            oos.writeObject(categories);
            oos.writeObject(vocabulary);
        }
    }

    public static LearningMachine load(String modelFile) throws IOException, ClassNotFoundException {
        LearningMachine machine = new LearningMachine();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(modelFile))) {
            machine.categories = (Set<String>) ois.readObject();
            machine.vocabulary = (Map<String, Double>) ois.readObject();
        }
        return machine;
    }

    // ✅ New method for embedded model loading
    public static LearningMachine loadFromStream(InputStream stream) {
        LearningMachine machine = new LearningMachine();
        try (ObjectInputStream ois = new ObjectInputStream(stream)) {
            machine.categories = (Set<String>) ois.readObject();
            machine.vocabulary = (Map<String, Double>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error loading embedded model: " + e.getMessage());
        }
        return machine;
    }

    public String classify(Document doc) {
        // Simple placeholder classification logic for testing
        String[] tokens = doc.getText().split("\\W+");
        String topCategory = categories.isEmpty() ? "Unknown" : categories.iterator().next();
        return topCategory;
    }
}
