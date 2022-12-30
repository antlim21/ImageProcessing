import org.junit.Before;
import org.junit.Test;

import cs3500.controller.PhotoshopControllerImpl;
import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.view.GuiImpl;

import static org.junit.Assert.assertEquals;

/**
  java doc
 */
public class GUITests {
  private PhotoshopAdditionalOperationsImpl model;
  private GuiImpl view;
  private PhotoshopControllerImpl controller;
  @Before
  public void init() {
    PhotoshopAdditionalOperationsImpl model = new PhotoshopAdditionalOperationsImpl();

  }

  @Test
  public void testLoad() {
    this.model.load("images/Koala.ppm","koala");
    this.model.greyscale("koala", "koala-g");
    assertEquals("File Opened", this.view.toString());
  }
  @Test
  public void testLoad1() {
    this.model.load("images/Koala.ppm","koala");
    this.model.save("koala", "koala-g");
    assertEquals("File Opened \n File Saved", this.view.toString());
  }
}
