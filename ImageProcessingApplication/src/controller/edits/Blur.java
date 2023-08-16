package controller.edits;

/**
 * Gets Blurred version of an image.
 */
public class Blur extends AbstractFilter {

  /**
   * Constructor for gaussian blur filter.
   */
  public Blur() {
    this.matrix = new double[][]{
        new double[]{0.0625, 0.125, 0.0625},
        new double[]{0.125, 0.25, 0.125},
        new double[]{0.0625, 0.125, 0.0625}
    };
  }
}
