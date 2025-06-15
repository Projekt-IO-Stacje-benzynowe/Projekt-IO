package app.service.branch_panel.ClientSimulation;

/**
 *  LauncherService class for managing the execution of client and server tasks.
 */
public class LauncherService {
    
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