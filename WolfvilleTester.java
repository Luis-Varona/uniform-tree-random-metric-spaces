import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WolfvilleTester {
    public static void main(String[] args) {
        PruferCode code = new PruferCode(new ArrayList<>(List.of(2, 1, 0, 5, 2, 7, 0)));
        TreeNode root = code.getTreeRoot();
        
        String adjString = root.adjMatrixToString();        
        File dir = new File("examples");
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        try (java.io.PrintWriter out = new java.io.PrintWriter("examples/wolfville.txt")) {
            out.print(adjString);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        System.out.println(adjString);
    }
}