package model.image;


/**
 * Intensity histogram class.
 */
public class IntensityHistogram extends AbstractHistogram {

  /**
   * Intensity histogram constructor.
   * @param pixels pixels
   */
  public IntensityHistogram(InterfacePixel[][] pixels) {
    super("intensity", pixels);
  }
}
