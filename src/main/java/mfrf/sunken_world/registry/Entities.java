package mfrf.sunken_world.registry;

import mfrf.sunken_world.Entities.bubble.BubbleEntity;
import mfrf.sunken_world.SunkenWorld;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Entities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, SunkenWorld.MODID);

    public static final RegistryObject<EntityType<BubbleEntity>> BUBBLE = ENTITIES.register("oxygen_bubble", () -> EntityType.Builder.of(BubbleEntity::new, MobCategory.WATER_AMBIENT).sized(1.5f, 1.5f).clientTrackingRange(8).setShouldReceiveVelocityUpdates(true).build("oxygen_bubble"));
}
