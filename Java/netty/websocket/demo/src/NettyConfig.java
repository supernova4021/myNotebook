package com.me.websocket.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 *  netty global config
 * @author Administrator
 *
 */
public class NettyConfig {
	/**
	 *  store the channel of clients
	 */
	public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	

}
