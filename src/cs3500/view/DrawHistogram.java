package cs3500.view;


import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.JPanel;


import cs3500.model.HistogramUtil;
import cs3500.model.PixelClass;

/**
 * DrawHistogram takes in the red, green, blue, and intensity values in order to construct a
 *     histogram.
 */
public class DrawHistogram extends JPanel {
  private int[] redValues;
  private int[] greenValues;
  private int[] blueValues;
  private int[] intensityValues;

  DrawHistogram() {
    paintOverHistogram(new PixelClass[][]{});
  }

  DrawHistogram(PixelClass[][] image) {
    paintOverHistogram(image);
  }

  /**
   * Given an image, it will retrieve the red, green, blue, and intensity (average) values to
   *     create the histogram with.
   * @param image The image that the histogram is going to get the color frequncies from.
   */
  public void paintOverHistogram(PixelClass[][] image) {
    redValues = HistogramUtil.getFrequencies(image, new GetRedValueFunction());
    greenValues = HistogramUtil.getFrequencies(image, new GetGreenValueFunction());
    blueValues = HistogramUtil.getFrequencies(image, new GetBlueValueFunction());
    intensityValues = HistogramUtil.getFrequencies(image, new GetIntensityValueFunction());
  }

  @Override
  public void paintComponent(Graphics g) {


    for (int x = 0; x < 255; x += 1) {
      ArrayList<Integer> values = new ArrayList<Integer>();
      values.add(1);
      values.add(2);
      values.add(3);
      values.add(4);

      double max = Collections.max(values);


      g.setColor(Color.RED);
      int redY = redValues[x] / 300;
      if (redY > 90) {
        redY = 90;
      }
      g.fillRect(x, 100 - redY, 10, redY);
      if (redValues[x] > 0) {
        //System.out.println("" + x + " Frequency: " + redValues[x]);
      }

      int greenY = greenValues[x] / 300;
      if (greenY > 90) {
        greenY = 90;
      }
      g.setColor(Color.GREEN);
      g.fillRect(x, 200 - greenY, 10, greenY);
      if (greenValues[x] > 0) {
        //System.out.println("" + x + " Frequency: " + greenValues[x]);
      }

      int blueY = blueValues[x] / 300;
      if (blueY > 90) {
        blueY = 90;
      }
      g.setColor(Color.BLUE);
      g.fillRect(x, 300 - blueY, 10, blueY);
      if (blueValues[x] > 0) {
        //System.out.println("" + x + " Frequency: " + blueValues[x]);
      }

      int intensityY = intensityValues[x] / 300;
      if (intensityY > 90) {
        intensityY = 90;
      }
      g.setColor(Color.BLACK);
      g.fillRect(x, 400 - intensityY, 10, intensityY);
      if (intensityValues[x] > 0) {
        //System.out.println("" + x + " Frequency: " + intensityValues[x]);
      }
    }
  }
}
