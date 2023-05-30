package com.shiroroku.theaurorian.Entities.Passive;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AurorianSheepEntityModel2 extends ModelQuadruped {
	private float headRotationAngleX;

	public AurorianSheepEntityModel2() {
		super(12, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
		this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
		this.body = new ModelRenderer(this, 28, 8);
		this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
		this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
	}

	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
		super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
		this.head.rotationPointY = 6.0F + ((AurorianSheepEntity) entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
		this.headRotationAngleX = ((AurorianSheepEntity) entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		this.head.rotateAngleX = this.headRotationAngleX;
	}
}
