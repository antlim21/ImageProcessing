package cs3500.view;

import java.util.function.Function;

import cs3500.model.PixelClass;

/**
 * GetIntensityValueFunction gets all the blue values from an image and allows the client to see in
 *     a histogram the frequency of intensity (average) values in the image.
 */
public class GetIntensityValueFunction implements Function<PixelClass,Integer> {
  @Override
  public Integer apply(PixelClass pixel) {
    return (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3;
  }
}
