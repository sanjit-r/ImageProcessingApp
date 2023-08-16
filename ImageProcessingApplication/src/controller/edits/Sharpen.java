package controller.edits;

/**
 * Gets Sharpened version of an image.
 */
public class Sharpen extends AbstractFilter {

  /**
   * Constructor for sharpen filter.
   */
  public Sharpen() {
    this.matrix = new double[][]{
        new double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
        new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
        new double[]{-0.125, 0.25, 1, 0.25, -0.125},
        new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
        new double[]{-0.125, -0.125, -0.125, -0.125, -0.125}
    };
  }
}
