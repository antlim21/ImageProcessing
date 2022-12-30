package cs3500.controller;

import java.io.IOException;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.PhotoshopViewInterface;

/**
 * Represents the operation for the Intensity method.
 */
public class Intensity implements PhotoshopCommand {
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
  public Intensity(boolean quit, String arg2, String arg3) {
    this.quit = quit;
    this.arg2 = arg2;
    this.arg3 = arg3;
  }

  /**
   * Executes the intensity-component() method on the given model as long as the user has not
   * already quit.
   *
   * @param model The model that will execute the Intensity Operation.
   * @param view  The view that will render the error message (if needed).
   */
  @Override
  public void run(PhotoshopAdditionalOperationsInterface model, PhotoshopViewInterface view)
          throws IOException {
    if (!this.quit) {
      try {
        model.intensity(this.arg2, this.arg3);
        view.renderMessage("Successfully grey-scaled via intensity component \n");

      } catch (IllegalArgumentException | IOException e) {
        view.renderMessage(e.getMessage() + "\n");
      }
    }
  }
}
