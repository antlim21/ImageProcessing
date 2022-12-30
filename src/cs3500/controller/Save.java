package cs3500.controller;

import java.io.IOException;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.PhotoshopViewInterface;

/**
 * Represents the operation for the Save method.
 */
public class Save implements PhotoshopCommand {
  private final boolean quit;
  private final String arg2;
  private final String arg3;

  /**
   * Initializes the three parameters that allows the client to run the method.
   *
   * @param quit Determines whether the client has quit yet.
   * @param arg2 The file path the client wants to save to.
   * @param arg3 The image the client wants to save.
   */
  public Save(boolean quit, String arg2, String arg3) {
    this.quit = quit;
    this.arg2 = arg2;
    this.arg3 = arg3;
  }

  @Override
  public void run(PhotoshopAdditionalOperationsInterface model, PhotoshopViewInterface view)
          throws IOException {
    if (!this.quit) {
      try {
        model.save(this.arg2, this.arg3);
        view.renderMessage("Successfully saved image \n");
      } catch (IllegalArgumentException | IOException e) {
        view.renderMessage(e.getMessage() + "\n");
      }
    }
  }
}