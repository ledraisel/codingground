

public class Main {
    
    public static void main(String[] args) {
     
        Matrix m = new Matrix(3,3);
        m.randomMatrix();
        m.printMatrix();
        
        System.out.println();
        System.out.println();
        
        Matrix n = new Matrix(3,3);
        n.randomMatrix();
        n.printMatrix();
        
        System.out.println();
        System.out.println();
        
        Matrix result = new Matrix(3,3);
        result = m.mult(n);
        result.printMatrix();
    }
}