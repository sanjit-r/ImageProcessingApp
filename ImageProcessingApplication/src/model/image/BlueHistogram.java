package model.image;

/**
 * BlueHistogram class.
 */
public class BlueHistogram extends AbstractHistogram {

  /**
   * BlueHistogram constructor w/ 1 parameter.
   * @param pixels pixels of the interface
   */
  public BlueHistogram(InterfacePixel[][] pixels) {
    super("blue", pixels);
  }
}
