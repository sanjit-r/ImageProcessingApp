package controller.edits;

/**
 * Creates greyscale image filter.
 */
public class Greyscale extends AbstractColorTransformation {

  /**
   * Constructor to create greyscale image filter.
   */
  public Greyscale() {
    super(new double[][]{
        new double[]{0.2126, 0.7152, 0.0722},
        new double[]{0.2126, 0.7152, 0.0722},
        new double[]{0.2126, 0.7152, 0.0722}
    });
  }
}
