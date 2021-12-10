package org.boliveira.drawing.control;

import org.boliveira.drawing.control.CanvasCommandOrchestrator;
import org.boliveira.drawing.control.CommandExecutionFailedException;
import org.boliveira.drawing.control.CommandNotFoundException;
import org.boliveira.drawing.control.Orchestrable;
import org.boliveira.drawing.domain.Canvas;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CanvasCommandOrchestratorTest {

    private static Orchestrable<Canvas> orchestrator;

    @BeforeAll
    static void setup() {
        orchestrator = new CanvasCommandOrchestrator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"C", "L", "R", "B"})
    void whenValidCommandIsPassed_ExpectSuccessfulExecution(String commandLine) {
        assertThrows(CommandExecutionFailedException.class, () -> orchestrator.execute(commandLine));
    }

    @Test
    void whenInvalidCommandIsPassed_ExpectCommandNotFoundExecution() {
        assertThrows(CommandNotFoundException.class, () -> orchestrator.execute("A"));
    }
}