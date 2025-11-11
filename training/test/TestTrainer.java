package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Trainer.
 */
public class TestTrainer {
    
    @Test
    public void testTrainerExists() {
        Trainer trainer = new Trainer("a");
        assertNotNull(trainer);
    }
}
