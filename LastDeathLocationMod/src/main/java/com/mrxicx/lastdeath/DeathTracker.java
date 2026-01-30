package com.mrxicx.lastdeath;

import net.minecraft.client.MinecraftClient;

public class DeathTracker {

    private static String lastDeath = "No death recorded yet.";

    public static void recordDeath(MinecraftClient client) {
        if (client.player == null) return;

        double x = client.player.getX();
        double y = client.player.getY();
        double z = client.player.getZ();
        String dimension = client.player.getWorld()
                .getRegistryKey().getValue().toString();

        lastDeath = String.format(
                "Last Death → X: %.1f Y: %.1f Z: %.1f | %s",
                x, y, z, dimension
        );
    }

    public static String getLastDeath() {
        return lastDeath;
    }
}
