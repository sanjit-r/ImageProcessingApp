package controller;


/**
 * Features interface.
 */
public interface Features {

  /**
   * Loads a file from the given path.
   *
   * @param path path of file.
   */
  void load(String path);

  /**
   * Saves a file.
   *
   * @param path path of file.
   */
  void save(String path);


}
