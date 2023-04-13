import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashBasedGraph implements Graph {

    private Map<Integer, Set<Integer>> inNeighborsByVertex;
    private Map<Integer, Set<Integer>> outNeighborsByVertex;

    public HashBasedGraph(int n) {
        inNeighborsByVertex = new HashMap<>();
        outNeighborsByVertex = new HashMap<>();
    }

    @Override
    public void addEdge(int origin, int destination) {

    }

    @Override
    public int getVertexCount() {
        return inNeighborsByVertex.size();
    }

    @Override
    public int getVertexInDegree(Integer v) {
        Set<Integer> inNeighbors = inNeighborsByVertex.get(v);
        return inNeighbors == null ? 0 : inNeighbors.size();
    }

    @Override
    public int getVertexOutDegree(Integer v) {
        return 0;
    }

    @Override
    public void removeVertex(int v) {

    }

    @Override
    public boolean hasVertex(int v) {
        return false;
    }

    @Override
    public boolean hasEdge(int origin, int destination) {
        return false;
    }
}
