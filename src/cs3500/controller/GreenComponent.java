package cs3500.controller;

import java.io.IOException;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.PhotoshopViewInterface;

/**
 * Represents the operation for the Green Component method.
 */
public class GreenComponent implements PhotoshopCommand {
  private final boolean quit;
  private final String arg2;
  private final String arg3;

  /**
   * Initializes three parameters that allows method to run.
   *
   * @param quit Determines whether the client has quit yet.
   * @param arg2 The image the client wants to change.
   * @param arg3 The name that the client wants to assign to the changed image.
   */
  public GreenComponent(boolean quit, String arg2, String arg3) {
    this.quit = quit;
    this.arg2 = arg2;
    this.arg3 = arg3;
  }

  /**
   * Executes the greenComponent() method on the given model as long as the user has not already
   * quit.
   *
   * @param model The model that will execute the Green Component Operation.
   * @param view  The view that will render the error message (if needed).
   */
  @Override
  public void run(PhotoshopAdditionalOperationsInterface model, PhotoshopViewInterface view)
          throws IOException {
    if (!this.quit) {
      try {
        model.greenComponent(this.arg2, this.arg3);
        view.renderMessage("Successfully grey-scaled via green component \n");
      } catch (IllegalArgumentException | IOException e) {
        view.renderMessage(e.getMessage() + "\n");
      }
    }
  }
}
