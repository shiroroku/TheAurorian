package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Food_Tea extends Item {

	public static final String ITEMNAME_LAVENDER = "tealavender";
	public static final String ITEMNAME_SILKBERRY = "teasilkberry";
	public static final String ITEMNAME_SEEDY = "teaseedy";
	public static final String ITEMNAME_PETUNIA = "teapetunia";

	public enum Teas {
		LAVENDER(ITEMNAME_LAVENDER, new PotionEffect(MobEffects.RESISTANCE, 300)),
		SILKBERRY(ITEMNAME_SILKBERRY, new PotionEffect(MobEffects.REGENERATION, 100)),
		SEEDY(ITEMNAME_SEEDY, new PotionEffect(MobEffects.SPEED, 200)),
		PETUNIA(ITEMNAME_PETUNIA, new PotionEffect(MobEffects.STRENGTH, 300));
		
		private String ITEMNAME;
		private PotionEffect EFFECT;
		
		Teas(String itemname, PotionEffect effect){
			this.ITEMNAME = itemname;
			this.EFFECT = effect;
		}
		
		public String getName() {
			return ITEMNAME;
		}
		
		public PotionEffect getEffect() {
			return EFFECT;
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
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
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
			stack.shrink(1);

			if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
				if (stack.isEmpty()) {
					return new ItemStack(TAItems.cup);
				}

				if (entityplayer != null) {
					entityplayer.inventory.addItemStackToInventory(new ItemStack(TAItems.cup));
				}
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
		return 32;
	}

}
