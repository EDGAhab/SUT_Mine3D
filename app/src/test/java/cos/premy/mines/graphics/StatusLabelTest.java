//package cos.premy.mines.graphics;
//
//import android.graphics.Canvas;
//import android.graphics.Paint;
//
//
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import static org.mockito.Mockito.*;
//
//import cos.premy.mines.GameStatus;
//import cos.premy.mines.data.MinesContainer;
//
//@RunWith(RobolectricTestRunner.class)
////@Config(sdk = 34, constants = BuildConfig.class)
//public class StatusLabelTest {
//
//    @Mock
//    private GameStatus mockGameStatus;
//    @Mock
//    private MinesContainer mockMinesContainer;
//    @Mock
//    private Canvas mockCanvas;
//
//    private StatusLabel statusLabel;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        when(mockMinesContainer.getMinesBlocked()).thenReturn(5);
//        when(mockMinesContainer.getMinesNumber()).thenReturn(10);
//
//        statusLabel = new StatusLabel(mockMinesContainer, mockGameStatus);
//        statusLabel.setPosition(10, 100, 10, 30);
//    }
//
//    @Test
//    public void testDraw() {
//
//        statusLabel.draw(mockCanvas);
//        verify(mockGameStatus, times(1)).getStartTime().getTime();
////        verify(mockCanvas).drawText(startsWith("Time: "), eq(10f), eq(10f), any(Paint.class));
////        verify(mockCanvas).drawText(startsWith("Mines blocked: 5/10"), eq(10f), eq(40f), any(Paint.class)); // 10f + 30f 字体大小
//
//        verify(mockCanvas, times(2)).drawText(anyString(), anyFloat(), anyFloat(), any(Paint.class));
//    }
//}
