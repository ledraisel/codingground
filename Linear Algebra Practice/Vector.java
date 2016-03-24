import java.util.Random;

public class Vector {
    // Variables
    // Class Instance Variables
    private double[] data;
    private int size;
    
    // Variables
    // Useful private variables
    private Random rand = new Random();
    
    // Constructors
    // Copy Constructor
    public Vector (Vector v) {
        for (int i = 0; i < v.data.length; i++)
            this.data[i] = v.data[i];
        size = v.data.length;
    }
    
    // Constructors
    // Default Constructor
    public Vector () {
        data = new double[] {0, 1};
        size = data.length;
    }
    
    // Constructors
    // Normalized Constructor with Parameters
    public Vector (double[] info) {
        data = info;
        size = info.length;
    }
    
    // Constructors
    // Randomized Constructor
    public Vector randomVec() {
        size = 3;
        data = new double[size];
        
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(13);
        }
        
        Vector v = new Vector(data);
        return v;
    }
    
    // Accessor and Utility Methods


    // Accessor and Utility Methods
    // Utility Print Method
    public void printVec() {
        System.out.print("<" + data[0]);
        for (int i = 1; i < data.length; i++) {
            System.out.print(", " + data[i]);
        }
        
        System.out.println(">");
    }
    
    // Vector Mathematics
    
    
}