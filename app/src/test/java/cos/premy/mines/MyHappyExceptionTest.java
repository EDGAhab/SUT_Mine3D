package cos.premy.mines;

import static org.junit.Assert.*;
import static cos.premy.mines.MyHappyException.EX_TYPES.*;

import org.junit.Test;

public class MyHappyExceptionTest {

    @Test
    public void testGetMessageMINE_IS_FACTORIZED() {
        assertEquals("Mine has been already factorized and is read only.",
                MyHappyException.getMessage(MINE_IS_FACTORIZED));
    }

    @Test
    public void testGetMessageANIMATION_HASNT_BEEN_CONFIGURED() {

        assertEquals("Animation hasn't been configured yet.",
                MyHappyException.getMessage(ANIMATION_HASNT_BEEN_CONFIGURED));
    }


    @Test
    public void testConstructor() {
        MyHappyException myHappyException = new MyHappyException(MINE_IS_FACTORIZED);
        assertEquals("Mine has been already factorized and is read only.",
                myHappyException.getMessage());
    }


}

