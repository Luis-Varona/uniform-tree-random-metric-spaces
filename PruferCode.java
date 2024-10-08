import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class PruferCode {
    private final ArrayList<Integer> code;
    private final TreeNode treeRoot;
    
    public PruferCode(ArrayList<Integer> code) {
        this.code = code;
        this.treeRoot = buildTree();
    }
    
    public ArrayList<Integer> getCode() {
        return code;
    }
    
    public TreeNode getTreeRoot() {
        return treeRoot;
    }
    
    //
    private TreeNode buildTree() {
        int n = code.size() + 1;
        ListIterator<Integer> codeIterator = code.listIterator();
        ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(n + 1, false));
        
        int label = codeIterator.next();
        TreeNode root = new TreeNode(label, null, null);
        TreeNode node = root;
        visited.set(label, true);
        
        while (codeIterator.hasNext()) {
            int next = codeIterator.next();
            label = visited.get(next) ? visited.indexOf(false) : next;
            
            TreeNode child = new TreeNode(label, root, node);
            node.addChild(child);
            node = label == next ? child : root.findDescendant(next);
            visited.set(label, true);
        }
        
        node.addChild(new TreeNode(visited.indexOf(false), root, node));
        return root;
    }
}