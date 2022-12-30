package cs3500.model;

/**
 * This interface represents operations that can be used to monitor the state of an image, without
 * changing it. Ensures that images are properly changed.
 */
public interface PhotoshopStateInterface {

  /**
   * Finds the size of an image in pixels by multiplying height and width of the picture.
   *
   * @param imageName Name of the image that you want to find the size of.
   * @return An integer representing the size of the image.
   */
  int getPictureSize(String imageName);

  /**
   * Gets the color of a pixel in RGB format of an image the client wants.
   *
   * @param imageName Image that you want to get a pixel color at.
   * @param row       Row of specified pixel.
   * @param col       Column of specified pixel.
   * @return Returns the red, green, blue value of the pixel.
   */
  PixelClass getPixelColorAt(String imageName, int row, int col);
}
