/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.info.Page;
import in.spiph.info.packets.base.ErrorPacket;
import in.spiph.info.packets.client.PagePacket;
import in.spiph.info.packets.handling.PacketClientInitializer;
import in.spiph.info.packets.handling.PacketHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bennett.DenBleyker
 */
public class PacketClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.27.20.30");
    static final int PORT = 4198;

    public PacketHandler handler = new PacketClientHandler(Client.name);

    boolean started = false;

    public void initialize() throws IOException, InterruptedException {
        if (!started) {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Starting on port {0}", PORT);

            final SslContext sslCtx;
            if (SSL) {
                sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            } else {
                sslCtx = null;
            }
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new PacketClientInitializer(sslCtx, HOST, PORT, handler));
                ChannelFuture f = b.connect(HOST, PORT).sync().channel().closeFuture().sync();
            } finally {
                group.shutdownGracefully();
            }
            started = true;
        }
    }

    public void handleNewArgs(String[] args) {
        if (args.length > 1) {
            switch (args[0].toLowerCase()) {
                case "page":
                    String pgRq = "";
                    for (String arg : args) {
                        pgRq += arg + " ";
                    }
                    pgRq += "|X|";
                    handler.fireUserEventTriggered(new PagePacket(pgRq.replace(" |X|", "").replace("page ", "")));
                    break;
                default:
                    String s = "";
                    for (String arg : args) {
                        s += arg + " ";
                    }
                    handler.fireUserEventTriggered(new ErrorPacket(s));
            }
        } else if (args.length == 1) {
            handler.fireUserEventTriggered(new ErrorPacket(args[0]));
        }
    }
    
    public void requestPage() {
        handler.fireUserEventTriggered(new PagePacket("self"));
    }
    
    public void setPage(Page page) {
        handler.fireUserEventTriggered(new PagePacket(page));
    }

}
