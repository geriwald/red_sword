package com.redsword;

import net.fabricmc.api.ClientModInitializer;

public class RedSwordModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RedSwordMod.LOGGER.info("Red Sword Mod Client initialized!");
    }
}
