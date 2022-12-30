package cs3500.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.GuiInterface;

/**
 * PhotoshopControllerImpl is the implementation of its interface. It has one method, runPhotoshop,
 * which allows the client to run photoshop and edit their images.
 */
public class PhotoshopControllerImpl implements PhotoshopController {

  private PhotoshopAdditionalOperationsInterface model;
  protected GuiInterface view;
  protected Scanner scan;

  protected String arg1;
  protected String arg2;
  protected String arg3;
  protected String arg4;
  protected String arg5;
  boolean quit;

  /**
   * Where model, view, and readable is initialized.
   *
   * @param model    Photoshop implementation that the arguments are parsed through in order to
   *                 make he edits.
   * @param view     What the client can see. Deals with what the client can see in the console
   *                 after they put in arguments to edit their picture.
   * @param readable What the client puts in. Arguments that the client puts in the console to
   *                 edit their photos.
   */
  public PhotoshopControllerImpl(PhotoshopAdditionalOperationsInterface model,
                                 GuiInterface view, Readable readable) {

    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }

    if (view == null) {
      throw new IllegalArgumentException("View can't be null");
    }

    if (readable == null) {
      throw new IllegalArgumentException("Readable can't be null");
    }

    this.model = model;
    this.view = view;
    this.view.setController(this);
    this.scan = new Scanner(readable);
    this.arg1 = "";
    this.arg2 = "";
    this.arg3 = "";
    this.arg4 = "";
    this.arg5 = "";

    this.quit = false;

  }


  private void findArgs() throws IllegalArgumentException {
    if (this.scan.hasNext()) {
      this.arg2 = this.scan.next();
    } else {
      throw new IllegalStateException("Out of Inputs");
    }

    if (this.arg2.equalsIgnoreCase("q")) {
      this.quit = true;
    }

    if (this.scan.hasNext()) {
      this.arg3 = this.scan.next();
    } else if (arg3 == null && arg1.equals("-file")) {
      this.quit = false;
    } else {
      if (!this.quit) {
        throw new IllegalStateException("Out of Inputs");
      }
    }

    if (this.arg3.equalsIgnoreCase("q")) {
      this.quit = true;
    }

  }

  /**
   * Runs the photoshop program. Allows client to put in different edit components and outputs
   * an edited image.
   *
   * @throws IllegalStateException Throws Illegal State Exception if the name of the file client
   *                               wants to grab is not valid.
   * @throws IOException           Throws IOException if you aren't able to render message.
   */
  @Override
  public void runPhotoshop() throws IllegalStateException, IOException {

    this.view.makeVisible();
    this.quit = false;
    //String[] input = scan.nextLine().split(" ");
    while (this.scan.hasNext() && !quit) {
      this.arg1 = this.scan.next();
      this.arg2 = "";
      this.arg3 = "";
      this.arg4 = "";
      this.arg5 = "";

      if (this.arg1.equalsIgnoreCase("q")) {
        this.quit = true;
        view.renderMessage("Thank you for using photoshop program.");
      } else {
        PhotoshopCommand cmd = null;
        boolean validArg1 = true;
        switch (arg1) {
          case "load":
            findArgs();
            cmd = new Load(this.quit, this.arg2, this.arg3);
            view.refresh();
            break;
          case "save":
            System.out.println("Saving");
            findArgs();
            cmd = new Save(this.quit, this.arg2, this.arg3);
            break;
          case "red-component":
            System.out.println("red Component");
            findArgs();
            cmd = new RedComponent(this.quit, this.arg2, this.arg3);
            break;
          case "green-component":
            findArgs();
            cmd = new GreenComponent(this.quit, this.arg2, this.arg3);
            break;
          case "blue-component":
            findArgs();
            cmd = new BlueComponent(this.quit, this.arg2, this.arg3);
            break;
          case "value-component":
            findArgs();
            cmd = new Value(this.quit, this.arg2, this.arg3);
            break;
          case "intensity-component":
            findArgs();
            cmd = new Intensity(this.quit, this.arg2, this.arg3);
            break;
          case "luma-component":
            findArgs();
            cmd = new Luma(this.quit, this.arg2, this.arg3);
            break;
          case "horizontal-flip":
            findArgs();
            cmd = new HorizontalFlip(this.quit, this.arg2, this.arg3);
            break;
          case "vertical-flip":
            findArgs();
            cmd = new VerticalFlip(this.quit, this.arg2, this.arg3);
            break;
          case "brighten":
            findArgs();
            if (scan.hasNext()) {
              arg4 = scan.next();
              if (arg4.equals("q")) {
                quit = true;
              } else {
                cmd = new Brighten(this.quit, this.arg2, this.arg3, this.arg4);
              }
            } else {
              if (!this.quit) {
                throw new IllegalStateException("Out of Inputs");
              }
            }
            break;
          case "downsize":
            findArgs();
            if (scan.hasNext()) {
              arg4 = scan.next();
            }
            if (scan.hasNext()) {
              arg5 = scan.next();
            }
            if (arg4.equals("q")) {
              quit = true;
            } else if (arg5.equals("q")) {
              quit = true;
            } else {
              cmd = new Downsize(this.quit, this.arg2, this.arg3, this.arg4, this.arg5);
            }
            break;
          case "blur":
            findArgs();
            cmd = new Blur(this.quit, this.arg2, this.arg3);
            break;
          case "sharpen":
            findArgs();
            cmd = new Sharpen(this.quit, this.arg2, this.arg3);
            break;
          case "greyscale":
            findArgs();
            cmd = new Greyscale(this.quit, this.arg2, this.arg3);
            break;
          case "sepia":
            findArgs();
            cmd = new Sepia(this.quit, this.arg2, this.arg3);
            break;
          default:
            validArg1 = false;
            cmd = null;
            try {
              this.view.renderMessage("Invalid Input\n");
            } catch (IOException e) {
              System.out.println(e);
            }
        }
        if (validArg1) {
          if (this.arg1.equals("brighten")) {
            this.view.addImageName(this.arg3);
          } else {
            this.view.addImageName(this.arg2);
          }
        }
        if (cmd != null) {
          cmd.run(model, view);
        }
      }
    }
  }

  /**
   * Reads the arguments sent after the client presses a button to make changes to the image.
   * @param reader Where the arguments are taken in for the GUI to be able to run.
   */
  public void setReader(Readable reader) {
    this.scan = new Scanner(reader);
    try {
      this.runPhotoshop();
      System.out.println("Run Photoshop Again");
    } catch (IOException e) {
      System.out.println("Failed Run Photoshop Again");
    }

  }
}
