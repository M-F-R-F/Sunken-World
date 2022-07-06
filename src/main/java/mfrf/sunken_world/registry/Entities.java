package mfrf.sunken_world.registry;

import mfrf.sunken_world.Entities.mobs.piranha.MobEntityPiranha;
import mfrf.sunken_world.Entities.technical.under_water_torch.UnderWaterTorch;
import mfrf.sunken_world.Entities.technical.water_block_projectile.WaterBlockProjectile;
import mfrf.sunken_world.Entities.technical.bubble.BubbleEntity;
import mfrf.sunken_world.SunkenWorld;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Entities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, SunkenWorld.MODID);

    //technical
    public static final RegistryObject<EntityType<BubbleEntity>> BUBBLE = ENTITIES.register("oxygen_bubble", () -> EntityType.Builder.of(BubbleEntity::new, MobCategory.MISC).sized(1.5f, 1.5f).clientTrackingRange(8).setShouldReceiveVelocityUpdates(true).updateInterval(10).build("oxygen_bubble"));
    public static final RegistryObject<EntityType<WaterBlockProjectile>> WATER_BLOCK_PROJECTILE = ENTITIES.register("water_projectile", () -> EntityType.Builder.<WaterBlockProjectile>of(WaterBlockProjectile::new, MobCategory.MISC).sized(1f, 1f).clientTrackingRange(8).setShouldReceiveVelocityUpdates(true).updateInterval(1).build("water_projectile"));
    public static final RegistryObject<EntityType<UnderWaterTorch>> UNDERWATER_TORCH = ENTITIES.register("underwater_torch", () -> EntityType.Builder.<UnderWaterTorch>of(UnderWaterTorch::new, MobCategory.MISC).sized(1f, 1f).clientTrackingRange(8).setShouldReceiveVelocityUpdates(true).updateInterval(1).build("underwater_torch"));

    //mobs
    public static final RegistryObject<EntityType<MobEntityPiranha>> MOB_ENTITY_PIRANHA = ENTITIES.register("piranha", () -> EntityType.Builder.of(MobEntityPiranha::new, MobCategory.MONSTER).sized(0.5f, 0.5f).clientTrackingRange(16).setShouldReceiveVelocityUpdates(true).updateInterval(1).build("piranha"));

}
