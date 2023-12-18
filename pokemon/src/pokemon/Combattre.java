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
     * Première chose lorsque le dresseur appelle à combattre, il doit choisir des
     * Pokémon parmi ses Pokémon pour les utiliser pour combattre.
     * 
     * Deuxième étape : il faut une fonction d'attaque qui prend en paramètre les
     * deux dresseurs
     */

    public Combattre(Dresseur d1, Dresseur d2) {
        this.d1 = d1;
        this.d2 = d2;
        pokemonsCombatd1.addAll(d1.getPokemonCombat());
        pokemonsCombatd2.addAll(d2.getPokemonCombat());
    }

    public void attaque(Dresseur d1, Dresseur d2) {
        if (!d1.getPokemonCombat().isEmpty() && !d2.getPokemonCombat().isEmpty()) {
            // Obtenir les types des Pokémon attaquant et défenseur
            String typepokattaque = d1.getPokemonCombat().get(0).getType();
            String typePokDefense = d2.getPokemonCombat().get(0).getType();
            int PCd1 = d1.getPokemonCombat().get(0).getPc();
            int PVd2 = d2.getPokemonCombat().get(0).getPv();
            // Obtenir le coefficient de type pour le calcul des dégâts
            double coefficient = TypePokemon.obtenirCoefficient(typepokattaque, typePokDefense);
            // Appliquer les dégâts au Pokémon défenseur
            d2.getPokemonCombat().get(0).DiminuerPv(PCd1);
        } else
            return;
    }

    public void mortPokemon(Dresseur d1, Dresseur d2) {
        if (!d1.getPokemonCombat().isEmpty() && !d2.getPokemonCombat().isEmpty()) {
            // Vérifier si le Pokémon du dresseur 1 a été vaincu
            if (d1.getPokemonCombat().get(0).getPv() <= 0) {
                // Mettre les PV à 0 et récupérer le nom du Pokémon vaincu
                d1.getPokemonCombat().get(0).PV0();
                nomPokemon = d1.getPokemonCombat().get(0).getNom();
                // Ajouter des bonbons au dresseur 2 et évoluer le Pokémon si nécessaire
                ajoutBonbon(d2, nomPokemon);
                if (d2.getBonbon().get(nomPokemon) == 5)
                    d2.evoluer(d2.getPokemonCombat().get(0));
                // Supprimer le Pokémon vaincu du dresseur 1
                d1.SupprimerPokemonCombat(d1.getPokemonCombat().get(0));
            }

            // Vérifier si le Pokémon du dresseur 2 a été vaincu
            if (d2.getPokemonCombat().get(0).getPv() <= 0) {
                // Mettre les PV à 0 et récupérer le nom du Pokémon vaincu
                d2.getPokemonCombat().get(0).PV0();
                nomPokemon = d2.getPokemonCombat().get(nbPokemon).getNom();
                // Ajouter des bonbons au dresseur 1 et supprimer le Pokémon du dresseur 2
                ajoutBonbon(d1, nomPokemon);
                d2.SupprimerPokemonCombat(d2.getPokemonCombat().get(0));
            }
        }
    }

    public void ajoutBonbon(Dresseur dresseur, String nomPokemons) {
        // Ajouter des bonbons au dresseur en fonction du Pokémon vaincu
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
            if (role % 2 != 0) // Rôle du joueur 1 (attaquant)
            {
                AcceptClient.setUsername(d1.getNom());
                try {
                    // Afficher un message et attendre
                    d1.writer.println("Maintenant le dresseur:" + d1.getNom() + " qui attaque");
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Effectuer l'attaque du joueur 1
                attaque(d1, d2);
                // Afficher l'état des Pokémon après l'attaque
                d1.writer.println(
                        "*******************************************etat de pokemon de attaquant " + d1.getNom()
                                + ":*******************************************\n" + d1.getPokemonCombat().get(0).Afficher());

                d2.writer.println(
                        "*******************************************etat de pokemon de defenseur " + d2.getNom()
                                + ":*******************************************\n" + d2.getPokemonCombat().get(0).Afficher());

                try {
                    // Afficher un message et attendre
                    d1.writer.println("*******************************************il prepare son pokemon******************************************* ");
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {// Rôle du joueur 2 (attaquant)
                AcceptClient.setUsername(d2.getNom());
                try {
                    // Afficher un message et attendre
                    d2.writer.println("Maintenant le dresseur:" + d2.getNom() + " qui va attaquer");
                    Thread.sleep(9000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Effectuer l'attaque du joueur 2
                attaque(d2, d1);
                // Afficher l'état des Pokémon après l'attaque
                d2.writer.println(
                        "*******************************************etat de pokemon de attaquant " + d2.getNom()
                                + ":*******************************************\n" + d2.getPokemonCombat().get(0).Afficher());

                d1.writer.println(
                        "*******************************************etat de pokemon de defenseur  " + d1.getNom()
                                + ":*******************************************\n" + d1.getPokemonCombat().get(0).Afficher());
                try {
                    // Afficher un message et attendre
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Vérifier si un Pokémon a été vaincu à chaque tour
            mortPokemon(d1, d2);

        } while (!d1.getPokemonCombat().isEmpty() && !d2.getPokemonCombat().isEmpty());

        if (d1.getPokemonCombat().isEmpty()) {
            return d1;
        }
        return d2;

    }
}
