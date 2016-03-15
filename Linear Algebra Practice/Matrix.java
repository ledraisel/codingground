import java.util.Random;

public class Matrix {
    // Class Instance Variables
    private int numRows;
    private int numCols;
    private double[][] matData;
    
    // Useful private variables
    private Random rand = new Random();
    
    // Copy Constructor
    public Matrix(Matrix m) {
        numRows = m.numRows;
        numCols = m.numCols;
        matData = m.matData;
    }
    
    // Default Constructor
    public Matrix() {
        numRows = 2;
        numCols = 2;
        matData = new double[numRows][numCols];
    }
    
    // Normalized Constructor
    public Matrix(int rows, int cols) {
        numRows = rows;
        numCols = cols;
        matData = new double[rows][cols];
    }
    
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
    public int getNumRows() {return numRows;}
    public int getNumCols() {return numCols;}
    
    public double getNumAt(int row, int col) {return matData[row][col];}
    public void setNumAt(int row, int col, double num) {matData[row][col] = num;}
    
    public void printMatrix() {
        for (int i = 0; i < numRows; i++) {
            System.out.print("| ");
            for (int j = 0; j < numCols; j++) 
                System.out.printf("%5.1f ",matData[i][j]);
            System.out.println(" |");
        }
    }
    
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
    
    public static Matrix identityMatrix(int size) {
        Matrix result = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            result.matData[i][i] = 1;
        }
        
        return result;
    }
    
    public Matrix transpose() {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.matData[i][j] = this.matData[j][i];
            }
        }
        
        return result;
    }
    
    public Matrix adjoint() {
        if (numRows != numCols) throw new RuntimeException("Not a Square Matrix!");
        
        Matrix result = new Matrix(numRows,numCols);
        Matrix tempMat = new Matrix(numRows-1, numCols-1);
        double[] temp = new double[(numRows-1)*(numCols-1)];
        
        for (int i = 0; i < numRows; i++) {
            for (int j = 1; j < numCols; j++) {
                int count = 0;
                
                for (int k = 0; k < numRows * numCols; k++) {
                    if (k % numCols == i % numCols)
                        continue;
                    //else if (k )
                    //    continue;
                    else {
                        temp[count] = matData[i][j];
                        count++;
                    }
                }
                
                result.matData[i][j] = tempMat.determinant();        
                count = 0;
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
                for (int j = 1; j < numRows; j++) {
                    int count = 0;
                    
                    for (int k = 0; k < numRows; k++) {
                      if(k == i)
                              continue;
                      temp.matData[j-1][count] = this.matData[j][k];
                      count++;
                    }
                    
                }
                
                result += Math.pow(-1.0, 2.0 + i) * matData[0][i] * temp.determinant();
            }
        }
    
        return result;
    }
    
    //public Matrix inverse() {
        
    //}
}