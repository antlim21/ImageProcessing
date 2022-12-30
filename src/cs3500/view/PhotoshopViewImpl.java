package cs3500.view;

import java.io.IOException;

/**
 * PhotoshopViewImpl is where all methods related to the view, aka what the client sees, is created.
 */
public class PhotoshopViewImpl implements PhotoshopViewInterface {

  protected Appendable appendable;

  /**
   * PhotoshopViewImpl takes in methods for the client to see a message in the console.
   *
   * @param appendable Input that we want to give in. In this case, we want to give in messages
   *                   that will be sent to the client.
   * @throws IllegalArgumentException If appendable is null.
   */
  public PhotoshopViewImpl(Appendable appendable) throws IllegalArgumentException {
    if (appendable == null) {
      throw new IllegalArgumentException("appendable can't be null");
    }
    this.appendable = appendable;
  }

  /**
   * Sends a message to the client (used typically when sending error messages).
   *
   * @param message Message that photoshop program sends to client via console.
   * @throws IOException If message is not able to be sent to client.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
