package app.service.branch_panel.ClientSimulation;

import java.util.Map;

import app.service.TypeValidation;

public class ServerTask{
    public ServerTask(){};

    public void run(){
        Server server = new Server(9999);

        while (true) {
            System.out.println("Odebrano wiadomość: " + server.read());
            Map<String, String>  dataMap = Parser.parseJson(server.read());
            
            // dataMap['productID']
            // dataMap['quantity']

            // trzeba sparsować values z mapy na inty
            int productID = TypeValidation.intValidation(dataMap.get("productID"));
            int quantity = TypeValidation.intValidation(dataMap.get("quantity"));



            // if(isQuantityAvailable(dataMap['productID'], dataMap['quantity'])){

            // }


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Wątek został przerwany");
                break;
            }
        }
    }


    public boolean isQuantityAvailable(int productID, int Quantity){
        // zapytanie do BD o ilosc dla produktu



        return false;
    }


}
