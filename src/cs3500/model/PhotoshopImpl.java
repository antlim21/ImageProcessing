package cs3500.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import cs3500.controller.ImageUtil;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * PhotoshopImpl is where all the editing happens. Allows image to be grey scaled six different
 * ways, flipped horizontally and vertically, and brightened and darkened.
 */
public class PhotoshopImpl implements PhotoshopInterface {

  protected String imageName;
  protected Map<String, PixelClass[][]> images;


  /**
   * Allows images to be taken in via a hashMap.
   */
  public PhotoshopImpl() {
    images = new HashMap<String, PixelClass[][]>();
  }

  /**
   * Allows images to be taken in via a map with a name assigned to it.
   *
   * @param other Images that should be taken in.
   */
  public PhotoshopImpl(Map<String, PixelClass[][]> other) {

    images = other;
  }

  //Takes an image that you want to load and puts it onto a hashmap. Hashmap has String and
  // pixel class.

  /**
   * Loads an image to be edited. Takes it in via the imagePath and assigns a name to it.
   *
   * @param imagePath Ppm file name of the image we want to take in.
   * @param imageName Name that we want to call the image we are using in the program.
   */
  public void load(String imagePath, String imageName) {
    System.out.println("Loading");
    String reversedImagePath = this.reverseWord(imagePath);
    PixelClass[][] imageArray;
    if (reversedImagePath.indexOf("mpp") == 0) {

      imageArray = ImageUtil.readPPM(imagePath);
    } else {
      imageArray = ImageUtil.readConventional(imagePath);
    }
    this.imageName = imageName;
    this.images.put(imageName, imageArray);
  }

