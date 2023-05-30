package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class TeaFood extends Item implements ItemRegistry.IUniqueModel {

	public static final String PARENT_MODEL = "tea";
	public static final String ITEMNAME_LAVENDER = "tealavender";
	public static final String ITEMNAME_SILKBERRY = "teasilkberry";
	public static final String ITEMNAME_SEEDY = "teaseedy";
	public static final String ITEMNAME_PETUNIA = "teapetunia";

	public enum Teas {
		LAVENDER(ITEMNAME_LAVENDER, new PotionEffect(MobEffects.RESISTANCE, (int) (300 * AurorianConfig.Config_Tea_EffectDuration_Muliplier)), new Color(118, 70, 255)),
		SILKBERRY(ITEMNAME_SILKBERRY, new PotionEffect(MobEffects.REGENERATION, (int) (100 * AurorianConfig.Config_Tea_EffectDuration_Muliplier)), new Color(14, 35, 75)),
		SEEDY(ITEMNAME_SEEDY, new PotionEffect(MobEffects.SPEED, (int) (200 * AurorianConfig.Config_Tea_EffectDuration_Muliplier)), new Color(174, 188, 215)),
		PETUNIA(ITEMNAME_PETUNIA, new PotionEffect(MobEffects.STRENGTH, (int) (300 * AurorianConfig.Config_Tea_EffectDuration_Muliplier)), new Color(255, 186, 255));

		private final String ITEMNAME;
		private final PotionEffect EFFECT;
		private final Color OVERLAYCOLOR;

		Teas(String itemname, PotionEffect effect, Color color) {
			this.ITEMNAME = itemname;
			this.EFFECT = effect;
			this.OVERLAYCOLOR = color;
		}

		public String getName() {
			return this.ITEMNAME;
		}

		public PotionEffect getEffect() {
			return this.EFFECT;
		}

		public Color getColor() {
			return this.OVERLAYCOLOR;
		}
	}

	private final Teas itemTea;

	public TeaFood(Teas tea) {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(tea.getName());
		this.setUnlocalizedName(AurorianMod.MODID + "." + tea.getName());
		this.setMaxStackSize(8);
		this.itemTea = tea;
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemColorHandler(ColorHandlerEvent.Item event) {
		event.getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				if (tintIndex != 0) {
					if (stack.getItem() instanceof TeaFood) {
						return ((TeaFood) stack.getItem()).getTeaType().getColor().getRGB();
					}
				}
				return 16777215;
			}
		}, ItemRegistry.Registry.TEALAVENDER.getItem(), ItemRegistry.Registry.TEAPETUNIA.getItem(), ItemRegistry.Registry.TEASEEDY.getItem(), ItemRegistry.Registry.TEASILKBERRY.getItem());
	}

	public Teas getTeaType() {
		return this.itemTea;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(AurorianMod.MODID + ":" + PARENT_MODEL, "inventory"));
	}

	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote && this.itemTea.getEffect() != null) {
			player.addPotionEffect(new PotionEffect(this.itemTea.getEffect()));
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));

			if (entityplayer instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
			}

			if (!entityplayer.capabilities.isCreativeMode) {
				stack.shrink(1);
				if (stack.isEmpty()) {
					return new ItemStack(ItemRegistry.Registry.CUP.getItem());
				}
				entityplayer.inventory.addItemStackToInventory(new ItemStack(ItemRegistry.Registry.CUP.getItem()));
			}
		}

		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 16;
	}

}
