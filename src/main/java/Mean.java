import org.apache.commons.math3.util.Precision;

import java.util.List;

public class Mean {
    private final List<Main.Node> nodes;
    private double count;
    private int count_item;
    private int deep;
    private double weight;
    private boolean weightApply = false;

    public Mean (List<Main.Node> nodes) {
        this.nodes = nodes;
        this.count = 0.00;
        this.count_item = 0;
        this.deep = 0;
        this.weight = 1.00;
    }

    public void changeWeight (short weight) {
        if (weight >= 0 && weight <= 100) {
            this.weight = Precision.round(((100 - (double) weight) / 100),2);
            weightApply = false;
        }
    }
    private void reset () {
        count = 0.00;
        count_item = 0;
        deep = 0;
    }

    public double getMean () {
        return getMean(false);
    }

    public double getMean (boolean again) {
        System.out.println(weight);
        if(weightApply) {
            return Precision.round((count/count_item),2);
        }
        if (!again) {
            reset();
            compMean();
            return getMean(true);
        }
        return 0.00;
    }

    private void recursive (Main.Node node) {
        double value = node.getValue();
        if(deep != 0) {
            count += Precision.round(value * Math.pow(weight,deep),2);
        } else {
            count += Precision.round(value,2);
        }
        count_item++;
        List<Main.Node> arr = node.getNodes();
        if(!arr.isEmpty()) {
            for (Main.Node n : arr) {
                deep ++;
                recursive(n);
                deep --;
            }
        }
    }

    public void compMean () {
        for (Main.Node node: nodes) recursive(node);
        if(deep == 0)weightApply = true;
    }

    public static Mean initMean (List<Main.Node> nodes) {
        return new Mean(nodes);
    }
}
