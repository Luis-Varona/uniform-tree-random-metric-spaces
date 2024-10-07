import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTree {
    private final int order;
    private final PruferCode pruferCode;
    
    public RandomTree(int order) {
        this.order = order;
        this.pruferCode = generatePruferCode();
    }
    
    //
    public PruferCode getPruferCode() {
        return pruferCode;
    }
    
    //
    private PruferCode generatePruferCode() {
        Random random = new Random();
        Integer[] prufer = new Integer[order - 1];
        
        for (int i = 0; i < order - 1; i++) {
            prufer[i] = random.nextInt(order);
        }
        
        return new PruferCode(new ArrayList<>(List.of(prufer)));
    }
}