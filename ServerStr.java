import java.io.*;
import java.net.*;

public class ServerStr {

  ServerSocket server = null;
  Socket client = null;
  BufferedReader inDalClient; 
  DataOutputStream outVersoClient;
    
  public Socket attendi() {
    try {
      System.out.println("1. SERVER: Partito in esecuzione...");
      server = new ServerSocket(6789);

      System.out.println("2. SERVER: In attesa di connessioni...");
      
      client = server.accept();
      System.out.println("3. SERVER: Connessione accettata.");

      inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
      outVersoClient = new DataOutputStream(client.getOutputStream());
    } catch (IOException e) {
      System.err.println("Errore durante l'istanza del server: " + e.getMessage());
      System.exit(1);
    }
    return client;
  } 

  public void comunica() {
    try {
        System.out.println("4. SERVER: Attendo la stringa dal client...");
        
        String stringaRicevuta = inDalClient.readLine();
        System.out.println("5. SERVER: Ricevuta la stringa dal cliente: " + stringaRicevuta);
        
        String stringaModificata = stringaRicevuta.toUpperCase();
        outVersoClient.writeBytes(stringaModificata + '\n');
        System.out.println("6. SERVER: Risposta inviata al cliente.");

        client.close();
        System.out.println("7. SERVER: Connessione chiusa.");
    } catch (IOException e) {
        System.err.println("Errore durante la comunicazione con il client: " + e.getMessage());
        System.exit(1);
    }
}

  public static void main(String args[]) {
    ServerStr servente = new ServerStr();
    servente.attendi();
    servente.comunica();
  }
}