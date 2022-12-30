package cs3500.model;

/**
 * Mock photoshop model is a mock model that is used for testing.
 */
public class MockPhotoshopModel implements PhotoshopAdditionalOperationsInterface {

  private StringBuilder log;

  public MockPhotoshopModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void load(String imagePath, String imageName) {
    log.append("Loading with Image Path: " + imagePath + " and Image Name: " + imageName + "\n");
  }

  @Override
  public void save(String imagePath, String imageName) {
    log.append("Saving with Image Path: " + imagePath + " and Image Name: " + imageName + "\n");
  }

  @Override
  public void redComponent(String imageName, String destImageName) {
    log.append("Red Greyscale on Image: " + imageName + ", new image known as: " +
            destImageName + "\n");
  }

  @Override
  public void greenComponent(String imageName, String destImageName) {
    log.append("Green Greyscale on Image: " + imageName + ", new image known as: " +
            destImageName + "\n");
  }

  @Override
  public void blueComponent(String imageName, String destImageName) {
    log.append("Blue Greyscale on Image: " + imageName + ", new image known as: " +
            destImageName + "\n");
  }

  @Override
  public void value(String imageName, String destImageName) {
    log.append("Value Greyscale on Image: " + imageName + ", new image known as: "
            + destImageName + "\n");
  }

  @Override
  public void intensity(String imageName, String destImageName) {
    log.append("Intensity Greyscale on Image: " + imageName + ", new image known as: "
            + destImageName + "\n");
  }

  @Override
  public void luma(String imageName, String destImageName) {
    log.append("Luma Greyscale on Image: " + imageName + ", new image known as: "
            + destImageName + "\n");
  }

  @Override
  public void horizontalFlip(String imageName, String destImageName) {
    log.append("Horizontal Flip on Image: " + imageName + ", new image known as: "
            + destImageName + "\n");
  }

  @Override
  public void verticalFlip(String imageName, String destImageName) {
    log.append("Vertical Flip on Image: " + imageName + ", new image known as: "
            + destImageName + "\n");
  }

  @Override
  public void brighten(Integer increment, String imageName, String destImageName) {
    log.append("Brighten on Image: " + imageName + ", new image known as: "
            + destImageName + "\n");
  }

  @Override
  public PixelClass[][] getImage(String imageName) {
    return new PixelClass[0][];
  }

  @Override
  public int getPictureSize(String imageName) {
    log.append("Getting the Picture Size of Image: " + imageName + "\n");
    return 0;
  }

  @Override
  public PixelClass getPixelColorAt(String imageName, int row, int col) {
    log.append("Getting Pixel of Image: " + imageName + " at ( " + row + " , " + col + " )" + "\n");
    return null;
  }

  @Override
  public void blur(String imageName, String destImageName) {
    log.append("Blurring Image: " + imageName + ", new image known as: " + destImageName + "\n");
    return;
  }

  @Override
  public void sharpen(String imageName, String destImageName) {
    log.append("Sharpening Image: " + imageName + ", new image known as: " + destImageName + "\n");
    return;
  }

  @Override
  public void greyscale(String imageName, String destImageName) {
    log.append("Greyscaling Image: " + imageName + ", new image known as: " + destImageName + "\n");
    return;
  }

  @Override
  public void sepia(String imageName, String destImageName) {
    log.append("Sepia-ing Image: " + imageName + ", new image known as: " + destImageName + "\n");
    return;
  }

  @Override
  public void downsize(int newHeight, int newWidth, String imageName, String destImageName) {
    log.append("Downsize on Image: " + imageName + ", new image known as: "
            + destImageName + "\n");
  }

}