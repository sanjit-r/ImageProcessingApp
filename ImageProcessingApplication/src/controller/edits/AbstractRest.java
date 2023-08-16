package controller.edits;

import model.image.ImageInterface;

/**
 * Abstract class for anything but brighten and darken.
 */
public abstract class AbstractRest implements Edit {
  public abstract ImageInterface apply(ImageInterface image);
}
