package shiroroku.theaurorian.Blocks.BossSpawner;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.ModUtil;

public class BossSpawnerBlockEntity extends BlockEntity {

    public EntityType<?> bossEntity = null;
    public static final int spawnDistance = 16;

    public BossSpawnerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.boss_spawner.get(), pPos, pBlockState);
    }

    private boolean isNearPlayer() {
        return ModUtil.hasNearbyPlayerAbove(level, (double) worldPosition.getX() + 0.5D, worldPosition.getY(), (double) worldPosition.getZ() + 0.5D, spawnDistance, (player) -> EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(player) && EntitySelector.LIVING_ENTITY_STILL_ALIVE.test(player));
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if (level.isClientSide) {
            return;
        }

        if (t instanceof BossSpawnerBlockEntity spawner) {
            if (spawner.bossEntity != null && spawner.isNearPlayer()) {
                spawner.spawnBoss();
            }
        }
    }

    public void setBoss(EntityType<?> pType) {
        bossEntity = pType;
    }

    public void spawnBoss() {
        if (bossEntity == null || this.level.isClientSide) {
            return;
        }

        // Boss scaling
        int nearbyPlayers = level.getEntitiesOfClass(Player.class, new AABB(worldPosition, worldPosition.offset(1, 1, 1)).inflate(spawnDistance * 2)).size();
        TheAurorian.LOGGER.debug(nearbyPlayers);
        LivingEntity boss = (LivingEntity) bossEntity.spawn((ServerLevel) this.level, null, null, null, worldPosition.above(), MobSpawnType.STRUCTURE, false, false);
        if (nearbyPlayers > 1) {
            boss.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(boss.getAttribute(Attributes.MOVEMENT_SPEED).getValue() * ((nearbyPlayers * Configuration.boss_speed_per_player.get()) + 1));
            boss.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(boss.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * ((nearbyPlayers * Configuration.boss_damage_per_player.get()) + 1));
            boss.getAttribute(Attributes.MAX_HEALTH).setBaseValue(boss.getAttribute(Attributes.MAX_HEALTH).getValue() * ((nearbyPlayers * Configuration.boss_health_per_player.get()) + 1));
        }
        this.level.destroyBlock(this.worldPosition, false);
    }

    @Override
    public void load(CompoundTag pTag) {
        if (pTag.contains("boss")) {
            this.bossEntity = ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.tryParse(pTag.getString("boss")));
        }
        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        if (bossEntity != null) {
            pTag.putString("boss", ForgeRegistries.ENTITY_TYPES.getKey(bossEntity).toString());
        }
        super.saveAdditional(pTag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        this.saveAdditional(nbt);
        return nbt;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
