package edu.odu.cs.cs350;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a document that can be received from the Classifier.
 * Documents can be pre-classified or post-classified.
 */

public class Document implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String filePath;    // full or relative path to the file
    private final String name;        // file name only (no path)
    private String category;          // nullable: known for training, null for classification
    private final String text;        // file contents (may be empty)

    // Constructor for labeled doc
    public Document(String category, String filePath, String text) {
        this.filePath = filePath;
        this.name = extractName(filePath);
        this.category = category;
        this.text = (text == null) ? "" : text;
    }

    // Constructor for unlabeled doc
    public Document(String filePath, String text) {
        this(null, filePath, text);
    }

    private static String extractName(String path) {
        try {
            Path p = Paths.get(path);
            return p.getFileName().toString();
        } catch (Exception e) {
            // fallback
            int idx = path.lastIndexOf('/');
            if (idx == -1) idx = path.lastIndexOf('\\');
            return (idx == -1) ? path : path.substring(idx + 1);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    // Used by classifier to assign predicted category
    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Document[name=" + name + ", category=" + category + "]";
    }
}
