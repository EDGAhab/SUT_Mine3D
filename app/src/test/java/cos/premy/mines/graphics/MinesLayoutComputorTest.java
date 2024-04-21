package cos.premy.mines.graphics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MinesLayoutComputorTest {

    private MinesLayoutComputor computor;

    @Before
    public void setUp() {
        computor = new MinesLayoutComputor(800, 1200);
    }

    @Test
    public void testButtonWidthWhenWidthGreaterThanHeight() {
        assertEquals(1160/7, computor.getButtonWidth());
    }

    @Test
    public void testButtonWidthWhenWidthLessThanHeight() {
        computor = new MinesLayoutComputor(1200, 800);
        assertEquals(760, computor.getButtonWidth());
    }


    @Test
    public void testButtonHeightWhenWidthGreaterThanHeight() {
        assertEquals(740, computor.getButtonHeight());
    }

    @Test
    public void testButtonHeightWhenWidthLessThanHeight() {
        computor = new MinesLayoutComputor(1200, 800);
        assertEquals(1140/7, computor.getButtonHeight());
    }

    @Test
    public void testGridWidth() {
        assertEquals(740, computor.getGridWidth());
    }

    @Test
    public void testGridHeight() {
        assertEquals(740, computor.getGridHeight());
    }

    @Test
    public void testButtonXWhenWidthGreaterThanHeight() {
        assertTrue(computor.getButtonX() > 0);
    }

    @Test
    public void testButtonXWhenWidthLessThanHeight() {
        computor = new MinesLayoutComputor(1200, 800);
        assertEquals(20, computor.getButtonX());
    }

    @Test
    public void testButtonYWhenWidthGreaterThanHeight() {
        assertEquals(40, computor.getButtonY());
    }

    @Test
    public void testButtonYWhenWidthLessThanHeight() {
        computor = new MinesLayoutComputor(1200, 800);
        assertTrue(computor.getButtonY() > 0);
    }


    @Test
    public void testGridXWhenWidthGreaterThanHeight() {
        assertEquals(computor.getButtonX()+ computor.getButtonWidth()+30
                , computor.getGridX());
    }

    @Test
    public void testGridXWhenWidthLessThanHeight() {
        computor = new MinesLayoutComputor(1200, 800);
        assertEquals(computor.getButtonX(), computor.getGridX());
    }

    @Test
    public void testGridYWhenWidthGreaterThanHeight() {
        assertEquals(40, computor.getGridY());
    }

    @Test
    public void testGridYWhenWidthLessThanHeight() {
        computor = new MinesLayoutComputor(1200, 800);
        assertEquals(computor.getButtonY() + computor.getButtonHeight() + 30,
                computor.getGridY());
    }

    @Test
    public void testStatusLabelX() {
        assertEquals(20, computor.getStatusLabelX());
    }

    @Test
    public void testStatusLabelY() {
        assertEquals(40, computor.getStatusLabelY());
    }

    @Test
    public void testStatusLabelHeight() {

        assertEquals(21, computor.getStatusHeight());
    }
}

