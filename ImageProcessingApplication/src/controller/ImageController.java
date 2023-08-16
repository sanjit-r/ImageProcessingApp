package controller;


/**
 * This interface represents the Controller for image processor.
 */
public interface ImageController {

  /**
   * This method runs the program by taking in and parsing user input. After it will run the methods
   * from the model or view.
   *
   * @throws IllegalArgumentException if given file can't be read or the information in the file is
   *                                  not valid.
   */
  void begin() throws IllegalArgumentException;



}
