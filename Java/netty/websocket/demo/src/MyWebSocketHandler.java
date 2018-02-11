package com.me.websocket.netty;

import java.nio.charset.Charset;
import java.util.Date;

import javax.management.RuntimeErrorException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

	private WebSocketServerHandshaker handshaker;
	private final String WEBSOCKET_URL = "ws://localhost:8888/websockt"; 
	
	// when connected..
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.channelGroup.add(ctx.channel());
		System.err.println("A client has connected!");
	}

	// when disconnected..
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.channelGroup.remove(ctx.channel());
		System.err.println("A client has disconnected!");
	}

	// when finish receiving the client's message 
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	// when catch a exception...
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}

	// 处理客户端请求的核心方法
	@Override
	protected void messageReceived(ChannelHandlerContext cxt, Object msg) throws Exception {
		if(msg instanceof FullHttpRequest){// hand shake request
			handleHandShakeRequest(cxt,(FullHttpRequest)msg);
		}else if(msg instanceof WebSocketFrame){// 是websocket数据帧
			handleWebsocketFrame(cxt,(WebSocketFrame)msg);
		}
	}

	private void handleWebsocketFrame(ChannelHandlerContext cxt, WebSocketFrame frame) {
		// close 
		if( frame instanceof CloseWebSocketFrame){
			handshaker.close(cxt.channel(), (CloseWebSocketFrame)frame.retain());
			return;
		}
		
		// ping
		if(frame instanceof PingWebSocketFrame){
			cxt.channel().write(new PingWebSocketFrame(frame.content().retain()));
			return;
		}
		
		// binary message
		if(!(frame instanceof TextWebSocketFrame)){
			System.out.println("Binary message is not supported!");
			//throw new RuntimeException("....");
			return;
		}
		
		// response to client..
		String reqText = ((TextWebSocketFrame)frame).text();
		System.err.println("Received client message======>>>>" + reqText);
		TextWebSocketFrame txtwsf = new TextWebSocketFrame(
				new Date().toLocaleString() 
				+ " channel id:"+ cxt.channel().id()
				+"====>>"+reqText);
		//群发消息
		NettyConfig.channelGroup.writeAndFlush(txtwsf);
	}

	//处理握手请求
	private void handleHandShakeRequest(ChannelHandlerContext cxt, FullHttpRequest req) {
		
		// if not success
		if(!req.decoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))){//websocket is the "Upgrade" of http?
			sendHttpResponse(cxt, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		
		WebSocketServerHandshakerFactory wshsFactory = new WebSocketServerHandshakerFactory(WEBSOCKET_URL, null, false);
		handshaker = wshsFactory.newHandshaker(req);
		
		if(handshaker == null){
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(cxt.channel());
		}else{
			handshaker.handshake(cxt.channel(), req);
		}
		
	}
	
	private void sendHttpResponse(ChannelHandlerContext cxt, FullHttpRequest req, 
			DefaultFullHttpResponse resp){
		
		if(resp.status().code() != 200){//发生错误，发送错误状态
			ByteBuf buf = Unpooled.copiedBuffer(resp.status().toString(), CharsetUtil.UTF_8);
			resp.content().writeBytes(buf);
			buf.release();
		}
		
		ChannelFuture future = cxt.channel().writeAndFlush(resp);
		
		if(resp.status().code() != 200){//发生错误，关闭连接
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}


}
