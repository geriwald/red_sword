package com.redsword.item;

import com.redsword.RedSwordMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    // Custom tool material for the red sword (based on netherite but better)
    public static final ToolMaterial RED_SWORD_MATERIAL = new ToolMaterial(
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL,  // blocks it can't mine
        4000,  // durability
        10.0F, // mining speed
        199.0F, // attack damage bonus (100 hearts = 200 damage, minus 1 base = 199)
        25,    // enchantability
        ItemTags.NETHERITE_TOOL_MATERIALS  // repair items
    );

    public static final Item RED_SWORD = register(
        "red_sword",
        RedSwordItem::new,
        new Item.Settings()
            .sword(RED_SWORD_MATERIAL, 199.0F, -2.4F)  // damage, attack speed
            .fireproof()
    );

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
    }
}
