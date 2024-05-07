import java.io.*;
import java.net.*;

public class ClientStr {
    
    String nomeServer = "localhost";                // Indirizzo server locale
    int portaServer = 6789;                          // Porta per il servizio
    Socket miosocket;                                
    BufferedReader tastiera;                        // Lettura da tastiera
    String stringaUtente;                           // Stringa inserita dall'utente
    String stringaRicevutaDalServer;                // Stringa ricevuta dal server
    DataOutputStream outVersoServer;                // Stream di output verso il server
    BufferedReader inDalServer;                     // Stream di input dal server

    public void comunica() {
        try {
            System.out.println("4. Inserisci la stringa da trasmettere al server:");
            stringaUtente = tastiera.readLine();

            // Invio della stringa al server
            System.out.println("5. Invio della stringa al server e attesa...");
            outVersoServer.writeBytes(stringaUtente + '\n');

            // Lettura della risposta dal server
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8. Risposta dal server:");
            System.out.println(stringaRicevutaDalServer);

            // Chiusura della connessione
            System.out.println("9. Terminazione dell'elaborazione e chiusura della connessione.");
            miosocket.close(); 
        } catch (IOException e) {
            System.err.println("Errore durante la comunicazione con il server: " + e.getMessage());
            System.exit(1);
        }
    }

    public Socket connetti() {
        System.out.println("2. Il client è partito in esecuzione...");
        try {
            // Lettura da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            
            // Creazione del socket e connessione al server
            miosocket = new Socket(nomeServer, portaServer);
            
            // Associazione degli stream di input e output al socket
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Errore durante la connessione: " + e.getMessage());
            System.exit(1);
        }
        return miosocket;
    }

    public static void main(String[] args) {
        ClientStr cliente = new ClientStr();
        cliente.connetti();
        cliente.comunica();
    }   
}