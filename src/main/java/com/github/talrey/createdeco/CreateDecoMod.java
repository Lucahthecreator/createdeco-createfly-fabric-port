package com.github.talrey.createdeco;

import com.zurrtum.create.foundation.data.CreateDecoRegistrate;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDecoMod {
  public static final String MOD_ID = "createdeco";
  public static final String NAME = "Create Deco";
  public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

  public static final CreateDecoRegistrate REGISTRATE = new CreateDecoRegistrate(MOD_ID);

  public static void init() {
    ItemRegistry.init();
    BlockRegistry.init();
  }

  public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
