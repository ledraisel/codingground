import java.util.Random;

public class Vector2 {
    // Variables
    // Class Instance Variables
    private double x;
    private double y;
    
    // Variables
    // Useful private variables
    private Random rand = new Random();
    
    // Constructors
    // Copy Constructor
    public Vector2 (Vector2 v) {
        x = v.x;
        y = v.y;
    }
    
    // Constructors
    // Default Constructor
    public Vector2 () {
        x = 0;
        y = 1;
    }
    
    // Constructors
    // Normalized Constructor with Parameters
    public Vector2 (double x_, double y_) {
        x = x_;
        y = y_;
    }
    
    // Constructors
    // Randomized Constructor
    public Vector2 randomVec2() {
        Vector2 v = new Vector2();
        v.x = rand.nextInt(13);
        v.y = rand.nextInt(13);
        
        return v;
    }
    
    // Accessor and Utility Methods


    // Accessor and Utility Methods
    // Utility Print Method
    public void printVec2() {
        
    }
    
    // Vector Mathematics
    
    
}