  /**
   * Saves the image specified to our laptop. Takes in imagePath which is where client wants to
   * save, and imageName which is the image client wants to save.
   *
   * @param imagePath Ppm file name of the image we want to save.
   * @param imageName Image inside our program that we want to save.
   */
  @Override
  public void save(String imagePath, String imageName) {
    PixelClass[][] currentImage = this.images.get(imageName);
    if (currentImage == null) {
      throw new IllegalArgumentException("Image does not exist yet");
    }

    if (!imagePath.contains(".")) {
      throw new IllegalArgumentException("Need to specify file type");
    }

    int height = currentImage.length;
    int width = currentImage[0].length;

    String reversedImagePath = this.reverseWord(imagePath);

    if (reversedImagePath.indexOf("mpp") == 0) {
      try {
        PrintWriter fileWriter = new PrintWriter(imagePath);
        fileWriter.println("P3");
        fileWriter.println(width + " " + height);
        fileWriter.println("255");
        System.out.println("in try catch");

        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {

            int r = currentImage[i][j].getRedValue();
            int g = currentImage[i][j].getGreenValue();
            int b = currentImage[i][j].getBlueValue();

            fileWriter.println(r);
            fileWriter.println(g);
            fileWriter.println(b);
          }
        }
        fileWriter.flush();
        fileWriter.close();
      } catch (FileNotFoundException e) {

        System.out.println("File not found");
        System.out.println(e);
      }
    } else {

      int[][] rgb = new int[height][width];
      for (int x = 0; x < height; x += 1) {
        for (int y = 0; y < width; y += 1) {

          int r = currentImage[x][y].getRedValue();
          int g = currentImage[x][y].getGreenValue();
          int b = currentImage[x][y].getBlueValue();

          Color currentColor = new Color(r, g, b);

          rgb[x][y] = currentColor.getRGB();
        }
      }
      BufferedImage creation = new BufferedImage(width, height, TYPE_INT_RGB);
      for (int x = 0; x < creation.getHeight(); x += 1) {
        for (int y = 0; y < creation.getWidth(); y += 1) {


          Color currentColor = new Color(currentImage[x][y].getRedValue(),
                  currentImage[x][y].getGreenValue(),
                  currentImage[x][y].getBlueValue());

          creation.setRGB(y, x, currentColor.getRGB());
        }
      }
      int dotIndex = reversedImagePath.indexOf(".");
      String fileType = this.reverseWord(reversedImagePath.substring(0, dotIndex));

      try {
        boolean bool;
        bool = ImageIO.write(creation, fileType, new File(imagePath));
      } catch (IOException e) {
        e.getMessage();
      }

    }


  }

  private String reverseWord(String s) {
    String temp = "";
    for (int x = 0; x < s.length(); x += 1) {
      temp = "" + s.charAt(x) + temp;
    }
    return temp;
  }


  private void component(String imageName, String destImageName, String cmd, Integer increment) {
    PixelClass[][] currentImage = this.images.get(imageName);
    if (currentImage == null) {
      throw new IllegalArgumentException("Image you are trying to change does not exist yet.");
    }
    int height = currentImage.length;
    int width = currentImage[0].length;
    PixelClass[][] newImage = new PixelClass[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = currentImage[i][j].getRedValue();
        int g = currentImage[i][j].getGreenValue();
        int b = currentImage[i][j].getBlueValue();

        switch (cmd) {
          case "red-component":
            newImage[i][j] = new PixelClass(r, r, r);
            break;
          case "green-component":
            newImage[i][j] = new PixelClass(g, g, g);
            break;
          case "blue-component":
            newImage[i][j] = new PixelClass(b, b, b);
            break;
          case "value-component":
            int max = Math.max(r, (Math.max(g, b)));
            newImage[i][j] = new PixelClass(max, max, max);
            break;
          case "intensity-component":
            int avg = (r + g + b) / 3;
            newImage[i][j] = new PixelClass(avg, avg, avg);
            break;
          case "luma-component":
            int luma = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
            newImage[i][j] = new PixelClass(luma, luma, luma);
            break;
          case "vertical-flip":
            newImage[height - i - 1][j] = new PixelClass(r, g, b);
            break;
          case "horizontal-flip":
            newImage[i][width - j - 1] = new PixelClass(r, g, b);
            break;
          case "brighten":
            r = r + increment;
            g = g + increment;
            b = b + increment;

            r = over255under0(r);
            g = over255under0(g);
            b = over255under0(b);

            newImage[i][j] = new PixelClass(r, g, b);
            break;
          default:
            newImage[i][j] = new PixelClass(r, g, b);
            break;
        }
      }
    }
    this.images.put(destImageName, newImage);
  }

  protected int over255under0(int value) {
    int x = value;
    if (x > 255) {
      x = 255;
    } else if (x < 0) {
      x = 0;
    }

    return x;
  }

  /**
   * Allows for an image to be grey-scaled via the red component of each cell. Takes in an
   * imageName, and outputs an image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  @Override
  public void redComponent(String imageName, String destImageName) {
    System.out.println("In Impl Red Component");
    this.component(imageName, destImageName, "red-component", 0);
  }

  /**
   * Allows for an image to be grey-scaled via the green component of each cell. Takes in an
   * imageName, and outputs an image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  @Override
  public void greenComponent(String imageName, String destImageName) {
    this.component(imageName, destImageName, "green-component", 0);
  }

  /**
   * Allows for an image to be grey-scaled via the blue component of each cell. Takes in an
   * imageName, and outputs an image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  @Override
  public void blueComponent(String imageName, String destImageName) {
    this.component(imageName, destImageName, "blue-component", 0);
  }

  /**
   * Allows for an image to be grey-scaled via the value component of each cell. Takes in an
   * imageName, and outputs an image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  @Override
  public void value(String imageName, String destImageName) {
    this.component(imageName, destImageName, "value-component", 0);
  }

  /**
   * Allows for an image to be grey-scaled via the intensity component of each cell. Takes in an
   * imageName, and outputs an image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  @Override
  public void intensity(String imageName, String destImageName) {
    this.component(imageName, destImageName, "intensity-component", 0);
  }

  /**
   * Allows for an image to be grey-scaled via the luma component of each cell. Takes in an
   * imageName, and outputs an image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  @Override
  public void luma(String imageName, String destImageName) {
    this.component(imageName, destImageName, "luma-component", 0);
  }

  /**
   * Allows for an image to be flipped horizontally. Takes in an imageName, and outputs an
   * image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new
   *                      horizontally flipped image.
   */
  @Override
  public void horizontalFlip(String imageName, String destImageName) {
    this.component(imageName, destImageName, "horizontal-flip", 0);
  }

  /**
   * Allows for an image to be flipped vertically. Takes in an imageName, and outputs an
   * image with destImageName assigned to it.
   *
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new
   *                      horizontally flipped image.
   */
  @Override
  public void verticalFlip(String imageName, String destImageName) {
    this.component(imageName, destImageName, "vertical-flip", 0);
  }

  /**
   * Allows for an image to be brightened by the increment the client puts in. Takes in an
   * imageName and increment, and outputs an image with destImageName assigned to it.
   *
   * @param increment     Value that client wants to brighten/darken the image by.
   * @param imageName     Name of the image that we want to change.
   * @param destImageName Name of the image we use henceforth in the program of the new grey-scaled
   *                      image.
   */
  @Override
  public void brighten(Integer increment, String imageName, String destImageName) {
    this.component(imageName, destImageName, "brighten", increment);
  }

  /**
   * Gets the image size of an image by multiplying the length by the width.
   *
   * @param imageName Name of the image that you want to find the size of.
   * @return An integer representing the image size.
   */
  @Override
  public int getPictureSize(String imageName) {
    PixelClass[][] currentImage = this.images.get(imageName);
    if (currentImage == null) {
      throw new IllegalArgumentException("Cannot get size of an image that doesn't exist");
    }
    int height = currentImage.length;
    int width = currentImage[0].length;
    return height * width;
  }

  /**
   * Gets the color of a pixel in RGB format of an image the client wants.
   *
   * @param imageName Image that you want to get a pixel color at.
   * @param row       Row of specified pixel.
   * @param col       Column of specified pixel.
   * @return Returns the red, green, blue value of the pixel.
   */
  @Override
  public PixelClass getPixelColorAt(String imageName, int row, int col) {
    PixelClass[][] array = this.images.get(imageName);
    return array[row][col];
  }

  @Override
  public PixelClass[][] getImage(String imageName) {
    System.out.println(imageName);

    System.out.println(this.images.toString());

    PixelClass[][] image = this.images.get(imageName);
    //System.out.println(image.length);
    PixelClass[][] sendImage = new PixelClass[image.length][image[0].length];
    for (int x = 0; x < image.length; x += 1) {
      for (int y = 0; y < image[0].length; y += 1) {
        sendImage[x][y] = image[x][y];
      }
    }
    return sendImage;
  }
}
