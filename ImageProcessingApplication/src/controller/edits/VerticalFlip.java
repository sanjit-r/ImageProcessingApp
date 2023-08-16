package controller.edits;


import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.InterfacePixel;

/**
 * Vertically flips image.
 */
public class VerticalFlip extends AbstractRest {
  @Override
  public ImageInterface apply(ImageInterface image) {

    int w = image.getWidth();
    int h = image.getHeight();
    InterfacePixel[][] pixels = image.getPixels();
    InterfacePixel[][] nPixels = new InterfacePixel[h][w];

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        nPixels[i][j] = pixels[h - 1 - i][j];
      }
    }
    return new ImageImpl(nPixels, w, h);
  }

}
