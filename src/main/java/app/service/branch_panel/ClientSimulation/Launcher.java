package app.service.branch_panel.ClientSimulation;

public class Launcher {
    public Process runClientTask() {
        Client tk = new Client();

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