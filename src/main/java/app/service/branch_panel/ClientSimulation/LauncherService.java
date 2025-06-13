package app.service.branch_panel.ClientSimulation;



// used for run client and server task's

public class LauncherService {
    // public static ClientTask t1;
    // public static ServerTask t2;

    public void runClientTask() {
        ClientTaskService tk = new ClientTaskService();
        Thread t = new Thread(tk::run);

        t.setDaemon(true);
        t.start();
    }
    public void runServerTask(){
        ServerTaskService tk = new ServerTaskService();

        Thread t = new Thread(tk::run);
        t.setDaemon(true);
        t.start();
    }
}       