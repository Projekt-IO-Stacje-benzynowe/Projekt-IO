package app.service.branch_panel.ClientSimulation;

import java.util.Random;

// We simulate a client buying some items from our list, then send the data to our system via port 9999.

public class ClientTaskService {
    public ClientTaskService(){};
    
    public void run(){
        ClientService client = new ClientService("localhost", 9999);

        while(true){
            client.send(GenerateRandomJsonQuery());
            
            System.out.println("Wysłano wiadomość");
            try{
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                break;
            }
        }
        client.close();
    }
    public String GenerateRandomJsonQuery(){
        Random rand = new Random();
        return ParserService.toJson(rand.nextInt(11) + 1, rand.nextInt(50) + 1);
    
    }
}