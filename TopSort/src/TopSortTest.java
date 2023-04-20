import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TopSortTest {

    private Graph adjMatrixGraph;
    private Graph hashBasedGraph;

    private List<Graph> graphs;

    @Before
    public void setUp() {
        graphs = new ArrayList<>();

        adjMatrixGraph = new AdjMatrixGraph(5);
        hashBasedGraph = new HashBasedGraph(5);

        graphs.add(adjMatrixGraph);
        graphs.add(hashBasedGraph);
    }

    @Test
    public void testTopSortForAcyclicGraph() {
        for (Graph g : graphs) {
            g.addEdge(3, 1);
            g.addEdge(3, 2);
            g.addEdge(1, 2);
            g.addEdge(1, 0);
            g.addEdge(4, 1);
            g.addEdge(4, 0);
            g.addEdge(2, 0);

            List<Integer> topSort = TopSort.obtainTopSort(g);
            System.out.println(topSort);

            // check top sort

            assertEquals(5, topSort.size());
            assertTrue(topSort.indexOf(3) < topSort.indexOf(1));
            assertTrue(topSort.indexOf(3) < topSort.indexOf(2));
            assertTrue(topSort.indexOf(1) < topSort.indexOf(2));
            assertTrue(topSort.indexOf(1) < topSort.indexOf(0));
            assertTrue(topSort.indexOf(4) < topSort.indexOf(1));
            assertTrue(topSort.indexOf(4) < topSort.indexOf(0));
            assertTrue(topSort.indexOf(2) < topSort.indexOf(0));
        }
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

    @Test
//    @Ignore
    public void performanceTest() {
        Random random = new Random();

        final int N = 200;
        final int M = 300000;

        List<Graph> perfTestGraphs = new ArrayList<>();
        Graph adjGraph = new AdjMatrixGraph(N);
        Graph hashGraph = new HashBasedGraph(N);
        perfTestGraphs.add(adjGraph);
        perfTestGraphs.add(hashGraph);

        for (Graph g : perfTestGraphs) {
            for (int i = 0; i < M; i++) {
                int v1 = random.nextInt(N);
                int v2 = random.nextInt(N);
                if (v1 == v2) {
                    continue;
                }
                g.addEdge(Math.max(v1, v2),
                          Math.min(v1, v2));  // ensuring acyclicity
            }

            long start = System.currentTimeMillis();
            List<Integer> topSort = TopSort.obtainTopSort(g);
            long duration = System.currentTimeMillis() - start;

            System.out.printf("\n\n[%s]: %s in %.6f seconds",
                    g.getClass().getName(),
                    (topSort == null ?
                            "Failed to find a topsort" :
                            "Found a topsort"),
                    duration / 1000f);
            System.out.println(topSort);
        }
    }
}