package shiroroku.theaurorian.Items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import shiroroku.theaurorian.TheAurorian;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseAurorianCurio extends BaseAurorianItem implements ICurioItem {

    private List<SimpleAttibuteModifier> modifiers = new ArrayList<>();

    public BaseAurorianCurio(Properties pProperties, Attribute attribute, AttributeModifier.Operation operation, double amt) {
        super(pProperties.stacksTo(1));
        modifiers.add(new SimpleAttibuteModifier(attribute, operation, amt));
    }

    public BaseAurorianCurio(Properties pProperties, List<SimpleAttibuteModifier> modifiers) {
        super(pProperties.stacksTo(1));
        this.modifiers = modifiers;
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> map = HashMultimap.create();
        modifiers.forEach(mod -> map.put(Holder.direct(mod.attribute), mod.modifier));
        return map;
    }

    public static class SimpleAttibuteModifier {
        private final Attribute attribute;
        private final AttributeModifier modifier;

        public SimpleAttibuteModifier(Attribute attribute, AttributeModifier.Operation operation, double amt) {
            this.attribute = attribute;
            modifier = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_curios"), amt, operation);
        }
    }
}
