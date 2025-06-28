package shiroroku.theaurorian.Blocks.BossSpawner;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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
import shiroroku.theaurorian.Config.CommonConfig;
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
        int nearbyPlayers = level.getEntitiesOfClass(Player.class, AABB.encapsulatingFullBlocks(worldPosition, worldPosition.offset(1, 1, 1)).inflate(spawnDistance * 2)).size();
        TheAurorian.LOGGER.debug(nearbyPlayers);
        LivingEntity boss = (LivingEntity) bossEntity.spawn((ServerLevel) this.level, worldPosition.above(), MobSpawnType.STRUCTURE);
        if (nearbyPlayers > 1) {
            boss.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(boss.getAttribute(Attributes.MOVEMENT_SPEED).getValue() * ((nearbyPlayers * CommonConfig.boss_speed_per_player.get()) + 1));
            boss.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(boss.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * ((nearbyPlayers * CommonConfig.boss_damage_per_player.get()) + 1));
            boss.getAttribute(Attributes.MAX_HEALTH).setBaseValue(boss.getAttribute(Attributes.MAX_HEALTH).getValue() * ((nearbyPlayers * CommonConfig.boss_health_per_player.get()) + 1));
        }
        this.level.destroyBlock(this.worldPosition, false);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (tag.contains("boss")) {
            this.bossEntity = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.tryParse(tag.getString("boss")));
        }
        super.loadAdditional(tag, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (bossEntity != null) {
            tag.putString("boss", BuiltInRegistries.ENTITY_TYPE.getKey(bossEntity).toString());
        }
        super.saveAdditional(tag, registries);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag nbt = super.getUpdateTag(registries);
        this.saveAdditional(nbt, registries);
        return nbt;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
