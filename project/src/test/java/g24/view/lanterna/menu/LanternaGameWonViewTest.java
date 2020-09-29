package g24.view.lanterna.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.model.menu.GameWonModel;
import g24.view.lanterna.LanternaAction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

public class LanternaGameWonViewTest {
    LanternaAction actionMock;
    LanternaGameWonView gameWonView;
    TerminalScreen screenMock;

    @Before
    public void setup() {
        this.screenMock = mock(TerminalScreen.class);
        this.actionMock = mock(LanternaAction.class);
        this.gameWonView = new LanternaGameWonView(screenMock, actionMock);
    }

    @Test
    public void testDraw() {
        TextGraphics graphicsMock = mock(TextGraphics.class);
        when(screenMock.newTextGraphics()).thenReturn(graphicsMock);

        GameWonModel gameWonModelMock = mock(GameWonModel.class);
        when(gameWonModelMock.getReason()).thenReturn("reason");

        gameWonView.draw(gameWonModelMock);
        verify(graphicsMock, times(3)).putString(anyInt(), anyInt(), anyString());
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

        gameWonView.exit();
        assertEquals(1, counter[0]);

        try {
            doThrow(new IOException()).when(screenMock).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameWonView.exit();
        assertEquals(1, counter[0]);
    }
}
