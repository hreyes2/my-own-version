package edu.odu.cs.cs350;

import java.io.File;

/**
 * Represents a document that can be received from the Classifier.
 * Documents can be pre-classified or post-classified.
 */
public class Document {
    
    private File file;
    private boolean classified;
    
    /**
     * Creates a Document with the given file.
     * Defaults to post-classified (not yet classified).
     * 
     * @param file the document file
     * @throws IllegalArgumentException if file is null
     */
    public Document(File file) {
        this(file, false);
    }
    
    /**
     * Creates a Document with the given file and classification status.
     * 
     * @param file the document file
     * @param classified true if pre-classified, false if post-classified
     * @throws IllegalArgumentException if file is null
     */
    public Document(File file, boolean classified) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        this.file = file;
        this.classified = classified;
    }
    
    /**
     * Gets the document file.
     * 
     * @return the file
     */
    public File getFile() {
        return file;
    }
    
    /**
     * Checks if the document is classified.
     * 
     * @return true if pre-classified, false if post-classified
     */
    public boolean isClassified() {
        return classified;
    }
    
    /**
     * Sets the classification status.
     * 
     * @param classified the new classification status
     */
    public void setClassified(boolean classified) {
        this.classified = classified;
    }
}