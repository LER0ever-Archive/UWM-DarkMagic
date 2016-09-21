/**
 * This class represents a player. It contains functions associated with player actions.
 * Each player has a Pokedex which also acts as the player's inventory of caught Pokemon.
 * The player can capture a Pokemon after an encounter, see (but fail to capture) a Pokemon
 * during an encounter, and transfer a captured Pokemon to the Professor. Functions implemented
 * here rely heavily on the underlying Pokedex abstraction.
 */
public class PokemonTrainer {
  
  private String name;  
  private Pokedex pokedex;

  /**
   * Create a new PokemonTrainer (that is, a new player with his or her own progress in the game)
   *
   * @param name the name of the player; used to save the player's progress
   * @param pokedex the player's Pokedex. May be new or loaded from a previously saved game.
   */
  public PokemonTrainer(String name, Pokedex pokedex) {
    this.name = name;
    this.pokedex = pokedex;
  }
      
  /**
   * Getter methods
   */
  public String getName() {
    return name;
  }
  public Pokedex getPokedex() {
    return this.pokedex;
  }

  /**
   * Add Pokemon to player's inventory
   *
   * @param wildPokemon the Pokemon that the player captured
   */
  public void capturePokemon(Pokemon wildPokemon) {
    pokedex.addNewPokemon(wildPokemon);
  }
  
  /**
   * Add Pokemon to player's pokedex, but not inventory (Pokemon has been identified, 
   * but not captured).
   *
   * @param wildPokemon the Pokemon that the player encoutered
   */
  public void seePokemon(Pokemon wildPokemon) {
    try {
      pokedex.findSeenSpeciesData(wildPokemon.getSpecies());
      // then it has been seen before, do nothing
    } catch (PokedexException e) {
      // then Pokemon has not been seen, add it
      PokemonSpecies pokemonSpecies = new PokemonSpecies(wildPokemon.getPokedexNumber(), wildPokemon.getSpecies(), 0);
      pokedex.addNewSpecies(pokemonSpecies);
    }
  }
  
  /**
   * Transfer a Pokemon to the Professor; return the transferred Pokemon
   *
   * @param transferPokemonName the species name of the Pokemon to be transferred
   * @param transferPokemonCp the combatPower of the Pokemon to be transferred
   * @return the Pokemon that was transferred
   * @throws PokedexException if there is no species with the @transferPokemonName@ in the
   * Pokedex or if there are no Pokemon with the @transferPokemonCp@ in the player's inventory
   */
  public Pokemon transferPokemon(String transferPokemonName, int transferPokemonCp) throws PokedexException {
    Pokemon pokemon = pokedex.transferPokemon(transferPokemonName, transferPokemonCp);
    return pokemon;
  } 
}
