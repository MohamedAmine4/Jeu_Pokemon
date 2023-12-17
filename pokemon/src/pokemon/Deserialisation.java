package pokemon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Deserialisation {

	
	
	public Object deserialiser() throws IOException, ClassNotFoundException {
		// On crée un flux d'entrée vers un fichier
				FileInputStream fichier = new
				FileInputStream("fichier.txt");
				// On crée un flux d'entrée d'objet
				ObjectInput flux = new
				ObjectInputStream(fichier);
				// On récupère l'objet 
				Object objet = (Object) flux.readObject();
		     return objet;
	}
}
