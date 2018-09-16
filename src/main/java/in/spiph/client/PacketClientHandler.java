/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.info.Page;
import in.spiph.info.packets.base.APacket;
import in.spiph.info.packets.base.ErrorPacket;
import in.spiph.info.packets.base.TestPacket;
import in.spiph.info.packets.client.PagePacket;
import in.spiph.info.packets.handling.PacketHandler;
import io.netty.channel.ChannelPipeline;

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
            System.out.println("\n\tServer closed");
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void handlePacket(ChannelPipeline pipeline, APacket packet) {
        switch (packet.getType()) {
            case 0: // TestPacket
                switch (packet.getData().toString()) {
                    case "Request":
                        pipeline.fireUserEventTriggered(new TestPacket("Hello!"));
                        break;
                    default:
                        System.out.println("Test Succeeds");
                        pipeline.fireUserEventTriggered(new PagePacket("bdbleyker@gmail.com"));
                }
                break;
            case 2: // PagePacket
                if (packet.getData() instanceof Page) {
                    Client.changePage((Page) packet.getData());
                    System.out.println("Page read: " + packet.toString());
                    pipeline.fireUserEventTriggered(new ErrorPacket("WEHRUHAFG"));
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
