package com.github.hank9999.botconnector.Libs;

import com.github.hank9999.botconnector.Utils.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import java.net.URI;

public class WsClient extends WebSocketClient {

    public WsClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("WebSocket Connected");
        WebSocket.Connected = true;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "WebSocket Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
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
        System.out.println("WebSocket received: " + message);
    }
}
