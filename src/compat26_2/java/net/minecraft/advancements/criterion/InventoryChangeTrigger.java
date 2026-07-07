package net.minecraft.advancements.criterion;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.world.level.ItemLike;

public final class InventoryChangeTrigger {
    private InventoryChangeTrigger() {
    }

    public static final class TriggerInstance {
        private TriggerInstance() {
        }

        public static Criterion<?> hasItems(ItemLike... items) {
            return net.minecraft.advancements.triggers.InventoryChangeTrigger.TriggerInstance.hasItems(items);
        }

        public static Criterion<?> hasItems(net.minecraft.advancements.predicates.ItemPredicate... predicates) {
            return net.minecraft.advancements.triggers.InventoryChangeTrigger.TriggerInstance.hasItems(predicates);
        }

        public static Criterion<?> hasItems(net.minecraft.advancements.predicates.ItemPredicate.Builder... builders) {
            return net.minecraft.advancements.triggers.InventoryChangeTrigger.TriggerInstance.hasItems(builders);
        }
    }
}
