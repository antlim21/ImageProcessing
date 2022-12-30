package cs3500.view;

import java.util.function.Function;

import cs3500.model.PixelClass;

/**
 * GetGreenValueFunction gets all the blue values from an image and allows the client to see in a
 *     histogram the frequency of green values in the image.
 */
public class GetGreenValueFunction implements Function<PixelClass,Integer> {
  @Override
  public Integer apply(PixelClass pixel) {
    return pixel.getGreenValue();
  }
}
