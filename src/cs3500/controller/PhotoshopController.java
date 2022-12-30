package cs3500.controller;

import java.io.IOException;

/**
 * Interface to represent a controller for Photoshop. Has one void method to run the photoshop
 * program.
 */
public interface PhotoshopController {
  void runPhotoshop() throws IllegalStateException, IOException;

  void setReader(Readable reader);
}
