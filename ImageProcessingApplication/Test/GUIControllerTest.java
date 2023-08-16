
import org.junit.Before;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import model.image.ImageInterface;
import view.ImageGUI;
import view.ImageGUIView;


/**
 * Runs tests for GUI controller.
 */
public class GUIControllerTest {


  Appendable log;
  ImageGUIView view;
  HashMap<String, ImageInterface> models;
  ActionEvent argument;


  @Before
  public void init() {
    log = new StringBuilder();
    view = new ImageGUI();
    models = new HashMap<>();
  }

  //    @Test
  //    public void testRedComponent() throws IOException {
  //      Readable read = new StringReader("red-component");
  //      models.put("model", ImageUtil.
  //      readMainstreamFormat("C:\\Users\\Alexa\\IdeaProjects\\Assignment6\\res\\website.jpg"));
  //      ImageControllerGUI controller = new ImageControllerGUI(read, models, view);
  //      controller.actionPerformed("red-component");
  //      assertEquals(new RedComponent().apply(models.get("model")),
  //          models.get("model"));
  //    }
  //
  //  @Test
  //  public void testBlurJPG() {
  //    Readable in = new StringReader("load res/website.jpg website \n " +
  //        "blur website website-blur q\n");
  //
  //    ImageControllerGUI controller = new ImageControllerGUI(in, models, view);
  //    controller.actionPerformed(blur );
  //    assertEquals(new Blur().apply(models.get("website")),
  //        models.get("website-blur"));
  //  }
  //
  //  @Test
  //  public void testScriptInput() {
  //    HashMap<String, ImageInterface> modelsTwo = new HashMap<>();
  //    Readable in = new StringReader("load res/website.jpg website \n" +
  //        "sepia website website-sepia \n" +
  //        "save res/website-sepia.jpg website-sepia q");
  //    Readable in2 = new StringReader("-file TestText.txt");
  //    ImageController c1 = new ImageControllerImpl(in, models, view);
  //    ImageController c2 = new ImageControllerImpl(in2, modelsTwo, view);
  //    c1.begin();
  //    c2.begin();
  //    assertEquals(new Sepia().apply(models.get("website")), modelsTwo.get("website-sepia"));
  //  }
}
