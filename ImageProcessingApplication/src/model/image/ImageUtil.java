package model.image;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from
 * file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static InterfacePixel[][] constructPixelsFromPPM(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
      StringBuilder builder = new StringBuilder();
      while (sc.hasNextLine()) {
        String help = sc.nextLine();
        if (help.charAt(0) != '#') {
          builder.append(help + System.lineSeparator());
        }
      }

      sc = new Scanner(builder.toString());

      String goated;
      goated = sc.next();
      int w = sc.nextInt();
      int h = sc.nextInt();
      int max = sc.nextInt();

      InterfacePixel[][] pixels = new InterfacePixel[h][w];
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          int red = sc.nextInt();
          int green = sc.nextInt();
          int blue = sc.nextInt();
          pixels[i][j] = new PixelClass(red, green, blue);
        }
      }
      return pixels;
    } catch (FileNotFoundException e) {
      throw new IOException();
    }
  }

  /**
   * Reads in files of mainsteam formats.
   *
   * @param filepath filename
   * @return new ImageImpl from file
   * @throws IOException bad filename
   */
  public static ImageInterface readMainstreamFormat(String filepath) throws IOException {
    BufferedImage image = ImageIO.read(new File(filepath));
    InterfacePixel[][] pixels = new InterfacePixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color color = new Color(image.getRGB(j, i));
        pixels[i][j] = new PixelClass(color.getRed(), color.getGreen(), color.getBlue());
      }
    }
    return new ImageImpl(pixels, image.getWidth(), image.getHeight());
  }

  /**
   * Gets width.
   *
   * @param filename filename
   * @return integer of width
   * @throws IOException bad filename
   */
  public static int getWidth(String filename) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
      StringBuilder b = new StringBuilder();
      while (sc.hasNextLine()) {
        String help = sc.nextLine();
        if (help.charAt(0) != '#') {
          b.append(help + System.lineSeparator());
        }
      }
      sc = new Scanner(b.toString());
      String placeHolder = sc.nextLine();
      int width = sc.nextInt();

      return width;

    } catch (FileNotFoundException | InputMismatchException e) {
      throw new IOException();
    }

  }

  /**
   * Gets height of image.
   *
   * @param filename Name of file
   * @return height of image
   * @throws IOException if invalid ppm file
   */
  public static int getHeight(String filename) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
      StringBuilder b = new StringBuilder();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          b.append(s + System.lineSeparator());
        }
      }
      sc = new Scanner(b.toString());
      String placeHolder = sc.nextLine();
      int placeHolder2 = sc.nextInt();
      int height = sc.nextInt();

      return height;

    } catch (FileNotFoundException | InputMismatchException e) {
      throw new IOException();
    }
  }

}

