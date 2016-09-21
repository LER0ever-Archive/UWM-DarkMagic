import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * The main class. Provides the main method which is responsible for game play.
 * Also provides assistance for gameplay with functions to generate Pokemon, and 
 * save a player's progress.
 */
public class PokemonGO {
  public static final int NEW_POKEMON_CANDIES = 3;
  public static final int TRANSFER_POKEMON_CANDIES = 1;

  /**
   * The game begins here! The set of Pokemon that the player will encounter
   * must be provided as the first and only command-line argument which will
   * be loaded into the {@link PokemonDB}
   * <p>
   * Players are prompted to enter their name which will be used to save their
   * progress. Players are then prompted with a set of menu options which can
   * be selected by entering a single character e.g. 's' for "search". Options
   * include search for Pokemon, display encountered Pokemon, display caught Pokemon, 
   * transfer caught Pokemon, and quit.
   *
   * @param args the command line arguments. args[0] must be a file which is loaded
   *   by {@link PokemonDB}
   */
  public static void main(String[] args) throws FileNotFoundException {

    // Interpret command-line arguments and use them to load PokemonDB
    if(args.length != 1) {
      System.err.println("Invalid arguments");
      System.exit(1);
    }
    PokemonDB db = new PokemonDB(args[0]);

    // Start 
    System.out.println(Config.WELCOME);
    
    // Prompt user to enter player name    
    System.out.println(Config.USER_PROMPT);
    
    // Take name of Pokemon trainer
    String playerName = Config.getNextLine();
    
    // Provide a name for a txt file which will be used to save the player's progress
    String playerFileName = playerName + ".txt";
    Pokedex pokedex = new Pokedex();
    try {
      pokedex.loadFromFile(playerFileName);
    } catch (FileNotFoundException e) {
      // then the player has not saved any progress yet, start a new game!
    }
    PokemonTrainer pokemonTrainer = new PokemonTrainer(playerName, pokedex);
    System.out.println("Hi " + playerName);
    
    // main menu for the game. accept commands until the player enters 'q' to quit
    String option = null;
    while ((!"Q".equals(option)) && (!"q".equals(option))) {
      // Ask user to choose 
      // [C] display caught Pokemon
      // [D]isplay encountered Pokemon
      // [S]earch for Pokemon
      // [T]ransfer Pokemon to the Professor
      // [Q]uit
      System.out.println(Config.MENU_PROMPT);
      option = Config.getNextLine();
    
      switch(option) {
        case "C":
        case "c":
          System.out.println(pokemonTrainer.getPokedex().caughtPokemonMenu());
          break;
        case "D":
        case "d":
          System.out.println(pokemonTrainer.getPokedex().seenPokemonMenu());
          break;
        case "S":
        case "s":
          Pokemon wildPokemon = encounterPokemon(db);
          
          // Provide alternative guessing options
          int[] pokedexNumbers = new int[Config.DIFFICULTY];
          pokedexNumbers[0] = wildPokemon.getPokedexNumber();
          for(int i = 1; i < pokedexNumbers.length; i++) {
            pokedexNumbers[i] = db.generatePokedexNumber();
          }
          Arrays.sort(pokedexNumbers);

          // Prompt user for input
          System.out.println(String.format(
            Config.ENCOUNTERED_POKEMON,
            wildPokemon.getSpecies(),
            wildPokemon.getCombatPower(),
            Arrays.toString(pokedexNumbers)));
          int guessedId = 0;
          while(guessedId < 1 || guessedId > 151) {
            // then prompt is invalid
            try {
              guessedId = Config.getNextInteger();
              if(guessedId < 1 || guessedId > 151) {
                throw new RuntimeException(Config.INVALID_INT_INPUT);
              }
            } catch (InputMismatchException e) {
              System.out.println(Config.INVALID_INT_INPUT);
              Config.getNextLine();
            } catch (RuntimeException e) {
              System.out.println(Config.INVALID_INT_INPUT);
            } 
          }
          
          if (guessedId == wildPokemon.getPokedexNumber()) { 
            // guessed correctly, pokemon is captured
             pokemonTrainer.capturePokemon(wildPokemon);
            System.out.println(String.format(
              Config.SUCCESSFUL_CAPTURE,
              playerName,
              wildPokemon.getSpecies(),
              wildPokemon.getCombatPower()));
          }
          else { 
            // guessed incorrectly, pokemon escapes
            pokemonTrainer.seePokemon(wildPokemon);
            System.out.println(String.format(
              Config.FAILED_CAPTURE,
              playerName,
              wildPokemon.getSpecies()));
          }
          break;
            
        case "T":
        case "t":
          // Select Pokemon species to transfer
          System.out.println(Config.TRANSFER_PROMPT);
          pokedex = pokemonTrainer.getPokedex();
          String speciesName = Config.getNextLine();
          if(speciesName.toLowerCase().equals("cancel")) {
            break;
          }

          try {
            db.lookupPokedexNumber(speciesName);
          } catch (PokedexException e) {
            System.out.println(e.toString());
            break;
          }

          // Begin transfer of selected species
          PokemonSpecies species = null;
          try {
        	  species = pokedex.findCaughtSpeciesData(speciesName);
          } catch (PokedexException e) {
        	  System.out.println(e.toString());
            break;
          }
          String transferPokemonName = species.getSpeciesName();
          
          // Select Pokemon of that species to transfer
          System.out.println(String.format(
            Config.TRANSFER_CP_PROMPT, 
            transferPokemonName,
            species.caughtPokemonToString()));

          int transferPokemonCp = -1;
          while(transferPokemonCp == -1) {
            try {
              transferPokemonCp = Config.getNextInteger();
              if(transferPokemonCp < 0) {
              	System.out.println(Config.INVALID_CP_INPUT);
                transferPokemonCp = -1;
              }
            } catch (InputMismatchException e) {
              System.out.println(Config.INVALID_CP_INPUT);
              transferPokemonCp = -1;
              Config.getNextLine();
            } catch (RuntimeException e) {
              System.out.println(Config.INVALID_CP_INPUT);
              transferPokemonCp = -1;
            }
          }
          if(transferPokemonCp == 0) {
            break;
          }

          try {
            // Call transfer function; should throw exceptions within transfer but are to be caught here 
            pokemonTrainer.transferPokemon(transferPokemonName, transferPokemonCp);
            System.out.println(String.format(
              Config.SUCCESSFUL_TRANSFER,
              transferPokemonName,
              transferPokemonCp));
          }
          catch (PokedexException pokedexException) {
            System.out.println (pokedexException.toString());
          }
          break;
        case "Q":
        case "q":
        	break;

        default:
          System.out.println(Config.INVALID_RESPONSE);
      }       
    }
    
    // Save the game when the player quits
    File outFile = new File(playerFileName);
    saveGame(outFile, pokemonTrainer);
    System.out.println(String.format(
      Config.QUIT_MESSAGE, 
      playerName));
  }
  
  /**
   * A wild <pokemon> has appeared! ~*~ battle music plays ~*~
   *
   * @param db the PokemonDB to generate a Pokemon from
   * @return a Pokemon for the encounter
   */
  public static Pokemon encounterPokemon(PokemonDB db) {    
    // random number to pick pokemon
    int pokedexNumber = db.generatePokedexNumber();
    String species = db.lookupSpeciesName(pokedexNumber);
    
    // random number to decide CP
    int cp = Config.CP_GENERATOR.nextInt(Config.MAX_CP-1)+1; // adjustments for origin 0/1
    
    Pokemon wildPokemon = new Pokemon(pokedexNumber, species, cp);
    return wildPokemon;
  }
  
  /**
   * Save the game by writing the Pokedex into a file. The game can be loaded again
   * by functions provided by the {@link Pokedex} class
   *
   * @param outFile the file handle to write the game progress to
   * @param pokemonTrainer the player whose game we are saving
   */
  public static void saveGame(File outFile, PokemonTrainer pokemonTrainer) throws FileNotFoundException {
    // TODO
  }
}
