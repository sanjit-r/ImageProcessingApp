package model.image;


/**
 * ImageInterface.
 */
public interface ImageInterface {
  /**
   * Gets pixels of image.
   *
   * @return the pixels of the image.
   */
  InterfacePixel[][] getPixels();

  /**
   * Gets width of image.
   *
   * @return width of image.
   */
  int getWidth();


  /**
   * Gets height of image.
   *
   * @return height of image.
   */
  int getHeight();

  @Override
  boolean equals(Object o);


}
