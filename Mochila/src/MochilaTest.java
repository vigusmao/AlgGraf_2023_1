import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class MochilaTest {

    private Random random = new Random();
    private List<ItemMochila> itens = new ArrayList<>();

    @Test
    public void testSolve() {
        itens.add(new ItemMochila(10, 2));
        itens.add(new ItemMochila(30, 3));
        itens.add(new ItemMochila(5, 1));
        itens.add(new ItemMochila(35, 3));
        itens.add(new ItemMochila(36, 4));
        itens.add(new ItemMochila(25, 2));

        assertEquals(40, Mochila.solve(itens, 4));
    }

    @Test
//    @Ignore
    public void testPerformance() {
        for (int N = 10; N <= 400; N++) {
            itens.clear();

            for (int i = 0; i < N; i++) {
                int valor = random.nextInt(10) + 1;
                int peso = random.nextInt(10) + 1;
                itens.add(new ItemMochila(valor, peso));
            }

            int capacMochila = 4 * N;

            long start = System.currentTimeMillis();
            int result = Mochila.solve(
                    itens,
                    capacMochila);
            long elapsed = System.currentTimeMillis() - start;
            System.out.printf(
                    "\nN = %d, duração: %.3f segundos",
                    N, elapsed / 1000f);
        }

    }
}




