package matrix;

public class Matrix {
    double[]data;
    int rows;
    int cols;
    //...
    public int getRows(){return rows;}
    public int getCols() {return cols;}

    double get(int r,int c) {
        return data[r*cols+c];
    }

    void set (int r,int c, double value) {
        data[r*cols+c] = value;
    }

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d){
        rows = d.length;
        int max = 0;
        for (double [] row : d) {
            if(max < row.length)
                max = row.length;
        }
        cols = max;

        data = new double[rows*cols];
        int iter = 0;

        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (j < d[i].length)
                    data[iter+j] = d[i][j];
                else
                    data[iter+j] = 0;
            }
            iter+=cols;
        }
    }

    public double[][] asArray(){
        double[][]array = new double[rows][cols];
        int iter = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                array[i][j] = data[j+iter];
            }
            iter += cols;
        }
        return array;
    }

    @Override
    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        int iter=0;
        for(int i=0;i<rows;i++){
            buf.append("[");
            for (int j=0;j<cols; j++){
                buf.append(data[iter+j]);
                if (j!=cols-1)
                    buf.append(", ");
            }
            buf.append("]");
            iter+=cols;
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        rows = newRows;
        cols = newCols;
    }

    int[] shape() {
        int [] result = new int[2];
        result[0] = rows;
        result[1] = cols;
        return result;
    }

    Matrix add(Matrix m) {
        if (rows!=m.getRows() || cols!=m.getCols()) {
            throw new RuntimeException();
        }
        Matrix result = new Matrix(rows,cols);
        for (int i=0; i<rows*cols; i++) {
            result.data[i] = data[i] + m.data[i];
        }
        return result;
    }

    Matrix sub(Matrix m){
        if (rows!=m.getRows() || cols!=m.getCols()) {
            throw new RuntimeException();
        }
        Matrix result = new Matrix(rows,cols);
        for (int i=0; i<rows*cols; i++) {
            result.data[i] = data[i] - m.data[i];
        }
        return result;
    }
    Matrix mul(Matrix m){
        if (rows!=m.getRows() || cols!=m.getCols()) {
            throw new RuntimeException();
        }
        Matrix result = new Matrix(rows,cols);
        for (int i=0; i<rows*cols; i++) {
            result.data[i] = data[i] * m.data[i];
        }
        return result;
    }
    Matrix div(Matrix m){
        if (rows!=m.getRows() || cols!=m.getCols()) {
            throw new RuntimeException();
        }
        Matrix result = new Matrix(rows,cols);
        for (int i=0; i<rows*cols; i++) {
            result.data[i] = data[i] / m.data[i];
        }
        return result;
    }

    Matrix dot(Matrix m) {
        if(cols == m.rows) {
            Matrix newMatrix = new Matrix(rows, m.cols);
            double sum;
            for(int i = 0; i < rows; i++){
                for (int j = 0; j < m.cols; j++){
                    sum = 0;
                    for (int k = 0; k < cols; k++){
                        sum += this.data[i*this.cols + k] * m.data[k*this.cols + j];
                    }
                    newMatrix.data[i*newMatrix.getCols() + j] = sum;
                }
            }
            return newMatrix;
        } else {
            throw new RuntimeException();
        }
    }

    double frobenius() {
        double result= 0;
        for (double i : data)
            result += Math.pow(i,2);
        return result;
    }

    //////// GRUPA A /////////
    Matrix getColumn(int i) throws RuntimeException{
        if(i>=getCols())
            throw new RuntimeException("nie ma takiej kolumny");

        Matrix r = new Matrix(rows,1);

        for (int j = 0; j < rows; j++) {
            r.set(j, 0, get(j, i));
        }

        return r;
    }


/////////////////////////


    public static void main(String[] args) {
        double [][] d = {{1,2,3,4},{5,6},{7,8,9}};
        Matrix M = new Matrix(d);
        Matrix N = new Matrix(d);
        for (int i=0; i<M.getCols()*M.getRows(); i++) {
            System.out.println(M.data[i]);
        }
        System.out.println(M.toString());
        Matrix K = M.add(N);
        System.out.println(K.toString());
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = m.getColumn(0);
        System.out.println("...");
        System.out.println(col.toString());
        col = K.getColumn(2);
        System.out.println(col.toString());
    }

}

