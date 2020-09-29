package g24.view.lanterna;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.controller.commands.user.COMMAND;
import g24.model.Model;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LanternaStateViewTest {
    TerminalScreen screenMock;
    LanternaAction actionMock;
    private LanternaStateView stateView;
    private final int[] counter = new int[1];

    @Before
    public void setUp() {

        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.EOF);

        Answer<Boolean> answer = invocation -> {
            counter[0]++;
            return true;
        };

        screenMock = mock(TerminalScreen.class);
        actionMock = mock(LanternaAction.class);

        try {
            when(screenMock.readInput()).thenReturn(keyStrokeMock);
            doAnswer(answer).when(screenMock).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stateView = new LanternaStateView(screenMock, actionMock) {
            @Override
            public void draw(Model model) {
                return;
            }
        };
    }

    @Test
    public void getCommand() {
        when(actionMock.keyboardParser()).thenReturn(COMMAND.NOTHING);

        assertTrue(stateView.getCommand() instanceof COMMAND);
    }


    @Test
    public void exitTest() {
        stateView.exit();
        assertEquals(1, counter[0]);

        try {
            doThrow(new IOException()).when(screenMock).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stateView.exit();
        assertEquals(1, counter[0]);
    }
}
