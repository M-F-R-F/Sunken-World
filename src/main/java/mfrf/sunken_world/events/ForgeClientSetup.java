package mfrf.sunken_world.events;


import mfrf.sunken_world.SunkenWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientSetup {

//    @SubscribeEvent
//    public static void onRenderFog(net.minecraftforge.client.event.EntityViewRenderEvent.FogColors event){
//        if (event.getCamera().getFluidInCamera() == FogType.WATER && Config.DISABLE_WATER_FOG.get()) {
//////            LocalPlayer player = Minecraft.getInstance().player;
//////            player.level.getBiome(player.blockPosition()).value().getWaterColor();
//            event.setRed(0);
//            event.setGreen(0);
//            event.setBlue(0);
//        }
//}

//    @SubscribeEvent
//    public static void onRenderFog(net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent event) {
//        if (event.getCamera().getFluidInCamera() == FogType.NONE && Config.DISABLE_WATER_FOG.get()) {
//            float v = event.getFarPlaneDistance() - event.getNearPlaneDistance();
//            event.setNearPlaneDistance(event.getFarPlaneDistance());
//            event.setFarPlaneDistance(event.getFarPlaneDistance()*2);
////        }
//    }
}
