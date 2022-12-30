package cs3500.view;

import java.util.function.Function;

import cs3500.model.PixelClass;

/**
 * GetRedValueFunction gets all the blue values from an image and allows the client to see in a
 *     histogram the frequency of red values in the image.
 */
public class GetRedValueFunction implements Function<PixelClass, Integer> {
  @Override
  public Integer apply(PixelClass pixel) {
    return pixel.getRedValue();
  }
}
