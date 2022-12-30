package cs3500.model;

import java.util.Map;

/**
 * PhotoshopAdditionalOperationsImpl is where additional operations to edit happen. Allows image
 * to be blurred, sharpened, grey-scaled, and apply a sepia filter.
 */
public class PhotoshopAdditionalOperationsImpl extends PhotoshopImpl
        implements PhotoshopAdditionalOperationsInterface {

  public PhotoshopAdditionalOperationsImpl() {
    super();
  }

  public PhotoshopAdditionalOperationsImpl(Map<String, PixelClass[][]> images) {
    super(images);
  }

  private void operation(String imageName, String destImageName, String cmd) {
    PixelClass[][] currentImage = this.images.get(imageName);
    if (currentImage == null) {
      throw new IllegalArgumentException("Image you are trying to change does not exist yet.");
    }
    int height = currentImage.length;
    int width = currentImage[0].length;
    PixelClass[][] newImage = new PixelClass[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        double[][] kernel;

        int r = currentImage[i][j].getRedValue();
        int g = currentImage[i][j].getGreenValue();
        int b = currentImage[i][j].getBlueValue();

        double[][] rgb = {
                {r},
                {g},
                {b}
        };

        int[][] newRGB;
        int newR;
        int newG;
        int newB;
        switch (cmd) {
          case "greyscale":
            kernel = new double[][]{
                    {0.2126, 0.7152, 0.0722},
                    {0.2126, 0.7152, 0.0722},
                    {0.2126, 0.7152, 0.0722}
            };
            newRGB = MatrixUtil.multiply(kernel, rgb);

            newR = this.over255under0((int) newRGB[0][0]);
            newG = this.over255under0((int) newRGB[1][0]);
            newB = this.over255under0((int) newRGB[2][0]);
            newImage[i][j] = new PixelClass(newR, newG, newB);
            break;
          case "sepia":
            kernel = new double[][]{
                    {0.393, 0.769, 0.189},
                    {0.349, 0.686, 0.168},
                    {0.272, 0.534, 0.131}
            };
            newRGB = MatrixUtil.multiply(kernel, rgb);

            newR = this.over255under0((int) newRGB[0][0]);
            newG = this.over255under0((int) newRGB[1][0]);
            newB = this.over255under0((int) newRGB[2][0]);
            newImage[i][j] = new PixelClass(newR, newG, newB);
            break;
          case "blur":
            kernel = new double[][]{
                    {(1.0 / 16.0), (1.0 / 8.0), (1.0 / 16.0)},
                    {(1.0 / 8.0), (1.0 / 4.0), (1.0 / 8.0)},
                    {(1.0 / 16.0), (1.0 / 8.0), (1.0 / 16.0)}
            };
            newImage[i][j] = this.filterHelp(kernel, currentImage, i, j);
            break;
          case "sharpen":
            kernel = new double[][]{
                    {(-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0)},
                    {(-1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), (-1.0 / 8.0)},
                    {(-1.0 / 8.0), (1.0 / 4.0), 1.0, (1.0 / 4.0), (-1.0 / 8.0)},
                    {(-1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), (-1.0 / 8.0)},
                    {(-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0)},
            };
            newImage[i][j] = this.filterHelp(kernel, currentImage, i, j);
            break;
          default:
            newImage[i][j] = new PixelClass(r, g, b);
            break;

        }
      }
    }
    this.images.put(destImageName, newImage);
  }

  private PixelClass filterHelp(double[][] kernel, PixelClass[][] currentImage, int i, int j) {
    double[][] pixelRed = new double[currentImage.length][currentImage[0].length];
    double[][] pixelGreen = new double[currentImage.length][currentImage[0].length];
    double[][] pixelBlue = new double[currentImage.length][currentImage[0].length];
    for (int x = 0; x < currentImage.length; x += 1) {
      for (int y = 0; y < currentImage[0].length; y += 1) {
        pixelRed[x][y] = currentImage[x][y].getRedValue();
        pixelGreen[x][y] = currentImage[x][y].getGreenValue();
        pixelBlue[x][y] = currentImage[x][y].getBlueValue();
      }
    }
    double newR;
    double newG;
    double newB;
    newR = MatrixUtil.filter(kernel, pixelRed, i, j);
    newG = MatrixUtil.filter(kernel, pixelGreen, i, j);
    newB = MatrixUtil.filter(kernel, pixelBlue, i, j);

    newR = this.over255under0((int) newR);
    newG = this.over255under0((int) newG);
    newB = this.over255under0((int) newB);

    return new PixelClass((int) newR, (int) newG, (int) newB);
  }

  @Override
  public void blur(String imageName, String destImageName) {
    this.operation(imageName, destImageName, "blur");
  }

  @Override
  public void sharpen(String imageName, String destImageName) {
    this.operation(imageName, destImageName, "sharpen");
  }

  @Override
  public void greyscale(String imageName, String destImageName) {
    this.operation(imageName, destImageName, "greyscale");
  }

  @Override
  public void sepia(String imageName, String destImageName) {
    this.operation(imageName, destImageName, "sepia");
  }

  @Override
  public void downsize(int newHeight, int newWidth, String imageName, String destImageName) {
    PixelClass[][] currentImage = this.images.get(imageName);
    if (currentImage == null) {
      throw new IllegalArgumentException("Image you are trying to change does not exist yet.");
    }
    if (newHeight >= 100 || newWidth >= 100) {
      throw new IllegalArgumentException("Cannot downsize by a percentage greater than 100");
    }
    int height = currentImage.length;
    int width = currentImage[0].length;
    int newImageHeight = (int) ((currentImage.length * newHeight) / 100);
    int newImageWidth = (int) ((currentImage[0].length * newWidth) / 100);
    PixelClass[][] newImage = new PixelClass[newImageHeight][newImageWidth];

    for (int i = 0; i < newImageHeight; i++) {
      for (int j = 0; j < newImageWidth; j++) {

        //PixelClass pixel1 = new PixelClass();

        double x = i * (100.0 / newHeight);
        double y = j * (100.0 / newWidth);
        if (x == (int) x || y == (int) y) {
          newImage[i][j] = new PixelClass(currentImage[(int) x][(int) y].getRedValue(),
                  currentImage[(int) x][(int) y].getGreenValue(),
                  currentImage[(int) x][(int) y].getBlueValue());
        } else {

          PixelClass pixel1 = currentImage[(int) Math.floor(x)][(int) Math.floor(y)];
          PixelClass pixel2 = currentImage[(int) Math.ceil(x)][(int) Math.floor(y)];
          PixelClass pixel3 = currentImage[(int) Math.floor(x)][(int) Math.ceil(y)];
          PixelClass pixel4 = currentImage[(int) Math.ceil(x)][(int) Math.ceil(y)];

          //ca
          int p1r = pixel1.getRedValue();
          int p1g = pixel1.getGreenValue();
          int p1b = pixel1.getBlueValue();

          //cb
          int p2r = pixel2.getRedValue();
          int p2g = pixel2.getGreenValue();
          int p2b = pixel2.getBlueValue();

          //cc
          int p3r = pixel3.getRedValue();
          int p3g = pixel3.getGreenValue();
          int p3b = pixel3.getBlueValue();

          //cd
          int p4r = pixel4.getRedValue();
          int p4g = pixel4.getGreenValue();
          int p4b = pixel4.getBlueValue();


          double mRed = ((p2r * (x - Math.floor(x))) + (p1r * (Math.ceil(x) - x)));

          double nRed = ((p4r * (x - Math.floor(x))) + (p3r * (Math.ceil(x) - x)));
          double newRed = ((nRed * (y - Math.floor(y))) + (mRed * (Math.ceil(y) - y)));
          System.out.println(mRed + " this is the value of mRed");
          System.out.println(nRed + " this is the value of nRed");

          double mGreen = ((p2g * (x - Math.floor(x))) + (p1g * (Math.ceil(x) - x)));
          double nGreen = ((p4g * (x - Math.floor(x))) + (p3g * (Math.ceil(x) - x)));
          double newGreen = ((nGreen * (y - Math.floor(y))) + (mGreen * (Math.ceil(y) - y)));

          double mBlue = ((p2b * (x - Math.floor(x))) + (p1b * (Math.ceil(x) - x)));
          double nBlue = ((p4b * (x - Math.floor(x))) + (p3b * (Math.ceil(x) - x)));
          double newBlue = ((nBlue * (y - Math.floor(y))) + (mBlue * (Math.ceil(y) - y)));

          newImage[i][j] = new PixelClass((int) newRed, (int) newGreen, (int) newBlue);
          System.out.println(Math.ceil(newRed) + " this is the value of newRed");
          System.out.println(Math.ceil(newGreen));
          System.out.println(Math.ceil(newBlue));
        }
      }
    }
    System.out.println(destImageName + " model#0");
    System.out.println(newImage.toString() + " model#1");
    this.images.put(destImageName, newImage);
  }
}
