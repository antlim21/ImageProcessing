import org.junit.Before;
import org.junit.Test;

import cs3500.model.MatrixUtil;

import static org.junit.Assert.assertEquals;

/**
 * MatrixUtilTest test if the matrices used to multiply/filter works correctly.
 */
public class MatrixUtilTest {


  @Test
  public void testMultiply() {
    double[][] one = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    double[][] two = {
            {1},
            {2},
            {3}
    };
    int[][] result1 = {
            {14},
            {32},
            {50}
    };
    assertEquals(result1, MatrixUtil.multiply(one, two));

    double[][] three = {
            {12, 21, 30},
            {14, 25, 36}
    };
    double[][] four = {
            {1, 2},
            {2, 3},
            {3, 4}
    };
    int[][] result2 = {
            {144, 207},
            {172, 247}
    };
    assertEquals(result2, MatrixUtil.multiply(three, four));

  }


  double[][] matrix1;
  double[][] matrix2;
  double[][] matrix3;
  double[][] matrix4;

  @Before
  public void setUpExceptions() {
    matrix1 = new double[][]{
            {14},
            {32},
            {50}
    };
    matrix2 = new double[][]{
            {14},
            {32},
            {50}
    };
    matrix3 = new double[][]{
            {14, 32, 50}
    };
    matrix4 = new double[][]{
            {14, 32, 50}
    };
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiplyExceptions() {
    //Number of Columns of first doesn't equal Number of Rows of seconds
    MatrixUtil.multiply(matrix1, matrix2);
    MatrixUtil.multiply(matrix2, matrix1);
    MatrixUtil.multiply(matrix3, matrix4);
    MatrixUtil.multiply(matrix4, matrix3);
  }
}
