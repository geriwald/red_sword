package com.redsword.item;

import com.redsword.RedSwordMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    // Item de test simple
    public static final Item TEST_ITEM = register(
            "test_item",
            Item::new,
            new Item.Settings());

    public static final Item RED_SWORD = register(
            "red_sword",
            RedSwordItem::new,
            new Item.Settings()
                    .maxCount(1)
                    .fireproof());

    private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(RedSwordMod.MOD_ID, name));
        return Registry.register(Registries.ITEM, key, factory.apply(settings.registryKey(key)));
    }

    public static void registerItems() {
        RedSwordMod.LOGGER.info("Registering items for " + RedSwordMod.MOD_ID);

        // Add to combat item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(RED_SWORD);
        });
        
        // Add test item to ingredients
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TEST_ITEM);
        });
    }
}
