package controller;

import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import controller.edits.AbstractBD;
import controller.edits.BlueComponent;
import controller.edits.Blur;
import controller.edits.Brighten;
import controller.edits.Darken;
import controller.edits.Edit;
import controller.edits.GrayScale;
import controller.edits.GreenComponent;
import controller.edits.Greyscale;
import controller.edits.HorizontalFlip;
import controller.edits.IntensityComponent;
import controller.edits.LumaComponent;
import controller.edits.RedComponent;
import controller.edits.Sepia;
import controller.edits.Sharpen;
import controller.edits.VerticalFlip;
import model.image.ImageImpl;
import model.image.ImageInterface;
import model.image.ImageUtil;
import view.ImageGUIView;


/**
 * GUI Controller class.
 */
public class ImageControllerGUI implements Features, ActionListener {

  private Readable in;
  private final ImageGUIView view;

  ImageInterface currentVersion;

  ArrayList<ImageInterface> previousVersions = new ArrayList<ImageInterface>();

  private final HashMap<String, ImageInterface> models;
  private final HashMap<String, Edit> commands;
  private final HashMap<String, AbstractBD> commandsWeird;

  private static final String name = "model";

  String currentFile;



  /**
   * No parameter constructor.
   */
  public ImageControllerGUI(ImageGUIView view) {
    this.in = new InputStreamReader(System.in);
    this.view = view;
    this.models = new HashMap<>();

    this.commands = new HashMap<>();
    this.commandsWeird = new HashMap<>();

    commandsWeird.put("darken", new Darken());
    commandsWeird.put("brighten", new Brighten());
    commands.put("sharpen", new Sharpen());
    commands.put("blur", new Blur());
    commands.put("sepia", new Sepia());
    commands.put("greyscale", new Greyscale());
    commands.put("red-component", new RedComponent());
    commands.put("green-component", new GreenComponent());
    commands.put("blue-component", new BlueComponent());
    commands.put("horizontal-flip", new HorizontalFlip());
    commands.put("vertical-flip", new VerticalFlip());
    commands.put("luma-component", new LumaComponent());
    commands.put("intensity-component", new IntensityComponent());
    commands.put("value-component", new GrayScale());

  }

  /**
   * Constructor takes in 3 parameters.
   *
   * @param in     reads user input
   * @param models hashmap for storing data
   * @param view   displays messages for user
   */
  public ImageControllerGUI(Readable in,
                            HashMap<String, ImageInterface> models, ImageGUIView view) {
    this.in = in;
    this.view = view;
    this.models = models;
    this.commands = new HashMap<>();
    this.commandsWeird = new HashMap<>();

    commandsWeird.put("darken", new Darken());
    commandsWeird.put("brighten", new Brighten());
    commands.put("sharpen", new Sharpen());
    commands.put("blur", new Blur());
    commands.put("sepia", new Sepia());
    commands.put("greyscale", new Greyscale());
    commands.put("red-component", new RedComponent());
    commands.put("green-component", new GreenComponent());
    commands.put("blue-component", new BlueComponent());
    commands.put("horizontal-flip", new HorizontalFlip());
    commands.put("vertical-flip", new VerticalFlip());
    commands.put("luma-component", new LumaComponent());
    commands.put("intensity-component", new IntensityComponent());
    commands.put("value-component", new GrayScale());
  }


  @Override
  public void load(String path) {
    try {
      if (path.substring(path.length() - 3).equals("ppm")) {
        models.put(name, new ImageImpl(path));
        currentVersion = models.get(name);
      } else {
        models.put(name, ImageUtil.readMainstreamFormat(path));
        currentVersion = models.get(name);
        view.drawImage(currentVersion);
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog((Component) view, "Invalid File Format");
      throw new RuntimeException(e);
    }
  }

  @Override
  public void save(String path) {
    try {
      String extension = path.substring(path.length() - 3);
      if (extension.equals("ppm")) {
        int w = currentVersion.getWidth();
        int h = currentVersion.getHeight();
        PrintWriter save = new PrintWriter(path);
        save.println("P3");
        save.println(w + " " + h);
        save.println("255");

        for (int i = 0; i < h; i++) {
          for (int j = 0; j < w; j++) {
            save.println(currentVersion.getPixels()[i][j].getR());
            save.println(currentVersion.getPixels()[i][j].getG());
            save.println(currentVersion.getPixels()[i][j].getB());
          }
        }
        save.close();
      } else if (extension.equals("jpg")
          || extension.equals("png")
          || extension.equals("bmp")) {
        BufferedImage image =
            new BufferedImage(currentVersion.getWidth(),
              currentVersion.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for (int row = 0; row < image.getHeight(); row++) {
          for (int col = 0; col < image.getWidth(); col++) {
            Color color =
                new Color(currentVersion.getPixels()[row][col].getR(),
                  currentVersion.getPixels()[row][col].getG(),
                  currentVersion.getPixels()[row][col].getB());
            image.setRGB(col, row, color.getRGB());
          }
        }
        ImageIO.write(image, extension, new File(path));
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog((Component) view, "Invalid Save Format");
      throw new RuntimeException(e);
    }
  }


  @Override
  public void actionPerformed(ActionEvent argument) {
    switch (argument.getActionCommand()) {
      case "Open file":
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            " PNG/PPM/JPG", "jpg", "png", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(null);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          currentFile = f.getAbsolutePath();
          load(currentFile);
        }
        break;
      case "vertical-flip":
        currentVersion = commands.get("vertical-flip").apply(currentVersion);
        break;
      case "horizontal-flip":
        currentVersion = commands.get("horizontal-flip").apply(currentVersion);
        break;
      case "blur":
        currentVersion = commands.get("blur").apply(currentVersion);
        break;
      case "sharpen":
        currentVersion = commands.get("sharpen").apply(currentVersion);
        break;
      case "sepia":
        currentVersion = commands.get("sepia").apply(currentVersion);
        break;
      case "greyscale":
        currentVersion = commands.get("greyscale").apply(currentVersion);
        break;
      case "red-component":
        currentVersion = commands.get("red-component").apply(currentVersion);
        break;
      case "blue-component":
        currentVersion = commands.get("blue-component").apply(currentVersion);
        break;
      case "green-component":
        currentVersion = commands.get("green-component").apply(currentVersion);
        break;
      case "luma-component":
        currentVersion = commands.get("luma-component").apply(currentVersion);
        break;
      case "intensity-component":
        currentVersion = commands.get("intensity-component").apply(currentVersion);
        break;
      case "value-component":
        currentVersion = commands.get("value-component").apply(currentVersion);
        break;
      case "darken":
        currentVersion = commandsWeird.get("darken").apply(currentVersion, "30");
        break;
      case "brighten":
        currentVersion = commandsWeird.get("brighten").apply(currentVersion, "30");
        break;
      case "save":
        final JFileChooser fSaver = new JFileChooser(".");
        FileNameExtensionFilter filter1  = new FileNameExtensionFilter(
            " PNG/PPM/JPG", "jpg", "png", "ppm");
        fSaver.setFileFilter(filter1);
        int value = fSaver.showSaveDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
          File f = fSaver.getSelectedFile();
          save(f.getName());
        }
        break;
      default:
        System.out.println("Unknown Command");
        break;
    }
    models.put("model-edit", currentVersion);
    view.drawHistogram(currentVersion);
    view.drawImage(currentVersion);
  }
}
