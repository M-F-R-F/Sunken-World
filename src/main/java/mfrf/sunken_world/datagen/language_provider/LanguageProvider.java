package mfrf.sunken_world.datagen.language_provider;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.data.DataGenerator;

public abstract class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(DataGenerator gen, String locale) {
        super(gen, SunkenWorld.MODID, locale);
    }
}
