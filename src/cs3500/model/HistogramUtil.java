package cs3500.model;

import java.util.function.Function;

/**
 * HistogramUtil holds the methods that allows fot the histogram to retrieve the frequency of values
 *     in an image.
 */
public class HistogramUtil {

  /**
   * Given an image, retrieves the frequency of the red, green, and blue from the image.
   * @param image The image that the frequencies of the histogram is measured from.
   * @param func The function that is applied onto the image.
   * @return A list of integers representing the results from getting frequency of a color from
   *             an image.
   */
  public static int[] getFrequencies(PixelClass[][] image, Function<PixelClass, Integer> func) {
    int[] results = new int[256];
    for (int counter = 0; counter < results.length; counter += 1) {
      results[counter] = 0;
    }
    for (int x = 0; x < image.length; x += 1) {
      for (int y = 0; y < image[0].length; y += 1) {
        int value = func.apply(image[x][y]);
        results[value] += 1;
      }
    }
    return results;

  }
}
