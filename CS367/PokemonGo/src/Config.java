import java.util.Scanner;
import java.util.Random;

/**
 * This class provides configuration for the game. It also provides localization Strings
 * for the game's interaction with the player.
 * DO NOT EDIT. Your edits will not be used in the evaluation of your program.
 */
public class Config {
	
  /** 
   * Random object is seeded to make "pseudo-random" values deterministic 
   * The SEED value may be changed while completing your own testing.
   * For sample test runs, set both CP_SEED and POKEDEX_NUMBER_SEED values to null.
   */
  private static final int CP_SEED = 0; 
  private static final int POKEDEX_NUMBER_SEED = 0;
	
  public static final Random CP_GENERATOR = new Random(CP_SEED);
  public static final Random POKEDEX_NUMBER_GENERATOR = new Random(POKEDEX_NUMBER_SEED);
  
  public static final int MAX_CP = 3000;
  public static final int DIFFICULTY = 3;

  public static final String USAGE = "java PokemonGO <pokemon_file>";
  public static final String WELCOME = "Welcome to PokemonGO 367!";
  public static final String USER_PROMPT = "Please enter your name: ";
  public static final String TRANSFER_PROMPT = "Which Pokemon do you wish to transfer? Enter " +
    "the Pokemon's species name or type \"cancel\" to cancel the transfer.";
  public static final String MENU_PROMPT = "Do you want to \n" + 
    "  Display [C]aught pokemon, or \n" +
    "  [D]isplay encoutered pokemon, or \n" + 
    "  [S]earch for a wild pokemon, or \n" +
    "  [T]ransfer a caught pokemon, or \n" + 
    "  [Q]uit the game?";
  public static final String INVALID_RESPONSE = "Invalid response: Please enter one of the menu options";
  public static final String INVALID_INT_INPUT = "Invalid input: please enter an integer between 1 and 151.";
  public static final String INVALID_CP_INPUT = "Invalid input: please enter an integer greater than or equal to 0.";
  public static final String INVALID_TRANSFER = "Invalid input: please enter an integer between 0 and %s";
  public static final String ENCOUNTERED_POKEMON = "A wild %s of %d CP appeared.\n" +
    "Guess its Pokedex number from %s.";
  public static final String SUCCESSFUL_CAPTURE = "Congratulations, %s! You have captured a %s of CP %d.";
  public static final String FAILED_CAPTURE = "Oops! %s, that's incorrect. The wild %s escaped!";
  public static final String TRANSFER_CP_PROMPT = "Enter combat power of the %s that you wish to transfer or type 0 to cancel the transfer\n%s";
  public static final String SUCCESSFUL_TRANSFER = "You successfully transferred a %s of CP %d";
  public static final String QUIT_MESSAGE = "%s has quit the game.";
  public static final String INVALID_POKEMON_SPECIES = "%s is not a valid Pokemon species name";
  public static final String INVALID_POKEDEX_NUMBER = "%d is not a valid Pokedex number";
  public static final String MULTIPLE_POKEDEX_EXCEPTION = "A Pokedex has already been loaded. You cannot load another one.";
  public static final String UNCAUGHT_POKEMON = "You have encountered %s but do not have any Pokemon of that species in the inventory";
  public static final String UNSEEN_POKEMON = "You have not encountered a Pokemon with species %s";
  public static final String POKEMON_NOT_FOUND = "Cannot find Pokemon of species %s with cp %d";

  /**
   * THE SCANNER MUST NOT BE USED DIRECTLY
   * Instead Students must call one of the following input methods
   */
  private static final Scanner STDIN = new Scanner(System.in);

  /**
   * Returns the next line of user input as a String
   */
  public static String getNextLine() {
       return STDIN.nextLine();
  }

  /** 
   * Returns the next user input if it is an integer.
   * If the input is not an integer a RuntimeException is thrown.
   */
  public static int getNextInteger() {
       int i = STDIN.nextInt();
       STDIN.nextLine(); // clear rest of current line from input stream
       return i;
  }

  /** 
   * Returns the next user input if it is an double.
   * If the input is not a valid double a RuntimeException is thrown.
   */
  public static double getNextDouble() {
       double d = STDIN.nextDouble();
       STDIN.nextLine(); // clear rest of current line from input stream
       return d;
  }
}
