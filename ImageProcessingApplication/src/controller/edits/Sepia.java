package controller.edits;

/**
 * Gets Sepia version of an image.
 */
public class Sepia extends AbstractColorTransformation {

  /**
   * Constructor to create Sepia filter.
   */
  public Sepia() {
    super(new double[][]{
        new double[]{0.393, 0.769, 0.189},
        new double[]{0.349, 0.686, 0.168},
        new double[]{0.272, 0.534, 0.131}
    });
  }
}
