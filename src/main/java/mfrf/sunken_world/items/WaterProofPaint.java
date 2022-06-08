package mfrf.sunken_world.items;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.blocks.water_proof_furnace.TileWaterProofFurnace;
import mfrf.sunken_world.registry.Attributes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

public class WaterProofPaint extends Item {
    public static final UUID SWIFT_DIG_UUID = UUID.randomUUID();

    public WaterProofPaint(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos clickedPos = pContext.getClickedPos();
        BlockState blockState = level.getBlockState(clickedPos);
        if (blockState.getBlock() == Blocks.FURNACE) {
            CompoundTag compoundTag = new CompoundTag();
            ((AbstractFurnaceBlockEntity) level.getBlockEntity(clickedPos)).saveAdditional(compoundTag);
            level.setBlockAndUpdate(clickedPos, mfrf.sunken_world.registry.Blocks.WATER_PROOF_FURNACE.block().get().defaultBlockState()
                    .setValue(AbstractFurnaceBlock.LIT, blockState.getValue(AbstractFurnaceBlock.LIT))
                    .setValue(AbstractFurnaceBlock.FACING, blockState.getValue(AbstractFurnaceBlock.FACING)));
            ((TileWaterProofFurnace) level.getBlockEntity(clickedPos)).setTag(compoundTag);
            pContext.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }

        return super.useOn(pContext);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            ItemStack stack_to_be_modify;
            ItemStack stack_to_be_shrink;
            boolean modified = false;

            if (pUsedHand == InteractionHand.MAIN_HAND) {
                stack_to_be_modify = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
                stack_to_be_shrink = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
            } else {
                stack_to_be_modify = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
                stack_to_be_shrink = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
            }

            if (!stack_to_be_modify.isEmpty()) {
                Collection<AttributeModifier> attributeModifiers = stack_to_be_modify.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.SWIFT_DIG.get());
                if (!attributeModifiers.isEmpty()) {


                    if (stack_to_be_modify.getOrCreateTag().contains("AttributeModifiers", 9)) {
                        ListTag listtag = stack_to_be_modify.getOrCreateTag().getList("AttributeModifiers", 10);

                        for (int i = 0; i < listtag.size(); ++i) {
                            CompoundTag compoundtag = listtag.getCompound(i);
                            if (!compoundtag.contains("Slot", 8) || compoundtag.getString("Slot").equals(EquipmentSlot.MAINHAND.getName())) {


                                if (ResourceLocation.tryParse(compoundtag.getString("AttributeName")).equals(Attributes.SWIFT_DIG.get().getRegistryName())) {
                                    if (compoundtag.getDouble("Amount") < Config.MAX_SWIFT_DIG.get()) {
                                        compoundtag.putDouble("Amount", Math.min(compoundtag.getDouble("Amount") + Config.SWIFT_DIG_ADDITION.get(), Config.MAX_SWIFT_DIG.get()));
                                        modified = true;
                                    }
                                }

                            }
                        }


                    } else {
                        stack_to_be_modify.addAttributeModifier(Attributes.SWIFT_DIG.get(), new AttributeModifier(SWIFT_DIG_UUID, "tool_modifier", Config.SWIFT_DIG_ADDITION.get(), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                    }


                    if (modified) {
                        stack_to_be_shrink.shrink(1);
                        return InteractionResultHolder.success(stack_to_be_shrink);
                    } else {
                        return InteractionResultHolder.pass(stack_to_be_shrink);
                    }
                }
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);

    }
}
