//package cos.premy.mines.graphics;
//
//import static org.mockito.Mockito.*;
//
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.os.Build;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import cos.premy.mines.GameStatus;
//import cos.premy.mines.data.Mine;
//import cos.premy.mines.data.MinesContainer;
//
//@RunWith(RobolectricTestRunner.class)
//@Config(sdk = 19, manifest = "src/main/AndroidManifest.xml")
//public class GridTest {
//
//    @Mock
//    private MinesContainer mockContainer;
//    @Mock
//    private GameStatus mockGameStatus;
//    @Mock
//    private Canvas mockCanvas;
//    @Mock
//    private MineField mockMineField;
//
//    private Grid grid;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        when(mockContainer.getHeight()).thenReturn(5);
//        when(mockContainer.getWidth()).thenReturn(5);
//        when(mockContainer.getMine(anyInt(), anyInt(), anyInt())).thenReturn(new Mine());
//        when(mockGameStatus.getLevel()).thenReturn(0);
//        grid = new Grid(mockContainer, mockGameStatus);
//        grid.setPosition(0, 500, 0, 500); // 假设网格大小为500x500
//    }
//
//    @Test
//    public void testDraw() {
//        grid.draw(mockCanvas);
//
//        // 验证是否画出了正确数量的线
//        verify(mockCanvas, times(6)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));
//        // 验证每个 mineField 被正确绘制
//        verify(mockMineField, times(25)).draw(any(Canvas.class));
//    }
//
//    @Test
//    public void testSetPositionAndSizeUpdatesMineFields() {
//        grid.setPosition(0, 1000, 0, 1000);
//        // 假设一个 MineField 的 setPosition 方法会被调用
//        verify(mockMineField, times(25)).setPosition(anyInt(), anyInt(), anyInt(), anyInt());
//    }
//
//    @Test
//    public void testSendVerifiedTap() {
//        grid.sendVerifiedTap(250, 250); // 假设点击在中心点
//        verify(mockMineField, atLeastOnce()).sendTap(250, 250);
//    }
//
//    @Test
//    public void testSendVerifiedLongTap() {
//        grid.sendVerifiedLongTap(250, 250); // 假设长按在中心点
//        verify(mockMineField, atLeastOnce()).sendLongTap(250, 250);
//    }
//
//    @Test
//    public void testSendVerifiedDoubleTap() {
//        grid.sendVerifiedDoubleTap(250, 250); // 假设双击在中心点
//        verify(mockMineField, atLeastOnce()).sendDoubleTap(250, 250);
//    }
//}
//
