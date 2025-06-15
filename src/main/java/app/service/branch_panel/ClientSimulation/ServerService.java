package app.service.branch_panel.ClientSimulation;

import java.net.*;
import java.io.*;
import java.io.IOException;


// used for establish connection on port 9999

public class ServerService{
    private ServerSocket serverSocket;
    private DataInputStream cliIn;
    private DataOutputStream cliOut;

    public ServerService(){
        serverSocket = null;
        cliIn = null;
        cliOut = null;
    }
    public ServerService(int port){
        try(ServerSocket ss = new ServerSocket(port)){
            this.serverSocket = ss;

            Socket cliSocket = serverSocket.accept();
            this.cliIn = new DataInputStream(cliSocket.getInputStream());
            this.cliOut = new DataOutputStream(cliSocket.getOutputStream());
            
        }catch(Exception e){
            System.out.println("Erorr while starting server");
        }
    }
    public String read(){
        try{
            return cliIn.readUTF();
        }catch(IOException e){
            System.out.println("Error while reading data from socket " + e);
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