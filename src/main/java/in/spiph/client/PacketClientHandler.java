/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.info.Page;
import in.spiph.info.packets.base.APacket;
import in.spiph.info.packets.base.TestPacket;
import in.spiph.info.packets.client.PagePacket;
import in.spiph.info.packets.handling.PacketHandler;
import io.netty.channel.ChannelPipeline;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bennett.DenBleyker
 */
public class PacketClientHandler extends PacketHandler {

    public PacketClientHandler(String from) {
        super(from);
    }

    @Override
    public boolean handleException(Throwable cause) {
        if (cause.getMessage().equals("An existing connection was forcibly closed by the remote host")) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "\n\tServer Closed");
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void handlePacket(ChannelPipeline pipeline, APacket packet) {
        switch (packet.getType()) {
            case TestPacket.TYPE_VALUE:
                if (packet.isRequest()) {
                    pipeline.fireUserEventTriggered(new TestPacket("Hello!"));
                } else {
                    Logger.getLogger(Client.class.getName()).log(Level.INFO, "Test Succeeds");
                }
                break;
            case PagePacket.TYPE_VALUE:
                if (packet.getData() instanceof Page) {
                    Client.changePage((Page) packet.getData());
                } else {
                    pipeline.fireUserEventTriggered(packet);
                }
                break;
            default: // ErrorPacket
                System.out.println("Invalid packet id (" + packet.getType() + "): " + packet.toString());
        }
    }

    @Override
    public boolean getTestMode() {
        return true;
    }

}
