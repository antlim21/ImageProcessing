import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import cs3500.controller.ImageUtil;
import cs3500.controller.PhotoshopController;
import cs3500.controller.PhotoshopTextFileControllerImpl;
import cs3500.model.MockPhotoshopModel;
import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.model.PixelClass;
import cs3500.view.PhotoshopViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the controller for photoshop.
 */


public class ControllerTest {

  private PhotoshopController failedController;

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions() {
    failedController = new PhotoshopTextFileControllerImpl(new PhotoshopAdditionalOperationsImpl(),
            new PhotoshopViewImpl(new StringBuilder()), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions1() {
    failedController = new PhotoshopTextFileControllerImpl(new PhotoshopAdditionalOperationsImpl(),
            null, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions2() {
    failedController = new PhotoshopTextFileControllerImpl(null,
            new PhotoshopViewImpl(new StringBuilder()), new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions3() {
    failedController = new PhotoshopTextFileControllerImpl(new PhotoshopAdditionalOperationsImpl(),
            null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions4() {
    failedController = new PhotoshopTextFileControllerImpl(null,
            new PhotoshopViewImpl(new StringBuilder()), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions5() {
    failedController = new PhotoshopTextFileControllerImpl(new PhotoshopAdditionalOperationsImpl(),
            null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions6() {
    failedController = new PhotoshopTextFileControllerImpl(null,
            null, null);
  }

  @Test
  public void testLoadAndSaveWithCorrectInputsMock() throws IOException {
    StringReader reader1 = new StringReader("load images/Koala.ppm Koala brighten 10 Koala " +
            "Koala-Brighten red-component Koala Koala-Red luma-component Koala-Brighten " +
            "Koala-Brighten-Luma save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma q");
    StringBuilder modelAppendable1 = new StringBuilder();
    StringBuilder viewAppendable1 = new StringBuilder();
    MockPhotoshopModel mockModel1 = new MockPhotoshopModel(modelAppendable1);
    PhotoshopViewImpl mockView1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(mockModel1, mockView1,
            reader1);
    controller1.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
                    "Brighten on Image: Koala, new image known as: Koala-Brighten\n" +
                    "Red Greyscale on Image: Koala, new image known as: Koala-Red\n" +
                    "Luma Greyscale on Image: Koala-Brighten, new image known as: " +
                    "Koala-Brighten-Luma\n" +
                    "Saving with Image Path: images/Koala-Brighten-Luma.ppm and Image Name: " +
                    "Koala-Brighten-Luma\n",
            modelAppendable1.toString());
    assertEquals("Rendering Message with Message: Successfully brightened \n" +
                    "\n" +
                    "Rendering Message with Message: Successfully grey-scaled via " +
                    "red component \n" +
                    "\n" +
                    "Rendering Message with Message: Successfully grey-scaled via " +
                    "luma component \n" +
                    "\n" +
                    "Rendering Message with Message: Successfully saved image \n" +
                    "\n" +
                    "Rendering Message with Message: Thank you for using photoshop program.\n",
            viewAppendable1.toString());

    StringReader reader2 = new StringReader("load images/Koala.ppm Koala value-component" +
            " Koala " +
            "Koala-Value horizontal-flip Koala Koala-Horizontal-Flip intensity-component " +
            "Koala-Value " +
            "Koala-Value-Intensity save images/Koala-Value-Intensity.ppm Koala-Value-Intensity q");
    StringBuilder modelAppendable2 = new StringBuilder();
    StringBuilder viewAppendable2 = new StringBuilder();
    MockPhotoshopModel mockModel2 = new MockPhotoshopModel(modelAppendable2);
    PhotoshopViewImpl mockView2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(mockModel2, mockView2,
            reader2);
    controller2.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
            "Value Greyscale on Image: Koala, new image known as: Koala-Value\n" +
            "Horizontal Flip on Image: Koala, new image known as: Koala-Horizontal-Flip\n" +
            "Intensity Greyscale on Image: Koala-Value, new image known as: " +
            "Koala-Value-Intensity\n" +
            "Saving with Image Path: images/Koala-Value-Intensity.ppm and Image Name: " +
            "Koala-Value-Intensity\n", modelAppendable2.toString());
    assertEquals("Rendering Message with Message: Successfully grey-scaled via " +
                    "value component \n" +
                    "\n" +
                    "Rendering Message with Message: Successful horizontal flip \n" +
                    "\n" +
                    "Rendering Message with Message: Successfully grey-scaled via " +
                    "intensity component \n" +
                    "\n" +
                    "Rendering Message with Message: Successfully saved image \n" +
                    "\n" +
                    "Rendering Message with Message: Thank you for using photoshop program.\n",
            viewAppendable2.toString());
  }

  @Test
  public void testLoadAndSaveWithCorrectInputs() throws IOException {
    StringReader reader1 = new StringReader("load images/Koala.ppm Koala brighten 10 Koala " +
            "Koala-Brighten red-component Koala Koala-Red luma-component Koala-Brighten " +
            "Koala-Brighten-Luma save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma q");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    controller1.runPhotoshop();
    assertEquals(4, images1.size());
    assertTrue(images1.containsKey("Koala"));
    assertTrue(images1.containsKey("Koala-Brighten"));
    assertTrue(images1.containsKey("Koala-Red"));
    assertTrue(images1.containsKey("Koala-Brighten-Luma"));

    StringReader reader2 = new StringReader("load images/Koala.ppm Koala value-component" +
            " Koala " +
            "Koala-Value horizontal-flip Koala Koala-Horizontal-Flip intensity-component" +
            " Koala-Value " +
            "Koala-Value-Intensity save images/Koala-Value-Intensity.ppm Koala-Value-Intensity q");
    StringBuilder viewAppendable2 = new StringBuilder();
    HashMap<String, PixelClass[][]> images2 = new HashMap<String, PixelClass[][]>();
    PhotoshopAdditionalOperationsInterface model2 = new PhotoshopAdditionalOperationsImpl(images2);
    PhotoshopViewImpl view2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(model2, view2, reader2);
    controller2.runPhotoshop();
    assertEquals(4, images1.size());
    assertTrue(images2.containsKey("Koala"));
    assertTrue(images2.containsKey("Koala-Value"));
    assertTrue(images2.containsKey("Koala-Horizontal-Flip"));
    assertTrue(images2.containsKey("Koala-Value-Intensity"));
  }

  @Test(expected = IllegalStateException.class)
  public void testLoadAndSaveWithCorrectInputsWithoutQuit() throws IOException {
    StringReader reader1 = new StringReader("load images/Koala.ppm Koala brighten 10 Koala " +
            "Koala-Brighten red-component Koala Koala-Red luma-component Koala-Brighten " +
            "Koala-Brighten-Luma save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    controller1.runPhotoshop();
    assertEquals(4, images1.size());
    assertTrue(images1.containsKey("Koala"));
    assertTrue(images1.containsKey("Koala-Brighten"));
    assertTrue(images1.containsKey("Koala-Red"));
    assertTrue(images1.containsKey("Koala-Brighten-Luma"));

    StringReader reader2 = new StringReader("load images/Koala.ppm Koala value-component" +
            " Koala " +
            "Koala-Value horizontal-flip Koala Koala-Horizontal-Flip intensity-component" +
            " Koala-Value " +
            "Koala-Value-Intensity save images/Koala-Value-Intensity.ppm Koala-Value-Intensity q");
    HashMap<String, PixelClass[][]> images2 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable2 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model2 = new PhotoshopAdditionalOperationsImpl(images2);
    PhotoshopViewImpl view2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(model2, view2, reader2);
    controller2.runPhotoshop();
    assertEquals(4, images2.size());
    assertTrue(images2.containsKey("Koala"));
    assertTrue(images2.containsKey("Koala-Value"));
    assertTrue(images2.containsKey("Koala-Horizontal-Flip"));
    assertTrue(images2.containsKey("Koala-Value-Intensity"));
  }

  @Test(expected = IllegalStateException.class)
  public void testLoadAndSaveWithCorrectInputsWithoutQuitMock() throws IOException {
    StringReader reader1 = new StringReader("load images/Koala.ppm Koala brighten 10 Koala " +
            "Koala-Brighten red-component Koala Koala-Red luma Koala-Brighten " +
            "Koala-Brighten-Luma save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma");
    StringBuilder modelAppendable1 = new StringBuilder();
    StringBuilder viewAppendable1 = new StringBuilder();
    MockPhotoshopModel mockModel1 = new MockPhotoshopModel(modelAppendable1);
    PhotoshopViewImpl mockView1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(mockModel1, mockView1,
            reader1);
    controller1.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
            "Brighten on Image: Koala, new image known as: Koala-Brighten\n" +
            "Red Greyscale on Image: Koala, new image known as: Koala-Red\n" +
            "Saving with Image Path: images/Koala-Brighten-Luma.ppm and Image Name:" +
            " Koala-Brighten-Luma" + "\n", modelAppendable1.toString());
    assertEquals("Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n", viewAppendable1.toString());

    StringReader reader2 = new StringReader("load images/Koala.ppm Koala value-component" +
            " Koala " +
            "Koala-Value horizontal-flip Koala Koala-Horizontal-Flip intensity-component " +
            "Koala-Value " +
            "Koala-Value-Intensity save images/Koala-Value-Intensity.ppm Koala-Value-Intensity q");
    StringBuilder modelAppendable2 = new StringBuilder();
    StringBuilder viewAppendable2 = new StringBuilder();
    MockPhotoshopModel mockModel2 = new MockPhotoshopModel(modelAppendable2);
    PhotoshopViewImpl mockView2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(mockModel2, mockView2,
            reader2);
    controller2.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
            "Value Greyscale on Image: Koala, new image known as: Koala-Value\n" +
            "Horizontal Flip on Image: Koala, new image known as: Koala-Horizontal-Flip\n" +
            "Intensity Greyscale on Image: Koala-Value, new image known as: " +
            "Koala-Value-Intensity\n" +
            "Saving with Image Path: images/Koala-Value-Intensity.ppm and Image Name: " +
            "Koala-Value-Intensity\n", modelAppendable2.toString());
    assertEquals("", viewAppendable2.toString());
  }

  @Test
  public void testLoadAndSaveWithErroneousInputsButCorrect() throws IOException {
    StringReader reader1 = new StringReader("flood water light load images/Koala.ppm Koala " +
            "don't brighten 10 Koala " +
            "Koala-Brighten yellow-component red-component Koala Koala-Red luma-component" +
            " Koala-Brighten " +
            "Koala-Brighten-Luma saved save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma q");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    controller1.runPhotoshop();
    assertEquals(4, images1.size());
    assertTrue(images1.containsKey("Koala"));
    assertTrue(images1.containsKey("Koala-Brighten"));
    assertTrue(images1.containsKey("Koala-Red"));
    assertTrue(images1.containsKey("Koala-Brighten-Luma"));

    StringReader reader2 = new StringReader("what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala Koala-Horizontal-Flip" +
            " intencity intensity-component Koala-Value " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    HashMap<String, PixelClass[][]> images2 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable2 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model2 = new PhotoshopAdditionalOperationsImpl(images2);
    PhotoshopViewImpl view2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(model2, view2, reader2);
    controller2.runPhotoshop();
    assertEquals(4, images2.size());
    assertTrue(images2.containsKey("Koala"));
    assertTrue(images2.containsKey("Koala-Value"));
    assertTrue(images2.containsKey("Koala-Horizontal-Flip"));
    assertTrue(images2.containsKey("Koala-Value-Intensity"));
  }

  @Test
  public void testLoadAndSaveWithErroneousInputsButCorrectMock() throws IOException {
    StringReader reader1 = new StringReader("flood water light load images/Koala.ppm Koala " +
            "don't brighten 10 Koala " +
            "Koala-Brighten yellow-component red-component Koala Koala-Red luma Koala-Brighten " +
            "Koala-Brighten-Luma saved save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma q");
    StringBuilder modelAppendable1 = new StringBuilder();
    StringBuilder viewAppendable1 = new StringBuilder();
    MockPhotoshopModel mockModel1 = new MockPhotoshopModel(modelAppendable1);
    PhotoshopViewImpl mockView1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(mockModel1, mockView1,
            reader1);
    controller1.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
            "Brighten on Image: Koala, new image known as: Koala-Brighten\n" +
            "Red Greyscale on Image: Koala, new image known as: Koala-Red\n" +
            "Saving with Image Path: images/Koala-Brighten-Luma.ppm and Image Name:" +
            " Koala-Brighten-Luma" + "\n", modelAppendable1.toString());
    assertEquals("Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Successfully brightened \n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Successfully grey-scaled via red component \n"
                    + "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "\n" +
                    "Rendering Message with Message: Successfully saved image \n" +
                    "\n" +
                    "Rendering Message with Message: Thank you for using photoshop program.",
            viewAppendable1.toString());

    StringReader reader2 = new StringReader("what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala Koala-Horizontal-Flip" +
            " intencity intensity-component Koala-Value " +
            "greyscale Koala Koala-Greyscale sepai sepia Koala-Greyscale Koala-Greyscale-Sepia " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    StringBuilder modelAppendable2 = new StringBuilder();
    StringBuilder viewAppendable2 = new StringBuilder();
    MockPhotoshopModel mockModel2 = new MockPhotoshopModel(modelAppendable2);
    PhotoshopViewImpl mockView2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(mockModel2, mockView2,
            reader2);
    controller2.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
            "Value Greyscale on Image: Koala, new image known as: Koala-Value\n" +
            "Horizontal Flip on Image: Koala, new image known as: Koala-Horizontal-Flip\n" +
            "Intensity Greyscale on Image: Koala-Value, new image known as: greyscale\n" +
            "Sepia-ing Image: Koala-Greyscale, new image known as: Koala-Greyscale-Sepia\n" +
            "Saving with Image Path: images/Koala-Value-Intensity.ppm and Image Name:" +
            " Koala-Value-Intensity\n", modelAppendable2.toString());
    assertEquals("Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Successfully grey-scaled via value " +
                    "component \n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Successful horizontal flip \n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Successfully grey-scaled via intensity " +
                    "component \n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Successfully applied sepia filter onto " +
                    "image \n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Successfully saved image \n" +
                    "\n" +
                    "Rendering Message with Message: Thank you for using photoshop program.\n",
            viewAppendable2.toString());
  }

  @Test
  public void testQuitDuringLoadAndSaveCorrect() throws IOException {
    StringReader reader1 = new StringReader("flood water light load images/Koala.ppm Koala " +
            "don't brighten 10 Koala " +
            "Koala-Brighten yellow-component q red-component Koala Koala-Red luma-component " +
            "Koala-Brighten " +
            "Koala-Brighten-Luma saved " +
            "blue blur Koala Koala-Blur sharp Sharpen Koala-Blur Koala-Blur-Sharpen " +
            "save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma q");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    controller1.runPhotoshop();
    assertEquals(2, images1.size());
    assertTrue(images1.containsKey("Koala"));
    assertTrue(images1.containsKey("Koala-Brighten"));
    assertTrue(images1.containsKey("Koala-Blur"));
    assertTrue(images1.containsKey("Koala-Blur-Sharpen"));
    assertFalse(images1.containsKey("Koala-Luma-Red"));
    assertFalse(images1.containsKey("Koala-Brighten-Luma"));

    StringReader reader2 = new StringReader("what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala q Koala-Horizontal-Flip" +
            " intencity intensity-component Koala-Value " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    HashMap<String, PixelClass[][]> images2 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable2 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model2 = new PhotoshopAdditionalOperationsImpl(images2);
    PhotoshopViewImpl view2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(model2, view2, reader2);
    controller2.runPhotoshop();
    assertEquals(2, images2.size());
    assertTrue(images2.containsKey("Koala"));
    assertTrue(images2.containsKey("Koala-Value"));
    assertFalse(images2.containsKey("Koala-Horizontal-Flip"));
    assertFalse(images2.containsKey("Koala-Value-Intensity"));

    StringReader reader3 = new StringReader("what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala q Koala-Horizontal-Flip" +
            " intencity intensity-component q Koala-Value " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    HashMap<String, PixelClass[][]> images3 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable3 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model3 = new PhotoshopAdditionalOperationsImpl(images3);
    PhotoshopViewImpl view3 = new PhotoshopViewImpl(viewAppendable3);
    PhotoshopController controller3 = new PhotoshopTextFileControllerImpl(model3, view3, reader3);
    controller3.runPhotoshop();
    assertEquals(2, images3.size());
    assertTrue(images3.containsKey("Koala"));
    assertTrue(images3.containsKey("Koala-Value"));
    assertFalse(images3.containsKey("Koala-Horizontal-Flip"));
    assertFalse(images3.containsKey("Koala-Value-Intensity"));

    StringReader reader4 = new StringReader("q what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala q Koala-Horizontal-Flip" +
            " intencity intensity-component q Koala-Value " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    HashMap<String, PixelClass[][]> images4 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable4 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model4 = new PhotoshopAdditionalOperationsImpl(images4);
    PhotoshopViewImpl view4 = new PhotoshopViewImpl(viewAppendable4);
    PhotoshopController controller4 = new PhotoshopTextFileControllerImpl(model4, view4, reader4);
    controller4.runPhotoshop();
    assertEquals(0, images4.size());
    assertFalse(images4.containsKey("Koala"));
    assertFalse(images4.containsKey("Koala-Value"));
    assertFalse(images4.containsKey("Koala-Horizontal-Flip"));
    assertFalse(images4.containsKey("Koala-Value-Intensity"));
  }

  @Test
  public void testQuitDuringLoadAndSaveCorrectMock() throws IOException {
    StringReader reader1 = new StringReader("flood water light load images/Koala.ppm Koala " +
            "don't brighten 10 Koala " +
            "Koala-Brighten yellow-component q red-component Koala Koala-Red luma Koala-Brighten " +
            "Koala-Brighten-Luma saved save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma q");
    StringBuilder modelAppendable1 = new StringBuilder();
    StringBuilder viewAppendable1 = new StringBuilder();
    MockPhotoshopModel mockModel1 = new MockPhotoshopModel(modelAppendable1);
    PhotoshopViewImpl mockView1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(mockModel1, mockView1,
            reader1);
    controller1.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
                    "Brighten on Image: Koala, new image known as: Koala-Brighten\n",
            modelAppendable1.toString());
    assertEquals("Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Successfully brightened \n" +
                    "\n" +
                    "Rendering Message with Message: Invalid Input\n" +
                    "Rendering Message with Message: Thank you for using photoshop program.\n",
            viewAppendable1.toString());

    StringReader reader2 = new StringReader("what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala q Koala-Horizontal-Flip" +
            " intencity intensity-component Koala-Value " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    StringBuilder modelAppendable2 = new StringBuilder();
    StringBuilder viewAppendable2 = new StringBuilder();
    MockPhotoshopModel mockModel2 = new MockPhotoshopModel(modelAppendable2);
    PhotoshopViewImpl mockView2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(mockModel2, mockView2,
            reader2);
    controller2.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
                    "Value Greyscale on Image: Koala, new image known as: Koala-Value\n",
            modelAppendable2.toString());
    assertEquals("Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Successfully grey-scaled via value component \n" +
            "\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n", viewAppendable2.toString());

    StringReader reader3 = new StringReader("what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala q Koala-Horizontal-Flip" +
            " intencity intensity-component q Koala-Value " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    StringBuilder modelAppendable3 = new StringBuilder();
    StringBuilder viewAppendable3 = new StringBuilder();
    MockPhotoshopModel mockModel3 = new MockPhotoshopModel(modelAppendable3);
    PhotoshopViewImpl mockView3 = new PhotoshopViewImpl(viewAppendable3);
    PhotoshopController controller3 = new PhotoshopTextFileControllerImpl(mockModel3, mockView3,
            reader3);
    controller3.runPhotoshop();
    assertEquals("Loading with Image Path: images/Koala.ppm and Image Name: Koala\n" +
                    "Value Greyscale on Image: Koala, new image known as: Koala-Value\n",
            modelAppendable3.toString());
    assertEquals("Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Successfully grey-scaled via value component \n" +
            "\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n" +
            "Rendering Message with Message: Invalid Input\n", viewAppendable3.toString());

    StringReader reader4 = new StringReader("q what load images/Koala.ppm Koala value" +
            " values-component value-component Koala " +
            "Koala-Value diagonal-flip Koala Koala-Diagonal-Flip" +
            " horizontal-flip Koala q Koala-Horizontal-Flip" +
            " intencity intensity-component q Koala-Value " +
            "Koala-Value-Intensity noo save images/Koala-Value-Intensity.ppm " +
            "Koala-Value-Intensity q");
    StringBuilder modelAppendable4 = new StringBuilder();
    StringBuilder viewAppendable4 = new StringBuilder();
    MockPhotoshopModel mockModel4 = new MockPhotoshopModel(modelAppendable4);
    PhotoshopViewImpl mockView4 = new PhotoshopViewImpl(viewAppendable4);
    PhotoshopController controller4 = new PhotoshopTextFileControllerImpl(mockModel4, mockView4,
            reader4);
    controller4.runPhotoshop();
    assertEquals("", modelAppendable4.toString());
    assertEquals("Rendering Message with Message: Thank you for using " +
            "photoshop program.\n", viewAppendable4.toString());
  }


  private PhotoshopController controller1;
  private PhotoshopController controller2;
  private PhotoshopController controller3;
  private PhotoshopController controller4;
  private PhotoshopController controller5;
  private PhotoshopController controller6;
  private PhotoshopController controller7;


  @Before
  public void setup() {
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    images1.put("Koala", ImageUtil.readPPM("images/Koala.ppm"));
    StringReader reader0 = new StringReader("load images/Koala.ppm Koala");
    StringReader reader1 = new StringReader(reader0 + "");
    StringReader reader2 = new StringReader(reader0 + "load");
    StringReader reader3 = new StringReader(reader0 + "red-component Koala");
    StringReader reader4 = new StringReader(reader0 + "brighten 10 Koala");
    StringReader reader5 = new StringReader(reader0 + "value-component Koala Koala-Value save");
    StringReader reader6 = new StringReader(reader0 + "value-component Koala Koala-Value " +
            "luma-component Koala Koala-Luma");
    StringReader reader7 = new StringReader("flood water light load images/Koala.ppm Koala " +
            "don't brighten 10 Koala " +
            "Koala-Brighten yellow-component red-component Koala Koala-Red luma Koala-Brighten " +
            "Koala-Brighten-Luma saved save images/Koala-Brighten-Luma.ppm Koala-Brighten-Luma s");
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    controller2 = new PhotoshopTextFileControllerImpl(model1, view1, reader2);
    controller3 = new PhotoshopTextFileControllerImpl(model1, view1, reader3);
    controller4 = new PhotoshopTextFileControllerImpl(model1, view1, reader4);
    controller5 = new PhotoshopTextFileControllerImpl(model1, view1, reader5);
    controller6 = new PhotoshopTextFileControllerImpl(model1, view1, reader6);
    controller7 = new PhotoshopTextFileControllerImpl(model1, view1, reader7);
  }

  @Test(expected = IllegalStateException.class)
  public void testNonQuitExceptions() throws IOException {
    controller1.runPhotoshop();
    controller2.runPhotoshop();
    controller3.runPhotoshop();
    controller4.runPhotoshop();
    controller5.runPhotoshop();
    controller6.runPhotoshop();
    controller7.runPhotoshop();
  }

  @Test
  public void additionalOperationsControllerTests() {

    //Tests that blur wo
    StringReader reader1 = new StringReader("load res/grid-PNG/grid.png grid " +
            "blur grid grid-blur " +
            "save res/grid7-blur.ppm grid-blur " +
            "q");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    try {
      controller1.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images1.size());
    assertTrue(images1.containsKey("grid-blur"));
    assertFalse(images1.containsKey("grid-sharpen"));
    assertFalse(images1.containsKey("grid-greyscale"));
    assertFalse(images1.containsKey("grid-sepia"));

    StringReader reader2 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen sharpener " +
            "save res/grid7-sharpen.ppm grid-sharpen " +
            "q");
    HashMap<String, PixelClass[][]> images2 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable2 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model2 = new PhotoshopAdditionalOperationsImpl(images2);
    PhotoshopViewImpl view2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(model2, view2, reader2);
    try {
      controller2.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images2.size());
    assertFalse(images2.containsKey("grid-blur"));
    assertTrue(images2.containsKey("grid-sharpen"));
    assertFalse(images2.containsKey("grid-greyscale"));
    assertFalse(images2.containsKey("grid-sepia"));

    StringReader reader3 = new StringReader("load res/grid-PNG/grid.png grid " +
            "grayscale greyscale grid grid-greyscale " +
            "save res/grid7-greyscale.ppm grid-greyscale " +
            "q");
    HashMap<String, PixelClass[][]> images3 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable3 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model3 = new PhotoshopAdditionalOperationsImpl(images3);
    PhotoshopViewImpl view3 = new PhotoshopViewImpl(viewAppendable3);
    PhotoshopController controller3 = new PhotoshopTextFileControllerImpl(model3, view3, reader3);
    try {
      controller3.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images3.size());
    assertFalse(images3.containsKey("grid-blur"));
    assertFalse(images3.containsKey("grid-sharpen"));
    assertTrue(images3.containsKey("grid-greyscale"));
    assertFalse(images3.containsKey("grid-sepia"));

    StringReader reader4 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sepia grid grid-sepia " +
            "save res/grid7-sepia.ppm grid-sepia " +
            "q");
    HashMap<String, PixelClass[][]> images4 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable4 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model4 = new PhotoshopAdditionalOperationsImpl(images4);
    PhotoshopViewImpl view4 = new PhotoshopViewImpl(viewAppendable4);
    PhotoshopController controller4 = new PhotoshopTextFileControllerImpl(model4, view4, reader4);
    try {
      controller4.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images4.size());
    assertFalse(images4.containsKey("grid-blur"));
    assertFalse(images4.containsKey("grid-sharpen"));
    assertFalse(images4.containsKey("grid-greyscale"));
    assertTrue(images4.containsKey("grid-sepia"));

    StringReader reader5 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen the bear and the whale " +
            "sepia grid-sharpen grid-sharpen-sepia " +
            "whale save res/grid7-sharpen-sepia.ppm grid-sharpen-sepia " +
            "well well well q");
    HashMap<String, PixelClass[][]> images5 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable5 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model5 = new PhotoshopAdditionalOperationsImpl(images5);
    PhotoshopViewImpl view5 = new PhotoshopViewImpl(viewAppendable5);
    PhotoshopController controller5 = new PhotoshopTextFileControllerImpl(model5, view5, reader5);
    try {
      controller5.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(3, images5.size());
    assertFalse(images5.containsKey("grid-blur"));
    assertTrue(images5.containsKey("grid-sharpen"));
    assertFalse(images5.containsKey("grid-greyscale"));
    assertTrue(images5.containsKey("grid-sharpen-sepia"));

    StringReader reader6 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen the bear and the whale " +
            "sepia grid-sharpen grid-sharpen-sepia " +
            "whale greyscale grid-sharpen-sepia grid-sharpen-sepia-greyscale " +
            "wait blur grid-sharpen-sepia-greyscale grid-sharpen-sepia-greyscale-blur " +
            "save res/grid7-sharpen-sepia.ppm grid-sharpen-sepia " +
            "well well well q");
    HashMap<String, PixelClass[][]> images6 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable6 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model6 = new PhotoshopAdditionalOperationsImpl(images6);
    PhotoshopViewImpl view6 = new PhotoshopViewImpl(viewAppendable6);
    PhotoshopController controller6 = new PhotoshopTextFileControllerImpl(model6, view6, reader6);
    try {
      controller6.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(5, images6.size());
    assertTrue(images6.containsKey("grid-sharpen-sepia-greyscale-blur"));
    assertTrue(images6.containsKey("grid-sharpen"));
    assertTrue(images6.containsKey("grid-sharpen-sepia-greyscale"));
    assertTrue(images6.containsKey("grid-sharpen-sepia"));

  }

  @Test
  public void additionalOperationsControllerMockTests() {

    //Tests that blur wo
    StringReader reader1 = new StringReader("load res/grid-PNG/grid.png grid " +
            "blur grid grid-blur " +
            "save res/grid7-blur.ppm grid-blur " +
            "q");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    StringBuilder modelAppendable1 = new StringBuilder();
    MockPhotoshopModel model1 = new MockPhotoshopModel(modelAppendable1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    try {
      controller1.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Loading with Image Path: res/grid-PNG/grid.png and Image Name: grid\n" +
                    "Blurring Image: grid, new image known as: grid-blur\n" +
                    "Saving with Image Path: res/grid7-blur.ppm and Image Name: grid-blur\n",
            modelAppendable1.toString());
    assertEquals("Blur successful\n" +
            "Successfully saved image \n" +
            "Thank you for using photoshop program.", viewAppendable1.toString());

    StringReader reader2 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen sharpener " +
            "save res/grid7-sharpen.ppm grid-sharpen " +
            "q");
    HashMap<String, PixelClass[][]> images2 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable2 = new StringBuilder();
    StringBuilder modelAppendable2 = new StringBuilder();
    MockPhotoshopModel model2 = new MockPhotoshopModel(modelAppendable2);
    PhotoshopViewImpl view2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(model2, view2, reader2);
    try {
      controller2.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Loading with Image Path: res/grid-PNG/grid.png and Image Name: grid\n" +
                    "Sharpening Image: grid, new image known as: grid-sharpen\n" +
                    "Saving with Image Path: res/grid7-sharpen.ppm and Image Name: grid-sharpen\n",
            modelAppendable2.toString());
    assertEquals("Successfully sharpened image \n" +
            "Invalid Input\nSuccessfully saved image \n" +
            "Thank you for using photoshop program.", viewAppendable2.toString());

    StringReader reader3 = new StringReader("load res/grid-PNG/grid.png grid " +
            "grayscale greyscale grid grid-greyscale " +
            "save res/grid7-greyscale.ppm grid-greyscale " +
            "q");
    HashMap<String, PixelClass[][]> images3 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable3 = new StringBuilder();
    StringBuilder modelAppendable3 = new StringBuilder();
    MockPhotoshopModel model3 = new MockPhotoshopModel(modelAppendable3);
    PhotoshopViewImpl view3 = new PhotoshopViewImpl(viewAppendable3);
    PhotoshopController controller3 = new PhotoshopTextFileControllerImpl(model3, view3, reader3);
    try {
      controller3.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Loading with Image Path: res/grid-PNG/grid.png and Image Name: grid\n" +
                    "Greyscaling Image: grid, new image known as: grid-greyscale\n" +
                    "Saving with Image Path: res/grid7-greyscale.ppm and Image Name: grid-grey" +
                    "scale\n",
            modelAppendable3.toString());
    assertEquals("Invalid Input\nSuccessfully grey-scaled \n" +
            "Successfully saved image \n" +
            "Thank you for using photoshop program.", viewAppendable3.toString());

    StringReader reader4 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sepia grid grid-sepia " +
            "save res/grid7-sepia.ppm grid-sepia " +
            "q");
    HashMap<String, PixelClass[][]> images4 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable4 = new StringBuilder();
    StringBuilder modelAppendable4 = new StringBuilder();
    MockPhotoshopModel model4 = new MockPhotoshopModel(modelAppendable4);
    PhotoshopViewImpl view4 = new PhotoshopViewImpl(viewAppendable4);
    PhotoshopController controller4 = new PhotoshopTextFileControllerImpl(model4, view4, reader4);
    try {
      controller4.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Loading with Image Path: res/grid-PNG/grid.png and Image Name: grid\n" +
                    "Sepia-ing Image: grid, new image known as: grid-sepia\n" +
                    "Saving with Image Path: res/grid7-sepia.ppm and Image Name: grid-sepia\n",
            modelAppendable4.toString());
    assertEquals("Successfully applied sepia filter onto image \n" +
            "Successfully saved image \n" +
            "Thank you for using photoshop program.", viewAppendable4.toString());

    StringReader reader5 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen the bear and the whale " +
            "sepia grid-sharpen grid-sharpen-sepia " +
            "whale save res/grid7-sharpen-sepia.ppm grid-sharpen-sepia " +
            "well well well q");
    HashMap<String, PixelClass[][]> images5 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable5 = new StringBuilder();
    StringBuilder modelAppendable5 = new StringBuilder();
    MockPhotoshopModel model5 = new MockPhotoshopModel(modelAppendable1);
    PhotoshopViewImpl view5 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller5 = new PhotoshopTextFileControllerImpl(model5, view5, reader5);
    try {
      controller5.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("", modelAppendable5.toString());
    assertEquals("", viewAppendable5.toString());

    StringReader reader6 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen the bear and the whale " +
            "sepia grid-sharpen grid-sharpen-sepia " +
            "whale greyscale grid-sharpen-sepia grid-sharpen-sepia-greyscale " +
            "wait blur grid-sharpen-sepia-greyscale grid-sharpen-sepia-greyscale-blur " +
            "save res/grid7-sharpen-sepia.ppm grid-sharpen-sepia " +
            "well well well q");
    HashMap<String, PixelClass[][]> images6 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable6 = new StringBuilder();
    StringBuilder modelAppendable6 = new StringBuilder();
    MockPhotoshopModel model6 = new MockPhotoshopModel(modelAppendable6);
    PhotoshopViewImpl view6 = new PhotoshopViewImpl(viewAppendable6);
    PhotoshopController controller6 = new PhotoshopTextFileControllerImpl(model6, view6, reader6);
    try {
      controller6.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Loading with Image Path: res/grid-PNG/grid.png and Image Name: grid\n" +
                    "Sharpening Image: grid, new image known as: grid-sharpen\n" +
                    "Sepia-ing Image: grid-sharpen, new image known as: grid-sharpen-sepia\n" +
                    "Greyscaling Image: grid-sharpen-sepia, new image known as: grid-sharpen-" +
                    "sepia-greyscale\n" +
                    "Blurring Image: grid-sharpen-sepia-greyscale, new image known as: " +
                    "grid-sharpen-sepia-greyscale-blur\n" +
                    "Saving with Image Path: res/grid7-sharpen-sepia.ppm and Image Name: " +
                    "grid-sharpen-sepia\n",
            modelAppendable6.toString());
    assertEquals("Successfully sharpened image \n" +
                    "Invalid InputInvalid InputInvalid InputInvalid InputInvalid " +
                    "InputSuccessfully " +
                    "applied sepia filter onto image \n" +
                    "Invalid InputSuccessfully grey-scaled \n" +
                    "Invalid InputBlur successful\n" +
                    "Successfully saved image \n" +
                    "Invalid InputInvalid InputInvalid InputThank you for using photoshop program.",
            viewAppendable6.toString());

  }

  @Test
  public void differentFileFormats() { //Tests that blur wo
    StringReader reader1 = new StringReader("load res/grid-PNG/grid.png grid " +
            "blur grid grid-blur " +
            "save res/grid7-blur.ppm grid-blur " +
            "q");
    HashMap<String, PixelClass[][]> images1 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable1 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model1 = new PhotoshopAdditionalOperationsImpl(images1);
    PhotoshopViewImpl view1 = new PhotoshopViewImpl(viewAppendable1);
    PhotoshopController controller1 = new PhotoshopTextFileControllerImpl(model1, view1, reader1);
    try {
      controller1.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images1.size());
    assertTrue(images1.containsKey("grid-blur"));
    assertFalse(images1.containsKey("grid-sharpen"));
    assertFalse(images1.containsKey("grid-greyscale"));
    assertFalse(images1.containsKey("grid-sepia"));

    StringReader reader2 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen sharpener " +
            "save res/grid7-sharpen.jpg grid-sharpen " +
            "q");
    HashMap<String, PixelClass[][]> images2 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable2 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model2 = new PhotoshopAdditionalOperationsImpl(images2);
    PhotoshopViewImpl view2 = new PhotoshopViewImpl(viewAppendable2);
    PhotoshopController controller2 = new PhotoshopTextFileControllerImpl(model2, view2, reader2);
    try {
      controller2.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images2.size());
    assertFalse(images2.containsKey("grid-blur"));
    assertTrue(images2.containsKey("grid-sharpen"));
    assertFalse(images2.containsKey("grid-greyscale"));
    assertFalse(images2.containsKey("grid-sepia"));

    StringReader reader3 = new StringReader("load res/grid-PNG/grid.png grid " +
            "grayscale greyscale grid grid-greyscale " +
            "save res/grid7-greyscale.png grid-greyscale " +
            "q");
    HashMap<String, PixelClass[][]> images3 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable3 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model3 = new PhotoshopAdditionalOperationsImpl(images3);
    PhotoshopViewImpl view3 = new PhotoshopViewImpl(viewAppendable3);
    PhotoshopController controller3 = new PhotoshopTextFileControllerImpl(model3, view3, reader3);
    try {
      controller3.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images3.size());
    assertFalse(images3.containsKey("grid-blur"));
    assertFalse(images3.containsKey("grid-sharpen"));
    assertTrue(images3.containsKey("grid-greyscale"));
    assertFalse(images3.containsKey("grid-sepia"));

    StringReader reader4 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sepia grid grid-sepia " +
            "save res/grid7-sepia.bmp grid-sepia " +
            "q");
    HashMap<String, PixelClass[][]> images4 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable4 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model4 = new PhotoshopAdditionalOperationsImpl(images4);
    PhotoshopViewImpl view4 = new PhotoshopViewImpl(viewAppendable4);
    PhotoshopController controller4 = new PhotoshopTextFileControllerImpl(model4, view4, reader4);
    try {
      controller4.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, images4.size());
    assertFalse(images4.containsKey("grid-blur"));
    assertFalse(images4.containsKey("grid-sharpen"));
    assertFalse(images4.containsKey("grid-greyscale"));
    assertTrue(images4.containsKey("grid-sepia"));

    StringReader reader5 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen the bear and the whale " +
            "sepia grid-sharpen grid-sharpen-sepia " +
            "whale save res/grid7-sharpen-sepia.bmp grid-sharpen-sepia " +
            "well well well q");
    HashMap<String, PixelClass[][]> images5 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable5 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model5 = new PhotoshopAdditionalOperationsImpl(images5);
    PhotoshopViewImpl view5 = new PhotoshopViewImpl(viewAppendable5);
    PhotoshopController controller5 = new PhotoshopTextFileControllerImpl(model5, view5, reader5);
    try {
      controller5.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(3, images5.size());
    assertFalse(images5.containsKey("grid-blur"));
    assertTrue(images5.containsKey("grid-sharpen"));
    assertFalse(images5.containsKey("grid-greyscale"));
    assertTrue(images5.containsKey("grid-sharpen-sepia"));

    StringReader reader6 = new StringReader("load res/grid-PNG/grid.png grid " +
            "sharpen grid grid-sharpen the bear and the whale " +
            "sepia grid-sharpen grid-sharpen-sepia " +
            "whale greyscale grid-sharpen-sepia grid-sharpen-sepia-greyscale " +
            "wait blur grid-sharpen-sepia-greyscale grid-sharpen-sepia-greyscale-blur " +
            "save res/grid7-sharpen-sepia.ppm grid-sharpen-sepia " +
            "well well well q");
    HashMap<String, PixelClass[][]> images6 = new HashMap<String, PixelClass[][]>();
    StringBuilder viewAppendable6 = new StringBuilder();
    PhotoshopAdditionalOperationsInterface model6 = new PhotoshopAdditionalOperationsImpl(images6);
    PhotoshopViewImpl view6 = new PhotoshopViewImpl(viewAppendable6);
    PhotoshopController controller6 = new PhotoshopTextFileControllerImpl(model6, view6, reader6);
    try {
      controller6.runPhotoshop();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(5, images6.size());
    assertTrue(images6.containsKey("grid-sharpen-sepia-greyscale-blur"));
    assertTrue(images6.containsKey("grid-sharpen"));
    assertTrue(images6.containsKey("grid-sharpen-sepia-greyscale"));
    assertTrue(images6.containsKey("grid-sharpen-sepia"));
  }
}
