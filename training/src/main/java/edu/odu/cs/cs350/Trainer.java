package edu.odu.cs.cs350;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Description of what this class or method does.
 */


class Trainer {

    private String category;
    private HashMap<String, Integer> topWords;
    private String[] stopList = {"the", "and", "of", "to", "in", "a", ".", "on", "for", "is", "as", "from", "with", "that", "an", "using"};
    private ArrayList<String> stopArrayList = new ArrayList<>(Arrays.asList(stopList));
    
    public Trainer(String category) {
        this.category = category;
        this.topWords = new HashMap<String, Integer>();
    }

    public String getCategory() {
        return this.category;
        
    }

    private void addWord(String word) {
        if (topWords.containsKey(word)) {
            topWords.put(word, topWords.get(word)+1);
        } else {
            topWords.put(word, 1);
        }
    }

    public void getTopWord(int n){
        int highestValue = Integer.MIN_VALUE;
        String keyOfHighestValue = null;
        ArrayList<String> checkedWords = new ArrayList<>();
        System.out.println(topWords.size());
        
        for (int i = 0; i < n; i++) {
            for (HashMap.Entry<String, Integer> entry: topWords.entrySet()) {
                if (entry.getValue() > highestValue && checkedWords.contains(entry.getKey()) == false && stopArrayList.contains(entry.getKey().toLowerCase()) == false) {
                    highestValue = entry.getValue();
                    keyOfHighestValue = entry.getKey();
                }
            }
            
            System.out.println(keyOfHighestValue + ": " + highestValue);
            checkedWords.add(keyOfHighestValue);
            highestValue = Integer.MIN_VALUE;
            keyOfHighestValue = null;
        }
        
        // return keyOfHighestValue;


    }
    
    public void store(File file) {
        if (file.exists()) {
            try {
                if (file.canRead()) {
                    System.out.println("File is readable.\n" + file.getName());
                    Scanner scanner = new Scanner(file, "UTF-8");
                    int lines = 0;
                    while (scanner.hasNext()) {
                        addWord(scanner.next());
                        lines++;
                    }

                    scanner.close();
                    System.out.println("words:" + lines);
                    getTopWord(15);
                }
                
            } catch (FileNotFoundException e) {
                System.err.println("Error: File not found " + e.getMessage());
            }
            
        }   else {
            System.err.println("File does not exist!");
        }
        

    }
    public static void main(String[] args) {
        Trainer applied_Computing = new Trainer("Applied Computing");
        File file = new File("common/src/main/java/edu/odu/cs/cs350/Knowledge-Distillation-in-RNN.txt");
        applied_Computing.store(file);
    }
    
}

