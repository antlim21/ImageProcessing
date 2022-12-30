package cs3500;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.controller.PhotoshopController;
import cs3500.controller.PhotoshopControllerImpl;
import cs3500.controller.PhotoshopTextFileControllerImpl;
import cs3500.model.PhotoshopAdditionalOperationsImpl;
import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.GuiImpl;
import cs3500.view.GuiInterface;
import cs3500.view.PhotoshopViewImpl;

/**
 * Where the client goes to run the photoshop program in order edit images.
 */
public final class Photoshop {
  /**
   * Main method where the arguments (in string form) are put in to run photoshop program and
   * edit images.
   *
   * @param args Arguments that the client puts in to edit the image.
   * @throws IOException If model, view, or controller is null.
   */
  public static void main(String[] args) throws IOException {

    if (args.length == 0) {
      PhotoshopAdditionalOperationsInterface model = new PhotoshopAdditionalOperationsImpl();
      GuiInterface view = new GuiImpl(model);
      PhotoshopControllerImpl controller = new PhotoshopControllerImpl(model, view,
              new StringReader(""));
      view.setController(controller);
      controller.runPhotoshop();

    } else if (args[0].equals("-text")) {
      PhotoshopAdditionalOperationsInterface model = new PhotoshopAdditionalOperationsImpl();
      PhotoshopViewImpl view = new PhotoshopViewImpl(System.out);
      PhotoshopController controller = new PhotoshopTextFileControllerImpl(model, view,
              new InputStreamReader(System.in));
      controller.runPhotoshop();

    } else if (args[0].equals("-file")) {
      BufferedReader br = new BufferedReader(new FileReader(args[1]));
      String commands = "";
      String input = "";
      commands = br.readLine();
      input = input + commands + "\n";
      while (commands != null) {
        commands = br.readLine();
        input = input + commands + "\n";
      }
      PhotoshopAdditionalOperationsInterface model = new PhotoshopAdditionalOperationsImpl();
      PhotoshopViewImpl view = new PhotoshopViewImpl(System.out);
      PhotoshopController controller = new PhotoshopTextFileControllerImpl(model, view,
              new StringReader(input));
      controller.runPhotoshop();
    }
  }
}
