/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.info.packets.base.APacket;
import in.spiph.info.packets.base.ErrorPacket;
import in.spiph.info.packets.base.TestPacket;
import in.spiph.info.packets.client.PagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bennett.DenBleyker
 */
public class PacketClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context;
    private List<APacket> readablePackets = new ArrayList();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        APacket packet = (APacket) msg;
        System.out.println(packet.getFrom() + ": " + packet);
        switch (packet.getType()) {
            case 0: // TestPacket
                switch (packet.getData().toString()) {
                    case "Request":
                        sendPacket(new TestPacket("Hello!"));
                        break;
                    default:
                        System.out.println("Test Succeeds");
                        sendPacket(new PagePacket(123456789));
                }
                break;
            case 2: // PagePacket
                readablePackets.add(packet);
                System.out.println("Page read: " + packet.toString());
                sendPacket(new ErrorPacket("WEHRUHAFG"));
                break;
            default: // ErrorPacket
                System.out.println("Invalid packet id (" + packet.getType() + "): " + packet.toString());
                readablePackets.add(packet);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.context = ctx;
        //ctx.write(new TestPacket());
        //ctx.write(new IpPacket(123456789));
        packetsAwaitingSending.forEach((packet) -> {
            ctx.write(packet);
        });
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause.getMessage().equals("An existing connection was forcibly closed by the remote host")) {
            System.out.println("\n\tServer closed");
        } else {
            cause.printStackTrace();
        }
        ctx.close();
    }

    List<APacket> packetsAwaitingSending = new ArrayList();

    public void sendPacket(APacket packet) {
        System.out.println("Sending " + packet);
        if (context != null) {
            context.writeAndFlush(packet);
        } else {
            packetsAwaitingSending.add(packet);
        }
    }

    public List<APacket> getPackets() {
        List<APacket> packets = this.readablePackets;
        this.readablePackets = new ArrayList();
        return packets;
    }
}
