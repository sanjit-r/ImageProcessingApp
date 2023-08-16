package controller.edits;


import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.InterfacePixel;
import model.image.PixelClass;


/**
 * Brightens the image.
 */
public class Brighten extends AbstractBD {

  @Override
  public ImageInterface apply(ImageInterface image, String s) {
    int width = image.getWidth();
    int height = image.getHeight();
    InterfacePixel[][] pixels = image.getPixels();

    InterfacePixel[][] newPixels = new InterfacePixel[height][width];
    int parsed;

    parsed = Integer.parseInt(s);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int bR = pixels[i][j].getR();
        int bG = pixels[i][j].getG();
        int bB = pixels[i][j].getB();

        if (bR + parsed > 255) {
          bR = 255;
        } else {
          bR = bR + parsed;
        }

        if (bG + parsed > 255) {
          bG = 255;
        } else {
          bG = bG + parsed;
        }

        if (bB + parsed > 255) {
          bB = 255;
        } else {
          bB = bB + parsed;
        }

        newPixels[i][j] = new PixelClass(bR, bG, bB);
      }
    }

    return new ImageImpl(newPixels, width, height);
  }
}


