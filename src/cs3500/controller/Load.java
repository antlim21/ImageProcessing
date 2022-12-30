package cs3500.controller;

import java.io.IOException;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.PhotoshopViewInterface;


/**
 * Represents the operation for the Load method.
 */
public class Load implements PhotoshopCommand {

  private final boolean quit;
  private final String arg2;
  private final String arg3;

  /**
   * Initializes the three parameters that allows the client to run the method.
   *
   * @param quit Determines whether the client has quit yet.
   * @param arg2 The file that the client wants to load.
   * @param arg3 The name that the client wants to assign to the loaded image.
   */
  public Load(boolean quit, String arg2, String arg3) {
    this.quit = quit;
    this.arg2 = arg2;
    this.arg3 = arg3;
  }

  @Override
  public void run(PhotoshopAdditionalOperationsInterface model, PhotoshopViewInterface view)
          throws IOException {
    if (!this.quit) {
      try {
        model.load(this.arg2, this.arg3);
      } catch (IllegalArgumentException e) {
        view.renderMessage(e.getMessage() + "\n");
      }
    }
  }
}