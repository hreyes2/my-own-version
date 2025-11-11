package edu.odu.cs.cs350;

import java.io.File;

/**
 * Classifier class that can retrieve file paths from command-line arguments.
 * Story 49: Classifier class to retrieve file path from the CLI.
 */
public class Classifier {
    
    private String filePath;
    
    /**
     * Sets the file path from command-line arguments.
     * Uses the first argument as the file path.
     * 
     * @param args command-line arguments
     * @throws IllegalArgumentException if args is null or empty
     */
    public void setFilePathFromCLI(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments cannot be empty");
        }
        this.filePath = args[0];
    }
    
    /**
     * Gets the file path that was set from CLI.
     * 
     * @return the file path string
     */
    public String getFilePath() {
        return filePath;
    }
    
    /**
     * Gets a File object for the stored file path.
     * 
     * @return File object representing the file path
     */
    public File getFile() {
        if (filePath == null) {
            return null;
        }
        return new File(filePath);
    }

    public static void main(String[] args) {
        
    }
}