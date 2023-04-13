import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TopSortTest {

    @Test
    public void testTopSortForAcyclicGraph() {
        AdjMatrixGraph g = new AdjMatrixGraph(5);
        g.addEdge(3, 1);
        g.addEdge(3, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 0);
        g.addEdge(4, 1);
        g.addEdge(4, 0);
        g.addEdge(2, 0);

        List<Integer> topSort = TopSort.obtainTopSort(g);
        System.out.println(topSort);

        assertEquals(5, topSort.size());

        assertTrue(topSort.indexOf(3) < topSort.indexOf(1));
        assertTrue(topSort.indexOf(3) < topSort.indexOf(2));
        assertTrue(topSort.indexOf(1) < topSort.indexOf(2));
        assertTrue(topSort.indexOf(1) < topSort.indexOf(0));
        assertTrue(topSort.indexOf(4) < topSort.indexOf(1));
        assertTrue(topSort.indexOf(4) < topSort.indexOf(0));
        assertTrue(topSort.indexOf(2) < topSort.indexOf(0));
    }

    @Test
    public void testTopSortForCyclicGraph() {
        AdjMatrixGraph g = new AdjMatrixGraph(5);
        g.addEdge(3, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        List<Integer> topSort = TopSort.obtainTopSort(g);
        System.out.println(topSort);

        assertNull("The expected top sort is null for cyclic graphs",
                topSort);
    }
}