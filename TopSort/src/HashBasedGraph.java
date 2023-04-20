import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashBasedGraph implements Graph {

    private Map<Integer, Set<Integer>> inNeighborsByVertex;
    private Map<Integer, Set<Integer>> outNeighborsByVertex;

    public HashBasedGraph(int n) {
        inNeighborsByVertex = new HashMap<>(n);
        outNeighborsByVertex = new HashMap<>(n);

        for (int v = 0; v < n; v++) {
            inNeighborsByVertex.put(v, null);
            outNeighborsByVertex.put(v, null);
        }
    }

    @Override
    public void addEdge(int origin, int destination) {
        Set<Integer> inNeighbors = inNeighborsByVertex.get(destination);
        if (inNeighbors == null) {
           inNeighbors = new HashSet<>();  // lazy instantiation
           inNeighborsByVertex.put(destination, inNeighbors);
        }
        inNeighbors.add(origin);

        Set<Integer> outNeighbors = outNeighborsByVertex.get(origin);
        if (outNeighbors == null) {
            outNeighbors = new HashSet<>();  // lazy instantiation
            outNeighborsByVertex.put(origin, outNeighbors);
        }
        outNeighbors.add(destination);
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
        Set<Integer> outNeighbors = outNeighborsByVertex.get(v);
        return outNeighbors == null ? 0 : outNeighbors.size();
    }

    @Override
    public void removeVertex(int v) {
        Set<Integer> inNeighbors = inNeighborsByVertex.get(v);
        if (inNeighbors != null) {
            for (int inNeighbor : inNeighbors) {
                outNeighborsByVertex.get(inNeighbor).remove(v);
            }
        }
        inNeighborsByVertex.remove(v);

        Set<Integer> outNeighbors = outNeighborsByVertex.get(v);
        if (outNeighbors != null) {
            for (int outNeighbor : outNeighbors) {
                inNeighborsByVertex.get(outNeighbor).remove(v);
            }
        }
        outNeighborsByVertex.remove(v);
    }

    @Override
    public boolean hasVertex(int v) {
        return inNeighborsByVertex.containsKey(v);
    }

    @Override
    public boolean hasEdge(int origin, int destination) {
        Set<Integer> outNeighbors = outNeighborsByVertex.get(origin);
        return outNeighbors != null && outNeighbors.contains(destination);
    }

    @Override
    public Integer getSourceVertex() {
        for (Map.Entry<Integer, Set<Integer>> entry :
                inNeighborsByVertex.entrySet()) {
            Set<Integer> inNeighbors = entry.getValue();
            if (inNeighbors == null || inNeighbors.isEmpty()) {
                return entry.getKey();
            }
        }
        return null;
    }
}
