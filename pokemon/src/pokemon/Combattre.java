package pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class Combattre {
	static int i = 0;
	static int nbPokemon = 0;
	private String nomPokemon;
	static ArrayList<Pokemon> pokemonsCombatd1 = new ArrayList<>();
	static ArrayList<Pokemon> pokemonsCombatd2 = new ArrayList<>();
	Dresseur d1 = new Dresseur("", 1);
	Dresseur d2 = new Dresseur("", 1);

	/*
	 * premiere chose lorsque dresseur appel a combatre il faut qu'il choisit des
	 * pokemons parmi ses pokemons pour l'utuliser pour combattre
	 * 
	 * deuxieme etape: il faut une fonction attaque qui va prend en parametre les deux
	 * dresseur 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public Combattre(Dresseur d1, Dresseur d2) {
		this.d1 = d1;
		this.d2 = d2;
		pokemonsCombatd1.addAll(d1.getPokemonCombat());
		pokemonsCombatd2.addAll(d2.getPokemonCombat());
//    	System.out.println(d1.toString());
//    	System.out.println(d2.toString());

	}

	public void attaque(Dresseur d1, Dresseur d2) {
		if (!d1.getPokemonCombat().isEmpty() && !d2.getPokemonCombat().isEmpty()) {
			String typepokattaque=d1.getPokemonCombat().get(0).getType();
			String typePokDefense=d2.getPokemonCombat().get(0).getType();
			int PCd1 = d1.getPokemonCombat().get(0).getPc();
			int PVd2 = d2.getPokemonCombat().get(0).getPv();
			 double coefficient = TypePokemon.obtenirCoefficient(typepokattaque, typePokDefense);
			d2.getPokemonCombat().get(0).DiminuerPv(PCd1);
		} else
			return;
	}

	public void mortPokemon(Dresseur d1, Dresseur d2) {
		if (!d1.getPokemonCombat().isEmpty() && !d2.getPokemonCombat().isEmpty()) {
			if (d1.getPokemonCombat().get(0).getPv() <= 0) {
				d1.getPokemonCombat().get(0).PV0();
				nomPokemon = d1.getPokemonCombat().get(0).getNom();
				ajoutBonbon(d2, nomPokemon);
				if (d2.getBonbon().get(nomPokemon) == 5)
					d2.evoluer(d2.getPokemonCombat().get(0));
				d1.SupprimerPokemonCombat(d1.getPokemonCombat().get(0));

			}
			
			if (d2.getPokemonCombat().get(0).getPv() <= 0) {
				d2.getPokemonCombat().get(0).PV0();
				nomPokemon = d2.getPokemonCombat().get(nbPokemon).getNom();
				ajoutBonbon(d1, nomPokemon);
				d2.SupprimerPokemonCombat(d2.getPokemonCombat().get(0));

			}
		}
	}

	public void ajoutBonbon(Dresseur dresseur, String nomPokemons) {
		if (dresseur.getBonbon().containsKey(nomPokemons)) {
			int valeur = dresseur.getBonbon().get(nomPokemons);
			valeur += 1;
			dresseur.getBonbon().put(nomPokemon, valeur);
		} else
			dresseur.getBonbon().put(nomPokemon, 1);
	}

	public Dresseur combattree(Dresseur d1, Dresseur d2) {

		int role = 0;

		do {
			role++;
			if (role % 2 != 0) // role de joueur 1 c a dire le joueur 1 qui va attaquer le joueur 2
			{
				AcceptClient.setUsername(d1.getNom());
				 try {
					 d1.writer.println("Maintenant le dresseur:"+d1.getNom()+" qui attaque");
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				attaque(d1, d2);
			
				d1.writer.println("*******************************************etat de pokemon de attaquant "+d1.getNom()+":*******************************************\n"+d1.getPokemonCombat().get(0).Afficher());
				
				d2.writer.println("*******************************************etat de pokemon de defenseur "+d2.getNom()+":*******************************************\n"+d2.getPokemonCombat().get(0).Afficher());
				
				 try {
					 d1.writer.println("*******************************************il prepare son pokemon******************************************* ");
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {// role de joueur 2 c a dire le joueur 2 qui va attaquer le joueur 1
				AcceptClient.setUsername(d2.getNom());
				try {
					d2.writer.println("Maintenant le dresseur:"+d2.getNom()+" qui va attaquer");
					Thread.sleep(9000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				attaque(d2, d1);
				d2.writer.println("*******************************************etat de pokemon de attaquant "+d2.getNom()+":*******************************************\n"+d2.getPokemonCombat().get(0).Afficher());
				
				d1.writer.println("*******************************************etat de pokemon de defenseur  "+d1.getNom()+":*******************************************\n"+d1.getPokemonCombat().get(0).Afficher());
				 try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			mortPokemon(d1, d2);

		} while (!d1.getPokemonCombat().isEmpty() && !d2.getPokemonCombat().isEmpty());
         
           
           if (d1.getPokemonCombat().isEmpty()) {
			return d1;
		}
		return d2;

	}

}
