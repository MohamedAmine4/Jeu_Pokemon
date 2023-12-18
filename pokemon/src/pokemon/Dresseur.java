package pokemon;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Dresseur implements Serializable {

	protected String nom;
	protected int niveau; // chaque fois le dresseur gagne en ajoute +1 a ce niveau
	ArrayList<Pokemon> pokemons = new ArrayList<>();
	ArrayList<Evolution> evolution = new ArrayList<>();
	ArrayList<String> evolution1 = new ArrayList<>();// list evolution pokemon 1
	ArrayList<String> evolution2 = new ArrayList<>();// list evolution pokemon 2
	ArrayList<String> evolution3 = new ArrayList<>();// list evolution pokemon 3
	HashMap<String, Integer> bonbon = new HashMap<>();// gagne +2 equiveau +1 perdu+0
	ArrayList<Pokemon> pokemonCombat = new ArrayList<>();
	static PrintWriter writer = null;
	static int j = 1; // joueur 1
	static int d1 = 1;// joueur 1 d2==2 joueur 2
	static ArrayList<Dresseur> dres = new ArrayList<>();

	public Dresseur(String nom, int niveau) {
		super();
		this.nom = nom;
		this.niveau = niveau;
	}

	public Dresseur() {
	}

	public HashMap<String, Integer> getBonbon() {
		return bonbon;
	}

	public void setNom(String nom2) {
		nom = nom2;
	}

	public void setBonbon(HashMap<String, Integer> bonbon) {
		this.bonbon = bonbon;
	}

	public String getNom() {
		return nom;
	}

	public int getNiveau() {
		return niveau;
	}

	public ArrayList<Pokemon> getPokemons() { // les pokemons de adresseur
		return pokemons;
	}

	public static ArrayList<Dresseur> LesDreseur() {
		return dres;
	}

	public void SupprimerPokemon(Pokemon p) {
		pokemons.remove(p);
	}
	public void SupprimerPokemonCombat(Pokemon p) {
		pokemonCombat.remove(p);
	}
	public void AjouterPokemon(Pokemon p) {
		pokemons.add(p);
	}

	public void AugmenterNiveau() {
		niveau += 1;
	}

	public ArrayList<Pokemon> getPokemonCombat() {
		return pokemonCombat;
	}

	public void setPokemonCombat(ArrayList<Pokemon> pokemonCombat) {
		this.pokemonCombat = pokemonCombat;
	}

	public static void AfficherPokemons() {
		String path = "Liste Pokemon csv.csv";
		String line = "";
		BufferedReader fi = null;

		try {
			fi = new BufferedReader(new FileReader(path));

			System.out.println("Nom,Evolution1,Evolution2,Type,Type");
			while (line != null) {
				System.out.println(line);
				line = fi.readLine();
				if (line == null)
					break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void lootBox() {
		Random r = new Random();
		// chaque drisseur peut avoir seulement 3 pokemon
		int poke1 = r.nextInt(0, 10);
		int poke2 = r.nextInt(0, 10);
		int poke3 = r.nextInt(0, 10);
		// les pc et les pv aleatoire
		int pcPoke1 = r.nextInt(1, 6);
		int pvPoke1 = r.nextInt(1, 6);
		int pcPoke2 = r.nextInt(1, 6);
		int pvPoke2 = r.nextInt(1, 6);
		int pcPoke3 = r.nextInt(1, 6);
		int pvPoke3 = r.nextInt(1, 6);
		String path = "Liste Pokemon csv.csv";
		String line = "";
		BufferedReader fi = null;
		try {
			fi = new BufferedReader(new FileReader(path));

			int ligneCourante = 0;

			while (ligneCourante < 10) {
				line = fi.readLine();

				if (line == null) {
					// Si la ligne est null, nous avons atteint la fin du fichier
					break;
				}
				if (ligneCourante == poke1) {
					String[] ligne = line.split(";");
					String nomPoke1 = ligne[0];
					String evolution1Poke1 = ligne[1];
					String evolution2Poke1 = ligne[2];
					String Type1Poke1 = ligne[3];
					String Type2Poke1 = ligne[4];
					bonbon.put(nomPoke1, 1);
					// System.out.println("nom: "+nomPoke1+" evolution1: " + evolution1Poke1+"
					// evolution2: " + evolution2Poke1+" Type1: "+Type1Poke1+" Type2: " +
					// Type2Poke1);
					Pokemon pokemon1 = new Pokemon(nomPoke1, Type1Poke1, pcPoke1, pvPoke1, evolution1);// pokemon 1 de
																										// dresseur avec
																										// une list vide
																										// des
																										// evolutions
					pokemons.add(pokemon1);
					if (evolution2Poke1.equals("none")) {
						Evolution evpok1 = new Evolution(evolution1Poke1);// list des evolution possible pour pokemon1
																			// non affecter a le pokemon 1 seulement
																			// pour memoriser
						evolution.add(evpok1);
					} else {
						Evolution evpok1 = new Evolution(evolution1Poke1, evolution2Poke1);
						evolution.add(evpok1);
					}

				}
				if (ligneCourante == poke2) {
					String[] ligne = line.split(";");
					String nomPoke2 = ligne[0];
					String evolution1Poke2 = ligne[1];
					String evolution2Poke2 = ligne[2];
					String Type1Poke2 = ligne[3];
					String Type2Poke2 = ligne[4];
					// System.out.println("nom: "+nomPoke2+" evolution1: " + evolution1Poke2+"
					// evolution2: " + evolution2Poke2+" Type1: "+Type1Poke2+" Type2: " +
					// Type2Poke2);
					if (!bonbon.containsKey(nomPoke2)) { // si le bonbone n'existe pas dans la liste des bonbones alors
															// on ajoute a la liste
						bonbon.put(nomPoke2, 1);
					} else { // si le bonbone qu'on veut ajouter existe deja dans la liste des bonbones on
								// incremente donc sa valeur
						int ancieneValeur = bonbon.get(nomPoke2);
						bonbon.put(nomPoke2, ancieneValeur + 1);
					}

					Pokemon pokemon2 = new Pokemon(nomPoke2, Type1Poke2, pcPoke2, pvPoke2, evolution2);// pokemon 1 de
																										// dresseur avec
																										// une list vide
																										// des
																										// evolutions
					pokemons.add(pokemon2);
					if (evolution2Poke2.equals("none")) {
						Evolution evpok2 = new Evolution(evolution1Poke2);// list des evolution possible pour pokemon1
																			// non affecter a le pokemon 1 seulement
																			// pour memoriser
						evolution.add(evpok2);
					} else {
						Evolution evpok2 = new Evolution(evolution1Poke2, evolution2Poke2);
						evolution.add(evpok2);
					}
				}
				if (ligneCourante == poke3) {
					String[] ligne = line.split(";");
					String nomPoke3 = ligne[0];
					String evolution1Poke3 = ligne[1];
					String evolution2Poke3 = ligne[2];
					String Type1Poke3 = ligne[3];
					String Type2Poke3 = ligne[4];
					// System.out.println("nom: "+nomPoke3+" evolution1: " + evolution1Poke3+"
					// evolution2: " + evolution2Poke3+" Type1: "+Type1Poke3+" Type2: " +
					// Type2Poke3);

					if (!bonbon.containsKey(nomPoke3)) { // si le bonbone n'existe pas dans la liste des bonbones alors
															// on ajoute a la liste
						bonbon.put(nomPoke3, 1);
					} else { // si le bonbone qu'on veut ajouter existe deja dans la liste des bonbones on
								// incremente donc sa valeur
						int ancieneValeur = bonbon.get(nomPoke3);
						bonbon.put(nomPoke3, ancieneValeur + 1);
					}
					Pokemon pokemon3 = new Pokemon(nomPoke3, Type1Poke3, pcPoke3, pvPoke3, evolution3);// pokemon 1 de
																										// dresseur avec
																										// une list vide
																										// des
																										// evolutions
					pokemons.add(pokemon3);
					if (evolution2Poke3.equals("none")) {
						Evolution evpok3 = new Evolution(evolution1Poke3);// list des evolution possible pour pokemon1
																			// non affecter a le pokemon 1 seulement
																			// pour memoriser
						evolution.add(evpok3);
					} else {
						Evolution evpok3 = new Evolution(evolution1Poke3, evolution2Poke3);
						evolution.add(evpok3);
					}
				}

				ligneCourante++;
			}
//                System.out.println(pokemons);
//                 System.out.println(evolution);
//                 System.out.println(bonbon);
			// pokemons.get(0).ajouerEvolution(evolution.get(0).getEvolution1());
			// System.out.println(pokemons);
//                 System.out.println(evolution.get(1).getEvolution1());//pour acceder au premier evolution 
//                 System.out.println(evolution.get(1).getEvolutionList().size());//pour savoir combien d'evolution possible pour le pokemon 1
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fi != null) {
					fi.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//	    System.out.println("pc "+pcPoke1 +" pv " + pvPoke1);
//	    System.out.println("pc "+pcPoke2 +" pv " + pvPoke2);
//	    System.out.println("pc "+pcPoke3 +" pv " + pvPoke3);
	}// a partire de fichier en ratrappe le nom pokemon et son type et ses evolutions
		// .generer pc et pv aleatoire

	public void evoluer(Pokemon p) { // le parametre c'est le pokemon adversaire

		int i = 0;
		if (pokemons.contains(p)) {
			for (Pokemon r : pokemons) {
				if (r == p) {
					if (bonbon.get(r.getNom()) == 5) { // evoluer seulement si le nombre de bonbone de meme type que le
														// nom de pokemon adeversaire egal 5
						bonbon.put(r.getNom(), 0);// met en zero les bonbons de pokemon qu'on a evoluer
						if (r.getEvolution().size() == 0) {
							r.ajouerEvolution(evolution.get(i).getEvolution1());
							r.setNom(evolution.get(i).getEvolution1());
							r.setPc(1);
							r.setPv(1);
							bonbon.put(r.getNom(), 1); // ajouter dans le dictionnaire de bonbon le nouveau pokemon
														// apres evolution
							return;
						} else if (r.getEvolution().size() == 1) {
							r.ajouerEvolution(evolution.get(i).getEvolution2());
							r.setPc(1);
							r.setPv(1);
							bonbon.put(r.getNom(), 0);
							return;
						}
					} else
						System.out.println("nombre de bonbone n'est pa suffisant");
					return;
				}

				i++;
			}
		}
	}

	public ArrayList<Evolution> getEvolution() {// pour voir les evolution possible de pokemon
		return evolution;
	}

	public String Afficher() {
		return "****************Dresseur********** \n nom=" + nom + "\n niveau=" + niveau + "\n Pokemons" + pokemons
				+ "\n bonbon=" + bonbon + "\n*****************";
	}


	

	@Override
	public String toString() {
		return "Dresseur [nom=" + nom + ", niveau=" + niveau + ", pokemons=" + pokemons + ", bonbon=" + bonbon + "]";
	}

	public void choisirpokimon(String message) {
		if (message.equals("12")) {
			pokemonCombat.add(pokemons.get(0));
			pokemonCombat.add(pokemons.get(1));
		} else if (message.equals("13")) {
			pokemonCombat.add(pokemons.get(0));
			pokemonCombat.add(pokemons.get(2));
		} else if (message.equals("31")) {
			pokemonCombat.add(pokemons.get(2));
			pokemonCombat.add(pokemons.get(0));
		} else if (message.equals("21")) {
			pokemonCombat.add(pokemons.get(1));
			pokemonCombat.add(pokemons.get(0));
		} else if (message.equals("32")) {
			pokemonCombat.add(pokemons.get(2));
			pokemonCombat.add(pokemons.get(1));
		} else {
			pokemonCombat.add(pokemons.get(1));
			pokemonCombat.add(pokemons.get(2));
		}
	}
		public static void message(String r) {
			
		}
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Serialisation ser = new Serialisation();
		Deserialisation des = new Deserialisation();
		BufferedReader readerfile = new BufferedReader(new FileReader("fichier.txt"));

		String role = readerfile.readLine();

		Dresseur d = new Dresseur(" ", 1);
		d.lootBox();

		Socket socket = null;
		
		BufferedReader consoleReader = null;

		try {
			// Connexion au serveur
			socket = new Socket("localhost", 12345);

			// Thread pour recevoir les messages du serveur
			new Thread(new ReceiveMessage(socket)).start();

			// Envoi des messages au serveur
			writer = new PrintWriter(socket.getOutputStream(), true);
			consoleReader = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String message;
			int compteur = 0; // pour separer le nom
			while ((message = consoleReader.readLine()) != null) {
				if (compteur == 0)
					writer.println(message);
				compteur++;
				if (message.length() < 7 && !message.equals("1") && !message.equals("2") && !message.equals("3")
						&& !message.equals("4")) {
					d.setNom(message);
				}
				if (message.equals("1")) {
					Dresseur.AfficherPokemons();
				} else if (message.equals("2")) {
					Game.MenuCombattre();
					while ((message = consoleReader.readLine()) != null) {

						if (message.equals("11"))
							System.out.println(" " + d);
						else if (message.equals("22")) {
							System.out.println("1:" + d.getPokemons().get(0));
							System.out.println("2:" + d.getPokemons().get(1));
							System.out.println("3:" + d.getPokemons().get(2));

							System.out.println(
									"choisir deux pokemone pour le combat entrer un nemuro parmi 12,21,13,31,23,32");
							String poki = consoleReader.readLine();
							d.choisirpokimon(poki);
							System.out.println(d.getPokemonCombat().toString());
							// String pokimons =consoleReader.readLine();
							// writer.println(pokimons);
							if (role == null || role.isEmpty()) {

								d.getPokemons().removeIf(pokemon -> !d.getPokemonCombat().contains(pokemon));

								// d.evoluer(d.getPokemons().get(0));
								ser.serialiser(d);
								// Dresseur donnes= (Dresseur) des.deserialiser(); //contient le dreseur
								// adversaire
								System.out.println("Attendez que l'autre joueur entre");
							} else {
								Dresseur donnes = (Dresseur) des.deserialiser();

								Combattre c = new Combattre(d, donnes);
								Dresseur dresseurperdu = c.combattree(d, donnes);
								if (dresseurperdu.equals(d)) {
									donnes.AugmenterNiveau();
									writer.println("😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀 \n le dresseur gagnant est " + donnes.getNom());
									writer.println("avec un resultat final \n" + donnes.Afficher());
									writer.println("😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞 \n le dresseur perdant est " + d.getNom());
									writer.println("avec un resultat final \n" + d.Afficher());
									ser.vider();
									try {
										Thread.sleep(8000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								} else {
									d.AugmenterNiveau();
//									for (Pokemon pokemon : d.getPokemons()) {
//									    if (!d.getPokemonCombat().contains(pokemon)) {
//									        d.getPokemonCombat().add(pokemon); // Assurez-vous d'avoir une méthode ajouterPokemon dans votre classe Dresseur
//									    }
//									}

									writer.println("😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀 \n le dresseur gagnant est " + d.getNom());
									writer.println("avec un resultat final \n" + d.Afficher());
									writer.println("😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞😞 \n le dresseur perdant est " + donnes.getNom());
									writer.println("avec un resultat final \n " + donnes.Afficher());
									ser.vider();
									try {
										Thread.sleep(8000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								}

							}

						} else if (message.equals("33"))
							break;
						else
							System.out.println("choix invalide");
					}
				} else if (message.equals("3")) {
					if (j == 1)
						;
					j++;
					System.out.println(" " + d);
					// writer.println(" " + donnes); il affiche le message dans votre console et il
					// transmettre le message a les autres
				} else if (message.equals("4")) {
					// Sortir de la boucle si l'option 4 est choisie
					break;
				} else {
					System.out.println("Choix invalide. Veuillez saisir 1, 2, 3 ou 4.");
				}

				Game.Menu();
			}

			// Reste du code après la boucle
			System.out.println("Vous avez choisi l'option 4. Le jeu se termine.");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Fermeture des ressources dans le bloc finally
			try {
				if (writer != null) {
					writer.close();
				}
				if (consoleReader != null) {
					consoleReader.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}