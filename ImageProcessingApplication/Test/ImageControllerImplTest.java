
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.edits.BlueComponent;
import controller.edits.Blur;
import controller.edits.Brighten;
import controller.edits.GreenComponent;
import controller.edits.Greyscale;
import controller.edits.HorizontalFlip;
import controller.edits.LumaComponent;
import controller.edits.RedComponent;
import controller.edits.Sepia;
import controller.edits.Sharpen;
import controller.edits.VerticalFlip;
import model.image.ImageInterface;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Runs tests for the controller.
 */
public class ImageControllerImplTest {

  Appendable log;
  ImageView view;
  HashMap<String, ImageInterface> models;

  @Before
  public void init() {
    log = new StringBuilder();
    view = new ImageViewImpl(log);
    models = new HashMap<>();
  }

  @Test
  public void testLoadPPM() {
    Readable in = new StringReader("load res/example.ppm example q\n");
    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("example"), models.get("example"));
  }

  @Test
  public void testSavePPM() {
    Readable in = new StringReader("load res/example.ppm example q\n " +
            "brighten res/example.ppm example-brighten q\n" +
            "save res/example-brighten.ppm example-brighten");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("example-brighten"), models.get("example-brighten"));
  }

  @Test
  public void testInvalidInput() {
    Readable in = new StringReader("load res/example.ppm example\n" +
            "darken invalid-scale example example-brighter q\n");
    ImageController c = new ImageControllerImpl(in, models, view);
    c.begin();

    assertEquals("Scale must be an integer.", log.toString());
  }

  @Test
  public void parsedValid() {
    Readable in = new StringReader("load res/example.ppm example\n" +
            "brighten 50 example example-brighter q\n");
    ImageController c = new ImageControllerImpl(in, models, view);
    c.begin();

    assertEquals(new Brighten().apply(models.get("example"), "50"),
            models.get("example-brighter"));
  }


  @Test
  public void testFlipHorizontal() {
    Readable in = new StringReader("load res/example.ppm example\n" +
            "horizontal-flip example example-horizontal q\n");
    ImageController c = new ImageControllerImpl(in, models, view);
    c.begin();

    assertEquals(new HorizontalFlip().apply(models.get("example")),
            models.get("example-horizontal"));
  }

  @Test
  public void testFlipVertical() {
    Readable in = new StringReader("load res/example.ppm example\n" +
            "vertical-flip example example-vertical q\n");
    ImageController c = new ImageControllerImpl(in, models, view);
    c.begin();

    assertEquals(new VerticalFlip().apply(models.get("example")),
            models.get("example-vertical"));
  }

  @Test
  public void testVisualizeLuma() {
    Readable in = new StringReader("load res/example.ppm example\n" +
            "luma-component example example-luma q\n");
    ImageController c = new ImageControllerImpl(in, models, view);
    c.begin();

    assertEquals(new LumaComponent().apply(models.get("example")),
            models.get("example-luma"));
  }


  @Test
  public void testRedComponent() {
    Readable read = new StringReader("load res/example.ppm example\n" +
            "red-component example example-red q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new RedComponent().apply(models.get("example")),
            models.get("example-red"));
  }

  @Test
  public void testBlueComponent() {
    Readable read = new StringReader("load res/example.ppm example\n" +
            "blue-component example example-blue q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new BlueComponent().apply(models.get("example")),
            models.get("example-blue"));
  }

  @Test
  public void testGreenComponent() {
    Readable read = new StringReader("load res/example.ppm example\n" +
            "green-component example example-green q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new GreenComponent().apply(models.get("example")),
            models.get("example-green"));
  }

  @Test
  public void testBlurPPM() {
    Readable read = new StringReader("load res/example.ppm example\n" +
            "blur example example-blur q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new Blur().apply(models.get("example")),
            models.get("example-blur"));
  }

  @Test
  public void testSepiaPPM() {
    Readable read = new StringReader("load res/example.ppm example\n" +
            "sepia example example-sepia q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new Sepia().apply(models.get("example")),
            models.get("example-sepia"));
  }

  @Test
  public void testSharpenPPM() {
    Readable read = new StringReader("load res/example.ppm example\n" +
            "sharpen example example-sharpen q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new Sharpen().apply(models.get("example")),
            models.get("example-sharpen"));
  }

  @Test
  public void testGreyScalePPM() {
    Readable read = new StringReader("load res/example.ppm example\n" +
            "greyscale example example-grey q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new Greyscale().apply(models.get("example")),
            models.get("example-grey"));
  }

  @Test
  public void testLoadJPG() {
    Readable in = new StringReader("load res/website.jpg website q\n");
    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("website"), models.get("website"));
  }

  @Test
  public void testSaveJPG() {
    Readable in = new StringReader("load res/website.jpg website q\n " +
            "brighten res/website.jpg website-brighten q\n" +
            "save res/website-brighten.ppm website-brighten");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("website-brighten"), models.get("website-brighten"));
  }

  @Test
  public void testBlurJPG() {
    Readable in = new StringReader("load res/website.jpg website \n " +
            "blur website website-blur q\n");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(new Blur().apply(models.get("website")),
            models.get("website-blur"));
  }

  @Test
  public void testSharpenJPG() {
    Readable in = new StringReader("load res/website.jpg website \n " +
            "sharpen website website-sharpen q\n");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(new Sharpen().apply(models.get("website")),
            models.get("website-sharpen"));
  }

  @Test
  public void testSepiaJPG() {
    Readable in = new StringReader("load res/website.jpg website \n" +
            "sepia website website-sepia q\n");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(new Sepia().apply(models.get("website")),
            models.get("website-sepia"));
  }

  @Test
  public void testGreyScaleJPG() {
    Readable read = new StringReader("load res/website.jpg website\n" +
            "greyscale website website-grey q\n");
    ImageController controller = new ImageControllerImpl(read, models, view);

    controller.begin();
    assertEquals(new Greyscale().apply(models.get("website")),
            models.get("website-grey"));
  }

  @Test
  public void testLoadPNG() {
    Readable in = new StringReader("load res/Dice.png example q\n");
    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("Dice"), models.get("Dice"));
  }

  @Test
  public void testLoadBMP() {
    Readable in = new StringReader("load res/Dice.bmp example q\n");
    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("Dice"), models.get("Dice"));
  }

  @Test
  public void testSaveMultiple() {
    Readable in = new StringReader("load res/example.ppm example q\n " +
            "brighten res/example.ppm example-brighten q\n" +
            "save res/example-brighten.ppm example-brighten \n" +
            "load res/website.jpg website q\n " +
            "sharpen res/website.jpg website-sharpen q\n" +
            "save res/website-sharpen.jpg website-sharpen");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("example-brighten"), models.get("example-brighten"));
    assertEquals(models.get("website-sharpen"), models.get("website-sharpen"));
  }


  @Test
  public void testExportPPMToJPG() {
    Readable in = new StringReader("load res/example.ppm example q\n " +
        "brighten 20 res/example.ppm example-brighten q\n" +
        "save res/example-brighten-two.jpg example-brighten-two");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("example-brighten-two"), models.get("example-brighten-two"));
  }

  @Test
  public void testExportJPGtoBMP() {
    Readable in = new StringReader("load res/website.jpg website q\n " +
        "brighten 20 res/website.jpg website-brighten q\n" +
        "save res/website-brighten-two.bmp website-brighten-two");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("website-brighten-two"), models.get("website-brighten-two"));
  }

  @Test
  public void testExportJPGtoPPM() {
    Readable in = new StringReader("load res/website.jpg website q\n " +
        "brighten 20 res/website.jpg website-brighten q\n" +
        "save res/website-brighten-two.ppm website-brighten-two");

    ImageController c1 = new ImageControllerImpl(in, models, view);
    c1.begin();
    assertEquals(models.get("website-brighten-two"), models.get("website-brighten-two"));
  }


  @Test
  public void testScriptInput() {
    HashMap<String, ImageInterface> modelsTwo = new HashMap<>();
    Readable in = new StringReader("load res/website.jpg website \n" +
            "sepia website website-sepia \n" +
            "save res/website-sepia.jpg website-sepia q");
    Readable in2 = new StringReader("-file TestText.txt");
    ImageController c1 = new ImageControllerImpl(in, models, view);
    ImageController c2 = new ImageControllerImpl(in2, modelsTwo, view);
    c1.begin();
    c2.begin();
    assertEquals(new Sepia().apply(models.get("website")), modelsTwo.get("website-sepia"));
  }
}