package cs260.graph;

public class GraphFactory {

    static public <V> Graph<V> createGraph() {
        return new GraphZickb();
    }

}
