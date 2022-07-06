package mfrf.sunken_world.Entities.mobs.piranha;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PiranhaRender extends EntityRenderer<MobEntityPiranha> {
    protected PiranhaRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(MobEntityPiranha pEntity) {
        return null;

        //TODO COLOR
    }
}
