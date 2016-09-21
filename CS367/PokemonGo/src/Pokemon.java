/**
 * This class represents a Pokemon
 */
public class Pokemon {
  private int pokedexNumber;
  private String species;
  private int combatPower;

  /**
   * Create a new Pokemon
   * @param pokedexNumber the number 1 to 151 (or higher for later generations of Pokemon) 
   * associated with a Pokemon species
   * @param species the name of the species of this Pokemon
   * @param combatPower the strength of this Pokemon for fighting battles (implementedd
   * in a future version of this application)
   */
  public Pokemon(int pokedexNumber, String species, int combatPower) {
    this.pokedexNumber = pokedexNumber;
    this.species = species;
    this.combatPower = combatPower;
  }

  /**
   * Getter methods
   */
  public int getPokedexNumber() {
    return this.pokedexNumber;
  }
  public String getSpecies() {
    return this.species;
  }
  public int getCombatPower() {
    return this.combatPower;
  }

  /**
   * A Pokemon within a PokemonSpecies can be simply represented by its combatPower.
   * This function is used to save the player's game into a save file.
   *
   * @return String the combatPower of the Pokemon
   */
  public String toString() {
    return Integer.toString(combatPower);
  }
}
