package mfrf.sunken_world.items;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.Entities.water_block_projectile.WaterBlockProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.minecraft.world.level.block.Block.dropResources;

public class ItemSpongeOnAStick extends Item {
    public ItemSpongeOnAStick(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {

        if (!level.isClientSide()) {
            BlockPos blockPos = player.blockPosition();
            ItemStack itemInHand = player.getItemInHand(pUsedHand);
//            CompoundTag orCreateTag = itemInHand.getOrCreateTag();
//            Integer capacity = Config.MAX_WATER_CONTAINS_IN_SPONGE_STICK.get();
//            int water = getWater(itemInHand);
//            int capRemain = capacity - water;
//            if (!(player.isShiftKeyDown() || (isSqueezeMode(itemInHand) && squeeze(level, player, orCreateTag, water)))) {
//
//                if (capRemain > 0) {
//                    int i = removeWater(level, blockPos, capRemain);
//                    orCreateTag.putInt("water", water + i);
//                    if (i >= capRemain) {
//                        orCreateTag.putBoolean("squeeze_mode", true);
//                    }
//                }
//
//            } else {
//                orCreateTag.putBoolean("squeeze_mode", true);
//            }
            player.sendMessage(new TextComponent("受到TeaCon规则限制，为防止破坏场馆，本物品已暂时禁用"),null);
            return InteractionResultHolder.success(itemInHand);
        }
        return super.use(level, player, pUsedHand);
    }

    private boolean squeeze(Level level, Player player, CompoundTag orCreateTag, int water) {
        if (water <= 0) {
            orCreateTag.putBoolean("squeeze_mode", false);
            return false;
        }
        WaterBlockProjectile projectile = new WaterBlockProjectile(player, level);
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        level.addFreshEntity(projectile);
        orCreateTag.putInt("water", water - Config.SPONGE_STICK_BRUST_CONSUME.get());
        return true;
    }


    private int removeWater(Level world, BlockPos pPos, int max) {
        int i = 0;
        if (max >= 9) {
            int range = (int) Math.pow(max, 1 / 3f) / 2;
            for (int y = -range; y <= range && i <= max; y++) {
                for (int x = -range; x <= range && i <= max; x++) {
                    for (int z = -range; z <= range && i <= max; z++) {
                        BlockPos offset = pPos.offset(x, y, z);

                        BlockState blockState = world.getBlockState(offset);
                        if (blockState.getBlock() == Blocks.WATER && blockState.getValue(LiquidBlock.LEVEL) == 0) {
                            world.setBlock(offset, Blocks.AIR.defaultBlockState(), 3);
                            i++;
                        } else if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                            world.setBlock(offset, blockState.setValue(BlockStateProperties.WATERLOGGED, false), 3);
                            i++;
                        }

                    }
                }
            }
        } else {
            for (int x = -1; x <= 1 && i <= max; x++) {
                for (int y = -1; y <= 1 && i <= max; y++) {
                    for (int z = -1; z <= 1 && i <= max; z++) {
                        BlockPos offset = pPos.offset(x, y, z);
                        BlockState blockState = world.getBlockState(offset);
                        if (blockState.getBlock() == Blocks.WATER && blockState.getValue(LiquidBlock.LEVEL) == 0) {
                            world.setBlock(offset, Blocks.AIR.defaultBlockState(), 3);
                            i++;
                        } else if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                            world.setBlock(offset, blockState.setValue(BlockStateProperties.WATERLOGGED, false), 3);
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        CompoundTag orCreateTag = pStack.getOrCreateTag();
        if (!orCreateTag.contains("water")) {
            orCreateTag.putInt("water", 0);
        }
        return (int) (13 * ((float) orCreateTag.getInt("water")) / Config.MAX_WATER_CONTAINS_IN_SPONGE_STICK.get());
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return isSqueezeMode(pStack) ? 0x00FF00 : 0x0000FF;
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    private int getWater(ItemStack pStack) {
        CompoundTag orCreateTag = pStack.getOrCreateTag();
        if (!orCreateTag.contains("water")) {
            orCreateTag.putInt("water", 0);
        }
        return orCreateTag.getInt("water");
    }

    private boolean isSqueezeMode(ItemStack stack) {
        CompoundTag orCreateTag = stack.getOrCreateTag();
        if (!orCreateTag.contains("squeeze_mode")) {
            orCreateTag.putBoolean("squeeze_mode", false);
        }
        return orCreateTag.getBoolean("squeeze_mode");
    }
}
