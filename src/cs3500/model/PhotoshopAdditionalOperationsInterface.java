package cs3500.model;

/**
 * This interface represents the additional operations offered by the photoshop additional
 * operations implementation in the model class. One object of the model represents one
 * change in the photo.
 */
public interface PhotoshopAdditionalOperationsInterface extends PhotoshopInterface {

  void blur(String imageName, String destImageName);

  void sharpen(String imageName, String destImageName);

  void greyscale(String imageName, String destImageName);

  void sepia(String imageName, String destImageName);

  void downsize(int newHeight, int newWidth, String imageName, String destImageName);


}
