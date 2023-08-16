package controller.edits;


import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.InterfacePixel;
import model.image.PixelClass;


/**
 * Darkens an image.
 */
public class Darken extends AbstractBD {

  @Override
  public ImageInterface apply(ImageInterface image, String s) {
    int width = image.getWidth();
    int height = image.getHeight();
    InterfacePixel[][] pixels = image.getPixels();

    InterfacePixel[][] newPixels = new InterfacePixel[height][width];
    int yoyoyo;

    yoyoyo = Integer.parseInt(s);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int dR = pixels[i][j].getR();
        int dG = pixels[i][j].getG();
        int dB = pixels[i][j].getB();

        if (dR - yoyoyo < 0) {
          dR = 0;
        } else {
          dR = dR - yoyoyo;
        }

        if (dG - yoyoyo < 0) {
          dG = 0;
        } else {
          dG = dG - yoyoyo;
        }

        if (dB - yoyoyo < 0) {
          dB = 0;
        } else {
          dB = dB - yoyoyo;
        }

        newPixels[i][j] = new PixelClass(dR, dG, dB);
      }
    }

    return new ImageImpl(newPixels, width, height);
  }

}


