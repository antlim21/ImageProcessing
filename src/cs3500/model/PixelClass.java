package cs3500.model;

/**
 * Way to access the color of a pixel represented in RGB format. Allows client to see the value
 * of each component (red, green, blue) in each pixel.
 */
public class PixelClass {
  protected int redValue;
  protected int greenValue;
  protected int blueValue;

  /**
   * Color of a pixel represented in RGB format. Allows each value (red, green, blue) to be
   * accessed.
   *
   * @param redValue   Red-value of a RBG representing the color of a pixel.
   * @param greenValue Green-value of a RBG representing the color of a pixel.
   * @param blueValue  Blue-value of a RBG representing the color of a pixel.
   */
  public PixelClass(int redValue, int greenValue, int blueValue) {
    this.redValue = redValue;
    this.greenValue = greenValue;
    this.blueValue = blueValue;
  }

  /**
   * Gets the red-value of a RBG representing the color of a pixel.
   *
   * @return An integer value represented the red-value of a pixel
   */
  public int getRedValue() {
    return redValue;
  }

  /**
   * Gets the green-value of a RBG representing the color of a pixel.
   *
   * @return An integer value represented the green-value of a pixel
   */
  public int getGreenValue() {
    return greenValue;
  }

  /**
   * Gets the blue-value of a RBG representing the color of a pixel.
   *
   * @return An integer value represented the blue-value of a pixel
   */
  public int getBlueValue() {
    return blueValue;
  }

  /**
   * Puts in a string format the RGB value of a cell.
   *
   * @return Returns the RGB value of a cell in a string format.
   */
  @Override
  public String toString() {
    return "( " + this.redValue + " , " + this.greenValue + " , " + this.blueValue + " )";
  }
}
