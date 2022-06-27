package mfrf.sunken_world.datagen.language_provider;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Items;
import mfrf.sunken_world.registry.ModTab;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;

public class ENUSProvider extends LanguageProvider {
    public ENUSProvider(DataGenerator gen) {
        super(gen, "en_us");
    }

    @Override
    protected void addTranslations() {
        ForgeRegistries.ITEMS.getValues().stream().filter(item -> item.getRegistryName().getNamespace().equals(SunkenWorld.MODID)).forEach(item -> {
            String path = item.getRegistryName().getPath();
            char[] chars = path.replace("_", " ").toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            add(item, new String(chars));
        });

        add("itemGroup." + SunkenWorld.MODID, "Sunken World");
    }
}
