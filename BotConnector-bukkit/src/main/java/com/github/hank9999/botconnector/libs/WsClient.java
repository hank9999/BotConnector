package com.github.hank9999.botconnector.libs;

import com.github.hank9999.botconnector.events.WebSocketRecevicedMessage;
import com.github.hank9999.botconnector.utils.WebSocket;
import com.github.hank9999.botconnector.BotConnectorBukkit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import java.net.URI;

public class WsClient extends WebSocketClient {

    public WsClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        if (BotConnectorBukkit.plugin != null) {
            BotConnectorBukkit.logger.info("WebSocket Connected");
        }
        WebSocket.Connected = true;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        if (BotConnectorBukkit.plugin != null) {
            BotConnectorBukkit.logger.info(
                    "WebSocket Connection closed by " + (remote ? "remote" : "local") + " Code: " + code + " Reason: " + reason
            );
        }
        WebSocket.Connected = false;
        WebSocket.Reconnect();
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    @Override
    public void onMessage(String message) {
        new WebSocketRecevicedMessage().handler(message);
    }
}
