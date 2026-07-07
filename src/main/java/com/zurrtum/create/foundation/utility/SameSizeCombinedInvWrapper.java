package com.zurrtum.create.foundation.utility;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;

public class SameSizeCombinedInvWrapper {
    public static IItemHandlerModifiable create(IItemHandlerModifiable[] inventories) {
        return new ItemStackHandler();
    }
}
