package view;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;


import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import controller.ImageControllerGUI;
import model.image.ImageInterface;



/**
 * Class to create graphical user interface.
 */
public class ImageGUI extends JFrame implements ImageGUIView {


  private final ImageIcon mainImage;

  private Appendable help;

  private final JLabel picLabel;

  private final JButton fileOpenButton;
  private final JButton flipVerticalButton;
  private final JButton flipHorizontalButton;
  private final JButton darkenButton;
  private final JButton brightenButton;
  private final JButton sharpenButton;
  private final JButton blurButton;
  private final JButton sepiaButton;
  private final JButton greyscaleButton;
  private final JButton redButton;
  private final JButton greenButton;
  private final JButton blueButton;
  private final JButton lumaButton;
  private final JButton intensityButton;
  private final JButton valueButton;
  private final JButton saveButton;
  private JPanel histogramPanel;

  /**
   * Constructor to create base GUI design.
   *
   */
  public ImageGUI() {
    JPanel mainPanel;
    setTitle("DaVinci Resolve");
    setSize(1920, 1080);
    this.setLayout(new BorderLayout());


    // mainPanel
    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    add(mainPanel);

    //image panel
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setPreferredSize(new Dimension(400, 800));
    imagePanel.setLayout(new GridLayout(1, 0, 0, 10));
    JScrollPane scrollImagePane = new JScrollPane(imagePanel);


    mainImage = new ImageIcon();
    picLabel = new JLabel(mainImage);
    imagePanel.add(picLabel);
    //imagePanel.setMaximumSize(null);
    mainPanel.add(scrollImagePane);

    //histogram section
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("HistogramFinal"));
    histogramPanel.setPreferredSize(new Dimension(800, 400));
    mainPanel.add(histogramPanel);

    //button section
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Image Processing"));
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
    this.add(buttonPanel, BorderLayout.EAST);

    //file open
    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    buttonPanel.add(fileOpenButton);

    //flipVertical Button
    flipVerticalButton = new JButton("Flip Vertical");
    flipVerticalButton.setActionCommand("vertical-flip");
    buttonPanel.add(flipVerticalButton);

    //flipHorizontal Button
    flipHorizontalButton = new JButton("Flip Horizontal");
    flipHorizontalButton.setActionCommand("horizontal-flip");
    buttonPanel.add(flipHorizontalButton);

    //Darken Button
    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("darken");
    buttonPanel.add(darkenButton);

    //Brighten Button
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten");
    buttonPanel.add(brightenButton);

    //Sharpen Button
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    buttonPanel.add(sharpenButton);

    //Blur Button
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    buttonPanel.add(blurButton);

    //Sepia Button
    sepiaButton = new JButton("Sepia Tone");
    sepiaButton.setActionCommand("sepia");
    buttonPanel.add(sepiaButton);

    //GreyScale Button
    greyscaleButton = new JButton("GreyScale");
    greyscaleButton.setActionCommand("greyscale");
    buttonPanel.add(greyscaleButton);

    //redcomponent Button
    redButton = new JButton("RedComponent");
    redButton.setActionCommand("red-component");
    buttonPanel.add(redButton);

    //greencomponent Button
    greenButton = new JButton("GreenComponent");
    greenButton.setActionCommand("green-component");
    buttonPanel.add(greenButton);

    //bluecomponent Button
    blueButton = new JButton("BlueComponent");
    blueButton.setActionCommand("blue-component");
    buttonPanel.add(blueButton);

    //luma Button
    lumaButton = new JButton("Luma Component");
    lumaButton.setActionCommand("luma-component");
    buttonPanel.add(lumaButton);

    //intensity Button
    intensityButton = new JButton("Intensity Component");
    intensityButton.setActionCommand("intensity-component");
    buttonPanel.add(intensityButton);

    //value Button
    valueButton = new JButton("Value Component");
    valueButton.setActionCommand("value-component");
    buttonPanel.add(valueButton);


    //save Button
    saveButton = new JButton("Save File");
    saveButton.setActionCommand("save");
    buttonPanel.add(saveButton);
  }


  @Override
  public void renderMessage(String message) throws IOException {
    help.append(message);
  }


  @Override
  public void drawImage(ImageInterface image) {
    mainImage.setImage(convertToBufferedImage(image));
    picLabel.setIcon(mainImage);
    repaint();
    revalidate();
  }

  @Override
  public void drawHistogram(ImageInterface image) {
    histogramPanel.removeAll();
    histogramPanel.add(new CustomHistPanel(image, histogramPanel));
    repaint();
    revalidate();
  }

  // convert image to bufferedImage
  @Override
  public BufferedImage convertToBufferedImage(ImageInterface image) {
    BufferedImage image1 =
        new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        Color color =
            new Color(image.getPixels()[row][col].getR(), image.getPixels()[row][col].getG(),
                image.getPixels()[row][col].getB());
        image1.setRGB(col, row, color.getRGB());
      }
    }
    return image1;
  }


  @Override
  public void addListener(ImageControllerGUI controller) {
    fileOpenButton.addActionListener(controller);
    flipVerticalButton.addActionListener(controller);
    flipHorizontalButton.addActionListener(controller);
    darkenButton.addActionListener(controller);
    brightenButton.addActionListener(controller);
    sharpenButton.addActionListener(controller);
    blurButton.addActionListener(controller);
    sepiaButton.addActionListener(controller);
    greyscaleButton.addActionListener(controller);
    redButton.addActionListener(controller);
    greenButton.addActionListener(controller);
    blueButton.addActionListener(controller);
    lumaButton.addActionListener(controller);
    intensityButton.addActionListener(controller);
    valueButton.addActionListener(controller);
    saveButton.addActionListener(controller);
  }
}

