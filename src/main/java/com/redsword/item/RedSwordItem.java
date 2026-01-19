package com.redsword.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class RedSwordItem extends Item {

    public RedSwordItem(Settings settings) {
        super(settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        // Spawn redstone particles on hit
        World world = attacker.getEntityWorld();
        if (world instanceof ServerWorld serverWorld) {
            spawnRedstoneParticles(serverWorld, target);
        }
    }

    private void spawnRedstoneParticles(ServerWorld world, LivingEntity target) {
        double x = target.getX();
        double y = target.getY();
        double z = target.getZ();

        // Red color (0xFF0000 = red in RGB)
        DustParticleEffect redDust = new DustParticleEffect(0xFF0000, 1.5f);

        // Spawn multiple particles in a burst effect around the target
        for (int i = 0; i < 30; i++) {
            double offsetX = (world.random.nextDouble() - 0.5) * 2.0;
            double offsetY = world.random.nextDouble() * 2.0;
            double offsetZ = (world.random.nextDouble() - 0.5) * 2.0;

            world.spawnParticles(
                redDust,
                x + offsetX,
                y + offsetY,
                z + offsetZ,
                1,
                0.1, 0.1, 0.1,
                0.05
            );
        }

        // Add some extra particles going upward for dramatic effect
        for (int i = 0; i < 15; i++) {
            double offsetX = (world.random.nextDouble() - 0.5) * 1.0;
            double offsetZ = (world.random.nextDouble() - 0.5) * 1.0;

            world.spawnParticles(
                redDust,
                x + offsetX,
                y + 1.0,
                z + offsetZ,
                1,
                0.0, 0.5, 0.0,
                0.2
            );
        }
    }
}
