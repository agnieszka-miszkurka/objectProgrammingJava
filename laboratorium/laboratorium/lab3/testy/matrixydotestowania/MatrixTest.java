package matrixydotestowania;

import java.util.Random;

import static org.junit.Assert.*;

public class MatrixTest {
    @org.junit.Test
    public void getRows() throws Exception {
        int rand_rows = (int)(Math.random()*10);  //od 0 do 9
        int rand_cols =(int)(Math.random()*10);
        Matrix M = new Matrix(rand_rows, rand_cols);
        assertEquals(rand_rows,M.getRows());
    }

    @org.junit.Test
    public void getCols() throws Exception {
        int rand_rows = (int)(Math.random()*10);
        int rand_cols =(int)(Math.random()*10);
        Matrix M = new Matrix(rand_rows, rand_cols);
        assertEquals(rand_cols,M.getCols());
    }

    @org.junit.Test
    public void MatrixConstructorWithRowsAndColsTest(){
        int rows = (int)(Math.random()*10);
        int cols =(int)(Math.random()*10);
        Matrix M = new Matrix(rows,cols);
        assertEquals(cols,M.getCols());
        assertEquals(rows,M.getRows());
    }

    @org.junit.Test
    public void MatirxConstructorWithArrayTest(){
        double [][] d = {{3,4,5,6,7},{4,5,6,8}};
        Matrix M = new Matrix(d);
        double [][] result = M.asArray();
        assertArrayEquals(d[0],result[0],.1);
        assertEquals(M.get(1,4),0,0.0);
    }

    @org.junit.Test
    public void get() throws Exception {
        double [][] d = {{3,4,5,6,7},{4,5,6,8,0}};
        Matrix M = new Matrix(d);
        assertEquals(M.get(0,0),3, 0.0);
    }

    @org.junit.Test
    public void set() throws Exception {
        double [][] d = {{3,4,5,6,7},{4,5,6,8,0}};
        Matrix M = new Matrix(d);
        M.set(1,1,3);
        assertEquals(M.get(1,1),3, 0.0);
    }

    @org.junit.Test
    public void asArray() throws Exception {
        double [][] d = {{3,4,5,6,7},{4,5,6,8,0}};
        Matrix M = new Matrix(d);
        double [][] result = M.asArray();
        assertArrayEquals(d[0],result[0],.1);
        assertArrayEquals(d[1],result[1],.1);
    }

    @org.junit.Test
    public void testToString() throws Exception {
    }

    @org.junit.Test
    public void reshape() throws Exception {
        double [][] d = {{3,4,5,6,7},{4,5,6,8,0}};
        Matrix M = new Matrix(d);
        try {
            M.reshape(2,2);
        } catch (RuntimeException e) {
            return;
        }
        fail("wyjatek nieprzechwycony!");
    }

    @org.junit.Test
    public void shape() throws Exception {
    }

    @org.junit.Test
    public void add() throws Exception {
    }

    @org.junit.Test
    public void sub() throws Exception {
    }

    @org.junit.Test
    public void mul() throws Exception {
    }

    @org.junit.Test
    public void div() throws Exception {
    }

    @org.junit.Test
    public void dot() throws Exception {
    }

    @org.junit.Test
    public void frobenius() throws Exception {
    }

}