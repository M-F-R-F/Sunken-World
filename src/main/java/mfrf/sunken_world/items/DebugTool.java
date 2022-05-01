package mfrf.sunken_world.items;

import mfrf.sunken_world.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.storage.LevelStorageException;
import net.minecraft.world.level.storage.LevelSummary;
import net.minecraftforge.common.world.ForgeWorldPreset;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class DebugTool extends Item {
    public DebugTool() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {

            String[] split = Config.DIMENSIONS_WILL_BE_EFFECT.get().split("\\|");
            Collection<ForgeWorldPreset> forgeWorldPresetCollection = ForgeRegistries.WORLD_TYPES.get().getValues();
            var dimensionsWillBeEffectCache = forgeWorldPresetCollection.stream().filter(dimensionType -> Arrays.asList(split).contains(dimensionType.getRegistryName().toString())).map(forgeWorldPreset -> forgeWorldPreset.getRegistryName().toString()).collect(Collectors.toList());

        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

}
