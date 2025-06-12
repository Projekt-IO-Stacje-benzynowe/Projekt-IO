package app.service.branch_panel.ClientSimulation;

import java.util.Random;

public class ClientTask {
    public ClientTask(){};
    
    public void run(){
        Client client = new Client("localhost", 9999);
        // while(!Session.isUser());

        while(true){
            client.send(GenerateRandomJsonQuery());
            
            System.out.println("Wysłano wiadomość");
            try{
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                break;
            }
        }
        client.close();
    }
    public String GenerateRandomJsonQuery(){
        Random rand = new Random();
        return Parser.toJson(rand.nextInt(11) + 1, rand.nextInt(50) + 1);
    
    }
}