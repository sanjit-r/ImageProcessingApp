package model.image;

/**
 * Green Histogram class.
 */
public class GreenHistogram extends AbstractHistogram {

  /**
   * Green histogram constructor.
   * @param pixels pixels
   */
  public GreenHistogram(InterfacePixel[][] pixels) {
    super("green", pixels);
  }
}
