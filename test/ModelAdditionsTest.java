import org.junit.Test;

import java.util.HashMap;

import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.model.PixelClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * ModelAdditionsTest tests all the new functionality that we included.
 */
public class ModelAdditionsTest {
  @Test
  public void testBadSepiaFilter() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo = new
            PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add sepia image to HashMap
    try {
      photo.sepia("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }
  }

  @Test
  public void testBadGreyScaleFilter() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo = new
            PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add greyscale image to HashMap
    try {
      photo.greyscale("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }
  }

  @Test
  public void testBadBlurFilter() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo = new
            PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add blur image to HashMap
    try {
      photo.blur("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }
  }

  @Test
  public void testBadSharpenFilter() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo = new
            PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add sharpen image to HashMap
    try {
      photo.sharpen("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }
  }

  @Test // We can create multiple images via editing it.
  public void testMultiEdits() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo = new
            PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.blueComponent("grid", "grid-blue");
    photo.greenComponent("grid", "grid-green");
    photo.redComponent("grid", "grid-red");
    photo.luma("grid", "grid-luma");
    photo.value("grid", "grid-value");
    photo.intensity("grid", "grid-intensity");
    photo.horizontalFlip("grid", "grid-hflip");
    photo.verticalFlip("grid", "grid-vflip");
    photo.brighten(10, "grid", "grid-bright");
    photo.brighten(-10, "grid", "grid-dark");
    photo.sepia("grid", "grid-sepia");
    photo.blur("grid", "grid-blur");
    photo.sharpen("grid", "grid-sharpen");
    photo.greyscale("grid", "grid-greyscale");
    assertEquals(15, sampleImages.size());


  }

  @Test //creates an edited image from an edited image (not orignal)
  public void testMultiEdits2() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo = new
            PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.blueComponent("grid", "grid-blue");
    photo.greenComponent("grid-blue", "grid-green");
    photo.redComponent("grid-green", "grid-red");
    photo.luma("grid-red", "grid-luma");
    photo.value("grid-luma", "grid-value");
    photo.intensity("grid-value", "grid-intensity");
    photo.horizontalFlip("grid-intensity", "grid-hflip");
    photo.verticalFlip("grid-hflip", "grid-vflip");
    photo.brighten(10, "grid-vflip", "grid-bright");
    photo.brighten(-10, "grid-bright", "grid-dark");
    photo.sepia("grid-dark", "grid-sepia");
    photo.blur("grid-sepia", "grid-blur");
    photo.sharpen("grid-blur", "grid-sharpen");
    photo.greyscale("grid-sharpen", "grid-greyscale");
    assertEquals(15, sampleImages.size());
  }

  @Test //reassignment test, we can edit an image and reassign it to a new one.
  public void testMultiEdits3() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo = new
            PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.blueComponent("grid", "grid-main");
    photo.horizontalFlip("grid-main", "grid-main");
    photo.verticalFlip("grid-main", "grid-main");
    photo.brighten(20, "grid-main", "grid-main");
    photo.brighten(-10, "grid-main", "grid-main");
    photo.sepia("grid-main", "grid-main");
    photo.blur("grid-main", "grid-main");
    photo.sharpen("grid-main", "grid-main");
    photo.greyscale("grid-main", "grid-main");
    assertEquals(2, sampleImages.size());
  }
}