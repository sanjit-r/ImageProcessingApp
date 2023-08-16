package controller.edits;

import model.image.ImageInterface;

/**
 * Abstract class for brighten and darken.
 */
public abstract class AbstractBD {
  public abstract ImageInterface apply(ImageInterface image, String s);
}
