package app.service.branch_panel.ClientSimulation;

import java.io.*;
import java.net.*;

// we establish client socket's

public class ClientService{
    private Socket s = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public ClientService(){};


    public ClientService(String address, int port) {
        try {
            s = new Socket(address, port);
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void send(String mess){
        try{
            out.writeUTF(mess);
            out.flush();
        } catch (IOException e){
            System.out.println("Błąd wysyłania: " + e.getMessage());
        }
    }
    public void close() {
        try {
            out.close();
            in.close();
            s.close();
        } catch (IOException e) {
            System.out.println("Błąd zamykania: " + e.getMessage());
        }
    }
}