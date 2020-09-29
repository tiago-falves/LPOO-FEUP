package g24.controller.map;

import g24.controller.observer.TimeObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TimeControllerTest {
    TimeController timeController;
    TimeObserver timeObserverMock;

    @Before
    public void setup() {
        timeController = new TimeController();
        timeObserverMock = Mockito.mock(TimeObserver.class);
    }

    @Test
    public void addObserver() {
        final int[] functionCallCounter = {0};
        Answer<Boolean> answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(timeObserverMock).updateTime(any(int.class));

        timeController.addObserver(timeObserverMock);
        timeController.notifyObservers();
        assertEquals(1, functionCallCounter[0]);
    }

    @Test
    public void removeObserver() {
        final int[] functionCallCounter = {0};
        Answer<Boolean>  answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(timeObserverMock).updateTime(any(int.class));

        timeController.addObserver(timeObserverMock);
        timeController.removeObserver(timeObserverMock);
        timeController.notifyObservers();
        assertEquals(0, functionCallCounter[0]);
    }

    @Test
    public void updateTime() {
        final int[] functionCallCounter = {0};
        Answer<Boolean>  answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(timeObserverMock).updateTime(any(int.class));

        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);

        timeController.addObserver(timeObserverMock);
        timeController.updateTime();

        int start = (int) (System.currentTimeMillis() / 1000);
        int end = (int) (System.currentTimeMillis() / 1000);
        while(start == end) {
            end = (int) (System.currentTimeMillis() / 1000);
        }

        timeController.updateTime();

        start = (int) (System.currentTimeMillis() / 1000);
        end = (int) (System.currentTimeMillis() / 1000);
        while(start == end) {
            end = (int) (System.currentTimeMillis() / 1000);
        }

        timeController.updateTime();

        assertTrue(functionCallCounter[0] == 2 || functionCallCounter[0] == 3);

        if(functionCallCounter[0] == 2) {
            verify(timeObserverMock, times(2)).updateTime(argument.capture());
            int firstTime = argument.getAllValues().get(0);
            int secondTime = argument.getAllValues().get(1);
            assertTrue(firstTime != secondTime);
        } else {
            verify(timeObserverMock, times(3)).updateTime(argument.capture());
            int firstTime = argument.getAllValues().get(0);
            int secondTime = argument.getAllValues().get(1);
            int thirdTime = argument.getAllValues().get(2);
            assertTrue(firstTime != secondTime && secondTime != thirdTime);
        }
    }
}
