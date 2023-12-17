package pokemon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialisation {

	
	public void serialiser(Dresseur p) throws IOException {
		FileOutputStream fichier = new FileOutputStream("fichier.txt");
				// On crée un flux de sortie d'objet
		ObjectOutputStream flux = new
		ObjectOutputStream(fichier);
				// On écrit l'objet
		flux.writeObject(p);

	}
}
