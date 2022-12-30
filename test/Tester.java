import org.junit.Test;

import java.util.HashMap;

import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.model.PixelClass;

import static org.junit.Assert.assertEquals;

/**
 * Tester was used to test if greyscale, sharpen, sepia, and blur works.
 */
public class Tester {
  @Test
  public void testGoodSaveLoadingPNGToAllFormats() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("res/grid.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("Koala"));

    photo.sepia("Koala", "koala-blur");
    assertEquals(2, sampleImages.size());
    photo.save("images/gridsharp.png", "koala-blur");


  }
}
