package net.minecraft.advancements.criterion;

public final class ItemPredicate {
    private ItemPredicate() {
    }

    public static final class Builder {
        private Builder() {
        }

        public static net.minecraft.advancements.predicates.ItemPredicate.Builder item() {
            return net.minecraft.advancements.predicates.ItemPredicate.Builder.item();
        }
    }
}
