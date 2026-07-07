package com.tterrag.registrate.providers;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.TrapDoorBlock;

public class RegistrateBlockstateProvider {
    public Identifier modLoc(String path) {
        return Identifier.fromNamespaceAndPath("createdeco", path);
    }

    public Identifier mcLoc(String path) {
        return Identifier.fromNamespaceAndPath("minecraft", path);
    }

    public void trapdoorBlock(TrapDoorBlock block, Identifier texture, boolean orientable) {
    }
}
