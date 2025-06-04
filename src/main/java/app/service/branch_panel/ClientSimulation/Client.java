package app.service.branch_panel.ClientSimulation;

import java.io.*;
import java.net.*;

public class Client{
    private Socket s = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public Client(){};


    public Client(String address, int port) {
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

    public void run(){
        Client client = new Client("localhost", 9999);

        while(true){

            client.send(Parser.toJson(1, 13));
            System.out.println("Wysłano wiadomość");
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
        }
        client.close();
    }
}