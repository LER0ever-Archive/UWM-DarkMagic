
/**
 * The Exception subclass used throughout this application
 */
@SuppressWarnings("serial")
public class PokedexException extends RuntimeException {

  /**
   * Initialize the exception with a more specific error message
   *
   * @param message the error message
   */
  public PokedexException(String message) {
    super(message);
  }
}
