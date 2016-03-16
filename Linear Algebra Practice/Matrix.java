import java.util.Random;

public class Matrix {
    // Class Instance Variables
    private int numRows;
    private int numCols;
    private double[][] matData;
    
    // Useful private variables
    private Random rand = new Random();
    
    // Constructors
    // Copy Constructor
    public Matrix(Matrix m) {
        numRows = m.numRows;
        numCols = m.numCols;
        matData = m.matData;
    }
    
    // Constructors
    // Default Constructor
    public Matrix() {
        numRows = 2;
        numCols = 2;
        matData = new double[numRows][numCols];
    }
    
    // Constructors
    // Normalized Constructor
    public Matrix(int rows, int cols) {
        numRows = rows;
        numCols = cols;
        matData = new double[rows][cols];
    }
    
    // Constructors
    // Normalized Constructor with Data
    public Matrix(int rows, int cols, double[][] data) {
        numRows = rows;
        numCols = cols;
        matData = new double[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matData[i][j] = data[i][j];   
            }
        }
    }
    
    // Constructors
    // Construct Matrix from multidimensional array
    public Matrix(double[][] data) {
        numRows = data.length;
        numCols = data[0].length;
        matData = new double[numRows][numCols];
        
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matData[i][j] = data[i][j];   
            }
        }
    }
    
    // Constructors
    // Construct Randomized Matrix of Size row*col
    public Matrix randomMatrix() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.setNumAt(i,j,rand.nextInt(8)/1.0);
            }
        }
        
        return this;
    }    
    
    // Accessor and Utility Methods
    // Access and Mutate
    public int getNumRows() {return numRows;}
    public int getNumCols() {return numCols;}
    
    public double getNumAt(int row, int col) {return matData[row][col];}
    public void setNumAt(int row, int col, double num) {matData[row][col] = num;}
    
    // Accessor and Utility Methods
    // Utility Print
    public void printMatrix() {
        for (int i = 0; i < numRows; i++) {
            System.out.print("| ");
            for (int j = 0; j < numCols; j++) 
                System.out.printf("%7.4f ",matData[i][j]);
            System.out.println(" |");
        }
    }
    
    // Accessor and Utility Methods
    // Utility Check Equality
    public boolean isEqual(Matrix m) {
        if (this.numRows != m.numRows || this.numCols != m.numCols)
            return false;
            
        for (int i = 0; i < m.numRows; i++) {
            for (int j = 0; j < m.numCols; j++) {
                if (this.matData[i][j] != m.matData[i][j])
                    return false;
            }
        }
        
        return true;
    }
    
    // Accessor and Utility Methods
    // Utility Generate Utility Matrix
    public static Matrix identityMatrix(int size) {
        Matrix result = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            result.matData[i][i] = 1;
        }
        
        return result;
    }
    
    // Accessor and Utility Methods
    // Utility Flip a Matrix
    public Matrix transpose() {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.matData[i][j] = this.matData[j][i];
            }
        }
        
        return result;
    }
    
    // Accessor and Utility Methods
    // Utility Find the adjoint Matrix for Inverses
    public Matrix adjoint() {
        if (numRows != numCols) throw new RuntimeException("Not a Square Matrix!");
        
        Matrix result = new Matrix(numRows,numCols);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.matData[j][i] = Math.pow(-1.0, i + j) * findMinor(i, j).determinant();
            }
        }

        return result;
    }
    
    // Accessor and Utility Methods
    // Utility Find the Minor Matrix for the Adjoint and Determinant
    public Matrix findMinor(int row, int col) {
        Matrix result = new Matrix(numRows-1, numCols -1);
        
        int k = 0, l = 0;
        for (int i = 0; i < numRows; i++) {
            if (i == row) continue;
            for (int j = 0; j < numCols; j++) {
                if (j == col) continue;
                result.matData[l][k] = this.matData[i][j];
                
                k = (k + 1) % (numRows - 1);
                if (k == 0) l++;
            }
        }
        
        return result;
    }
    
    // Matrix Mathematics
    // Addition function
    public Matrix add(Matrix m) {
        if (this.getNumRows() != m.getNumRows() || this.getNumCols() != m.getNumCols())
            throw new RuntimeException("Matrices aren't the same size!");
        
        double[][] result = new double[m.getNumRows()][m.getNumCols()];
        for (int i = 0; i < m.getNumRows(); i++) {
            for (int j = 0; j < m.getNumCols(); j++) {
                result[i][j] = this.getNumAt(i,j) + m.getNumAt(i,j);
            }
        }
        
        return new Matrix(result);
    }
    
    // Matrix Mathematics
    // Subtraction function
    public Matrix sub(Matrix m) {
        if (this.getNumRows() != m.getNumRows() || this.getNumCols() != m.getNumCols())
            throw new RuntimeException("Matrices aren't the same size!");

        Matrix temp = new Matrix(m.getNumRows(),m.getNumCols());
        Matrix result = new Matrix(m.getNumRows(),m.getNumCols());
        temp = m.scalMult(-1);

        return new Matrix(this.add(temp));
    }
    
    // Matrix Mathematics
    // Scalar Multiplication
    public Matrix scalMult(double scalar) {
        double[][] result = new double[this.numRows][this.numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = this.matData[i][j] * scalar;
            }
        }
        
        return new Matrix(result);
    }
    
    // Matrix Mathematics
    // Multiplying Matrices
    public Matrix mult(Matrix m) {
        if (this.numCols != m.numRows)
            throw new RuntimeException("Matrices can't be multiplied!");
        
        Matrix result = new Matrix(this.numRows, m.numCols);
        for (int i = 0; i < result.numRows; i++) {
            for (int j = 0; j < result.numCols; j++) {
                for (int k = 0; k < this.numCols; k++) {
                    result.matData[i][j] += (this.matData[i][k] * m.matData[k][j]);
                }
            }
        }
        
        return result;
    }
    
    // Matrix Mathematics
    // Finding the Determinant
    public double determinant() {
        if (numRows != numCols) throw new RuntimeException("Not a Square Matrix!");
        
        double result = 0;
        
        if (numRows == 1)
            result = matData[0][0];
        else if (numRows == 2)
            result = (matData[0][0] * matData[1][1]) - (matData[1][0] * matData[0][1]);
        else {
            Matrix temp = new Matrix(numRows - 1, numCols - 1);
                
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    temp = this.findMinor(i, j);
                    result += Math.pow(-1.0, 2.0 + j) * matData[i][j] * temp.determinant();
                }
            }    
        }
        
        return result;
    }
    
    // Matrix Mathematics
    // Finding the Inverse Matrix
    public Matrix inverse() {
        if (this.determinant() == 0) throw new RuntimeException("Can't invert with determinant 0!");
        return this.adjoint().scalMult(1/this.determinant());    
    }
}