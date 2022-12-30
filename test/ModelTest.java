import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


import cs3500.model.MatrixUtil;
import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.model.PhotoshopImpl;
import cs3500.model.PixelClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Regular tests that test for good inputs for edit methods. Also tests
 * load and save.
 */
public class ModelTest {

  @Before
  public void setUp() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "Koala");
  }

  @Test
  public void testLoad() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "Koala");
    //PixelClass pixel = new photo.getPixelColorAt(0,0);
    assertEquals(101, photo.getPixelColorAt("Koala", 0, 0).getRedValue());
  }


  @Test
  public void testGetSize() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(786432, photo.getPictureSize("Koala"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSizeBad() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "Koala");
    photo.getPictureSize("NonExistentKoala");
  }

  @Test
  public void testGetColor() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    PixelClass[][] gridArray = sampleImages.get("grid");

    assertEquals(126, gridArray[0][0].getRedValue());
  }

  @Test(expected = NullPointerException.class)
  public void testGetColorBad() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    PixelClass[][] gridArray = sampleImages.get("nonExistentGrid");

    gridArray[0][0].getRedValue();
  }

  ///tests if you can upload an image. Checks if hashmap received an image and size of image.
  @Test
  public void testGoodLoad() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    assertEquals(786432, photo.getPictureSize("Koala"));
  }

  ///tests if you can upload an image. Checks if hashmap received an image and size of image.
  @Test
  public void testGoodLoad1() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("images\\grid.ppm", "Grid");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("Grid"));
  }

  @Test
  public void testBadLoad1() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\badGrid.ppm", "Grid");
    try {
      assertEquals(35, photo.getPictureSize("Grid"));
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Cannot get size of an image that doesn't exist"))) {
        fail("Incorrect " +
                "error");
      }
    }
  }

  @Test
  public void testBadLoad2() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\badKoala.ppm", "Koala");
    try {
      assertEquals(786432, photo.getPictureSize("Koala"));
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Cannot get size of an image that doesn't exist"))) {
        fail("Incorrect " +
                "error");
      }
    }
  }

  @Test
  public void testGoodSave() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("images\\grid.ppm", "Grid");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("Grid"));

    photo.save("images\\GridSaved.ppm", "Grid");
    Path p1 = Paths.get("images\\GridSaved.ppm");
    assertTrue(Files.exists(p1));
  }

  @Test
  public void testGoodSave1() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    assertEquals(786432, photo.getPictureSize("Koala"));

    photo.save("images\\KoalaSaved.ppm", "Koala");
    Path p1 = Paths.get("images\\KoalaSaved.ppm");
    assertEquals(true, Files.exists(p1));
  }

  @Test
  public void testBadSave() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("grid"));

    try {
      photo.save("images\\GridSaved", "badgrid"); //badgrid won't exist
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image does not exist yet"))) {
        fail("Incorrect " +
                "error");
      }
    }
  }

  @Test
  public void testBadSave1() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    assertEquals(786432, photo.getPictureSize("Koala"));

    try {
      photo.save("images\\KoalaSaved", "badkoala"); //badkoala won't exist
      fail("Exception was not thrown");
    } catch (IllegalArgumentException e) {
      if (!(e.getMessage().equals("Image does not exist yet"))) {
        fail("Incorrect " +
                "error");
      }
    }
  }


  @Test
  public void testSave1() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "Koala");
    photo.blueComponent("Koala", "Koala-1");
    photo.save("images\\Koala-1.ppm", "Koala-1");
    photo.load("images\\Koala-1.ppm", "Koala-2");
    assertEquals(58, photo.getPixelColorAt("Koala-2", 0, 0).getRedValue());
  }

  @Test
  public void testRed() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.redComponent("Koala", "Koala-Red");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaRedArray = sampleImages.get("Koala-Red");
    for (int x = 0; x < koalaRedArray.length; x++) {
      for (int y = 0; y < koalaRedArray[0].length; y++) {
        int r = koalaArray[x][y].getRedValue();
        assertEquals(r, koalaRedArray[x][y].getRedValue());
        assertEquals(r, koalaRedArray[x][y].getGreenValue());
        assertEquals(r, koalaRedArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testBlue() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.blueComponent("Koala", "Koala-Blue");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaBlueArray = sampleImages.get("Koala-Blue");
    for (int x = 0; x < koalaBlueArray.length; x++) {
      for (int y = 0; y < koalaBlueArray[0].length; y++) {
        int b = koalaArray[x][y].getBlueValue();
        assertEquals(b, koalaBlueArray[x][y].getRedValue());
        assertEquals(b, koalaBlueArray[x][y].getGreenValue());
        assertEquals(b, koalaBlueArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testGreen() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.greenComponent("Koala", "Koala-Green");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaGreenArray = sampleImages.get("Koala-Green");
    for (int x = 0; x < koalaGreenArray.length; x++) {
      for (int y = 0; y < koalaGreenArray[0].length; y++) {
        int g = koalaArray[x][y].getGreenValue();
        assertEquals(g, koalaGreenArray[x][y].getRedValue());
        assertEquals(g, koalaGreenArray[x][y].getGreenValue());
        assertEquals(g, koalaGreenArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testValue() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.value("Koala", "Koala-Value");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaValueArray = sampleImages.get("Koala-Value");
    for (int x = 0; x < koalaValueArray.length; x++) {
      for (int y = 0; y < koalaValueArray[0].length; y++) {
        int r = koalaArray[x][y].getRedValue();
        int b = koalaArray[x][y].getBlueValue();
        int g = koalaArray[x][y].getGreenValue();
        int max = Math.max(r, Math.max(b, g));
        assertEquals(max, koalaValueArray[x][y].getRedValue());
        assertEquals(max, koalaValueArray[x][y].getGreenValue());
        assertEquals(max, koalaValueArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testIntensity() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.intensity("Koala", "Koala-Intensity");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaIntensityArray = sampleImages.get("Koala-Intensity");
    for (int x = 0; x < koalaIntensityArray.length; x++) {
      for (int y = 0; y < koalaIntensityArray[0].length; y++) {
        int r = koalaArray[x][y].getRedValue();
        int b = koalaArray[x][y].getBlueValue();
        int g = koalaArray[x][y].getGreenValue();
        int avg = (r + b + g) / 3;
        assertEquals(avg, koalaIntensityArray[x][y].getRedValue());
        assertEquals(avg, koalaIntensityArray[x][y].getGreenValue());
        assertEquals(avg, koalaIntensityArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testLuma() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.luma("Koala", "Koala-Luma");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaLumaArray = sampleImages.get("Koala-Luma");
    for (int x = 0; x < koalaLumaArray.length; x++) {
      for (int y = 0; y < koalaLumaArray[0].length; y++) {
        int r = koalaArray[x][y].getRedValue();
        int b = koalaArray[x][y].getBlueValue();
        int g = koalaArray[x][y].getGreenValue();
        int luma = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
        assertEquals(luma, koalaLumaArray[x][y].getRedValue());
        assertEquals(luma, koalaLumaArray[x][y].getGreenValue());
        assertEquals(luma, koalaLumaArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testHorizontalFlip() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.horizontalFlip("Koala", "Koala-HorizontalFlip");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaHorizontalFlipArray = sampleImages.get("Koala-HorizontalFlip");
    for (int x = 0; x < koalaArray.length; x++) {
      for (int y = 0; y < koalaArray[0].length; y++) {
        int otherWidth = koalaArray[0].length - y - 1;
        assertEquals(koalaArray[x][otherWidth].getRedValue(),
                koalaHorizontalFlipArray[x][y].getRedValue());
        assertEquals(koalaArray[x][otherWidth].getGreenValue(),
                koalaHorizontalFlipArray[x][y].getGreenValue());
        assertEquals(koalaArray[x][otherWidth].getBlueValue(),
                koalaHorizontalFlipArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testVerticalFlip() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.verticalFlip("Koala", "Koala-VerticalFlip");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaVerticalFlipArray = sampleImages.get("Koala-VerticalFlip");
    for (int x = 0; x < koalaArray.length; x++) {
      for (int y = 0; y < koalaArray[0].length; y++) {
        int otherHeight = koalaArray.length - x - 1;
        assertEquals(koalaArray[otherHeight][y].getRedValue(),
                koalaVerticalFlipArray[x][y].getRedValue());
        assertEquals(koalaArray[otherHeight][y].getGreenValue(),
                koalaVerticalFlipArray[x][y].getGreenValue());
        assertEquals(koalaArray[otherHeight][y].getBlueValue(),
                koalaVerticalFlipArray[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testBrighten() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.brighten(12, "Koala", "Koala-Brighten12");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaBrighten12Array = sampleImages.get("Koala-Brighten12");
    for (int x = 0; x < koalaArray.length; x++) {
      for (int y = 0; y < koalaArray[0].length; y++) {

        int r = koalaArray[x][y].getRedValue() + 12;
        int g = koalaArray[x][y].getGreenValue() + 12;
        int b = koalaArray[x][y].getBlueValue() + 12;

        if (r > 255) {
          r = 255;
        } else if (r < 0) {
          r = 0;
        }

        if (g > 255) {
          g = 255;
        } else if (g < 0) {
          g = 0;
        }

        if (b > 255) {
          b = 255;
        } else if (b < 0) {
          b = 0;
        }
        assertEquals(r, koalaBrighten12Array[x][y].getRedValue());
        assertEquals(g, koalaBrighten12Array[x][y].getGreenValue());
        assertEquals(b, koalaBrighten12Array[x][y].getBlueValue());
      }
    }
    photo.brighten(-15, "Koala", "Koala-BrightenNeg15");
    koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaBrightenNeg15Array = sampleImages.get("Koala-BrightenNeg15");
    for (int x = 0; x < koalaArray.length; x++) {
      for (int y = 0; y < koalaArray[0].length; y++) {

        int r = koalaArray[x][y].getRedValue() - 15;
        int g = koalaArray[x][y].getGreenValue() - 15;
        int b = koalaArray[x][y].getBlueValue() - 15;

        if (r > 255) {
          r = 255;
        } else if (r < 0) {
          r = 0;
        }

        if (g > 255) {
          g = 255;
        } else if (g < 0) {
          g = 0;
        }

        if (b > 255) {
          b = 255;
        } else if (b < 0) {
          b = 0;
        }
        assertEquals(r, koalaBrightenNeg15Array[x][y].getRedValue());
        assertEquals(g, koalaBrightenNeg15Array[x][y].getGreenValue());
        assertEquals(b, koalaBrightenNeg15Array[x][y].getBlueValue());
      }
    }
    photo.brighten(0, "Koala", "Koala-Brighten0");
    koalaArray = sampleImages.get("Koala");
    PixelClass[][] koalaBrighten0Array = sampleImages.get("Koala-Brighten0");
    for (int x = 0; x < koalaArray.length; x++) {
      for (int y = 0; y < koalaArray[0].length; y++) {

        int r = koalaArray[x][y].getRedValue();
        int g = koalaArray[x][y].getGreenValue();
        int b = koalaArray[x][y].getBlueValue();

        if (r > 255) {
          r = 255;
        } else if (r < 0) {
          r = 0;
        }

        if (g > 255) {
          g = 255;
        } else if (g < 0) {
          g = 0;
        }

        if (b > 255) {
          b = 255;
        } else if (b < 0) {
          b = 0;
        }
        assertEquals(r, koalaBrighten0Array[x][y].getRedValue());
        assertEquals(g, koalaBrighten0Array[x][y].getGreenValue());
        assertEquals(b, koalaBrighten0Array[x][y].getBlueValue());
      }
    }
  }


  @Test
  public void testLoadSaveReload() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "KoalaLoaded");
    photo.save("images\\KoalaLoaded.ppm", "KoalaLoaded");
    photo.load("images\\KoalaLoaded.ppm", "Koala-2");
    assertEquals(786432, photo.getPictureSize("Koala-2"));
  }

  @Test
  public void testMultipleCombos() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "KoalaLoaded");
    photo.blueComponent("KoalaLoaded", "KoalaLoadedBlue");
    photo.horizontalFlip("KoalaLoadedBlue", "KoalaLoadedBlueHori");
    photo.save("images/KoalaLoadedBlueHori.ppm", "KoalaLoadedBlueHori");
    assertEquals(786432, photo.getPictureSize("KoalaLoadedBlueHori"));
  }

  @Test
  public void testBlueComponent() {
    PhotoshopImpl photo = new PhotoshopImpl();
    photo.load("images\\Koala.ppm", "Koala");
    //assertEquals(101,photo.getPixelColorAt("Koala",0,0).getRedValue());
    photo.blueComponent("Koala", "Koala-1");
    //assertEquals(786432,photo.getPictureSize("Koala-1"));
    assertEquals(58, photo.getPixelColorAt("Koala-1", 0, 0).getRedValue());
    assertEquals(58, photo.getPixelColorAt("Koala-1", 0, 0).getGreenValue());
  }

  @Test
  public void testUploadMultiple() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.load("images\\grid.ppm", "grid");
    assertEquals(2, sampleImages.size());
  }

  @Test
  public void testUploadSameImage() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "Koala");
    assertEquals(1, sampleImages.size());
    photo.load("images\\Koala.ppm", "Koala-1");
    assertEquals(2, sampleImages.size());
  }

  @Test
  public void testUploadToSameName() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\Koala.ppm", "imageName");
    assertEquals(1, sampleImages.size());
    photo.load("images\\grid.ppm", "imageName");
    assertEquals(1, sampleImages.size());
  }

  @Test // We can create multiple images via editing it.
  public void testMultiEdits() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
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
    assertEquals(11, sampleImages.size());


  }

  @Test //creates an edited image from an edited image (not orignal)
  public void testMultiEdits2() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
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
    assertEquals(11, sampleImages.size());
  }

  @Test //reassignment test, we can edit an image and reassign it to a new one.
  public void testMultiEdits3() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    photo.load("images\\grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.blueComponent("grid", "grid-main");
    photo.horizontalFlip("grid-main", "grid-main");
    photo.verticalFlip("grid-main", "grid-main");
    photo.brighten(20, "grid-main", "grid-main");
    photo.brighten(-10, "grid-main", "grid-main");
    assertEquals(2, sampleImages.size());
    assertEquals(29, photo.getPixelColorAt("grid-main", 0, 0).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 0, 0).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 0, 0).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 0, 1).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 0, 1).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 0, 1).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 0, 2).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 0, 2).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 0, 2).getBlueValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 0, 3).getRedValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 0, 3).getGreenValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 0, 3).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 0, 4).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 0, 4).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 0, 4).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 1, 0).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 1, 0).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 1, 0).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 1, 1).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 1, 1).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 1, 1).getBlueValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 1, 2).getRedValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 1, 2).getGreenValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 1, 2).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 1, 3).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 1, 3).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 1, 3).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 1, 4).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 1, 4).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 1, 4).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 2, 0).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 2, 0).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 2, 0).getBlueValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 2, 1).getRedValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 2, 1).getGreenValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 2, 1).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 2, 2).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 2, 2).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 2, 2).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 2, 3).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 2, 3).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 2, 3).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 2, 4).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 2, 4).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 2, 4).getBlueValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 3, 0).getRedValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 3, 0).getGreenValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 3, 0).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 3, 1).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 3, 1).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 3, 1).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 3, 2).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 3, 2).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 3, 2).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 3, 3).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 3, 3).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 3, 3).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 3, 4).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 3, 4).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 3, 4).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 4, 0).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 4, 0).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 4, 0).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 4, 1).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 4, 1).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 4, 1).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 4, 2).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 4, 2).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 4, 2).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 4, 3).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 4, 3).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 4, 3).getBlueValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 4, 4).getRedValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 4, 4).getGreenValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 4, 4).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 5, 0).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 5, 0).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 5, 0).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 5, 1).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 5, 1).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 5, 1).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 5, 2).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 5, 2).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 5, 2).getBlueValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 5, 3).getRedValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 5, 3).getGreenValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 5, 3).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 5, 4).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 5, 4).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 5, 4).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 6, 0).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 6, 0).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 6, 0).getBlueValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 6, 1).getRedValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 6, 1).getGreenValue());
    assertEquals(175, photo.getPixelColorAt("grid-main", 6, 1).getBlueValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 6, 2).getRedValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 6, 2).getGreenValue());
    assertEquals(10, photo.getPixelColorAt("grid-main", 6, 2).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 6, 3).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 6, 3).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 6, 3).getBlueValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 6, 4).getRedValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 6, 4).getGreenValue());
    assertEquals(29, photo.getPixelColorAt("grid-main", 6, 4).getBlueValue());
  }

  @Test
  public void testGreyscale() {
    double[][] luma = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.greyscale("grid", "grid-Greyscale");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("grid");
    PixelClass[][] koalaGreyscale = sampleImages.get("grid-Greyscale");
    for (int x = 0; x < koalaArray.length; x++) {
      for (int y = 0; y < koalaArray[0].length; y++) {
        double[][] currentRGB = {
                {koalaArray[x][y].getRedValue()},
                {koalaArray[x][y].getGreenValue()},
                {koalaArray[x][y].getBlueValue()}
        };
        int[][] newRGB = MatrixUtil.multiply(luma, currentRGB);
        assertEquals(newRGB[0][0], koalaGreyscale[x][y].getRedValue(), 0.01);
        assertEquals(newRGB[1][0], koalaGreyscale[x][y].getGreenValue(), 0.01);
        assertEquals(newRGB[2][0], koalaGreyscale[x][y].getBlueValue(), 0.01);
      }
    }
  }

  @Test
  public void testSepia() {
    double[][] sepia = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.sepia("grid", "grid-sepia");
    assertEquals(2, sampleImages.size());
    PixelClass[][] koalaArray = sampleImages.get("grid");
    PixelClass[][] koalaGreyscale = sampleImages.get("grid-sepia");
    for (int x = 0; x < koalaArray.length; x++) {
      for (int y = 0; y < koalaArray[0].length; y++) {
        double[][] currentRGB = {
                {koalaArray[x][y].getRedValue()},
                {koalaArray[x][y].getGreenValue()},
                {koalaArray[x][y].getBlueValue()}
        };
        int[][] newRGB = MatrixUtil.multiply(sepia, currentRGB);
        assertEquals(newRGB[0][0], koalaGreyscale[x][y].getRedValue(), 0.01);
        assertEquals(newRGB[1][0], koalaGreyscale[x][y].getGreenValue(), 0.01);
        assertEquals(newRGB[2][0], koalaGreyscale[x][y].getBlueValue(), 0.01);
      }
    }
  }

  @Test
  public void testBlur() {
    double[][] blurKernel = {
            {(1.0 / 16.0), (1.0 / 8.0), (1.0 / 16.0)},
            {(1.0 / 8.0), (1.0 / 4.0), (1.0 / 8.0)},
            {(1.0 / 16.0), (1.0 / 8.0), (1.0 / 16.0)}
    };
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.blur("grid", "grid-blur");
    assertEquals(2, sampleImages.size());
    PixelClass[][] gridArray = sampleImages.get("grid");
    PixelClass[][] gridBlur = sampleImages.get("grid-blur");
    for (int x = 0; x < gridArray.length; x++) {
      for (int y = 0; y < gridArray[0].length; y++) {
        double[][] currentRGB = {
                {gridArray[x][y].getRedValue()},
                {gridArray[x][y].getGreenValue()},
                {gridArray[x][y].getBlueValue()}
        };
        double[][] pixelRed = new double[gridArray.length][gridArray[0].length];
        double[][] pixelGreen = new double[gridArray.length][gridArray[0].length];
        double[][] pixelBlue = new double[gridArray.length][gridArray[0].length];
        for (int xCounter = 0; xCounter < gridArray.length; xCounter += 1) {
          for (int yCounter = 0; yCounter < gridArray[0].length; yCounter += 1) {
            pixelRed[xCounter][yCounter] = gridArray[xCounter][yCounter].getRedValue();
            pixelGreen[xCounter][yCounter] = gridArray[xCounter][yCounter].getGreenValue();
            pixelBlue[xCounter][yCounter] = gridArray[xCounter][yCounter].getBlueValue();
          }
        }
        int[][] newRGB = {
                {MatrixUtil.filter(blurKernel, pixelRed, x, y)},
                {MatrixUtil.filter(blurKernel, pixelGreen, x, y)},
                {MatrixUtil.filter(blurKernel, pixelBlue, x, y)}
        };
        assertEquals(newRGB[0][0], gridBlur[x][y].getRedValue());
        assertEquals(newRGB[1][0], gridBlur[x][y].getGreenValue());
        assertEquals(newRGB[2][0], gridBlur[x][y].getBlueValue());
      }
    }
  }

  @Test
  public void testSharpen() {
    double[][] sharpenKernel = {
            {(-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0)},
            {(-1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), (-1.0 / 8.0)},
            {(-1.0 / 8.0), (1.0 / 4.0), 1.0, (1.0 / 4.0), (-1.0 / 8.0)},
            {(-1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), (-1.0 / 8.0)},
            {(-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0), (-1.0 / 8.0)},
    };
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid.ppm", "grid");
    assertEquals(1, sampleImages.size());
    photo.sharpen("grid", "grid-sharpen");
    assertEquals(2, sampleImages.size());
    PixelClass[][] gridArray = sampleImages.get("grid");
    PixelClass[][] gridSharpen = sampleImages.get("grid-sharpen");
    for (int x = 0; x < gridArray.length; x++) {
      for (int y = 0; y < gridArray[0].length; y++) {
        double[][] currentRGB = {
                {gridArray[x][y].getRedValue()},
                {gridArray[x][y].getGreenValue()},
                {gridArray[x][y].getBlueValue()}
        };
        double[][] pixelRed = new double[gridArray.length][gridArray[0].length];
        double[][] pixelGreen = new double[gridArray.length][gridArray[0].length];
        double[][] pixelBlue = new double[gridArray.length][gridArray[0].length];
        for (int xCounter = 0; xCounter < gridArray.length; xCounter += 1) {
          for (int yCounter = 0; yCounter < gridArray[0].length; yCounter += 1) {
            pixelRed[xCounter][yCounter] = gridArray[xCounter][yCounter].getRedValue();
            pixelGreen[xCounter][yCounter] = gridArray[xCounter][yCounter].getGreenValue();
            pixelBlue[xCounter][yCounter] = gridArray[xCounter][yCounter].getBlueValue();
          }
        }
        int[][] newRGB = {
                {MatrixUtil.filter(sharpenKernel, pixelRed, x, y)},
                {MatrixUtil.filter(sharpenKernel, pixelGreen, x, y)},
                {MatrixUtil.filter(sharpenKernel, pixelBlue, x, y)}
        };
        assertEquals(newRGB[0][0], gridSharpen[x][y].getRedValue());
        assertEquals(newRGB[1][0], gridSharpen[x][y].getGreenValue());
        assertEquals(newRGB[2][0], gridSharpen[x][y].getBlueValue());
      }
    }
  }

}
