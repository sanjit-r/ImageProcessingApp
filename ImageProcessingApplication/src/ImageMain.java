import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import controller.ImageController;
import controller.ImageControllerGUI;
import controller.ImageControllerImpl;
import view.ImageGUI;

/**
 * Main method.
 */
public class ImageMain {

  /**
   * Main method.
   *
   * @param args arguments
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      ImageGUI view = new ImageGUI();
      ImageControllerGUI controller = new ImageControllerGUI(view);
      view.addListener(controller);
      ImageGUI.setDefaultLookAndFeelDecorated(false);

      view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      view.setVisible(true);
      view.validate();
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException |
               InstantiationException e) {
        throw new RuntimeException(e);
      }
    }
    else if (args[0].equals("-text")) {
      ImageController controller = new ImageControllerImpl();
      controller.begin();
    } else if (args[0].equals("-script")) {
      ImageController controller = new ImageControllerImpl();
      controller.begin();
    } else {
      throw new IllegalArgumentException("Error: invalid command");
    }
  }
}
