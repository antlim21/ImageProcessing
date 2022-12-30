import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;

import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.GuiImpl;

import static org.junit.Assert.assertEquals;

public class ListenerTest {

  @Test
  public void testListener() {
    PhotoshopAdditionalOperationsInterface model = new PhotoshopAdditionalOperationsImpl();
    GuiImpl photo = new GuiImpl(model);
    photo.actionPerformed(new ActionEvent(new Object(), 0, "Load Button"));
    assertEquals(Color.BLUE, model.getPixelColorAt("Koala", 0, 0));

    //PixelClass pixel = new photo.getPixelColorAt(0,0);
  }
}
