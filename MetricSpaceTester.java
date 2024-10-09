import java.io.File;

public class MetricSpaceTester {
    public static void main(String[] args) {
        int n = (int) (2.4 * Math.pow(10, 5));
        int k = 600;
        RandomMetricSpace M = new RandomMetricSpace(n);
        double[][] D = M.getNormRandDistMatrix(k);
        
        String distString = M.normDistMatrixToString(D);
        File dir = new File("examples");
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        try (java.io.PrintWriter out = new java.io.PrintWriter(
            "examples/metric_240K_600.txt"
        )) {
            out.print(distString);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}