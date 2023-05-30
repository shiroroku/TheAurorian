package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import javax.annotation.Nullable;
import java.util.List;

public class UmbraPickaxe extends ItemPickaxe {
	public static final String ITEMNAME = "umbrapickaxe";

	public UmbraPickaxe() {
		super(ItemRegistry.Materials.UMBRA);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (player.isSneaking()) {
			player.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1.75f);
			setSelectedBlock(stack, null);
			return EnumActionResult.SUCCESS;
		}
		if (setSelectedBlock(stack, worldIn.getBlockState(pos).getBlock())) {
			if (worldIn.isRemote) {
				player.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 2f);
			}
			stack.damageItem(50, player);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	/**
	 * Draws the pulsating outline around a block if it's the selected one.
	 */
	@SuppressWarnings("static-access")
	@SideOnly(Side.CLIENT)
	public static void renderBlockOutline(DrawBlockHighlightEvent e) {
		EntityPlayer player = e.getPlayer();
		if (player.getHeldItemMainhand().getItem() instanceof UmbraPickaxe) {
			ItemStack stack = player.getHeldItemMainhand();
			UmbraPickaxe pick = (UmbraPickaxe) stack.getItem();
			Block b = pick.getSelectedBlock(stack);
			if (b == null) {
				return;
			}
			RayTraceResult movingObjectPositionIn = e.getTarget();
			float partialTicks = e.getPartialTicks();
			if (movingObjectPositionIn.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos blockpos = movingObjectPositionIn.getBlockPos();
				IBlockState iblockstate = e.getPlayer().world.getBlockState(blockpos);
				if (iblockstate.getBlock() == b) {
					GlStateManager.enableBlend();
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					GlStateManager.glLineWidth(3.0F);
					GlStateManager.disableTexture2D();
					GlStateManager.depthMask(false);
					if (iblockstate.getMaterial() != Material.AIR && e.getPlayer().world.getWorldBorder().contains(blockpos)) {
						double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) partialTicks;
						double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) partialTicks;
						double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) partialTicks;
						float scale = MathHelper.sin((float) (Math.toRadians(player.world.getWorldTime() % 360) * 3f));//Pulsate alpha, dunno if im doing this right but it works.
						scale = Math.abs(scale);
						e.getContext().drawSelectionBoundingBox(iblockstate.getSelectedBoundingBox(e.getPlayer().world, blockpos).grow(0.005D).offset(-d3, -d4, -d5), 0.0F, 0.4F, 1.0F, scale);
					}
					GlStateManager.depthMask(true);
					GlStateManager.enableTexture2D();
					GlStateManager.disableBlend();
				}
			}
		}
	}

	/**
	 * Renders the mini block of the selected block behind pickaxe when in
	 * inventory
	 */
	@SideOnly(Side.CLIENT)
	public static void renderSelectedBlock(DrawScreenEvent.Pre e) {
		if (e.getGui() instanceof GuiContainer) {
			GuiContainer container = (GuiContainer) e.getGui();
			for (Slot s : container.inventorySlots.inventorySlots) {
				if (!s.getStack().isEmpty()) {
					ItemStack stack = s.getStack();
					if (stack.getItem() instanceof UmbraPickaxe) {
						RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
						UmbraPickaxe pick = (UmbraPickaxe) stack.getItem();
						Block b = pick.getSelectedBlock(stack);
						if (b != null) {
							int slotposx = container.getGuiLeft() + s.xPos;
							int slotposy = container.getGuiTop() + s.yPos;
							GL11.glDisable(GL12.GL_RESCALE_NORMAL);
							RenderHelper.disableStandardItemLighting();
							GL11.glDisable(GL11.GL_LIGHTING);
							RenderHelper.enableGUIStandardItemLighting();
							GL11.glEnable(GL12.GL_RESCALE_NORMAL);
							GlStateManager.pushMatrix();
							GlStateManager.scale(0.5, 0.5, 1);
							GlStateManager.translate(slotposx, slotposy, 0);
							itemRender.renderItemIntoGUI(new ItemStack(Item.getItemFromBlock(b)), slotposx, slotposy);
							GlStateManager.popMatrix();
						}
					}
				}
			}
		}
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (getSelectedBlock(stack) != null) {
			if (state.getBlock() == getSelectedBlock(stack)) {
				return super.getDestroySpeed(stack, state) * AurorianConfig.Config_UmbraPickaxeMiningSpeedMultiplier;
			}
		}
		return super.getDestroySpeed(stack, state);
	}

	private NBTTagCompound getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		return nbt;
	}

	private Block getSelectedBlock(ItemStack stack) {
		String blockname = getNBT(stack).getString("selectedblock");
		if (blockname.isEmpty()) {
			return null;
		} else {
			return Block.getBlockFromName(blockname);
		}
	}

	private boolean setSelectedBlock(ItemStack stack, Block block) {
		NBTTagCompound nbt = getNBT(stack);
		if (block == null) {
			nbt.setString("selectedblock", "");
			return true;
		}
		if (block != getSelectedBlock(stack)) {
			nbt.setString("selectedblock", block.getRegistryName().toString());
			return true;
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.umbrapickaxe"));

		}
		if (getSelectedBlock(stack) != null) {
			tooltip.add(TextFormatting.AQUA + "[" + getSelectedBlock(stack).getLocalizedName() + "]");
		}
	}
}
