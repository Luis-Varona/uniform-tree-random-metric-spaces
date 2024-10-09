import java.util.Random;

public class RandomMetricSpace extends RandomTree {
    private final int order;
    private final PruferCode pruferCode = getPruferCode();
    
    public RandomMetricSpace(int order) {
        super(order);
        this.order = order;
    }
    
    public int getOrder() {
        return order;
    }
    
    //
    public double[][] getNormRandDistMatrix(int k) {
        TreeNode[] nodes = getRandomNodes(k);
        double[][] D = new double[k][k];
        
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                D[i][j] = D[j][i] = nodes[i].distanceFrom(nodes[j]) / Math.sqrt(order);
            }
        }
        
        return D;
    }
    
    public String normDistMatrixToString(double[][] D) {
        int k = D.length;
        StringBuilder out = new StringBuilder();
        
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k - 1; j++) {
                out.append(D[i][j]).append("  ");
            }
            
            out.append(D[i][k - 1]).append("\n");
        }
        
        return out.toString();
    }
    
    //
    private TreeNode[] getRandomNodes(int k) {
        TreeNode[] nodes = new TreeNode[k];
        Random random = new Random();
        
        for (int i = 0; i < k; i++) {
            int label = random.nextInt(order);
            nodes[i] = pruferCode.getTreeRoot().findDescendant(label);
        }
        
        return nodes;
    }
}
