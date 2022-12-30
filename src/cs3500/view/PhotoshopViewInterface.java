package cs3500.view;

import java.io.IOException;

/**
 * ViewInterface that allows client to see messages in the console.
 */
public interface PhotoshopViewInterface {

  /**
   * RenderMessage sends a message to the client via the console.
   *
   * @param message Message that photoshop program sends to client via console.
   * @throws IOException If a message is not able to successfully be sent to client.
   */
  void renderMessage(String message) throws IOException;


}
