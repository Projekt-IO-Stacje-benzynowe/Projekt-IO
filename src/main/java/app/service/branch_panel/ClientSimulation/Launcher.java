package app.service.branch_panel.ClientSimulation;

public class Launcher {
    public Process runClientTask() {
        ClientTask tk = new ClientTask();
        Thread t = new Thread(tk::run);

        t.setDaemon(true);
        t.start();

        return null;
    }
    public void runServerTask(){
        Task tk = new Task();

        Thread t = new Thread(tk::run);
        t.setDaemon(true);
        t.start();
    }
}