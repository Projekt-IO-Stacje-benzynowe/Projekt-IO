package app.service.branch_panel.ClientSimulation;


public class Task implements Runnable {
    @Override
    public void run() {
        Server server = new Server(9999);

        while (true) {
            // System.out.println("Wątek działa w tle!");
            System.out.println("Odebrano wiadomość: " + server.read());
           
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Wątek został przerwany");
                break;
            }
        }
    }

    // private void ()







}
