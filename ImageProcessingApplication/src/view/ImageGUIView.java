package view;

import java.awt.image.BufferedImage;

import controller.ImageControllerGUI;
import model.image.ImageInterface;

/**
 * Interface for GUI implimentation.
 */
public interface ImageGUIView extends ImageView {

  /**
   * Read an image object and draws it on gui.
   *
   * @param imageInterface Image to be drawn.
   */
  void drawImage(ImageInterface imageInterface);

  /**
   * Read a histogram as an image object and draws it on gui.
   *
   * @param imageInterface Image to be drawn.
   */
  void drawHistogram(ImageInterface imageInterface);

  /**
   * Converts an image object to a buffered image.
   *
   * @param imageInterface Image to be drawn.
   */
  BufferedImage convertToBufferedImage(ImageInterface imageInterface);

  /**
   * Creates ActionListeners for the controller.
   *
   * @param controller controller that will get commands.
   */
  void addListener(ImageControllerGUI controller);

}
