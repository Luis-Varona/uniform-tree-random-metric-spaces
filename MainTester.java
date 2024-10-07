import java.io.File;
import java.util.Scanner;

public class MainTester {
    public static void main(String[] args) {
        int n;
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of nodes (at least 3) in the random tree: ");
            n = scanner.nextInt();
            
            while (n < 3) {
                System.out.print("Please enter a number greater than or equal to 3. ");
                n = scanner.nextInt();
            }
            System.out.println();
        }
        
        RandomTree tree = new RandomTree(n);
        PruferCode pruferCode = tree.getPruferCode();
        TreeNode root = pruferCode.getTreeRoot();
        
        String adjString = root.adjMatrixToString();   
        File dir = new File("examples");
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        try (java.io.PrintWriter out = new java.io.PrintWriter("examples/main" + n + ".txt")) {
            out.print(adjString);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        System.out.println(pruferCode.getCode() + "\n");     
        System.out.println(adjString);
    }
}