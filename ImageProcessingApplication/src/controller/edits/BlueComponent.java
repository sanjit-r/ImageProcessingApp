package controller.edits;


import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.InterfacePixel;
import model.image.PixelClass;


/**
 * Constructs bluecomponent of image.
 */
public class BlueComponent extends AbstractRest {

  @Override
  public ImageInterface apply(ImageInterface image) {

    int w = image.getWidth();
    int h = image.getHeight();
    InterfacePixel[][] pixels = image.getPixels();
    InterfacePixel[][] nPixels = new InterfacePixel[h][w];

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        nPixels[i][j] = new PixelClass(pixels[i][j].getB(),
                pixels[i][j].getB(),
                pixels[i][j].getB());

      }
    }
    return new ImageImpl(nPixels, w, h);
  }
}

