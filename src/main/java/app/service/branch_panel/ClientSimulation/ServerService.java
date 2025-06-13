package app.service.branch_panel.ClientSimulation;

import java.net.*;
import java.io.*;
import java.io.IOException;


// used for establish connection on port 9999

public class ServerService{
    private ServerSocket serverSocket;
    private DataInputStream cliIn;
    private DataOutputStream cliOut;

    public ServerService(int port){
        try(ServerSocket ss = new ServerSocket(port)){
            System.out.println("Uruchomiono server");
            this.serverSocket = ss;

            Socket cliSocket = serverSocket.accept();
            this.cliIn = new DataInputStream(cliSocket.getInputStream());
            this.cliOut = new DataOutputStream(cliSocket.getOutputStream());
            
        }catch(Exception e){
            System.out.println("Błąd podczas uruchamiania servera");
        }
    }
    public String read(){
        try{
            return cliIn.readUTF();
        }catch(IOException e){
            System.out.println("Błąd odczytu: " + e);
        };        
        return null;
    }
    public void close(){
        try{
            this.serverSocket.close();
            this.cliIn.close();
            this.cliOut.close();
        }catch(IOException ioe){
            System.out.println("Error while closing sockets and streams " + ioe);
        }
    }
}