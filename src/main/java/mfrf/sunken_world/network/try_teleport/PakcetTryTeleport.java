package mfrf.sunken_world.network.try_teleport;

import com.mojang.math.Vector3f;
import mfrf.sunken_world.Config;
import mfrf.sunken_world.items.accessories.EndFlipper;
import mfrf.sunken_world.registry.CuriosSlotRegistry;
import net.minecraft.core.BlockPos;
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

public class PakcetTryTeleport {
    Vector3f targetVector;

    public PakcetTryTeleport(Vector3f targetVector) {
        this.targetVector = targetVector;
    }

    public PakcetTryTeleport(FriendlyByteBuf friendlyByteBuf) {
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
                    ItemStack end_flipper = ItemStack.EMPTY;
                    for (int i = 0; i < stacks.getSlots(); i++) {
                        ItemStack stackInSlot = stacks.getStackInSlot(i);
                        if (stackInSlot.getItem() instanceof EndFlipper) {
                            end_flipper = stackInSlot;
                            break;
                        }
                    }

                    if (end_flipper != ItemStack.EMPTY && !player.getCooldowns().isOnCooldown(end_flipper.getItem()) && EndFlipper.getEnergy(end_flipper) >= Config.END_FLIPPER_TELEPORT_COST.get()) {
                        ServerLevel level = player.getLevel();
//                        Vec3 lookAngle = player.getLookAngle();
                        Vector3f lookAngle = targetVector;
                        BlockPos eyePos = player.eyeBlockPosition();
//                        double xLook = lookAngle.x();
//                        double yLook = lookAngle.y();
//                        double zLook = lookAngle.z();
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
                                EndFlipper.setEnergy(end_flipper, EndFlipper.getEnergy(end_flipper) - Config.END_FLIPPER_TELEPORT_COST.get());
                                player.getCooldowns().addCooldown(end_flipper.getItem(), Config.END_FLIPPER_TELEPORT_COOL_DOWN.get());
                                //todo add sound, particles
                                break;
                            }

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
