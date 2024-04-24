package cos.premy.mines.generator;

import static org.junit.Assert.*;
import org.junit.Test;

import cos.premy.mines.MyHappyException;
import cos.premy.mines.data.MinesContainer;

public class RandomMinesGeneratorTest {
    @Test
    public void testNoMines() {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        MinesContainer minesContainer = generator.getNewProblem(10, 10, 0);

        int actualMinesCount = countMines(minesContainer);
        assertEquals(0, actualMinesCount);
    }

    @Test
    public void testFullMines() {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        MinesContainer minesContainer = generator.getNewProblem(2, 2, 8);

        int actualMinesCount = countMines(minesContainer);
        assertEquals(8, actualMinesCount);
    }

    @Test
    public void testRandomMinesPlacement() {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        MinesContainer minesContainer = generator.getNewProblem(10, 10, 20);

        int actualMinesCount = countMines(minesContainer);
        assertEquals(20, actualMinesCount);
    }

    @Test(expected = MyHappyException.class)
    public void testRandomMinesException() throws MyHappyException {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        MinesContainer minesContainer1 = generator.getNewProblem(10, 10, 20);
        minesContainer1.getMine(0,0,0).setIsReal(false);
        int actualMinesCount = countMines(minesContainer1);
        assertEquals(20, actualMinesCount);

    }




    private int countMines(MinesContainer minesContainer) {
        int count = 0;
        for (int z = 0; z < 2; z++) {
            for (int y = 0; y < minesContainer.getHeight(); y++) {
                for (int x = 0; x < minesContainer.getWidth(); x++) {
                    if (minesContainer.getMine(z, y, x).getIsReal()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

