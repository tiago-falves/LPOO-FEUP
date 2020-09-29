package g24.view.lanterna.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.model.menu.ButtonModel;
import g24.model.utils.Position;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

public class LanternaButtonViewTest {
    private TextGraphics graphicsMock;
    private Position upperLeftMock, bottomRightMock;
    private LanternaButtonView lanternaButtonView;
    private final int counters[] = new int[3];

    @Before
    public void setup(){

        graphicsMock = mock(TextGraphics.class);
        TerminalScreen screenMock = mock(TerminalScreen.class);
        when(screenMock.newTextGraphics()).thenReturn(graphicsMock);

        upperLeftMock = mock(Position.class);
        when(upperLeftMock.getX()).thenReturn(1);
        when(upperLeftMock.getY()).thenReturn(1);

        bottomRightMock = mock(Position.class);
        when(bottomRightMock.getX()).thenReturn(5);
        when(bottomRightMock.getY()).thenReturn(5);

        lanternaButtonView = new LanternaButtonView(screenMock);



    }

    @Test
    public void drawTest(){
        ButtonModel buttonMock = mock(ButtonModel.class);
        when(buttonMock.getUpperLeft()).thenReturn(upperLeftMock);
        when(buttonMock.getLowerRight()).thenReturn(bottomRightMock);
        when(buttonMock.isSelected()).thenReturn(true);
        when(buttonMock.getBackgroundColor()).thenReturn("#000000");
        when(buttonMock.getTextColor()).thenReturn("#000000");
        when(buttonMock.getText()).thenReturn("zas");

        lanternaButtonView.draw(buttonMock);

        verify(graphicsMock,times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        verify(graphicsMock,times(1)).setForegroundColor(any(TextColor.class));
        //verify(graphicsMock, times(16)).putString(any(TerminalPosition.class), any(String.class));
        verify(buttonMock, times(1)).isSelected();
        verify(graphicsMock, times(1)).putString(1, 3, "zas");

    }
    @Test
    public void drawTest2(){
        ButtonModel buttonMock = mock(ButtonModel.class);
        when(buttonMock.getUpperLeft()).thenReturn(upperLeftMock);
        when(buttonMock.getLowerRight()).thenReturn(bottomRightMock);
        when(buttonMock.isSelected()).thenReturn(false);
        when(buttonMock.getBackgroundColor()).thenReturn("#000000");
        when(buttonMock.getTextColor()).thenReturn("#000000");
        when(buttonMock.getText()).thenReturn("zas");

        lanternaButtonView.draw(buttonMock);

        verify(graphicsMock, times(1)).setBackgroundColor(any(TextColor.class));
        verify(graphicsMock, times(1)).setForegroundColor(any(TextColor.class));
        //verify(graphicsMock, times(1)).putString(any(Integer.class), any(Integer.class), any(String.class));
        //verify(graphicsMock, times(16)).putString(any(TerminalPosition.class), any(String.class));
        verify(buttonMock, times(1)).isSelected();

    }
}
