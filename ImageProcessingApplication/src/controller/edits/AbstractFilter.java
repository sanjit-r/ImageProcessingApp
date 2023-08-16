package controller.edits;

import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.InterfacePixel;
import model.image.PixelClass;

/**
 * Abstract class for applying filters to image.
 */
public class AbstractFilter implements Edit {
  double[][] matrix;

  /**
   * Constructor for applying filters to image.
   */
  public ImageInterface apply(ImageInterface image) {
    InterfacePixel[][] newPixels = new InterfacePixel[image.getHeight()][image.getWidth()];
    InterfacePixel[][] pixels = image.getPixels();
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int[] rgb = new int[3];
        for (int channel = 0; channel < 3; channel++) {
          rgb[channel] = getNewValue(row, col, channel, pixels);
        }
        newPixels[row][col] = new PixelClass(rgb[0], rgb[1], rgb[2]);
      }
    }
    return new ImageImpl(newPixels, image.getWidth(), image.getHeight());
  }

  /**
   * Method to get new value for the above method.
   *
   * @param row       given row
   * @param col       given col
   * @param component image component
   * @param pixels    2D array of pixels
   * @return changed ImageImpl
   */
  protected int getNewValue(int row, int col, int component, InterfacePixel[][] pixels) {
    InterfacePixel currentPixel = pixels[row][col];
    double currentVal;
    switch (component) {
      case 0:
        currentVal = currentPixel.getR();
        break;
      case 1:
        currentVal = currentPixel.getG();
        break;
      default:
        currentVal = currentPixel.getB();
    }
    int kerRow = 0;
    int kerCol = 0;
    for (int picRow = row - matrix.length / 2; picRow <= row + matrix.length / 2; picRow++) {
      kerCol = 0;
      for (int picCol = col - matrix.length / 2; picCol <= col + matrix.length / 2; picCol++) {
        try {
          switch (component) {
            case 0:
              currentVal += matrix[kerRow][kerCol] * pixels[picRow][picCol].getR();
              break;
            case 1:
              currentVal += matrix[kerRow][kerCol] * pixels[picRow][picCol].getG();
              break;
            default:
              currentVal += matrix[kerRow][kerCol] * pixels[picRow][picCol].getB();
              break;
          }
        } catch (IndexOutOfBoundsException e) {
          currentVal += 0;
        }
        kerCol++;
      }
      kerRow++;
    }
    currentVal = Math.round(currentVal / 2);
    if (currentVal > 255) {
      return 255;
    } else if (currentVal < 0) {
      return 0;
    }
    return (int) currentVal;
  }

}
