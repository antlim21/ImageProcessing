import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.model.PhotoshopImpl;
import cs3500.model.PixelClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * ConversionTest tests whether you can load any file format and save to
 * any file format.
 */
public class ConversionTest {

  @Test
  public void testLoadGridPNG() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid-PNG/grid.png", "Grid");
    assertEquals(35, photo.getPictureSize("Grid"));
    assertEquals(126, photo.getPixelColorAt("Grid", 0, 0).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 0, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 0, 0).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 0, 1).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 0, 1).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 0, 1).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 0, 2).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 0, 2).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 0, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 0, 3).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 0, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 0, 3).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 0, 4).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 0, 4).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 0, 4).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 1, 0).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 1, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 1, 0).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 1, 1).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 1, 1).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 1, 1).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 1, 2).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 1, 2).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 1, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 1, 3).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 1, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 1, 3).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 1, 4).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 1, 4).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 1, 4).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 2, 0).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 2, 0).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 2, 0).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 2, 1).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 2, 1).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 2, 1).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 2, 2).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 2, 2).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 2, 2).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 2, 3).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 2, 3).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 2, 3).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 2, 4).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 2, 4).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 2, 4).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 3, 0).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 3, 0).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 3, 0).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 3, 1).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 3, 1).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 3, 1).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 3, 2).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 3, 2).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 3, 2).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 3, 3).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 3, 3).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 3, 3).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 3, 4).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 3, 4).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 3, 4).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 4, 0).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 4, 0).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 4, 0).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 4, 1).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 4, 1).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 4, 1).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 4, 2).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 4, 2).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 4, 2).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 4, 3).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 4, 3).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 4, 3).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 4, 4).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 4, 4).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 4, 4).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 5, 0).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 5, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 5, 0).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 5, 1).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 5, 1).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 5, 1).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 5, 2).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 5, 2).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 5, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 5, 3).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 5, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 5, 3).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 5, 4).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 5, 4).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 5, 4).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 6, 0).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 6, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 6, 0).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 6, 1).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 6, 1).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 6, 1).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 6, 2).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 6, 2).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 6, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 6, 3).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 6, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 6, 3).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 6, 4).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 6, 4).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 6, 4).getBlueValue());
  }

  @Test
  public void testLoadGridBMP() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid-BMP/grid.bmp", "Grid");
    assertEquals(35, photo.getPictureSize("Grid"));
    assertEquals(126, photo.getPixelColorAt("Grid", 0, 0).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 0, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 0, 0).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 0, 1).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 0, 1).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 0, 1).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 0, 2).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 0, 2).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 0, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 0, 3).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 0, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 0, 3).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 0, 4).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 0, 4).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 0, 4).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 1, 0).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 1, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 1, 0).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 1, 1).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 1, 1).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 1, 1).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 1, 2).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 1, 2).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 1, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 1, 3).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 1, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 1, 3).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 1, 4).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 1, 4).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 1, 4).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 2, 0).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 2, 0).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 2, 0).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 2, 1).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 2, 1).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 2, 1).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 2, 2).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 2, 2).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 2, 2).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 2, 3).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 2, 3).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 2, 3).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 2, 4).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 2, 4).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 2, 4).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 3, 0).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 3, 0).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 3, 0).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 3, 1).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 3, 1).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 3, 1).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 3, 2).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 3, 2).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 3, 2).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 3, 3).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 3, 3).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 3, 3).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 3, 4).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 3, 4).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 3, 4).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 4, 0).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 4, 0).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 4, 0).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 4, 1).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 4, 1).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 4, 1).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 4, 2).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 4, 2).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 4, 2).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 4, 3).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 4, 3).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 4, 3).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 4, 4).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 4, 4).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 4, 4).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 5, 0).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 5, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 5, 0).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 5, 1).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 5, 1).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 5, 1).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 5, 2).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 5, 2).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 5, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 5, 3).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 5, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 5, 3).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 5, 4).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 5, 4).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 5, 4).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 6, 0).getRedValue());
    assertEquals(204, photo.getPixelColorAt("Grid", 6, 0).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 6, 0).getBlueValue());
    assertEquals(158, photo.getPixelColorAt("Grid", 6, 1).getRedValue());
    assertEquals(199, photo.getPixelColorAt("Grid", 6, 1).getGreenValue());
    assertEquals(0, photo.getPixelColorAt("Grid", 6, 1).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 6, 2).getRedValue());
    assertEquals(57, photo.getPixelColorAt("Grid", 6, 2).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 6, 2).getBlueValue());
    assertEquals(52, photo.getPixelColorAt("Grid", 6, 3).getRedValue());
    assertEquals(163, photo.getPixelColorAt("Grid", 6, 3).getGreenValue());
    assertEquals(165, photo.getPixelColorAt("Grid", 6, 3).getBlueValue());
    assertEquals(126, photo.getPixelColorAt("Grid", 6, 4).getRedValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 6, 4).getGreenValue());
    assertEquals(19, photo.getPixelColorAt("Grid", 6, 4).getBlueValue());
  }

  @Test //TA Matthew Love told us that due to the nature of how JPG/ImageIO compresses images,
  // it will change the RGB values of the pixel, which is why it does not
  public void testLoadGridJPG() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface photo =
            new PhotoshopAdditionalOperationsImpl(sampleImages);
    photo.load("res/grid-JPG/grid.jpg", "Grid");
    assertEquals(35, photo.getPictureSize("Grid"));
  }

  @Test
  public void testGoodSaveLoadingPPMToAllFormats() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("images/grid.ppm", "Grid");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("Grid"));

    photo.save("images/GridSavedFromPPM.ppm", "Grid");
    Path p1 = Paths.get("images/GridSavedFromPPM.ppm");
    assertTrue(Files.exists(p1));

    photo.save("images/GridSavedFromPPM.jpg", "Grid");
    Path p2 = Paths.get("images/GridSavedFromPPM.jpg");
    assertTrue(Files.exists(p2));

    photo.save("images/GridSavedFromPPM.bmp", "Grid");
    Path p3 = Paths.get("images/GridSavedFromPPM.bmp");
    assertTrue(Files.exists(p3));

    photo.save("images/GridSavedFromPPM.png", "Grid");
    Path p4 = Paths.get("images/GridSavedFromPPM.png");
    assertTrue(Files.exists(p4));
  }

  @Test
  public void testGoodSaveLoadingJPGToAllFormats() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("res/grid-JPG/grid.jpg", "Grid");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("Grid"));

    photo.save("images/GridSavedFromJPG.ppm", "Grid");
    Path p1 = Paths.get("images/GridSavedFromJPG.ppm");
    assertTrue(Files.exists(p1));

    photo.save("images/GridSavedFromJPG.jpg", "Grid");
    Path p2 = Paths.get("images/GridSavedFromJPG.jpg");
    assertTrue(Files.exists(p2));

    photo.save("images/GridSavedFromJPG.bmp", "Grid");
    Path p3 = Paths.get("images/GridSavedFromJPG.bmp");
    assertTrue(Files.exists(p3));

    photo.save("images/GridSavedFromJPG.png", "Grid");
    Path p4 = Paths.get("images/GridSavedFromJPG.png");
    assertTrue(Files.exists(p4));
  }

  @Test
  public void testGoodSaveLoadingBMPToAllFormats() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("res/grid-BMP/grid.bmp", "Grid");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("Grid"));

    photo.save("images/GridSavedFromBMP.ppm", "Grid");
    Path p1 = Paths.get("images/GridSavedFromBMP.ppm");
    assertTrue(Files.exists(p1));

    photo.save("images/GridSavedFromBMP.jpg", "Grid");
    Path p2 = Paths.get("images/GridSavedFromBMP.jpg");
    assertTrue(Files.exists(p2));

    photo.save("images/GridSavedFromBMP.bmp", "Grid");
    Path p3 = Paths.get("images/GridSavedFromBMP.bmp");
    assertTrue(Files.exists(p3));

    photo.save("images/GridSavedFromBMP.png", "Grid");
    Path p4 = Paths.get("images/GridSavedFromBMP.png");
    assertTrue(Files.exists(p4));
  }

  @Test
  public void testGoodSaveLoadingPNGToAllFormats() {
    HashMap<String, PixelClass[][]> sampleImages = new HashMap<String, PixelClass[][]>();
    PhotoshopImpl photo = new PhotoshopImpl(sampleImages);
    assertEquals(0, sampleImages.size());
    photo.load("res/grid-PNG/grid.png", "Grid");
    assertEquals(1, sampleImages.size());
    assertEquals(35, photo.getPictureSize("Grid"));

    photo.save("images/GridSavedFromPNG.ppm", "Grid");
    Path p1 = Paths.get("images/GridSavedFromPNG.ppm");
    assertTrue(Files.exists(p1));

    photo.save("images/GridSavedFromPNG.jpg", "Grid");
    Path p2 = Paths.get("images/GridSavedFromPNG.jpg");
    assertTrue(Files.exists(p2));

    photo.save("images/GridSavedFromPNG.bmp", "Grid");
    Path p3 = Paths.get("images/GridSavedFromPNG.bmp");
    assertTrue(Files.exists(p3));

    photo.save("images/GridSavedFromPNG.png", "Grid");
    Path p4 = Paths.get("images/GridSavedFromPNG.png");
    assertTrue(Files.exists(p4));
  }


}
