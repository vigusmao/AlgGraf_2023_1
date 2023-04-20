import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

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

        populateGraphs();
    }

    private void populateGraphs() {
        for (Graph g : graphs) {
            g.addEdge(3, 1);
            g.addEdge(3, 2);
            g.addEdge(1, 2);
            g.addEdge(1, 0);
            g.addEdge(4, 1);
            g.addEdge(4, 0);
            g.addEdge(2, 0);
        }
    }

    @Test
    public void testInDegree() {
        for (Graph g : graphs) {
            assertEquals(0, g.getVertexInDegree(3));
            assertEquals(3, g.getVertexInDegree(0));
            assertEquals(2, g.getVertexInDegree(1));
        }
    }

    @Test
    public void testOutDegree() {
        for (Graph g : graphs) {
            assertEquals(2, g.getVertexOutDegree(3));
            assertEquals(0, g.getVertexOutDegree(0));
            assertEquals(2, g.getVertexOutDegree(1));
        }
    }

    @Test
    public void testVertexCount() {
        for (Graph g : graphs) {
            assertEquals(5, g.getVertexCount());
        }
    }

    @Test
    public void testHasEdge() {
        for (Graph g : graphs) {
            assertTrue(g.hasEdge(3, 2));
            assertTrue(g.hasEdge(1, 0));

            assertFalse(g.hasEdge(3, 0));
            assertFalse(g.hasEdge(999, -178));
        }
    }

    @Test
    public void testRemoveVertex() {
        for (Graph g : graphs) {
            assertTrue(g.hasVertex(2));
            g.removeVertex(2);
            assertFalse(g.hasVertex(2));

            assertEquals(4, g.getVertexCount());

            assertFalse(g.hasEdge(1, 2));

            assertEquals(1, g.getVertexOutDegree(3));
            assertEquals(2, g.getVertexInDegree(0));
        }
    }

    @Test
    public void testGetSourceVertex() {
        for (Graph g : graphs) {
            int source = g.getSourceVertex();
            assertTrue(source == 3 || source == 4);

            g.removeVertex(source);

            int otherSource = g.getSourceVertex();
            assertTrue(otherSource == 3 || otherSource == 4);

            assertNotEquals(source, otherSource);
        }
    }
}