package com.tterrag.registrate.providers;

import com.github.talrey.createdeco.CreateDecoMod;
import net.minecraft.resources.Identifier;
import java.util.function.Supplier;

public class RegistrateItemModelProvider {
    public Identifier modLoc(String path) {
        return CreateDecoMod.id(path);
    }

    public Identifier mcLoc(String path) {
        return Identifier.withDefaultNamespace(path);
    }

    public RegistrateItemModelProvider withExistingParent(String name, Identifier parent) {
        return this;
    }

    public RegistrateItemModelProvider texture(String key, Identifier texture) {
        return this;
    }

    public RegistrateItemModelProvider blockSprite(Supplier<?> block, Identifier sprite) {
        return this;
    }

    public RegistrateItemModelProvider singleTexture(String name, Identifier parent, String key, Identifier texture) {
        return this;
    }
}
