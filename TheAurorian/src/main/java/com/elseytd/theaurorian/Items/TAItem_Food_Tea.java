package com.elseytd.theaurorian.Items;

import java.awt.Color;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

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

public class TAItem_Food_Tea extends Item implements TAItems.ISpecialModel{

	public static final String PARENT_MODEL = "tea";
	public static final String ITEMNAME_LAVENDER = "tealavender";
	public static final String ITEMNAME_SILKBERRY = "teasilkberry";
	public static final String ITEMNAME_SEEDY = "teaseedy";
	public static final String ITEMNAME_PETUNIA = "teapetunia";

	public enum Teas {
		LAVENDER(ITEMNAME_LAVENDER, new PotionEffect(MobEffects.RESISTANCE, 300), new Color(118, 70, 255)),
		SILKBERRY(ITEMNAME_SILKBERRY, new PotionEffect(MobEffects.REGENERATION, 100), new Color(14, 35, 75)),
		SEEDY(ITEMNAME_SEEDY, new PotionEffect(MobEffects.SPEED, 200), new Color(174, 188, 215)),
		PETUNIA(ITEMNAME_PETUNIA, new PotionEffect(MobEffects.STRENGTH, 300), new Color(255, 186, 255));

		private String ITEMNAME;
		private PotionEffect EFFECT;
		private Color OVERLAYCOLOR;

		Teas(String itemname, PotionEffect effect, Color color) {
			this.ITEMNAME = itemname;
			this.EFFECT = effect;
			this.OVERLAYCOLOR = color;
		}

		public String getName() {
			return ITEMNAME;
		}

		public PotionEffect getEffect() {
			return EFFECT;
		}

		public Color getColor() {
			return OVERLAYCOLOR;
		}
	}

	private Teas itemTea;

	public TAItem_Food_Tea(Teas tea) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(tea.getName());
		this.setUnlocalizedName(TAMod.MODID + "." + tea.getName());
		this.setMaxStackSize(1);
		this.itemTea = tea;
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemColorHandler(ColorHandlerEvent.Item event) {
		event.getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				if (tintIndex != 0) {
					if (stack.getItem() instanceof TAItem_Food_Tea) {
						return ((TAItem_Food_Tea) stack.getItem()).getTeaType().getColor().getRGB();
					}
				}
				return 16777215;
			}
		}, TAItems.tealavender, TAItems.teapetunia, TAItems.teaseedy, TAItems.teasilkberry);
	}

	public Teas getTeaType() {
		return itemTea;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(TAMod.MODID + ":" + PARENT_MODEL, "inventory"));
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
					return new ItemStack(TAItems.cup);
				}
				entityplayer.inventory.addItemStackToInventory(new ItemStack(TAItems.cup));
			}
		}

		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 16;
	}

}
