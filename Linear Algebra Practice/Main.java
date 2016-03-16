

public class Main {
    
    public static void main(String[] args) {
        double[][] temp = new double[][] {{1, 2, 3},
                                          {4, 5, 6},
                                          {7, 2, 9}};
     
        Matrix m = new Matrix(temp);
        m.printMatrix();
        
        System.out.println();
        System.out.println("The determinant is: " + m.determinant());
        System.out.println();
        
    }
}