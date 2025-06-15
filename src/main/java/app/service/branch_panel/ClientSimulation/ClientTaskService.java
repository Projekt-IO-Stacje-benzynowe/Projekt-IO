package app.service.branch_panel.ClientSimulation;

import java.util.Random;

/**
 *  ClientTaskService class for simulating client tasks in a branch panel.
 *  It generates random JSON queries and sends them to a server at specified intervals via port 9999.
 *  This service runs indefinitely until interrupted.
 */
public class ClientTaskService{
    public ClientTaskService(){};
    
    public void run(){
        ClientService client = new ClientService("localhost", 9999);

        while(true){
            client.send(GenerateRandomJsonQuery());
            try{
                Thread.sleep(21000);
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