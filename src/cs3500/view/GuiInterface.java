package cs3500.view;

import java.awt.event.ActionListener;

import cs3500.controller.PhotoshopController;

/**
 * GuiInterface holds all the methods that are needed to create a GUI, and for the client to see it.
 */
public interface GuiInterface extends PhotoshopViewInterface {

  void refresh();

  void makeVisible();

  String getMethod();

  String getCurrentImageName();

  void setActionListener(ActionListener listener);

  void setController(PhotoshopController controller);

  void addImageName(String imageName);

}
