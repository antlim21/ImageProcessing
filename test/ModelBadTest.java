import org.junit.Test;

import java.util.HashMap;

import cs3500.model.PhotoshopImpl;
import cs3500.model.PixelClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests bad inputs for the edit methods.
 */
public class ModelBadTest {

  @Test
  public void testBadRedComponent() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.redComponent("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadBlueComponent() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.blueComponent("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadGreenComponent() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.greenComponent("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadLumaComponent() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.luma("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadValueComponent() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.value("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadIntensityComponent() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.intensity("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadHorizontalFlip() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.horizontalFlip("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadVerticalFlip() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.verticalFlip("nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadBrighten() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.brighten(10, "nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }

  @Test
  public void testBadDarken() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());

    // Tries to create and add grey scaled image to HashMap
    try {
      photo.brighten(-10, "nonExistentGrid", "badgrid");
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image you are trying to change does not exist yet."))) {
        fail("Incorrect " +
                "error");
      }
    }

    assertEquals(1, sampleImages.size()); //grey-scaled image doesn't exist in HashMap
  }
}
