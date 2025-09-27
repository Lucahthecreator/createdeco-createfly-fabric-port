package com.github.talrey.createdeco.forge;

import com.github.talrey.createdeco.CreateDecoMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(CreateDecoMod.MOD_ID)
public class CreateDecoModForge {
    public CreateDecoModForge(IEventBus eventBus) {
        // registrate must be given the mod event bus on forge before registration
        CreativeTabsImpl.register(eventBus);
        CreateDecoMod.REGISTRATE.registerEventListeners(eventBus);
        NeoForge.EVENT_BUS.register(this);
        CreateDecoMod.init();
    }
}
