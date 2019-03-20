/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.client.ui.BrowserUI;
import in.spiph.client.ui.UI;
import static in.spiph.client.SingleInstanceService.SIS;
import in.spiph.info.Page;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bennett.DenBleyker
 */
public class Client {

    public static UI ui = new BrowserUI();

    public static String name = "C" + (int) (Math.random() * 1000000) + "C";

    public static Page currentPage = null;
    
    public static void main(String[] args) {
        if (!SIS.alreadyRunning()) {
            SIS.setRunning(true);
            Logger.getLogger(Client.class.getName()).setLevel(Level.WARNING);

            PacketClient server = new PacketClient();
            Thread serverThread = new Thread(() -> {
                try {
                    server.initialize();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "\tServer closed:\n\t" + ex.getMessage(), ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "\tInterrupted:\n\t" + ex.getMessage(), ex);
                }
            });
            Thread SISThread = new Thread(() -> {
                server.handleNewArgs(args);
                while (SIS.alreadyRunning()) {
                    SIS.setRunning(true);
                    String[] s = SIS.getNewArgs();
                    if (s.length > 0) {
                        server.handleNewArgs(s);
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            serverThread.start();
            SISThread.start();
        } else {
            try {
                SIS.setNewArgs(args);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Client is already running. Passing arguments to currently running instance.");
        }
    }

    public static void changePage(Page newPage) {
        ui.displayPage(newPage);
    }
}
