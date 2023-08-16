package model.image;

/**
 * PixelClass class.
 */
public class PixelClass implements InterfacePixel {

  private int first;
  private int second;
  private int third;

  /**
   * 3 parameter constructor.
   *
   * @param first  red
   * @param second green
   * @param third  blue
   * @throws IllegalArgumentException bad pixel
   */
  public PixelClass(int first, int second, int third) throws IllegalArgumentException {
    if (first < 0 || first > 255 || second < 0 || second > 255 || third < 0 || third > 255) {
      throw new IllegalArgumentException("Pixel components must be a valid integer " +
              "between 0 and 255 (inclusive).");
    }

    this.first = first;
    this.second = second;
    this.third = third;
  }

  /**
   * Gets red component.
   *
   * @return red component of the pixel
   */
  @Override
  public int getR() {
    return this.first;
  }

  /**
   * Gets green component.
   *
   * @return green component of the pixel
   */
  @Override
  public int getG() {
    return this.second;
  }

  /**
   * Gets blue component.
   *
   * @return blue component of the pixel
   */
  @Override
  public int getB() {
    return this.third;
  }

  /**
   * Returns average value.
   *
   * @return average value
   */
  @Override
  public int getValue() {
    return Math.max(Math.max(first, second), third);

  }

  /**
   * Intensity of the pixel.
   *
   * @return highest value of the pixel
   */
  @Override
  public int getIntensity() {
    return Math.round((first + second + third) / 3);
  }

  /**
   * Weighted sum of the values.
   *
   * @return luma of the pixel
   */
  @Override
  public int getLuma() {
    return (int) Math.round((0.2126 * first + 0.7152 * second + 0.0722 * third));
  }


  /**
   * Checks if object is equal to given object.
   *
   * @param o Object
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof InterfacePixel)) {
      return false;
    }

    InterfacePixel ip = (InterfacePixel) o;

    return this.getR() == ip.getR()
            && this.getG() == ip.getG()
            && this.getB() == ip.getB();
  }

  @Override
  public int hashCode() {

    return 0;
  }

}
