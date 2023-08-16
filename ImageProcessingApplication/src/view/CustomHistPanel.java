package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.swing.JComponent;
import javax.swing.JPanel;

import model.image.AbstractHistogram;
import model.image.BlueHistogram;
import model.image.GreenHistogram;
import model.image.ImageInterface;
import model.image.IntensityHistogram;
import model.image.InterfacePixel;
import model.image.RedHistogram;


/**
 * Class to translate image data to a histogram.
 */
public class CustomHistPanel extends JPanel {
  private List<AbstractHistogram> histograms;
  private JComponent parent;

  /**
   * Constructor takes in 3 parameters.
   *
   * @param image  Image to translate to histogram
   * @param parent parent JComponent
   */
  public CustomHistPanel(ImageInterface image, JComponent parent) {
    this.parent = parent;
    this.histograms = new ArrayList<>();
    setHistograms(image);
    setPreferredSize(parent.getPreferredSize());
  }

  /**
   * Method to get color data from image.
   *
   * @param image Image to translate to histogram
   */
  private void setHistograms(ImageInterface image) {
    InterfacePixel[][] pixels = image.getPixels();
    boolean allGray = true;
    for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[0].length; c++) {
        InterfacePixel pixel = pixels[r][c];
        if (!(pixel.getR() == pixel.getG() && pixel.getR() == pixel.getB()
                && pixel.getG() == pixel.getB())) {
          allGray = false;
        }
      }
    }
    if (!allGray) {
      histograms.add(new RedHistogram(pixels));
      histograms.add(new GreenHistogram(pixels));
      histograms.add(new BlueHistogram(pixels));
    }
    histograms.add(new IntensityHistogram(pixels));
  }

  /**
   * Method to draw histogram.
   *
   * @param g graphics object
   */
  @Override
  public void paintComponent(Graphics g) {
    Graphics2D graphics2D = (Graphics2D) g;
    for (int i = 0; i < histograms.size(); i++) {
      Map<Integer, Integer> currentHistogram = histograms.get(i).getHistogram();
      int highest = getHighest(currentHistogram);
      switch (i) {
        case 0:
          graphics2D.setColor(new Color(255, 0, 0, 80));
          break;
        case 1:
          graphics2D.setColor(new Color(0, 255, 0, 80));
          break;
        case 2:
          graphics2D.setColor(new Color(0, 0, 255, 80));
          break;
        default:
          graphics2D.setColor(new Color(200, 200, 200, 80));
          break;
      }
      for (int j = 0; j < 256; j++) {
        int width = 4;
        int height = currentHistogram.get(j) * parent.getHeight() / highest + 30;
        graphics2D.fillRect(width * j, parent.getHeight() - height, width, height);
      }
    }
    setVisible(true);
  }

  private int getHighest(Map<Integer, Integer> h) {
    int x = 0;
    for (Map.Entry<Integer, Integer> entry : h.entrySet()) {
      x = Math.max(x, entry.getValue());
    }
    return x;
  }
}
