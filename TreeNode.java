import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TreeNode {
    private final int label;
    private final int depth;
    private final TreeNode root;
    private final TreeNode parent;
    private final List<TreeNode> children;
    
    public TreeNode(int label, TreeNode root, TreeNode parent) {
        this.label = label;
        this.depth = root == null ? 0 : parent.getDepth() + 1;
        this.root = root == null ? this : root;
        this.parent = parent;
        this.children =  new LinkedList<>();
    }
    
    //
    public int getLabel() {
        return label;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public TreeNode getRoot() {
        return root;
    }
    
    public TreeNode getParent() {
        return parent;
    }
    
    public List<TreeNode> getChildren() {
        return children;
    }
    
    //
    public void addChild(TreeNode child) {
        children.add(child);
    }
    
    public void removeChild(TreeNode child) {
        children.remove(child);
    }
    
    //
    public int getOrder() {
        int size = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            size++;
            
            for (TreeNode child : node.getChildren()) {
                stack.push(child);
            }
        }
        
        return size;
    }
    
    //
    public int[][] getAdjMatrix() {
        int n = getOrder();
        int[][] adjMatrix = new int[n][n];
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                adjMatrix[i][j] = adjMatrix[j][i] = (
                    root.findDescendant(i).hasChild(j) || root.findDescendant(j).hasChild(i)
                ) ? 1 : 0;
            }
        }
        
        return adjMatrix;
    }
    
    public String adjMatrixToString() {
        int n = getOrder();
        int[][] adjMatrix = getAdjMatrix();
        StringBuilder out = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                out.append(adjMatrix[i][j]).append("  ");
            }
            
            out.append(adjMatrix[i][n - 1]).append("\n");
        }
        
        return out.toString();
    }
    
    //
    public boolean hasChild(int labelChild) {
        boolean result = false;
        ListIterator<TreeNode> childrenIterator = children.listIterator();
        
        while (!result && childrenIterator.hasNext()) {
            result = childrenIterator.next().getLabel() == labelChild;
        }
        
        return result;
    }
    
    public TreeNode findDescendant(int labelDescendant) {
        TreeNode descendant = label == labelDescendant ? this : null;
        ListIterator<TreeNode> childrenIterator = children.listIterator();
        
        while (descendant == null && childrenIterator.hasNext()) {
            descendant = childrenIterator.next().findDescendant(labelDescendant);
        }
        
        return descendant;
    }
    
    //
    public int distanceFrom(TreeNode node) {
        if (node.root != root) {
            throw new IllegalArgumentException("`node` is not in the same tree as `this`.");
        }
        
        TreeNode ancestorSelf = this;
        TreeNode ancestorNode = node;
        ArrayList<Integer> ancestorsSelf = new ArrayList<>(Arrays.asList(label));
        ArrayList<Integer> ancestorsNode = new ArrayList<>(Arrays.asList(node.getLabel()));
        int distanceSelf = ancestorsSelf.indexOf(node.getLabel());
        int distanceNode = ancestorsNode.indexOf(label);
        
        while (distanceSelf == -1 && distanceNode == -1) {
            if (ancestorSelf.getDepth() > 0) {
                ancestorSelf = ancestorSelf.getParent();
                ancestorsSelf.add(ancestorSelf.getLabel());
                distanceNode = ancestorsNode.indexOf(ancestorSelf.getLabel());
            }
            
            if (ancestorNode.getDepth() > 0) {
                ancestorNode = ancestorNode.getParent();
                ancestorsNode.add(ancestorNode.getLabel());
                distanceSelf = ancestorsSelf.indexOf(ancestorNode.getLabel());
            }
        }
        
        if (distanceSelf == -1) {
            distanceSelf = ancestorsSelf.indexOf(ancestorsNode.get(distanceNode));
        }
        else if (distanceNode == -1) {
            distanceNode = ancestorsNode.indexOf(ancestorsSelf.get(distanceSelf));
        }
        
        return distanceSelf + distanceNode;
    }
}