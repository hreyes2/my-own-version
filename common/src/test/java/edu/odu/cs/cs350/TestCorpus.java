package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Corpus.
 */
public class TestCorpus {
    
    @Test
    public void testCorpusExists() {
        Corpus corpus = new Corpus();
        assertNotNull(corpus);
    }
}
