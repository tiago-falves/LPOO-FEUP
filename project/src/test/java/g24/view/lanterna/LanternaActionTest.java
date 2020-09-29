package g24.view.lanterna;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import g24.controller.commands.user.COMMAND;
import g24.model.element.Isaac;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LanternaActionTest {

    @Test
    public void parseNothing() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        when(screenMock.pollInput()).thenReturn(null);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.NOTHING, actionMock.keyboardParser());
    }

    @Test
    public void parseArrowUp() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.ArrowUp);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.UP, actionMock.keyboardParser());
    }


    @Test
    public void parseArrowDown() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.ArrowDown);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.DOWN, actionMock.keyboardParser());
    }

    @Test
    public void parseArrowLeft() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.ArrowLeft);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.LEFT, actionMock.keyboardParser());
    }

    @Test
    public void parseArrowRight() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.ArrowRight);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.RIGHT, actionMock.keyboardParser());
    }

    @Test
    public void parseEnter() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.Enter);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);
        assertEquals(COMMAND.SELECT, actionMock.keyboardParser());
    }

    @Test
    public void parseException() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.ArrowRight);
        doThrow(new IOException()).when(screenMock).pollInput();

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.NOTHING, actionMock.keyboardParser());
    }

    @Test
    public void parseSpaceCharacter() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.Character);
        when(keyStrokeMock.getCharacter()).thenReturn(' ');
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.SHOOT, actionMock.keyboardParser());
    }

    @Test
    public void parseEOF() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.EOF);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.QUIT, actionMock.keyboardParser());
    }

    @Test
    public void parseQUIT() throws IOException {
        Isaac isaacMock = mock(Isaac.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.Escape);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        LanternaAction actionMock = new LanternaAction(screenMock);

        assertEquals(COMMAND.QUIT, actionMock.keyboardParser());
    }
}
