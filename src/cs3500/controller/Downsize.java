package cs3500.controller;

import java.io.IOException;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.PhotoshopViewInterface;

/**
 * Represents the operation for the Downsize method.
 */
public class Downsize implements PhotoshopCommand {
  private final boolean quit;
  private final String arg2;
  private final String arg3;
  private final String arg4;
  private final String arg5;

  /**
   * Initializes the 5 parameters that allows the method to run.
   * @param quit Determines whether the client has quit yet.
   * @param arg2 How much of the original the client wants the height to be (in percent).
   * @param arg3 How much of the original the client wants the width to be (in percent).
   * @param arg4 The image the client wants to change.
   * @param arg5 The name that the client wants to assign to the changed image.
   */
  public Downsize(boolean quit, String arg2, String arg3, String arg4, String arg5) {
    this.quit = quit;
    this.arg2 = arg2;
    this.arg3 = arg3;
    this.arg4 = arg4;
    this.arg5 = arg5;
  }

  /**
   * Executes the downsize() method on the given model as long as the user has not already
   * quit.
   *
   * @param model The model that will execute the Downsize Operation.
   * @param view  The view that will render the error message (if needed).
   */
  @Override
  public void run(PhotoshopAdditionalOperationsInterface model, PhotoshopViewInterface view)
          throws IOException {
    if (!this.quit) {
      try {
        model.downsize(Integer.parseInt(this.arg2), Integer.parseInt(this.arg3),
                this.arg4, this.arg5);
        view.renderMessage("Successfully downsized \n");
      } catch (IllegalArgumentException | IOException e) {
        view.renderMessage(e.getMessage() + "\n");
      }
    }
  }
}

