package org.boliveira.drawing.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MainViewTest {

    private MainView view = Mockito.spy(MainView.getDefaultInstance());

    @BeforeEach
    public void setup() {
        when(view.handleInput(anyString())).thenCallRealMethod();
        doNothing().when(view).quit();
    }

    @Test
    void whenInputIs1_ExpectMappingIsSuccessful() {
        assertEquals(Optional.empty(), view.handleInput("1"));
    }

    @Test
    void whenInputIs2_ExpectMappingIsSuccessful() {
        assertEquals(Optional.empty(), view.handleInput("2"));
    }

    @Test
    void whenInputIs3_ExpectQuitIsCalled() {
        view.handleInput("3");
        verify(view, times(1)).quit();
    }

    @Test
    void whenInputIs4_ExpectInvalidOption() {
        assertEquals(Optional.of("Invalid Option"), view.handleInput("4"));
    }
}