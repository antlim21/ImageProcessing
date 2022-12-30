import org.junit.Test;


import cs3500.model.HistogramUtil;
import cs3500.model.PixelClass;
import cs3500.view.GetBlueValueFunction;
import cs3500.view.GetGreenValueFunction;
import cs3500.view.GetIntensityValueFunction;
import cs3500.view.GetRedValueFunction;

import static org.junit.Assert.assertEquals;

/**
 * java doc
 */
public class HistogramUtilTest {



  @Test
  public void testGetFrequenciesGood() {
    PixelClass[][] image = new PixelClass[5][5];
    for (int x = 0; x < 5; x += 1) {
      for (int y = 0; y < 5; y += 1) {

        int r = x + y;
        int g = x * y;
        int b = 10;

        PixelClass pixel = new PixelClass(r, g, b);
        image[x][y] = pixel;

      }
    }
    int[] reds = HistogramUtil.getFrequencies(image, new GetRedValueFunction());
    int[] greens = HistogramUtil.getFrequencies(image, new GetGreenValueFunction());
    int[] blues = HistogramUtil.getFrequencies(image, new GetBlueValueFunction());
    int[] intensities = HistogramUtil.getFrequencies(image, new GetIntensityValueFunction());
    assertEquals(25, blues[10]);
    assertEquals(0, blues[255]);
    assertEquals(0, blues[0]);
    assertEquals(4, reds[5]);
    assertEquals(1, reds[8]);
    assertEquals(1, reds[0]);
    assertEquals(3, greens[4]);
    assertEquals(1, greens[16]);
    assertEquals(9, greens[0]);
    assertEquals(7, intensities[4]);
    assertEquals(0, intensities[16]);
    assertEquals(0, intensities[0]);

  }

  @Test
  public void testGetFrequenciesEdgeCases1() {
    PixelClass[][] image = new PixelClass[0][0];
    int[] reds = HistogramUtil.getFrequencies(image, new GetRedValueFunction());
    int[] greens = HistogramUtil.getFrequencies(image, new GetGreenValueFunction());
    int[] blues = HistogramUtil.getFrequencies(image, new GetBlueValueFunction());
    int[] intensities = HistogramUtil.getFrequencies(image, new GetIntensityValueFunction());
    assertEquals(0, blues[10]);
    assertEquals(0, blues[255]);
    assertEquals(0, blues[0]);
    assertEquals(0, reds[5]);
    assertEquals(0, reds[8]);
    assertEquals(0, reds[0]);
    assertEquals(0, greens[4]);
    assertEquals(0, greens[16]);
    assertEquals(0, greens[0]);
    assertEquals(0, intensities[4]);
    assertEquals(0, intensities[16]);
    assertEquals(0, intensities[0]);

  }

  @Test
  public void testGetFrequenciesEdgeCases2() {
    PixelClass[][] image = new PixelClass[5][5];
    for (int x = 0; x < 5; x += 1) {
      for (int y = 0; y < 5; y += 1) {

        int r = 10;
        int g = 10;
        int b = 10;

        PixelClass pixel = new PixelClass(r, g, b);
        image[x][y] = pixel;

      }
    }
    int[] reds = HistogramUtil.getFrequencies(image, new GetRedValueFunction());
    int[] greens = HistogramUtil.getFrequencies(image, new GetGreenValueFunction());
    int[] blues = HistogramUtil.getFrequencies(image, new GetBlueValueFunction());
    int[] intensities = HistogramUtil.getFrequencies(image, new GetIntensityValueFunction());
    assertEquals(25, blues[10]);
    assertEquals(0, blues[255]);
    assertEquals(0, blues[0]);
    assertEquals(25, reds[10]);
    assertEquals(0, reds[8]);
    assertEquals(0, reds[0]);
    assertEquals(25, greens[10]);
    assertEquals(0, greens[16]);
    assertEquals(0, greens[0]);
    assertEquals(25, intensities[10]);
    assertEquals(0, intensities[16]);
    assertEquals(0, intensities[0]);

  }

  @Test
  public void testGetFrequenciesEdgeCases3() {
    PixelClass[][] image = new PixelClass[5][5];
    for (int x = 0; x < 5; x += 1) {
      for (int y = 0; y < 5; y += 1) {

        int r = 10;
        int g = 11;
        int b = 12;

        PixelClass pixel = new PixelClass(r, g, b);
        image[x][y] = pixel;

      }
    }
    int[] reds = HistogramUtil.getFrequencies(image, new GetRedValueFunction());
    int[] greens = HistogramUtil.getFrequencies(image, new GetGreenValueFunction());
    int[] blues = HistogramUtil.getFrequencies(image, new GetBlueValueFunction());
    int[] intensities = HistogramUtil.getFrequencies(image, new GetIntensityValueFunction());
    assertEquals(25, blues[12]);
    assertEquals(0, blues[255]);
    assertEquals(0, blues[0]);
    assertEquals(25, reds[10]);
    assertEquals(0, reds[8]);
    assertEquals(0, reds[0]);
    assertEquals(25, greens[11]);
    assertEquals(0, greens[16]);
    assertEquals(0, greens[0]);
    assertEquals(25, intensities[11]);
    assertEquals(0, intensities[16]);
    assertEquals(0, intensities[0]);

  }


}
