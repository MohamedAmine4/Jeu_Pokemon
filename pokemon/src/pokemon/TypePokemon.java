package pokemon;

public class TypePokemon {
	  // Matrice d'efficacité
    private static final double[][] MATRIX = {
        // Plante  Poison  Feu   Eau    Insecte  Vol    Normal  Electrique
        {0.5,     0.5,    0.5,  2,     0.5,    0.5,   0,      0},      // Plante
        {2,       0.5,    0,    0,     0,      0,     0,      0},      // Poison
        {0.5,     0,      0.5,  0.5,   2,      0,     0,      0},      // Feu
        {0.5,     0,      2,    0.5,   0,      0,     0,      0},      // Eau
        {0.5,     0.5,    0.5,  0,     0,      0.5,   0,      0.5},    // Insecte
        {0.5,     0,      0.5,  0,     2,      0,     0,      0},      // Vol
        {0.5,     0,      0.5,  0,     0,      0,     0,      0},      // Normal
        {0.5,     0,      0.5,  2,     0,      2,     0,      0.5}     // Electrique
    };

    // Fonction pour obtenir le coefficient entre deux types
    public static double obtenirCoefficient(String typeAttaquant, String typeDefense) {
        int indiceAttaquant = obtenirIndiceType(typeAttaquant);
        int indiceDefense = obtenirIndiceType(typeDefense);

        // Vérifier que les types sont valides
        if (indiceAttaquant != -1 && indiceDefense != -1) {
            return MATRIX[indiceAttaquant][indiceDefense];
        } else {
            // Gérer les cas où les types sont invalides
            System.out.println("Types invalides : " + typeAttaquant + " et " + typeDefense);
            return -1;
        }
    }

    // Fonction utilitaire pour obtenir l'indice d'un type à partir de son nom
    public static int obtenirIndiceType(String typeName) {
        switch (typeName.toLowerCase()) {
            case "plante": return 0;
            case "poison": return 1;
            case "feu": return 2;
            case "eau": return 3;
            case "insecte": return 4;
            case "vol": return 5;
            case "normal": return 6;
            case "electrique": return 7;
            default: return -1; // Retourne -1 pour les types invalides
        }
    }
    public static void main(String[] args) {
        // Exemple d'utilisation de la fonction
        String typeAttaquant = "plante";
        String typeDefense = "electrique";

        double coefficient = obtenirCoefficient(typeAttaquant, typeDefense);
        System.out.println("Coefficient entre " + typeAttaquant + " et " + typeDefense + " : " + coefficient);
    }
}
