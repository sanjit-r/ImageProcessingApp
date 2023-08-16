package view;

import java.io.IOException;

/**
 * Implementation of ImageView.
 */
public class ImageViewImpl implements ImageView {

  private Appendable help;

  public ImageViewImpl() {
    this.help = System.out;
  }

  public ImageViewImpl(Appendable help) {
    this.help = help;
  }

  /**
   * Renders message to the user.
   *
   * @param message the message that needs to be rendered
   * @throws IOException if IOException error
   */
  @Override
  public void renderMessage(String message) throws IOException {
    help.append(message);
  }
}
