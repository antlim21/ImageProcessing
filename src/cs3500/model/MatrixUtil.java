package cs3500.model;

/**
 * Represents possible Matrix operations that can be utilized.
 */
public class MatrixUtil {

  /**
   * Multiplies two matrices if they are able to be multiplied together.
   *
   * @param first  The left matrix to be multiplied.
   * @param second The right matrix to be multiplied.
   * @return The product of the two matrices.
   * @throws IllegalArgumentException if the number of columns of the first does not equal the
   *                                  number of rows of the second.
   */
  public static int[][] multiply(double[][] first, double[][] second) {
    if (first[0].length != second.length) {
      throw new IllegalArgumentException("Matrix Multiplication Not Possible\n" +
              "Number of Columns from First Matrix must equal Number of Rows from Second Matrix");
    }
    int[][] product = new int[first.length][second[0].length];
    for (int x = 0; x < first.length; x += 1) {
      for (int y = 0; y < second[0].length; y += 1) {
        double dot = 0;
        for (int counter = 0; counter < first[0].length; counter += 1) {
          dot += first[x][counter] * second[counter][y];
        }
        product[x][y] = (int) dot;
      }
    }
    return product;
  }

  /**
   * Returns the value of the filter at the given x and y coordinates on the image as a result of
   * filtering with the given kernel.
   *
   * @param kernel The kernel to be used in filtering
   * @param image  The image to be filtered
   * @param x      The x position of the entry to be filtered
   * @param y      The y position of the entry to be filtered
   * @return Returns the filtered value
   * @throws IllegalArgumentException if kernel has an even number of rows or columns
   */
  public static int filter(double[][] kernel, double[][] image, int x, int y) {
    int kernelMid = kernel.length / 2;
    int filterAmount = 0;
    for (int filterX = 0; filterX < kernel.length; filterX += 1) {
      for (int filterY = 0; filterY < kernel.length; filterY += 1) {
        int diffX = filterX - kernelMid;
        int diffY = filterY - kernelMid;
        int newX = x + diffX;
        int newY = y + diffY;
        if (newX > 0 && newX < image.length && newY > 0 && newY < image[0].length) {
          filterAmount += kernel[filterX][filterY] * image[newX][newY];
        }
      }
    }
    return filterAmount;
  }

}

