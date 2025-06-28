package shiroroku.theaurorian.Config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {

    public static final ModConfigSpec config;

    public static final ModConfigSpec.ConfigValue<Boolean> enable_auroras;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        enable_auroras = builder.define("enable_auroras", true);
        config = builder.build();
    }
}
