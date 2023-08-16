
package controller.edits;


import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.InterfacePixel;
import model.image.PixelClass;


/**
 * Gets luna component of the image.
 */
public class LumaComponent extends AbstractRest {


  @Override
  public ImageInterface apply(ImageInterface image) {

    int w = image.getWidth();
    int h = image.getHeight();
    InterfacePixel[][] pixels = image.getPixels();
    InterfacePixel[][] nPixels = new InterfacePixel[h][w];

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        nPixels[i][j] = new PixelClass(pixels[i][j].getLuma(),
                pixels[i][j].getLuma(),
                pixels[i][j].getLuma());

      }
    }
    return new ImageImpl(nPixels, w, h);
  }
}