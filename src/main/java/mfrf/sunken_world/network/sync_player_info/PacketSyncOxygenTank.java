package mfrf.sunken_world.network.sync_player_info;

import com.mojang.math.Vector3f;
import mfrf.sunken_world.Config;
import mfrf.sunken_world.items.accessories.EndFlipper;
import mfrf.sunken_world.registry.CuriosSlotRegistry;
import mfrf.sunken_world.render.EquipmentHud;
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
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.function.Supplier;

public class PacketSyncOxygenTank {
    private float percent;


    public PacketSyncOxygenTank(float percent) {
        this.percent = percent;
    }

    public PacketSyncOxygenTank(FriendlyByteBuf friendlyByteBuf) {
        this.percent = friendlyByteBuf.readFloat();
    }


    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                EquipmentHud.setPercent(percent);
            }
        });
        return true;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(percent);
    }

}
