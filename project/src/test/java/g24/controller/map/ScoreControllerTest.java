package g24.controller.map;

import g24.controller.observer.ScoreObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ScoreControllerTest {
    ScoreController scoreController;
    ScoreObserver scoreObserverMock;

    @Before
    public void setup() {
        scoreController = new ScoreController();
        scoreObserverMock = Mockito.mock(ScoreObserver.class);
    }

    @Test
    public void addObserver() {
        final int[] functionCallCounter = {0};
        Answer<Boolean> answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(scoreObserverMock).updateScore(any(int.class));

        scoreController.addObserver(scoreObserverMock);
        scoreController.notifyObservers();
        assertEquals(1, functionCallCounter[0]);
    }

    @Test
    public void removeObserver() {
        final int[] functionCallCounter = {0};
        Answer<Boolean>  answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(scoreObserverMock).updateScore(any(int.class));

        scoreController.addObserver(scoreObserverMock);
        scoreController.removeObserver(scoreObserverMock);
        scoreController.notifyObservers();
        assertEquals(0, functionCallCounter[0]);
    }

    @Test
    public void updateScore() {
        final int[] functionCallCounter = {0};
        Answer<Boolean>  answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(scoreObserverMock).updateScore(any(int.class));

        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(int.class);


        scoreController.addObserver(scoreObserverMock);
        scoreController.increaseScore(500);
        assertEquals(1, functionCallCounter[0]);

        scoreController.increaseScore(500);
        assertEquals(2, functionCallCounter[0]);

        scoreController.decreaseScore(1000);
        assertEquals(3, functionCallCounter[0]);

        verify(scoreObserverMock, times(3)).updateScore(argument.capture());

        List<Integer> arguments = argument.getAllValues();
        assertEquals(Integer.valueOf(500), arguments.get(0));
        assertEquals(Integer.valueOf(999), arguments.get(1));
        assertEquals(Integer.valueOf(0), arguments.get(2));

    }

}
