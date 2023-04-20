import java.util.HashSet;
import java.util.Set;

public class AdjMatrixGraph implements Graph {

    private final int n;

    private boolean[][] adjMatrix;

    private Set<Integer> removedVertices;

    public AdjMatrixGraph(int vertexCount) {
        n = vertexCount;
        adjMatrix = new boolean[n][n];
        removedVertices = new HashSet<>();
    }

    @Override
    public void addEdge(int origin,
                        int destination) {
        if (!hasVertex(origin) || !hasVertex(destination)) {
            throw new IllegalArgumentException("Invalid edge");
        }
        adjMatrix[origin][destination] = true;
    }

    @Override
    public int getVertexCount() {
        return n - removedVertices.size();
    }

    @Override
    public int getVertexInDegree(Integer v) {
        int result = 0;
        for (int row = 0; row < n; row++) {
            if (removedVertices.contains(row)) {
                continue;
            }
            if (adjMatrix[row][v]) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int getVertexOutDegree(Integer v) {
        int result = 0;
        for (int col = 0; col < n; col++) {
            if (removedVertices.contains(col)) {
                continue;
            }
            if (adjMatrix[v][col]) {
                result++;
            }
        }
        return result;
    }

    @Override
    public void removeVertex(int v) {
        if (v < 0 || v >= n) {
            return;
        }

        removedVertices.add(v);
    }

    @Override
    public boolean hasVertex(int v) {
        return v >= 0 &&
               v < n &&
               !removedVertices.contains(v);
    }

    @Override
    public boolean hasEdge(int origin,
                           int destination) {
        if (!hasVertex(origin) || !hasVertex(destination)) {
            return false;
        }
        return adjMatrix[origin][destination];
    }

    @Override
    public Integer getSourceVertex() {
        for (int v = 0; v < n; v++) {
            if (hasVertex(v) &&
                    getVertexInDegree(v) == 0) {
                return v;
            }
        }
        return null;
    }
}
