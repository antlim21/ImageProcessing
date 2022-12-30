package cs3500.view;

import java.io.IOException;

/**
 * This is the mock view for photoshop that is used for testing.
 */
public class MockPhotoshopView implements PhotoshopViewInterface {

  private StringBuilder log;

  /**
   * MockPhotoshopView is the view that is used for testing.
   *
   * @param log Log of all the messages sent.
   */
  public MockPhotoshopView(StringBuilder log) {
    this.log = log;
  }

  /**
   * Sends a message to the client (used typically when sending error messages).
   *
   * @param message Message that photoshop program sends to client via console.
   * @throws IOException If message is not able to be sent to client.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    log.append("Rendering Message with Message: " + message + "\n");
  }
}