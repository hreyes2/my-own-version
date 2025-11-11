package edu.odu.cs.cs350;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.*;
/**
 * Description of what this class or method does.
 */


public class Trainer {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java -jar training.jar <trainingData.csv>");
            System.exit(1);
        }

        String csvPath = args[0];
        System.out.println("Loading training data from: " + csvPath);

        // Load the corpus (Option B: trainingData.csv lists category,filepath)
        Corpus corpus = new Corpus(csvPath, true);

        // Train the learning machine
        LearningMachine machine = new LearningMachine();
        machine.train(corpus);

        try {
            // Save model into classifier resources folder for embedding
            Path modelPath = Paths.get("../classifying/src/main/resources/model.dat");
            Files.createDirectories(modelPath.getParent());
            machine.save(modelPath.toString());

            System.out.println("Model trained and saved to: " + modelPath.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error saving trained model: " + e.getMessage());
        }
    }
}
