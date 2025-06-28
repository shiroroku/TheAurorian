package shiroroku.theaurorian.Blocks.SilentwoodChest;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import shiroroku.theaurorian.TheAurorian;

public class SilentwoodChestBlockRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {

    public static final ModelLayerLocation MODEL_LAYER_NORMAL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "silentwood_chest"), "normal");
    public static final ModelLayerLocation MODEL_LAYER_DOUBLE_LEFT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "silentwood_chest"), "double_left");
    public static final ModelLayerLocation MODEL_LAYER_DOUBLE_RIGHT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "silentwood_chest"), "double_right");

    private static final ResourceLocation NORMAL = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "entity/silentwood_chest/normal");
    private static final ResourceLocation DOUBLE_LEFT = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "entity/silentwood_chest/double_left");
    private static final ResourceLocation DOUBLE_RIGHT = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "entity/silentwood_chest/double_right");

    private static final Material MATERIAL_NORMAL = new Material(Sheets.CHEST_SHEET, NORMAL);
    private static final Material MATERIAL_DOUBLE_LEFT = new Material(Sheets.CHEST_SHEET, DOUBLE_LEFT);
    private static final Material MATERIAL_DOUBLE_RIGHT = new Material(Sheets.CHEST_SHEET, DOUBLE_RIGHT);

    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart lock;
    private final ModelPart doubleLeftLid;
    private final ModelPart doubleLeftBottom;
    private final ModelPart doubleLeftLock;
    private final ModelPart doubleRightLid;
    private final ModelPart doubleRightBottom;
    private final ModelPart doubleRightLock;

    public SilentwoodChestBlockRenderer(BlockEntityRendererProvider.Context pContext) {
        ModelPart normal = pContext.bakeLayer(SilentwoodChestBlockRenderer.MODEL_LAYER_NORMAL);
        this.bottom = normal.getChild("bottom");
        this.lid = normal.getChild("lid");
        this.lock = normal.getChild("lock");
        ModelPart left = pContext.bakeLayer(SilentwoodChestBlockRenderer.MODEL_LAYER_DOUBLE_LEFT);
        this.doubleLeftBottom = left.getChild("bottom");
        this.doubleLeftLid = left.getChild("lid");
        this.doubleLeftLock = left.getChild("lock");
        ModelPart right = pContext.bakeLayer(SilentwoodChestBlockRenderer.MODEL_LAYER_DOUBLE_RIGHT);
        this.doubleRightBottom = right.getChild("bottom");
        this.doubleRightLid = right.getChild("lid");
        this.doubleRightLock = right.getChild("lock");
    }

    public static LayerDefinition createSingleBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        partdefinition.addOrReplaceChild("lock", CubeListBuilder.create().texOffs(0, 0).addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 8.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createDoubleBodyRightLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        partdefinition.addOrReplaceChild("lock", CubeListBuilder.create().texOffs(0, 0).addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 8.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createDoubleBodyLeftLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        partdefinition.addOrReplaceChild("lock", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 8.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(T pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Level level = pBlockEntity.getLevel();
        boolean hasLevel = level != null;
        BlockState blockstate = hasLevel ? pBlockEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        if (blockstate.getBlock() instanceof AbstractChestBlock<?> chest) {
            pPoseStack.pushPose();
            float facing = blockstate.getValue(ChestBlock.FACING).toYRot();
            pPoseStack.translate(0.5D, 0.5D, 0.5D);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-facing));
            pPoseStack.translate(-0.5D, -0.5D, -0.5D);
            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combineResult;
            if (hasLevel) {
                combineResult = chest.combine(blockstate, level, pBlockEntity.getBlockPos(), true);
            } else {
                combineResult = DoubleBlockCombiner.Combiner::acceptNone;
            }
            float lidAngle = combineResult.apply(ChestBlock.opennessCombiner(pBlockEntity)).get(pPartialTick);
            lidAngle = 1.0F - lidAngle;
            lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
            int packedLight = combineResult.apply(new BrightnessCombiner<>()).applyAsInt(pPackedLight);
            boolean isDouble = chestType != ChestType.SINGLE;
            Material material = getMaterial(pBlockEntity, chestType);
            VertexConsumer vertexConsumer = material.buffer(pBufferSource, RenderType::entityCutout);
            if (isDouble) {
                if (chestType == ChestType.LEFT) {
                    this.render(pPoseStack, vertexConsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, lidAngle, packedLight, pPackedOverlay);
                } else {
                    this.render(pPoseStack, vertexConsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, lidAngle, packedLight, pPackedOverlay);
                }
            } else {
                this.render(pPoseStack, vertexConsumer, this.lid, this.lock, this.bottom, lidAngle, packedLight, pPackedOverlay);
            }
            pPoseStack.popPose();
        }
    }

    private void render(PoseStack pPoseStack, VertexConsumer pConsumer, ModelPart pLidPart, ModelPart pLockPart, ModelPart pBottomPart, float pLidAngle, int pPackedLight, int pPackedOverlay) {
        pLidPart.xRot = -(pLidAngle * ((float) Math.PI / 2F));
        pLockPart.xRot = pLidPart.xRot;
        pLidPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pLockPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pBottomPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
    }

    protected Material getMaterial(T blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> SilentwoodChestBlockRenderer.MATERIAL_DOUBLE_LEFT;
            case RIGHT -> SilentwoodChestBlockRenderer.MATERIAL_DOUBLE_RIGHT;
            default -> SilentwoodChestBlockRenderer.MATERIAL_NORMAL;
        };
    }

    @Override
    public net.minecraft.world.phys.AABB getRenderBoundingBox(T blockEntity) {
        net.minecraft.core.BlockPos pos = blockEntity.getBlockPos();
        return net.minecraft.world.phys.AABB.encapsulatingFullBlocks(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
    }
}
