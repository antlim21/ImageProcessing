
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import cs3500.Photoshop;
import cs3500.controller.PhotoshopController;
import cs3500.controller.PhotoshopTextFileControllerImpl;
import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.model.PixelClass;
import cs3500.view.PhotoshopViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests file upload.
 */

public class ScriptTest {

  @Test
  public void testGoodLoadFile() throws IOException {
    Photoshop.main(new String[]{"res/script1.txt"});
    FileReader reader1 = new FileReader("res/script1.txt");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    controller1.runPhotoshop();
    assertEquals(15, images1.size());

    assertEquals("Successfully Loaded.\n" +
                    "Successfully Loaded.\n" +
                    "Successfully Loaded.\n" +
                    "Successfully Loaded.\n" +
                    "Successfully grey-scaled via red component \n" +
                    "Successfully grey-scaled via blue component \n" +
                    "Successfully grey-scaled via green component \n" +
                    "Successfully grey-scaled via luma component \n" +
                    "Successfully grey-scaled via value component \n" +
                    "Successfully grey-scaled via intensity component \n" +
                    "Successfully brightened \n" +
                    "Successfully brightened \n" +
                    "Successful horizontal flip \n" +
                    "Successfully flipped image vertically \n" +
                    "Blur successful\n" +
                    "Successfully sharpened image \n" +
                    "Successfully applied sepia filter onto image \n" +
                    "Successfully grey-scaled \n" +
                    "in try catch\n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "in try catch\n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "in try catch\n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "in try catch\n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image \n" +
                    "Successfully saved image",
            model1.toString());
  }

  @Test
  public void testBadLoadFile() throws IOException {
    Photoshop.main(new String[]{"res/badScript.txt"});
    FileReader reader1 = new FileReader("res/badScript.txt");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    controller1.runPhotoshop();
    assertEquals(4, images1.size());

    assertEquals("Successfully Loaded.\n" +
                    "Successfully Loaded.\n" +
                    "Successfully Loaded.\n" +
                    "Successfully Loaded.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image you are trying to change does not exist yet.\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n" +
                    "Image does not exist yet\n",
            model1.toString());
  }
}



