public interface Graph {

    void addEdge(int origin,
                 int destination);

    int getVertexCount();

    /**
     * Returns the in-degree of the given vertex
     * @param v The intended vertex.
     * @return The in-degree. If the vertex does not exist,
     *         returns 0.
     */
    int getVertexInDegree(Integer v);

    int getVertexOutDegree(Integer v);

    void removeVertex(int v);

    boolean hasVertex(int v);

    boolean hasEdge(int origin,
                    int destination);
}
