package cs3500.controller;

import java.io.IOException;

import cs3500.model.PhotoshopAdditionalOperationsInterface;
import cs3500.view.PhotoshopViewInterface;

/**
 * Represents a specific command for the Model as a part of the Controller's Command Design
 * Pattern.
 */
public interface PhotoshopCommand {
  void run(PhotoshopAdditionalOperationsInterface model, PhotoshopViewInterface view)
          throws IOException;
}
