package model.image;

/**
 * Interface for pixels.
 */
public interface InterfacePixel {

  /**
   * Gets red component of pixel.
   *
   * @return integer representing red component of pixel
   */
  int getR();

  /**
   * Gets green component of pixel.
   *
   * @return integer representing green component of pixel
   */
  int getG();

  /**
   * Gets blue value of pixel.
   *
   * @return the integer representing the blue component of this pixel
   */
  int getB();

  /**
   * Gets value of a pixel.
   *
   * @return average value of all of the components in the pixel
   */
  int getValue();

  /**
   * Gets intensity of  pixel.
   *
   * @return gets the maximum value out of all of the components in the pixel
   */
  int getIntensity();

  /**
   * Gets luma of  pixel.
   *
   * @return weighted sum of all components of  pixel
   */
  int getLuma();

  /**
   * Checks if image is equal to the given object.
   *
   * @param o Object
   * @return true or false
   */
  @Override
  boolean equals(Object o);

}
