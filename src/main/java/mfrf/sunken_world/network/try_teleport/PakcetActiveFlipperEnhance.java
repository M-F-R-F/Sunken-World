package mfrf.sunken_world.network.try_teleport;

import com.mojang.math.Vector3f;
import mfrf.sunken_world.Config;
import mfrf.sunken_world.items.accessories.EndFlipper;
import mfrf.sunken_world.items.accessories.JetFlipper;
import mfrf.sunken_world.registry.CuriosSlotRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.function.Supplier;

public class PakcetActiveFlipperEnhance {
    Vector3f targetVector;

    public PakcetActiveFlipperEnhance(Vector3f targetVector) {
        this.targetVector = targetVector;
    }

    public PakcetActiveFlipperEnhance(FriendlyByteBuf friendlyByteBuf) {
        float x = friendlyByteBuf.readFloat();
        float y = friendlyByteBuf.readFloat();
        float z = friendlyByteBuf.readFloat();
        this.targetVector = new Vector3f(x, y, z);
    }


    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();

            player.getCapability(CuriosCapability.INVENTORY).ifPresent(cap -> {

                cap.getStacksHandler(CuriosSlotRegistry.SLOT_FLIPPER).ifPresent(stack -> {
                    IDynamicStackHandler stacks = stack.getStacks();
                    for (int i = 0; i < stacks.getSlots(); i++) {
                        ItemStack flipper = stacks.getStackInSlot(i);
                        if (flipper.getItem() instanceof EndFlipper) {
                            if (flipper != ItemStack.EMPTY && !player.getCooldowns().isOnCooldown(flipper.getItem()) && EndFlipper.getEnergy(flipper) >= Config.END_FLIPPER_TELEPORT_COST.get()) {

                                ServerLevel level = player.getLevel();
                                Vector3f lookAngle = targetVector;
                                BlockPos eyePos = player.eyeBlockPosition();
                                for (Integer offset = Config.END_FLIPPER_TELEPORT_DISTANCE.get(); offset > 0; offset--) {
                                    Vector3f copy = lookAngle.copy();
                                    copy.mul(offset);
                                    double x = eyePos.getX() + copy.x();
                                    double y = eyePos.getY() + copy.y();
                                    double z = eyePos.getZ() + copy.z();


                                    double f = player.getType().getDimensions().width * 0.8f;
                                    AABB aabb = AABB.ofSize(new Vec3(x, y, z), f, 1.0E-6D, f);


                                    if (!BlockPos.betweenClosedStream(aabb).anyMatch((p_201942_) -> {
                                        if (level.isLoaded(p_201942_)) {
                                            return false;
                                        }
                                        BlockState blockstate = level.getBlockState(p_201942_);
                                        return !blockstate.isAir() && blockstate.isSuffocating(level, p_201942_) && Shapes.joinIsNotEmpty(blockstate.getCollisionShape(level, p_201942_).move((double) p_201942_.getX(), (double) p_201942_.getY(), (double) p_201942_.getZ()), Shapes.create(aabb), BooleanOp.AND);
                                    })) {

                                        player.teleportTo(x, y, z);
                                        EndFlipper.setEnergy(flipper, EndFlipper.getEnergy(flipper) - Config.END_FLIPPER_TELEPORT_COST.get());
                                        player.getCooldowns().addCooldown(flipper.getItem(), Config.END_FLIPPER_TELEPORT_COOL_DOWN.get());
                                        //todo add sound, particles
                                        break;
                                    }

                                }

                            }
                        } else if (flipper.getItem() instanceof JetFlipper) {
                            JetFlipper.switchJetBoost(flipper);
                            break;
                        }

                    }

                });
            });
        });
        return true;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(targetVector.x());
        buf.writeFloat(targetVector.y());
        buf.writeFloat(targetVector.z());
    }
}
