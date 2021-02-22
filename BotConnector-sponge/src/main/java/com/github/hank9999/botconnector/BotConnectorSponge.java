package com.github.hank9999.botconnector;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "botconnector",
        name = "BotConnector",
        version = "0.1.1",
        description = "Connect your Minecraft server with the bot program via WebSocket",
        url = "https://github.com/hank9999/BotConnector",
        authors = {
                "hank9999"
        }
)
public class BotConnectorSponge {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
    }
}
