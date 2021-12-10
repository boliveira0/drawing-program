package org.boliveira.drawing.view;

import org.boliveira.drawing.control.CanvasCommandOrchestrator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandViewTest implements ArgumentsProvider {

    private Navigable parent = Mockito.mock(Navigable.class);
    private CommandView commandView = new CommandView(new CanvasCommandOrchestrator(), parent);


    @ParameterizedTest
    @ArgumentsSource(CommandViewTest.class)
    void whenValidCommandPassed_ExpectResult(CommandView view, String commandLine, Optional<String> expected) {
        assertEquals(expected, view.handleInput(String.valueOf(commandLine)));
    }


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(this.commandView, "C 20 4", Optional.of(this.blankCanvas())),
                Arguments.of(this.commandView, "L 1 2 6 2", Optional.of(this.horizontalLineCanvas())),
                Arguments.of(this.commandView, "L 6 3 6 4", Optional.of(this.vertialLineCanvas())),
                Arguments.of(this.commandView, "R 14 1 18 3", Optional.of(this.rectangleCanvas())),
                Arguments.of(this.commandView, "B 10 3 o", Optional.of(this.bucketFillCanvas())),
                Arguments.of(this.commandView, "Q", Optional.empty()));
    }

    private String blankCanvas() {
        return "Not yet implemented";
    }

    private String horizontalLineCanvas() {
        return "Not yet implemented";
    }


    private String vertialLineCanvas() {
        return "Not yet implemented";
    }

    private String rectangleCanvas() {
        return "Not yet implemented";
    }

    private String bucketFillCanvas() {
        return "Not yet implemented";
    }

}