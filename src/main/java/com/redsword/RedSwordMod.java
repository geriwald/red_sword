package com.redsword;

import com.redsword.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedSwordMod implements ModInitializer {
    public static final String MOD_ID = "red_sword";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Loading Red Sword Mod!");
        ModItems.registerItems();
    }
}
