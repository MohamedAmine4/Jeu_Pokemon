package pokemon;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Serialisation {

	
	public void serialiser(Dresseur p) throws IOException {
		FileOutputStream fichier = new FileOutputStream("fichier.txt");
				// On crée un flux de sortie d'objet
		ObjectOutputStream flux = new
		ObjectOutputStream(fichier);
				// On écrit l'objet
		flux.writeObject(p);

	}

	public void vider() throws IOException {
		Path filePath = Path.of("fichier.txt");

        try {
            // Écrivez un tableau vide de bytes pour vider le fichier
            Files.write(filePath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Le fichier a été vidé avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
