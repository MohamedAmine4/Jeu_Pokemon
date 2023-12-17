package pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class AcceptClient extends Thread {
	private Socket clientSocket;
	private List<PrintWriter> clientWriters;
	private static String username;

	public AcceptClient(Socket clientSocket, List<PrintWriter> clientWriters) {
		this.clientSocket = clientSocket;
		this.clientWriters = clientWriters;
	}

	public static String usrname() {
		return username;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

			// Demander le nom d'utilisateur
			writer.println("Entrez votre nom d'utilisateur:");
			username = reader.readLine();// pour ecrire au console
			writer.println("Bienvenue, " + username + "!");// afficher dans le console
			System.out.println(username + " a rejoint le jeu.");

			// Ajouter le PrintWriter au groupe des clients
			synchronized (clientWriters) { // synchronized garantit que plusieurs threads ne peuvent pas modifier la
											// liste en même temps, ce qui pourrait provoquer des problèmes de
											// concurrence.
				clientWriters.add(writer);
			}

			// Lire les messages du client et les diffuser à tous les clients
			Object message;
			while ((message = reader.readLine()) != null) {

				System.out.println(username + ": " + message);

				new BroadcastMessage(clientWriters).broadcast(username + ": " + message);

				if (message.equals("12") || message.equals("13") || message.equals("32")) {

					System.out.println("le message  " + message);

				}

			}

		} catch (SocketException se) {
			// La connexion a été réinitialisée, probablement le client s'est déconnecté
			System.out.println("");
		} catch (IOException e) {
			System.out.println("");
			// e.printStackTrace();
		} finally {
			// Enlever le PrintWriter du groupe des clients lorsqu'un client se déconnecte
			synchronized (clientWriters) {
				clientWriters.remove(clientSocket);
			}
			System.out.println(username + " a quitté le chat.");
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("a");
				// e.printStackTrace();
			}
		}
	}
}
