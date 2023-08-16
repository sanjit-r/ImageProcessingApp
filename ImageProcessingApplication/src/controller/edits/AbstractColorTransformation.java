package controller.edits;

import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.InterfacePixel;
import model.image.PixelClass;

/**
 * Abstract class for Color Transformations.
 */
public abstract class AbstractColorTransformation implements Edit {
  double[][] matrix;

  /**
   * Constructor for Color Transformations.
   */
  public AbstractColorTransformation(double[][] matrix) {
    if (matrix == null || matrix.length != 3 || matrix[0].length != 3) {
      throw new IllegalArgumentException("Must be a 3x3 matrix");
    }
    this.matrix = matrix;
  }

  /**
   * Method to apply color transformation to image.
   *
   * @param image reads user input
   * @return changed ImageImpl
   */
  public ImageInterface apply(ImageInterface image) {
    InterfacePixel[][] pixels = image.getPixels();
    InterfacePixel[][] newPixels = new InterfacePixel[image.getHeight()][image.getWidth()];
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int[] rgb = new int[3];
        for (int component = 0; component < 3; component++) {
          int value = (int) (matrix[component][0] * pixels[row][col].getR()
                  + matrix[component][1] * pixels[row][col].getG()
                  + matrix[component][2] * pixels[row][col].getB());
          if (value < 0) {
            rgb[component] = 0;
          } else if (value > 255) {
            rgb[component] = 255;
          } else {
            rgb[component] = value;
          }

        }
        newPixels[row][col] = new PixelClass(rgb[0], rgb[1], rgb[2]);
      }
    }
    return new ImageImpl(newPixels, image.getWidth(), image.getHeight());
  }
}
