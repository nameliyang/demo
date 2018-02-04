package com.ly;


import javax.websocket.*;
import java.io.IOException;

public class ChatServer extends Endpoint{

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        System.out.println("peer "+session.getId()+"  connected..");
        session.addMessageHandler(new MessageHandler.Whole<String>(){
            @Override
            public void onMessage(String message) {
                try {
                    session.getBasicRemote().sendText("Got message from " + session.getId() + "\n" + message);
                } catch (IOException ex) {

                }
            }
        });
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Peer " + session.getId() + " disconnected due to " + closeReason.getReasonPhrase());
    }

    @Override
    public void onError(Session session, Throwable error) {
        System.out.println("Error communicating with peer " + session.getId() + ". Detail: "+ error.getMessage());
    }


}
