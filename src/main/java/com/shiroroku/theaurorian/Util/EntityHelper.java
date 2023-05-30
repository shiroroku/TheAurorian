package com.shiroroku.theaurorian.Util;

import com.shiroroku.theaurorian.AurorianUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityHelper {

	/**
	 * Returns true when the looker is looking at the target.
	 *
	 * @param accuracy How close does the looker have to look at the target
	 */
	public static boolean isLookingAt(EntityLivingBase looker, EntityLivingBase target, double accuracy) {
		Vec3d lookvec = target.getLook(1.0F).normalize();
		Vec3d vec = new Vec3d(looker.posX - target.posX, looker.getEntityBoundingBox().minY + looker.getEyeHeight() - (target.posY + target.getEyeHeight()), looker.posZ - target.posZ);
		double leng = vec.lengthVector();
		vec = vec.normalize();
		double mult = lookvec.dotProduct(vec);
		return mult > 1.0D - accuracy / leng && target.canEntityBeSeen(looker);
	}

	public static List<EntityLivingBase> getEntitiesAround(World worldIn, double x, double y, double z, double distance, boolean debugRender) {
		return EntityHelper.getEntitiesAround(worldIn, x, y, z, distance, distance, debugRender);
	}

	public static List<EntityLivingBase> getEntitiesAround(World worldIn, double x, double y, double z, double distance, double height, boolean debugRender) {
		AxisAlignedBB aabb = new AxisAlignedBB(x - distance, y - height, z - distance, x + distance, y + height, z + distance);
		if (debugRender) {
			AurorianUtil.renderAABBBounds(worldIn, aabb);
		}
		return worldIn.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
	}

}
