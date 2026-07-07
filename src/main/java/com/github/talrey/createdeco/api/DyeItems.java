package com.github.talrey.createdeco.api;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class DyeItems {
    public static Item byColor(DyeColor color) {
        try {
            String fieldName = color.name() + "_DYE";
            return (Item) Items.class.getField(fieldName).get(null);
        } catch (ReflectiveOperationException ignored) {
            try {
                Object dyes = Items.class.getField("DYE").get(null);
                return (Item) dyes.getClass().getMethod("pick", DyeColor.class).invoke(dyes, color);
            } catch (ReflectiveOperationException ex) {
                return Items.AIR;
            }
        }
    }
}
