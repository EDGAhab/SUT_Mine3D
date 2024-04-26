package cos.premy.mines.graphics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.graphics.Canvas;
import android.graphics.Paint;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Date;

import cos.premy.mines.GameStatus;
import cos.premy.mines.data.MinesContainer;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Config.OLDEST_SDK})
public class StatusLabelTest {
    @Mock private MinesContainer mockMinesContainer;
    @Mock private GameStatus mockGameStatus;
    @Mock private Canvas mockCanvas;
    @Mock private Paint mockPaint;

    private StatusLabel statusLabel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mockGameStatus.getStartTime()).thenReturn(new Date());
        when(mockMinesContainer.getMinesBlocked()).thenReturn(5);
        when(mockMinesContainer.getMinesNumber()).thenReturn(20);

        statusLabel = new StatusLabel(mockMinesContainer, mockGameStatus);
        statusLabel.setPosition(0,10,0,10);

    }

    @Test
    public void testDraw() {
        statusLabel.draw(mockCanvas);
        verify(mockGameStatus, times(1)).getStartTime();
        verify(mockMinesContainer, times(1)).getMinesBlocked();
        verify(mockMinesContainer,times(1)).getMinesNumber();
        verify(mockCanvas, times(2)).drawText(anyString(), anyFloat(), anyFloat(), any(Paint.class));
    }


}
