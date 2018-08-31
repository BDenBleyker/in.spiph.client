/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.client.servercom.ServerCom;
import in.spiph.info.packets.base.TestPacket;
import java.io.IOException;

/**
 *
 * @author Bennett.DenBleyker
 */
public class Client {

    public static String name = (int) (Math.random() * 1000000) + "C";

    public static void main(String[] args) {
        //FIX: s.sendPacket(APacket) no longer works after running s.initialize() //NOTE: s.initialize only ends when server closes. After the block, the code is unreachable until then.
        ServerCom server = new ServerCom();
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
        server.sendPacket(new TestPacket());
    }
}
