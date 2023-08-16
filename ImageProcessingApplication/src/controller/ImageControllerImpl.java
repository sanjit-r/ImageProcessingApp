package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;
import javax.imageio.ImageIO;
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
import view.ImageView;
import view.ImageViewImpl;


/**
 * Controller class.
 */
public class ImageControllerImpl implements ImageController {
  private Readable in;
  private final ImageView view;
  private final HashMap<String, ImageInterface> models;
  private final HashMap<String, Edit> commands;
  private final HashMap<String, AbstractBD> commandsWeird;

  private final Scanner sc;


  /**
   * No parameter constructor.
   */
  public ImageControllerImpl() {
    this.in = new InputStreamReader(System.in);
    this.view = new ImageViewImpl();
    this.models = new HashMap<>();
    this.sc = new Scanner(in);

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
  public ImageControllerImpl(Readable in, HashMap<String, ImageInterface> models, ImageView view) {
    this.in = in;
    this.view = view;
    this.models = models;
    this.sc = new Scanner(in);

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
   * Begins/runs the application.
   */
  @Override
  public void begin() throws IllegalStateException {
    Scanner sc = new Scanner(in);
    String input;
    String scale;
    ImageInterface ii;
    String iiName;
    String iiFileName;
    String iiNewName;
    boolean quit = true;

    while (quit) {
      try {
        input = sc.next();
        if (input.equals("-file")) {
          try {
            sc = new Scanner(new FileInputStream(sc.next()));
            StringBuilder b = new StringBuilder();
            while (sc.hasNextLine()) {
              String s = sc.nextLine();
              b.append(s);
              b.append("\n");
            }
            b.append("q");
            in = new StringReader(b.toString());

            ImageController controller = new ImageControllerImpl(in, models, view);
            controller.begin();
            break;
          } catch (IOException io) {
            throw new IllegalArgumentException("Script file not read correctly");
          }
        }
        if (input.equals("load")) {
          iiFileName = sc.next();
          iiName = sc.next();
          if (iiFileName.substring(iiFileName.length() - 3).equals("ppm")) {
            ii = new ImageImpl(iiFileName);
            models.put(iiName, ii);
          } else {
            ii = ImageUtil.readMainstreamFormat(iiFileName);
            models.put(iiName, ii);
          }
        } else if (input.equalsIgnoreCase("q") ||
                input.equalsIgnoreCase("quit")) {
          quit = false;
        } else if (commandsWeird.containsKey(input)) {
          scale = sc.next();
          iiName = sc.next();
          iiNewName = sc.next();
          try {
            ii = commandsWeird.get(input).apply(models.get(iiName), scale);
          } catch (NumberFormatException e) {
            view.renderMessage("Scale must be an integer.");
            break;
          }
          models.put(iiNewName, ii);
        } else if (commands.containsKey(input)) {
          iiName = sc.next();
          iiNewName = sc.next();
          ii = commands.get(input).apply(models.get(iiName));
          models.put(iiNewName, ii);
        } else if (input.equals("save")) {
          iiFileName = sc.next();
          iiName = sc.next();
          ii = models.get(iiName);

          int w = ii.getWidth();
          int h = ii.getHeight();
          String extension = iiFileName.substring(iiFileName.length() - 3);
          if (extension.equals("ppm")) {
            PrintWriter save = new PrintWriter(iiFileName);
            save.println("P3");

            save.println(w + " " + h);
            save.println("255");

            for (int i = 0; i < h; i++) {
              for (int j = 0; j < w; j++) {
                save.println(ii.getPixels()[i][j].getR());
                save.println(ii.getPixels()[i][j].getG());
                save.println(ii.getPixels()[i][j].getB());
              }
            }
            save.close();
          } else if (extension.equals("jpg")
                  || extension.equals("png")
                  || extension.equals("bmp")) {
            BufferedImage image =
                    new BufferedImage(ii.getWidth(), ii.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            for (int row = 0; row < image.getHeight(); row++) {
              for (int col = 0; col < image.getWidth(); col++) {
                Color color =
                        new Color(ii.getPixels()[row][col].getR(), ii.getPixels()[row][col].getG(),
                        ii.getPixels()[row][col].getB());
                image.setRGB(col, row, color.getRGB());
              }
            }
            ImageIO.write(image, extension, new File(iiFileName));
          }
        }
      } catch (IOException e) {
        throw new IllegalStateException("Bad Input");
      }
    }
  }
}







