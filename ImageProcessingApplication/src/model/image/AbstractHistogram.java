package model.image;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for abstract histogram.
 */
public abstract class AbstractHistogram {

  private Map<Integer, Integer> list;

  /**
   * Constructor for the histogram.
   * @param type type of histogram, rgb
   * @param pixels pixels to be implemented on
   */
  public AbstractHistogram(String type, InterfacePixel[][] pixels) {
    this.list = new HashMap<>();

    for (int i = 0; i <= 255; i++) {
      list.put(i, 0);
    }

    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        InterfacePixel yes = pixels[row][col];
        int save = 0;
        if (type.equals("red")) {
          save = yes.getR();
        }
        else if (type.equals("green")) {
          save = yes.getG();
        }
        else if (type.equals("blue")) {
          save = yes.getB();
        }
        else {
          save = yes.getIntensity();
        }
        list.put(save, list.get(save) + 1);
      }
    }

  }

  /**
   * Gets the histogram.
   * @return the histogram.
   */
  public Map<Integer, Integer> getHistogram() {
    return new HashMap<>(list);
  }
}
