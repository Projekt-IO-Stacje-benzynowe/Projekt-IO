package app.service.branch_panel.ClientSimulation;

import java.util.Random;


public class ClientTask {
    public ClientTask(){};
    
    public void run(){
        Client client = new Client("localhost", 9999);

        while(true){
            client.send(GenerateRandomJsonQuery());
            
            System.out.println("Wysłano wiadomość");
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
        }
        client.close();
    }

    public String GenerateRandomJsonQuery(){
        Random rand = new Random();

        // ewentualnie zmienic wraz id ostatniego dodanego produktu
        return Parser.toJson(rand.nextInt(12), rand.nextInt(51));
    }



}
