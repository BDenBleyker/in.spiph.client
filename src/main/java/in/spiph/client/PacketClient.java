/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.client;

import in.spiph.client.PacketClientHandler;
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

/**
 *
 * @author Bennett.DenBleyker
 */
public class PacketClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = 4198;
    
    public PacketHandler handler = new PacketClientHandler(Client.name);

    boolean started = false;

    public void initialize() throws IOException, InterruptedException {
        if (!started) {
            System.out.println("Starting on port " + PORT);

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

}
