package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

/**
 * Test class for Document using TDD approach.
 */
public class TestDocument {
    
    @TempDir
    Path tempDir;
    
    private File testFile;
    
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary test file for testing
        testFile = tempDir.resolve("test-document.txt").toFile();
        Files.writeString(testFile.toPath(), "Sample document content");
    }
    
    // TDD Cycle 1: Test that Document can accept a File
    @Test
    public void testDocumentCanReceiveFile() {
        Document doc = new Document(testFile);
        assertNotNull(doc);
    }
    
    // TDD Cycle 2: Test that Document stores the file
    @Test
    public void testDocumentStoresFile() {
        Document doc = new Document(testFile);
        assertEquals(testFile, doc.getFile());
    }
    
    // TDD Cycle 3: Test pre-classified document
    @Test
    public void testDocumentCanBePreClassified() {
        Document doc = new Document(testFile, true);
        assertTrue(doc.isClassified());
    }
    
    // TDD Cycle 4: Test post-classified document
    @Test
    public void testDocumentCanBePostClassified() {
        Document doc = new Document(testFile, false);
        assertFalse(doc.isClassified());
    }
    
    // TDD Cycle 5: Test default is post-classified
    @Test
    public void testDocumentDefaultsToPostClassified() {
        Document doc = new Document(testFile);
        assertFalse(doc.isClassified(), "Document should default to post-classified (false)");
    }
    
    // TDD Cycle 6: Test null file is rejected
    @Test
    public void testDocumentRejectsNullFile() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Document(null);
        });
    }
    
    // TDD Cycle 7: Test classification status can be changed
    @Test
    public void testClassificationStatusCanBeChanged() {
        Document doc = new Document(testFile, false);
        assertFalse(doc.isClassified());
        
        doc.setClassified(true);
        assertTrue(doc.isClassified());
    }
}
