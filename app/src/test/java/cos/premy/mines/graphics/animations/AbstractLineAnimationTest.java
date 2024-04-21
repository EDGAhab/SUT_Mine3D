package cos.premy.mines.graphics.animations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AbstractLineAnimationTest {

    @Mock
    private Line mockStartLine;

    @Mock
    private Line mockEndLine;

    private AbstractLineAnimation animation;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        animation = new AbstractLineAnimation(mockStartLine, mockEndLine, 10) {
            @Override
            public Line getLine() {
                return null;
            }
        };


    }

    @Test
    public void testConstructorAndGetters(){
        assertEquals(mockStartLine, animation.getStartLine());
        assertEquals(mockEndLine, animation.getEndLine());
        assertEquals(10L, animation.getAnimationDuration());
    }

    @Test
    public void testSetStartLine(){
        animation.setStartLine(mockStartLine);
        assertEquals(mockStartLine, animation.getStartLine());
    }

    @Test
    public void testSetEndLine(){
        animation.setEndLine(mockEndLine);
        assertEquals(mockEndLine, animation.getEndLine());
    }

    @Test
    public void testSetAnimationDuration(){
        animation.setAnimationDuration(10L);
        assertEquals(10L, animation.getAnimationDuration());
    }


    @Test
    public void testAnimationReverse() {
        animation.finishAnimation();
        animation.reverseAnimation();
        assertEquals(mockEndLine, animation.getStartLine());
        assertEquals(mockStartLine, animation.getEndLine());
    }

    @Test
    public void testAnimationReverseDeltaTlessThanDuration() {
        animation.setAnimationDuration(999L);
        animation.startAnimation();
        animation.reverseAnimation();
        assertEquals(mockEndLine, animation.getStartLine());
        assertEquals(mockStartLine, animation.getEndLine());
    }



    @Test
    public void testMoveToNewLine() {
        animation.moveTo(mockStartLine, 2000);
        assertEquals(mockStartLine, animation.getEndLine());
        assertEquals(2000, animation.getAnimationDuration());
    }
}

