package com.github.hank9999.botconnector.Events.sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.message.MessageEvent;

public class ChatEvent {

    @Listener(order=Order.POST)
    public void onChat(MessageEvent event) {
    }

}