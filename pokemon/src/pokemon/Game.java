package pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	static int i = 0;
	static int choix;

	public static void Menu() {

		System.out.println(
				"*********************** Bonjour Dans le jeu de Pokemon : Voila le Menu *************************");
		System.out.println("1: Afficher toutes les pokemons ");
		System.out.println("2: Combattre d'autre Joueur");
		System.out.println("3: Vos Pokemons");
		System.out.println("4: Quitter");
		System.out.println(
				"************************************************ FIN ******************************************");

		System.out.print("Veuillez saisir votre choix (1, 2, 3 ou 4): ");
	}

	public static void MenuCombattre() {
		System.out.println(
				"*********************** Bonjour Dans le jeu de Pokemon : Voila le Menu de Combat*************************");
		System.out.println("11:Vos Pokemons ");
		System.out.println("22:entrer les numeros des pokemons que tu vas utuliser dans le combats ");
		System.out.println("33: Quitter");
		System.out.println(
				"************************************************ FIN ******************************************");
	}

	public static void attaque(ArrayList<Dresseur> s) {
		System.out.println("votre attaque va etre commencer" + s.toString());

	}

	public static void main(String[] args) {
		Game g = new Game();
		g.Menu();

	}
}
