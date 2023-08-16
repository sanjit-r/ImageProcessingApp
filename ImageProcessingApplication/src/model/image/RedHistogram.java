package model.image;

/**
 * Red Histogram class.
 */
public class RedHistogram extends AbstractHistogram {

  /**
   * Red Histogram constructor.
   * @param pixels pixels
   */
  public RedHistogram(InterfacePixel[][] pixels) {
    super("red", pixels);
  }
}