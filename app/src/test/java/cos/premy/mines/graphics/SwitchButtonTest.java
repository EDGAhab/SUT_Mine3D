//package cos.premy.mines.graphics;
//
//import static org.mockito.Mockito.*;
//
//import android.graphics.Canvas;
//import android.graphics.Paint;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.*;
//
//import cos.premy.mines.GameStatus;
//import cos.premy.mines.graphics.animations.Line;
//import cos.premy.mines.graphics.animations.LineAnimation;
//
//public class SwitchButtonTest {
//    @Mock
//    private GameStatus mockGameStatus;
//    @Mock
//    private Canvas mockCanvas;
//    @Mock
//    private LineAnimation mockLineAnimation1;
//    @Mock
//    private LineAnimation mockLineAnimation2;
//    @Mock
//    private Line mockLine;
//    @Mock
//    private Paint mockPaint;
//
//    private SwitchButton switchButton;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        switchButton = new SwitchButton(mockGameStatus);
//    }
//
//    @Test
//    public void testConstructor(){
//        verify(mockPaint, times(1)).setAntiAlias(anyBoolean());
//
//    }
//
////    @Test
////    public void testDraw() {
////
////        InOrder inOrder = Mockito.inOrder(mockLineAnimation1, mockLineAnimation2, mockCanvas);
////        switchButton.draw(mockCanvas);
////
////        inOrder.verify(mockCanvas, times(4)).drawLine(anyFloat(), anyFloat(),
////                anyFloat(), anyFloat(), any());
////        inOrder.verify(mockLineAnimation1, times(1)).getLine();
////        inOrder.verify(mockLineAnimation2, times(1)).getLine();
////        verify(mockCanvas).drawLine(eq(0f), eq(0f), eq(10f), eq(10f), any());
////        verify(mockCanvas).drawLine(eq(10f), eq(0f), eq(0f), eq(10f), any());
////    }
//
////    @Test
////    public void testSetPositionWidthGreaterThanHeight() {
////        switchButton.setPosition(0, 100, 0, 10);
////
////        verify(mockLineAnimation1).reverseAnimation();
////        verify(mockLineAnimation1).finishAnimation();
////        verify(mockLineAnimation2).reverseAnimation();
////        verify(mockLineAnimation2).finishAnimation();
////    }
//
////    @Test
////    public void testSendVerifiedTap() {
////        switchButton.sendVerifiedTap(50, 50);
////
////        verify(mockLineAnimation1).reverseAnimation();
////        verify(mockLineAnimation2).reverseAnimation();
////        verify(mockGameStatus).switchLevel();
////    }
//}
//
