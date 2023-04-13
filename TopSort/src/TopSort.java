import java.util.ArrayList;
import java.util.List;

public class TopSort {

    /**
     * Finds a topological sorting for the given graph.
     *
     * @param graph The graph
     * @return A topsort, if one exists;
     *         null, otherwise
     */
    public static List<Integer> obtainTopSort(
            AdjMatrixGraph graph) {

        int n = graph.getVertexCount();
        List<Integer> topSort = new ArrayList<>();

        while (topSort.size() < n) {
            Integer source = findSource(graph);

            if (source == null) {
                return null;
            }

            topSort.add(source);
            graph.removeVertex(source);
        }

        return topSort;
    }

    private static Integer findSource(AdjMatrixGraph graph) {

        for (int v = 0; v < graph.getVertexCount(true); v++) {
            if (graph.hasVertex(v) &&
                    graph.getVertexInDegree(v) == 0) {
                return v;
            }
        }

        return null;
    }
}
