import org.junit.Test;
import org.junit.Before;

import java.io.IOException;

import cs3500.view.PhotoshopViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the view for photoshop.
 */
public class ViewTest {

  private Appendable appendable;
  private PhotoshopViewImpl view1;

  @Before
  public void setUp() {
    appendable = new StringBuilder();
    view1 = new PhotoshopViewImpl(appendable);
  }

  @Test
  public void testConstructor() {
    assertEquals("", appendable.toString());
    try {
      view1.renderMessage("");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("", appendable.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions() {
    view1 = new PhotoshopViewImpl(null);
  }

  @Test
  public void testRenderMessage() {

    assertEquals("", appendable.toString());
    try {
      view1.renderMessage("");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("", appendable.toString());
    try {
      view1.renderMessage("Message");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Message", appendable.toString());
    try {
      view1.renderMessage("1");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Message1", appendable.toString());
  }
}
