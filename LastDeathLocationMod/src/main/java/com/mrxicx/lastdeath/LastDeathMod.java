package com.mrxicx.lastdeath;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class LastDeathMod implements ClientModInitializer {

    private boolean wasAlive = true;

    @Override
    public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            boolean alive = client.player.isAlive();
            if (wasAlive && !alive) {
                DeathTracker.recordDeath(client);
            }
            wasAlive = alive;
        });

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(CommandManager.literal("lastdeath")
                .executes(context -> {
                    context.getSource().sendFeedback(
                        Text.literal(DeathTracker.getLastDeath())
                    );
                    return 1;
                })
            );
        });
    }
}
