package cs3500.model;

/**
 * This interface represents the operations offered by the photoshop implementation
 * model. One object of the model represents one change in the photo.
 */
public interface PhotoshopInterface extends PhotoshopStateInterface {

  /**
   * Loads the image into our program and assigns it to the name we want to call it in the program.
   *
   * @param imagePath Ppm file name of the image we want to take in.
   * @param imageName Name that we want to call the image we are using in the program.
   */
  void load(String imagePath, String imageName);

  /**
   * Saves the image specified to our laptop.
   *
   * @param imagePath Ppm file name of the image we want to save.
   * @param imageName Image inside our program that we want to save.
   */
  void save(String imagePath, String imageName);

  /**
   * Creates a greyscale image with the red-component of the image.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  void redComponent(String imageName, String destImageName);

  /**
   * Creates a greyscale image with the green-component of the image.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  void greenComponent(String imageName, String destImageName);

  /**
   * Creates a greyscale image with the blue-component of the image.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  void blueComponent(String imageName, String destImageName);

  /**
   * Creates a greyscale image with the maximum value of the three color components of the original
   * image. Maximum value replaces all the original color components.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  void value(String imageName, String destImageName);

  /**
   * Creates a greyscale image with the average value of the three color components of the original
   * image. Average value replaces all the original color components.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  void intensity(String imageName, String destImageName);

  /**
   * Creates a greyscale image with the "luma" value of the three color components of the original
   * image. Luma value replaces all the original color components. Luma Value is represented
   * as 0.2126r + 0.7152g + 0.0722b.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  void luma(String imageName, String destImageName);

  /**
   * Creates a horizontally flipped image by changing the Row-value of each pixel.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new
   *                      horizontally flipped image.
   */
  void horizontalFlip(String imageName, String destImageName);

  /**
   * Creates a vertically flipped image by changing the Row-value of each pixel.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new
   *                      vertically flipped image.
   */
  void verticalFlip(String imageName, String destImageName);

  /**
   * Creates a brightened/darkened image by adding the value that client wants. If the value is
   * negative, it will darken the image. If the value is positive, it will brighten the image.
   * If changing a color component's value will result it to be less than 0, it will
   * automatically be set to 0. If changing a color component's value will result it to be
   * greater than 255, it will automatically be set to 255.
   *
   * @param increment     Value that client wants to brighten/darken the image by.
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  void brighten(Integer increment, String imageName, String destImageName);

  PixelClass[][] getImage(String imageName);

  //HashMap<String, PixelClass[][]> getImageTable();
}
