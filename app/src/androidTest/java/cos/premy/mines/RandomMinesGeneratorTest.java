package cos.premy.mines;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import cos.premy.mines.data.MinesContainer;
import cos.premy.mines.generator.RandomMinesGenerator;

public class RandomMinesGeneratorTest {

    @Test
    public void testGenerateCorrectNumberOfMines() {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        MinesContainer problem = generator.getNewProblem(10, 10, 20);
        int mineCount = 0;
        for (int z = 0; z < 2; z++) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (problem.isRealMine(z, y, x)) {
                        mineCount++;
                    }
                }
            }
        }
        assertEquals(20, mineCount);
    }

    @Test(expected = MyHappyException.class)
    public void testInvalidInput() {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        generator.getNewProblem(-1, 10, 10);
    }

    @Test
    public void testRandomness() {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        Set<MinesContainer> configurations = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            configurations.add(generator.getNewProblem(5, 5, 5));
        }
        assertTrue(configurations.size() > 1);
    }

    @Test(timeout = 1000)
    public void testPerformance() {
        RandomMinesGenerator generator = new RandomMinesGenerator();
        generator.getNewProblem(1000, 1000, 50000);
    }
}

