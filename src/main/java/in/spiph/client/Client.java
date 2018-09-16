/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.info.Page;
import java.io.IOException;

/**
 *
 * @author Bennett.DenBleyker
 */
public class Client {

    public static String name = "C" + (int) (Math.random() * 1000000) + "C";

    public static Page currentPage = null;

    public static void main(String[] args) {
        //FIX: s.sendPacket(APacket) no longer works after running s.initialize() //NOTE: s.initialize only ends when server closes. After the block, the code is unreachable until then.
        PacketClient server = new PacketClient();
        Thread serverThread = new Thread(() -> {
            try {
                server.initialize();
            } catch (IOException ex) {
                System.out.println("\tServer closed:\n\t" + ex.getMessage());
            } catch (InterruptedException ex) {
                System.out.println("\tInterrupted:\n\t" + ex.getMessage());
            }
        });
        serverThread.start();
    }

    public static void changePage(Page newPage) {
        System.out.println("\nNEW PAGE\n\n" + newPage.getHtml() + "\n\n" + newPage.getPosts().stream().map((p) -> p.toString() + "\n").reduce("", String::concat) + "\n");
    }
}
