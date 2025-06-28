package shiroroku.theaurorian.Items.MirrorOfGuidance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import shiroroku.theaurorian.TheAurorian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MirrorDataLoader extends SimpleJsonResourceReloadListener {

    public static final Map<ResourceLocation, MirrorNode> NODES = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public static boolean loaded = false;

    public MirrorDataLoader() {
        super(GSON, "mirror_of_guidance");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        loaded = false;
        NODES.clear();
        for (Map.Entry<ResourceLocation, JsonElement> file : pObject.entrySet()) {
            try {
                JsonObject json = file.getValue().getAsJsonObject();

                Item icon = BuiltInRegistries.ITEM.get(ResourceLocation.tryParse(json.get("icon").getAsString()));
                int x = json.get("x").getAsInt();
                int y = json.get("y").getAsInt();
                List<ResourceLocation> children = new ArrayList<>();
                if (json.has("children")) {
                    json.get("children").getAsJsonArray().forEach(entry -> children.add(ResourceLocation.tryParse(entry.getAsString())));
                }
                MirrorNode.NODE_BORDER border = MirrorNode.NODE_BORDER.REGULAR;
                if (json.has("border")) {
                    border = MirrorNode.NODE_BORDER.valueOf(json.get("border").getAsString());
                }

                String langKey = String.format("mirror_of_guidance.%s.%s", file.getKey().getNamespace(), file.getKey().getPath());
                Component name = Component.translatable(langKey + ".name");
                Component description = Component.translatable(langKey + ".desc");

                // Flip y so +y is goes up
                MirrorNode node = new MirrorNode(name, icon, x, -y, description, children, border);

                NODES.put(file.getKey(), node);
            } catch (Exception e) {
                TheAurorian.LOGGER.error("Failed to load Mirror Of Guidance [{}]: {}", file.getKey(), e.getMessage());
            }
        }
        loaded = true;
    }
}
