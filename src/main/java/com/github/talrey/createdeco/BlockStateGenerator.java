package com.github.talrey.createdeco;

import com.github.talrey.createdeco.blocks.DecalBlock;
import com.zurrtum.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.zurrtum.create.content.decoration.palettes.ConnectedPillarBlock;
import com.zurrtum.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;

public class BlockStateGenerator {
    public static void bar(String base, String post, Identifier barTexture, Identifier postTexture, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void barItem(String base, String suf, Identifier bartex, DataGenContext<Item, ?> ctx, RegistrateItemModelProvider prov) {}
    public static void fence(String metal, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void cageLamp(Identifier cage, Identifier lampOn, Identifier lampOff, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void catwalk(CreateRegistrate reg, String metal, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void catwalkItem(String metal, DataGenContext<Item, ?> ctx, RegistrateItemModelProvider prov) {}
    public static void catwalkStair(String texture, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void catwalkRailing(CreateRegistrate reg, String metal, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void door(CreateRegistrate reg, String metal, boolean locked, DataGenContext<Block, DoorBlock> ctx, RegistrateBlockstateProvider prov) {}
    public static void doorItem(CreateRegistrate reg, String metal, DataGenContext<Item, ?> ctx, RegistrateItemModelProvider prov) {}
    public static void hull(CreateRegistrate reg, String metal, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void support(CreateRegistrate reg, String metal, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void supportWedge(CreateRegistrate reg, String metal, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void trapdoorItem(CreateRegistrate reg, String metal, DataGenContext<Item, ?> ctx, RegistrateItemModelProvider prov) {}
    public static void placard(CreateRegistrate reg, DyeColor color, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void shippingContainer(CreateRegistrate reg, DyeColor color, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void coinstackBlock(Identifier side, Identifier bottom, Identifier top, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void brick(DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov, String color) {}
    public static void brickStair(DataGenContext<Block, StairBlock> ctx, RegistrateBlockstateProvider prov, String color) {}
    public static void brickSlab(DataGenContext<Block, SlabBlock> ctx, RegistrateBlockstateProvider prov, String color) {}
    public static void brickWall(DataGenContext<Block, WallBlock> ctx, RegistrateBlockstateProvider prov, String color) {}
    public static void window(DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov, NonNullFunction<String, Identifier> sideTexture, NonNullFunction<String, Identifier> endTexture) {}
    public static NonNullBiConsumer<DataGenContext<Block, ConnectedGlassPaneBlock>, RegistrateBlockstateProvider> windowPane(String parents, String prefix, Identifier sideTexture, Identifier topTexture) {
        return (ctx, prov) -> {};
    }
    public static void ladder(DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov, String regName) {}
    public static void facade(CreateRegistrate reg, String metal, DataGenContext<Block, ?> ctx, RegistrateBlockstateProvider prov) {}
    public static void decal(CreateRegistrate reg, String prefix, DataGenContext<Block, DecalBlock> ctx, RegistrateBlockstateProvider prov) {}
    public static void brickWallItem(DataGenContext<Item, BlockItem> ctx, RegistrateItemModelProvider prov, String color) {}
    public static void sheetMetal(String metal, DataGenContext<Block, ConnectedPillarBlock> ctx, RegistrateBlockstateProvider prov) {}
}
