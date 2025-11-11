package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

/**
 * Test class for Classifier using TDD.
 * Story 49: Classifier class to retrieve file path from the CLI.
 */
public class TestClassifier {
    
    private Classifier classifier;
    
    @BeforeEach
    public void setUp() {
        classifier = new Classifier();
    }
    
    @Test
    public void testClassifierExists() {
        assertNotNull(classifier);
    }
    
    // TDD Cycle 1: Test that Classifier can accept file path from CLI args
    @Test
    public void testClassifierAcceptsFilePathFromArgs() {
        String[] args = {"/path/to/document.txt"};
        classifier.setFilePathFromCLI(args);
        assertNotNull(classifier.getFilePath());
    }
    
    // TDD Cycle 2: Test that Classifier stores the correct file path
    @Test
    public void testClassifierStoresCorrectFilePath() {
        String[] args = {"/path/to/document.txt"};
        classifier.setFilePathFromCLI(args);
        assertEquals("/path/to/document.txt", classifier.getFilePath());
    }
    
    // TDD Cycle 3: Test with multiple arguments (should use first one)
    @Test
    public void testClassifierUsesFirstArgument() {
        String[] args = {"/path/to/first.txt", "/path/to/second.txt"};
        classifier.setFilePathFromCLI(args);
        assertEquals("/path/to/first.txt", classifier.getFilePath());
    }
    
    // TDD Cycle 4: Test with empty args array
    @Test
    public void testClassifierRejectsEmptyArgs() {
        String[] args = {};
        assertThrows(IllegalArgumentException.class, () -> {
            classifier.setFilePathFromCLI(args);
        });
    }
    
    // TDD Cycle 5: Test with null args
    @Test
    public void testClassifierRejectsNullArgs() {
        assertThrows(IllegalArgumentException.class, () -> {
            classifier.setFilePathFromCLI(null);
        });
    }
    
    // TDD Cycle 6: Test that file path can be converted to File object
    @Test
    public void testClassifierReturnsFileObject() {
        String[] args = {"/path/to/document.txt"};
        classifier.setFilePathFromCLI(args);
        File file = classifier.getFile();
        assertNotNull(file);
        assertEquals("/path/to/document.txt", file.getPath());
    }
    
    // TDD Cycle 7: Test with whitespace in path
    @Test
    public void testClassifierHandlesWhitespaceInPath() {
        String[] args = {"/path/to/my document.txt"};
        classifier.setFilePathFromCLI(args);
        assertEquals("/path/to/my document.txt", classifier.getFilePath());
    }
}
