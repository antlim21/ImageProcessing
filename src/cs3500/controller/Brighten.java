package cs3500.controller;

import java.io.IOException;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.PhotoshopViewInterface;

/**
 * Represents the operation for the Brighten method.
 */
public class Brighten implements PhotoshopCommand {
  private final boolean quit;
  private final String arg2;
  private final String arg3;
  private final String arg4;

  /**
   * Initializes three parameters that allows method to run.
   *
   * @param quit Determines whether the client has quit yet.
   * @param arg2 How much the client wants to brighten by.
   * @param arg3 The image the client wants to change.
   * @param arg4 The name that the client wants to assign to the changed image.
   *
   */
  public Brighten(boolean quit, String arg2, String arg3, String arg4) {
    this.quit = quit;
    this.arg2 = arg2;
    this.arg3 = arg3;
    this.arg4 = arg4;
  }

  /**
   * Executes the brighten() method on the given model as long as the user has not already
   * quit.
   *
   * @param model The model that will execute the Brighten Operation.
   * @param view  The view that will render the error message (if needed).
   */
  @Override
  public void run(PhotoshopAdditionalOperationsInterface model, PhotoshopViewInterface view)
          throws IOException {
    if (!this.quit) {
      try {
        model.brighten(Integer.parseInt(this.arg2), this.arg3, this.arg4);
        view.renderMessage("Successfully brightened \n");
      } catch (IllegalArgumentException | IOException e) {
        view.renderMessage(e.getMessage() + "\n");
      }
    }
  }
}
