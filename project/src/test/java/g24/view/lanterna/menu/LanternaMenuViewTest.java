package g24.view.lanterna.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.model.menu.ButtonModel;
import g24.model.menu.MenuModel;
import g24.view.lanterna.LanternaAction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

public class LanternaMenuViewTest {
    LanternaAction actionMock;
    LanternaButtonView buttonViewMock;
    LanternaMenuView menuView;
    TerminalScreen screenMock;

    @Before
    public void setup() {
        this.buttonViewMock = mock(LanternaButtonView.class);
        this.screenMock = mock(TerminalScreen.class);
        this.actionMock = mock(LanternaAction.class);
        this.menuView = new LanternaMenuView(screenMock, actionMock, buttonViewMock);
    }

    @Test
    public void testDraw() {
        TextGraphics graphicsMock = mock(TextGraphics.class);
        when(screenMock.newTextGraphics()).thenReturn(graphicsMock);

        ButtonModel buttonModelMock = mock(ButtonModel.class);
        List<ButtonModel> buttonModelList = new ArrayList();
        buttonModelList.add(buttonModelMock);
        buttonModelList.add(buttonModelMock);

        MenuModel menuModelMock = mock(MenuModel.class);
        when(menuModelMock.getNumberOfButtons()).thenReturn(2);
        when(menuModelMock.getButtonModels()).thenReturn(buttonModelList);

        menuView.draw(menuModelMock);
        verify(graphicsMock, times(1)).putString(anyInt(), anyInt(), anyString());
        verify(buttonViewMock, times(2)).draw(any(ButtonModel.class));
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

        menuView.exit();
        assertEquals(1, counter[0]);

        try {
            doThrow(new IOException()).when(screenMock).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuView.exit();
        assertEquals(1, counter[0]);
    }
}
