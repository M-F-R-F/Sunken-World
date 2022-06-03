package mfrf.sunken_world.network.sync_player_info;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.network.try_teleport.PakcetTryTeleport;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.sql.Connection;

public class SyncPlayerInfoChannel {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }


    public static void register() {
        // Make the channel. If needed you can do version checking here
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(SunkenWorld.MODID, "sync_player_info"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        // Register all our packets. We only have one right now. The new message has a unique ID, an indication
        // of how it is going to be used (from client to server) and ways to encode and decode it. Finally 'handle'
        // will actually execute when the packet is received
        net.messageBuilder(PacketSyncOxygenTank.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncOxygenTank::new)
                .encoder(PacketSyncOxygenTank::toBytes)
                .consumer(PacketSyncOxygenTank::handle)
                .add();
    }

    public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            INSTANCE.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}
