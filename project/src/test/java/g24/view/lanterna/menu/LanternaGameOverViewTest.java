package g24.view.lanterna.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.controller.commands.user.COMMAND;
import g24.model.menu.GameOverModel;
import g24.view.lanterna.LanternaAction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LanternaGameOverViewTest {
    LanternaAction actionMock;
    LanternaGameOverView gameOverView;
    TerminalScreen screenMock;

    @Before
    public void setup() {
        this.screenMock = mock(TerminalScreen.class);
        this.actionMock = mock(LanternaAction.class);
        this.gameOverView = new LanternaGameOverView(screenMock, actionMock);
    }

    @Test
    public void testDraw() {
        TextGraphics graphicsMock = mock(TextGraphics.class);
        when(screenMock.newTextGraphics()).thenReturn(graphicsMock);

        GameOverModel gameOverModelMock = mock(GameOverModel.class);
        when(gameOverModelMock.getReason()).thenReturn("reason");

        gameOverView.draw(gameOverModelMock);
        verify(graphicsMock, times(3)).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    public void getCommand() {
        when(actionMock.keyboardParser()).thenReturn(COMMAND.NOTHING);

        assertTrue(gameOverView.getCommand() instanceof COMMAND);
    }

    @Test
    public void testExit() {
        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.EOF);

        final int[] counter = new int[1];
        Answer<Boolean> answer = invocation -> {
            counter[0]++;
            return true;
        };
        try {
            when(screenMock.readInput()).thenReturn(keyStrokeMock);
            doAnswer(answer).when(screenMock).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameOverView.exit();
        assertEquals(1, counter[0]);

        try {
            doThrow(new IOException()).when(screenMock).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOverView.exit();
        assertEquals(1, counter[0]);
    }
}
