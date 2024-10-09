import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WolfvilleTester {
    public static void main(String[] args) {
        //
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
        
        //
        TreeNode nodeFour = root.findDescendant(4);
        int distanceRootToRoot = root.distanceFrom(root);
        int distanceFourToFour = nodeFour.distanceFrom(nodeFour);
        int distanceRootToFour = root.distanceFrom(nodeFour);
        
        System.out.println("Distance from root to root: " + distanceRootToRoot);
        System.out.println("Distance from node 4 to node 4: " + distanceFourToFour);
        System.out.println("Distance from root to node 4: " + distanceRootToFour);
        
        //
        TreeNode nodeZero = root.findDescendant(0);
        TreeNode nodeFive = root.findDescendant(5);
        int distanceZeroToFive = nodeZero.distanceFrom(nodeFive);
        int distanceFourToFive = nodeFour.distanceFrom(nodeFive);
        
        System.out.println("Distance from node 0 to node 5: " + distanceZeroToFive);
        System.out.println("Distance from node 4 to node 5: " + distanceFourToFive);
        
        //
        TreeNode nodeTwo = root.findDescendant(2);
        TreeNode nodeSix = root.findDescendant(6);
        int distanceTwoToSix = nodeTwo.distanceFrom(nodeSix);
        
        System.out.println("Distance from node 2 (root) to node 6: " + distanceTwoToSix);
        
        //
        TreeNode nodeOne = root.findDescendant(1);
        TreeNode nodeThree = root.findDescendant(3);
        int distanceOneToThree = nodeOne.distanceFrom(nodeThree);
        
        System.out.println("Distance from node 1 to node 3: " + distanceOneToThree);
    }
}