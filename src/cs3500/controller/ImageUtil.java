package cs3500.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import cs3500.model.PixelClass;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
public class ImageUtil {

  /**
   * Read an image file in any traditional format (png, jpg, bmp) and stores the values in a 2D
   * array.
   *
   * @param filename the path of the file.
   */
  public static PixelClass[][] readConventional(String filename) {

    File file = new File(filename);
    BufferedImage image;
    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Successfully Loaded.");

    PixelClass[][] newImage = new PixelClass[image.getHeight()][image.getWidth()];

    for (int x = 0; x < newImage[0].length; x += 1) {
      for (int y = 0; y < newImage.length; y += 1) {
        int rgb = image.getRGB(x, y);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb) & 0xFF;
        newImage[y][x] = new PixelClass(r, g, b);
      }
    }

    return newImage;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static PixelClass[][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
      System.out.println("Successfully Loaded.");
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt(); //<- DO NOT DELETE, though not used it is required.

    PixelClass[][] array = new PixelClass[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        array[i][j] = new PixelClass(r, g, b);
      }
    }
    return array;
  }

  /**
   * Creates a buffered image from the current image.
   * @param currentImage The image provided that wants to be created.
   * @return Creates a buffered image from the current image.
   */
  public static BufferedImage createImage(PixelClass[][] currentImage) {
    int height = currentImage.length;
    int width = currentImage[0].length;

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
    return creation;
  }

}

