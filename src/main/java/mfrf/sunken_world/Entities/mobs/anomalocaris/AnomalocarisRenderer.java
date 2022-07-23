package mfrf.sunken_world.Entities.mobs.anomalocaris;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AnomalocarisRenderer extends MobRenderer<MobEntityAnomalocaris, AnomalocarisModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(SunkenWorld.MODID, "textures/entity/anomalocaris.png");

    public AnomalocarisRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AnomalocarisModel(pContext.bakeLayer(AnomalocarisModel.LAYER_LOCATION)), 1.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MobEntityAnomalocaris pEntity) {
        return TEXTURE;
    }
}
