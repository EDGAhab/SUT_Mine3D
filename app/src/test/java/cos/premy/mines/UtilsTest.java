package cos.premy.mines;

import org.junit.Test;
import static org.junit.Assert.*;
import cos.premy.mines.Utils;



public class UtilsTest {
    @Test
    public void dToITest() {
        assertEquals(10, Utils.dToI(10.0));
        assertEquals(-10, Utils.dToI(-10.0));
    }

    @Test
    public void fToITest() {
        assertEquals(10, Utils.fToI(10.0f));
    }
}
