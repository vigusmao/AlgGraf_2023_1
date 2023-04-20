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
    public static List<Integer> obtainTopSort(Graph graph) {

        int n = graph.getVertexCount();
        List<Integer> topSort = new ArrayList<>();

        while (topSort.size() < n) {
            Integer source = graph.getSourceVertex();

            if (source == null) {
                return null;
            }

            topSort.add(source);
            graph.removeVertex(source);
        }

        return topSort;
    }
}
