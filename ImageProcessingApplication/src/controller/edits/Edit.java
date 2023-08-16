package controller.edits;

import model.image.ImageInterface;

/**
 * This interface is for applying edits to the given images.
 */
public interface Edit {
  ImageInterface apply(ImageInterface image);
}
