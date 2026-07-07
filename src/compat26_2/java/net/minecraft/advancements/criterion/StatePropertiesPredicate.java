package net.minecraft.advancements.criterion;

public final class StatePropertiesPredicate {
    private StatePropertiesPredicate() {
    }

    public static final class Builder {
        private Builder() {
        }

        public static net.minecraft.advancements.predicates.StatePropertiesPredicate.Builder properties() {
            return net.minecraft.advancements.predicates.StatePropertiesPredicate.Builder.properties();
        }
    }
}
