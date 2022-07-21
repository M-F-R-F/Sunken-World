package mfrf.sunken_world.Entities.harmless.spadefish;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SpadeFishRenderer extends LivingEntityRenderer<EntitySpadeFish,SpadeFishModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(SunkenWorld.MODID,"textures/entity/spade_fish.png");
    public SpadeFishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext,new SpadeFishModel(pContext.bakeLayer(SpadeFishModel.LAYER_LOCATION)),0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySpadeFish pEntity) {
        return TEXTURE;
    }
}
