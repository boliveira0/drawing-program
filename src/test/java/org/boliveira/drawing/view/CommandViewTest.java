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

    private final Navigable parent = Mockito.mock(Navigable.class);
    private final CommandView commandView = new CommandView(new CanvasCommandOrchestrator(), parent);


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
                Arguments.of(this.commandView, "B 3 3 o", Optional.of(this.bucketFillCanvas2())),
                Arguments.of(this.commandView, "B 15 2 o", Optional.of(this.bucketFillCanvas3())),
                Arguments.of(this.commandView, "C 20 4", Optional.of(this.blankCanvas())),
                Arguments.of(this.commandView, "L 1 2 6 2", Optional.of(this.horizontalLineCanvas())),
                Arguments.of(this.commandView, "L 7 3 7 4", Optional.of(this.vertialLineCanvas2())),
                Arguments.of(this.commandView, "R 14 1 18 3", Optional.of(this.rectangleCanvas2())),
                Arguments.of(this.commandView, "B 10 3 o", Optional.of(this.bucketFillCanvas4())),
                Arguments.of(this.commandView, "Q", Optional.empty()));
    }


    private String blankCanvas() {
        return "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "----------------------";
    }

    private String horizontalLineCanvas() {
        return "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|xxxxxx              |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "----------------------";
    }


    private String vertialLineCanvas() {
        return "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|xxxxxx              |" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "----------------------";
    }

    private String vertialLineCanvas2() {
        return "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|xxxxxx              |" + System.lineSeparator() +
                "|      x             |" + System.lineSeparator() +
                "|      x             |" + System.lineSeparator() +
                "----------------------";
    }

    private String rectangleCanvas() {
        return "----------------------" + System.lineSeparator() +
                "|             xxxxx  |" + System.lineSeparator() +
                "|xxxxxx       x   x  |" + System.lineSeparator() +
                "|     x       xxxxx  |" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "----------------------";
    }

    private String rectangleCanvas2() {
        return "----------------------" + System.lineSeparator() +
                "|             xxxxx  |" + System.lineSeparator() +
                "|xxxxxx       x   x  |" + System.lineSeparator() +
                "|      x      xxxxx  |" + System.lineSeparator() +
                "|      x             |" + System.lineSeparator() +
                "----------------------";
    }

    private String bucketFillCanvas() {
        return "----------------------" + System.lineSeparator() +
                "|oooooooooooooxxxxxoo|" + System.lineSeparator() +
                "|xxxxxxooooooox   xoo|" + System.lineSeparator() +
                "|     xoooooooxxxxxoo|" + System.lineSeparator() +
                "|     xoooooooooooooo|" + System.lineSeparator() +
                "----------------------";
    }

    private String bucketFillCanvas2() {
        return "----------------------" + System.lineSeparator() +
                "|oooooooooooooxxxxxoo|" + System.lineSeparator() +
                "|xxxxxxooooooox   xoo|" + System.lineSeparator() +
                "|oooooxoooooooxxxxxoo|" + System.lineSeparator() +
                "|oooooxoooooooooooooo|" + System.lineSeparator() +
                "----------------------";
    }

    private String bucketFillCanvas3() {
        return "----------------------" + System.lineSeparator() +
                "|oooooooooooooxxxxxoo|" + System.lineSeparator() +
                "|xxxxxxoooooooxoooxoo|" + System.lineSeparator() +
                "|oooooxoooooooxxxxxoo|" + System.lineSeparator() +
                "|oooooxoooooooooooooo|" + System.lineSeparator() +
                "----------------------";
    }

    private String bucketFillCanvas4() {
        return "----------------------" + System.lineSeparator() +
                "|oooooooooooooxxxxxoo|" + System.lineSeparator() +
                "|xxxxxxooooooox   xoo|" + System.lineSeparator() +
                "|      xooooooxxxxxoo|" + System.lineSeparator() +
                "|      xooooooooooooo|" + System.lineSeparator() +
                "----------------------";
    }
    private String bucketFillCanvas5() {
        return "----------------------" + System.lineSeparator() +
                "|oooooooooooooxxxxxoo|" + System.lineSeparator() +
                "|xxxxxxooooooox   xoo|" + System.lineSeparator() +
                "|      xooooooxxxxxoo|" + System.lineSeparator() +
                "|      xooooooooooooo|" + System.lineSeparator() +
                "----------------------";
    }
}