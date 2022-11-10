import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        List<Node> list = new ArrayList<>();
        list.add(node());
        list.add(node(2,new Node[]{node(3),node(4)}));
        list.add(node(5,new Node[]{node(6, new Node[]{node(7)})}));
        list.add(node(9,new Node[]{node(6,new Node[]{node(8),node(15),node(6,new Node[]{node(3)})}),node(11,new Node[]{node(4),node(11)})}));

        short weight = 99;

        Mean mean = Mean.initMean(list);
        System.out.println(mean.getMean());
        mean.changeWeight(weight);
        System.out.println(mean.getMean());

    }


    public interface Node {
        double getValue();
        List<Node> getNodes();
    }

    public static Node node () {
        return node(0.00,new Node[]{});
    }

    public static Node node (double value) {
        return node(value,new Node[]{} );
    }

    public static Node node (double value, Node[] nodes) {
        return new Node() {
            public double getValue() {
                return value;
            }
            public List<Node> getNodes() {
                return Arrays.asList(nodes);
            }

        };
    }
}
