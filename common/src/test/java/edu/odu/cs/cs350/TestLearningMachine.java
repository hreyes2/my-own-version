package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for LearningMachine.
 */
public class TestLearningMachine {
    
    @Test
    public void testLearningMachineExists() {
        LearningMachine machine = new LearningMachine();
        assertNotNull(machine);
    }
}
