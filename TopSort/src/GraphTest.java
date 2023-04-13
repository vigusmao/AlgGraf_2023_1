import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {

    private AdjMatrixGraph graph;

    @Before
    public void setUp() {
        graph = new AdjMatrixGraph(5);
        graph.addEdge(3, 1);
        graph.addEdge(3, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(4, 1);
        graph.addEdge(4, 0);
        graph.addEdge(2, 0);
    }

    @Test
    public void testInDegree() {
        assertEquals(0, graph.getVertexInDegree(3));
        assertEquals(3, graph.getVertexInDegree(0));
        assertEquals(2, graph.getVertexInDegree(1));
    }

    @Test
    public void testOutDegree() {
        assertEquals(2, graph.getVertexOutDegree(3));
        assertEquals(0, graph.getVertexOutDegree(0));
        assertEquals(2, graph.getVertexOutDegree(1));
    }

    @Test
    public void testVertexCount() {
        assertEquals(5, graph.getVertexCount());
    }

    @Test
    public void testHasEdge() {
        assertTrue(graph.hasEdge(3, 2));
        assertTrue(graph.hasEdge(1, 0));

        assertFalse(graph.hasEdge(3, 0));
        assertFalse(graph.hasEdge(999, -178));
    }

    @Test
    public void testRemoveVertex() {
        assertTrue(graph.hasVertex(2));
        graph.removeVertex(2);
        assertFalse(graph.hasVertex(2));

        assertEquals(4, graph.getVertexCount());
        assertEquals(5, graph.getVertexCount(true));

        assertFalse(graph.hasEdge(1, 2));

        assertEquals(1, graph.getVertexOutDegree(3));
        assertEquals(2, graph.getVertexInDegree(0));
    }
}