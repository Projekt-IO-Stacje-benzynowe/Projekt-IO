package app.service.branch_panel.ClientSimulation;

public class Launcher {
    public void runClientTask() {
        ClientTask tk = new ClientTask();
        Thread t = new Thread(tk::run);

        t.setDaemon(true);


        t.start();
    }
    public void runServerTask(){
        ServerTask tk = new ServerTask();

        Thread t = new Thread(tk::run);
        t.setDaemon(true);
        

        t.start();

    }
}       