package model.image;

import java.io.IOException;
import java.util.Arrays;


/**
 * Image implemntation.
 */
public class ImageImpl implements ImageInterface {

  private InterfacePixel[][] pixels;
  private int width;
  private int height;


  /**
   * Just need credit.
   *
   * @param filename filename
   * @throws IOException if bad file
   */
  public ImageImpl(String filename) throws IOException {
    this.pixels = ImageUtil.constructPixelsFromPPM(filename);
    this.width = ImageUtil.getWidth(filename);
    this.height = ImageUtil.getHeight(filename);
  }

  /**
   * Consrtructor w/ 3 parameters.
   *
   * @param pixels pixels
   * @param width  width
   * @param height height
   */
  public ImageImpl(InterfacePixel[][] pixels, int width, int height) {
    this.pixels = pixels;
    this.width = width;
    this.height = height;
  }


  @Override
  public InterfacePixel[][] getPixels() {
    return this.pixels;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ImageInterface)) {
      return false;
    }

    ImageInterface ii = (ImageInterface) obj;

    return Arrays.deepEquals(this.getPixels(), ii.getPixels())
            && this.getWidth() == ii.getWidth()
            && this.getHeight() == ii.getHeight();
  }

  @Override
  public int hashCode() {

    return 0;
  }
}
