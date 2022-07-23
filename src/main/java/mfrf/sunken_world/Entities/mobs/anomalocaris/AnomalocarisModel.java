package mfrf.sunken_world.Entities.mobs.anomalocaris;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mfrf.sunken_world.SunkenWorld;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class AnomalocarisModel extends EntityModel<MobEntityAnomalocaris> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(SunkenWorld.MODID, "anomalocaris"), "main");
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart body3;

	public AnomalocarisModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body1 = root.getChild("body1");
		this.body2 = root.getChild("body2");
		this.body3 = root.getChild("body3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(68, 48).addBox(-6.0F, -4.0F, -35.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(39, 66).addBox(-6.0F, -6.0F, -33.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 23).addBox(-6.0F, -7.0F, -31.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 66).addBox(-7.0F, -4.0F, -32.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-10.0F, -2.0F, -28.0F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.01F))
		.texOffs(31, 13).addBox(-3.0F, 2.0F, -32.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(23, 13).addBox(-3.0F, 2.0F, -27.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-3.0F, 2.0F, -31.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(68, 48).mirror().addBox(0.0F, -4.0F, -35.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(31, 13).mirror().addBox(0.0F, 2.0F, -32.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(23, 13).mirror().addBox(0.0F, 2.0F, -27.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 28).mirror().addBox(2.0F, 2.0F, -31.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(39, 66).mirror().addBox(0.0F, -6.0F, -33.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(28, 23).mirror().addBox(0.0F, -7.0F, -31.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 66).mirror().addBox(6.0F, -4.0F, -32.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(7.0F, -2.0F, -28.0F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.01F)).mirror(false)
		.texOffs(28, 23).mirror().addBox(0.0F, -7.0F, -31.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(39, 66).mirror().addBox(0.0F, -6.0F, -33.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 19.0F, 11.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(0.0F, -4.0F, -4.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(-0.5F)).mirror(false)
		.texOffs(0, 23).mirror().addBox(0.0F, -4.0F, -2.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(38, 15).mirror().addBox(1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -4.0F, -34.0F, -0.3927F, -0.7854F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(55, 67).mirror().addBox(-6.0F, -7.0F, 0.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.001F)).mirror(false)
		.texOffs(55, 67).addBox(-12.002F, -7.0F, 0.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(6.001F, -8.0967F, -30.9016F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 41).mirror().addBox(-6.0F, -3.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 41).addBox(-12.002F, -3.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.001F, -6.5967F, -33.4996F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(69, 7).mirror().addBox(-6.0F, -3.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)).mirror(false)
		.texOffs(69, 7).addBox(-12.0F, -3.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(6.0F, -4.0F, -35.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(47, 0).mirror().addBox(-0.75F, -0.75F, -2.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.75F)).mirror(false), PartPose.offsetAndRotation(5.0951F, 4.6237F, -42.5626F, 1.4399F, -0.1745F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, 39).mirror().addBox(-0.75F, -0.75F, -1.25F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.749F)).mirror(false), PartPose.offsetAndRotation(5.0726F, 4.1408F, -42.4351F, 1.309F, -0.1745F, 0.0F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(10, 66).mirror().addBox(-0.5F, -0.5F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(4.7923F, 3.1288F, -42.2855F, 1.1345F, -0.1745F, 0.0F));

		PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(70, 56).mirror().addBox(-0.5F, -0.5F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.499F)).mirror(false), PartPose.offsetAndRotation(4.6927F, 2.3096F, -41.7206F, 0.9599F, -0.1745F, 0.0F));

		PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(70, 61).mirror().addBox(-0.25F, -0.25F, -1.75F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offsetAndRotation(4.2931F, 1.0722F, -40.8936F, 0.7854F, -0.1745F, 0.0F));

		PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(71, 19).mirror().addBox(-0.25F, -0.25F, -1.75F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.249F)).mirror(false), PartPose.offsetAndRotation(4.0734F, 0.2663F, -39.6477F, 0.5672F, -0.1745F, 0.0F));

		PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(71, 67).mirror().addBox(0.0F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5157F, -0.6527F, -37.9245F, 0.3491F, -0.1745F, 0.0F));

		PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(72, 33).mirror().addBox(0.0F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(3.1736F, -1.0F, -35.9848F, 0.1745F, -0.1745F, 0.0F));

		PartDefinition cube_r13 = head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(18, 29).mirror().addBox(0.0F, 0.0F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -1.0F, -35.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r14 = head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(18, 29).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -35.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r15 = head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(72, 33).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-3.1736F, -1.0F, -35.9848F, 0.1745F, 0.1745F, 0.0F));

		PartDefinition cube_r16 = head.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(71, 67).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5157F, -0.6527F, -37.9245F, 0.3491F, 0.1745F, 0.0F));

		PartDefinition cube_r17 = head.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(71, 19).addBox(-2.75F, -0.25F, -1.75F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.249F)), PartPose.offsetAndRotation(-4.0734F, 0.2663F, -39.6477F, 0.5672F, 0.1745F, 0.0F));

		PartDefinition cube_r18 = head.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(70, 61).addBox(-2.75F, -0.25F, -1.75F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-4.2931F, 1.0722F, -40.8936F, 0.7854F, 0.1745F, 0.0F));

		PartDefinition cube_r19 = head.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(70, 56).addBox(-2.5F, -0.5F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.499F)), PartPose.offsetAndRotation(-4.6927F, 2.3096F, -41.7206F, 0.9599F, 0.1745F, 0.0F));

		PartDefinition cube_r20 = head.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(10, 66).addBox(-2.5F, -0.5F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-4.7923F, 3.1288F, -42.2855F, 1.1345F, 0.1745F, 0.0F));

		PartDefinition cube_r21 = head.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(38, 39).addBox(-2.25F, -0.75F, -1.25F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.749F)), PartPose.offsetAndRotation(-5.0726F, 4.1408F, -42.4351F, 1.309F, 0.1745F, 0.0F));

		PartDefinition cube_r22 = head.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(47, 0).addBox(-2.25F, -0.75F, -2.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(-5.0951F, 4.6237F, -42.5626F, 1.4399F, 0.1745F, 0.0F));

		PartDefinition cube_r23 = head.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 7).addBox(-4.0F, -4.0F, -4.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(-0.5F))
		.texOffs(0, 23).addBox(-4.0F, -4.0F, -2.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 15).addBox(-3.0F, -3.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -4.0F, -34.0F, -0.3927F, 0.7854F, 0.0F));

		PartDefinition body1 = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -24.0F, 4.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(23, 0).addBox(-9.0F, -6.5F, -18.0F, 9.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-8.0F, -7.0F, -24.0F, 4.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(0.0F, -7.0F, -24.0F, 4.0F, 8.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(23, 0).mirror().addBox(0.0F, -6.5F, -18.0F, 9.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 23).mirror().addBox(4.0F, -7.0F, -24.0F, 4.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 19.0F, 11.0F));

		PartDefinition wing_1_left_r1 = body1.addOrReplaceChild("wing_1_left_r1", CubeListBuilder.create().texOffs(54, 26).mirror().addBox(0.0F, 0.001F, 0.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(7.5F, -4.001F, -24.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition wing_2_left_r1 = body1.addOrReplaceChild("wing_2_left_r1", CubeListBuilder.create().texOffs(48, 33).mirror().addBox(0.0F, 0.001F, 0.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(8.5F, -4.001F, -18.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition wing_2_right_r1 = body1.addOrReplaceChild("wing_2_right_r1", CubeListBuilder.create().texOffs(48, 33).addBox(-9.0F, 0.001F, 0.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-8.5F, -4.001F, -18.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition wing_1_right_r1 = body1.addOrReplaceChild("wing_1_right_r1", CubeListBuilder.create().texOffs(54, 26).addBox(-7.0F, 0.001F, 0.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-7.5F, -4.001F, -24.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition body2 = partdefinition.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(47, 7).addBox(-8.0F, -6.0F, -23.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(40, 44).addBox(-7.0F, -5.5F, -18.0F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(36, 56).addBox(-6.0F, -5.0F, -11.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(47, 7).mirror().addBox(0.0F, -6.0F, -23.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 44).mirror().addBox(0.0F, -5.5F, -18.0F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(36, 56).mirror().addBox(0.0F, -5.0F, -11.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 19.0F, 22.0F));

		PartDefinition wing_3_left_r1 = body2.addOrReplaceChild("wing_3_left_r1", CubeListBuilder.create().texOffs(47, 19).mirror().addBox(0.0F, 0.001F, 0.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(7.5F, -4.001F, -23.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition wing_4_left_r1 = body2.addOrReplaceChild("wing_4_left_r1", CubeListBuilder.create().texOffs(14, 51).mirror().addBox(0.0F, 0.001F, 0.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(6.5F, -4.001F, -17.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition wing_5_left_r1 = body2.addOrReplaceChild("wing_5_left_r1", CubeListBuilder.create().texOffs(53, 0).mirror().addBox(0.0F, 0.001F, 0.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(5.5F, -4.001F, -11.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition wing_5_right_r1 = body2.addOrReplaceChild("wing_5_right_r1", CubeListBuilder.create().texOffs(53, 0).addBox(-7.0F, 0.001F, 0.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-5.5F, -4.001F, -11.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition wing_4_right_r1 = body2.addOrReplaceChild("wing_4_right_r1", CubeListBuilder.create().texOffs(14, 51).addBox(-8.0F, 0.001F, 0.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-6.5F, -4.001F, -17.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition wing_3_right_r1 = body2.addOrReplaceChild("wing_3_right_r1", CubeListBuilder.create().texOffs(47, 19).addBox(-9.0F, 0.001F, 0.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-7.5F, -4.001F, -23.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition body3 = partdefinition.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(19, 39).addBox(-5.0F, -4.5F, -24.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(61, 40).addBox(-3.0F, -4.0F, -15.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(19, 39).mirror().addBox(0.0F, -4.5F, -24.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(61, 40).mirror().addBox(0.0F, -4.0F, -15.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 19.0F, 40.0F));

		PartDefinition wing_6_left_r1 = body3.addOrReplaceChild("wing_6_left_r1", CubeListBuilder.create().texOffs(53, 59).mirror().addBox(0.0F, 0.001F, 0.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(4.5F, -4.001F, -23.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r24 = body3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(25, 60).mirror().addBox(0.5F, -15.0F, 3.0F, 1.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 41).mirror().addBox(0.0F, -18.0F, 0.0F, 1.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -4.5F, -25.0F, -0.6545F, 0.0F, 0.7854F));

		PartDefinition cube_r25 = body3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(18, 23).mirror().addBox(-3.0F, 0.0F, -5.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(5.0996F, -3.6298F, -4.9042F, 0.0F, -0.6109F, 0.1745F));

		PartDefinition cube_r26 = body3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(38, 44).mirror().addBox(0.0F, 0.001F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 58).mirror().addBox(0.0F, 0.001F, -7.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -4.001F, -9.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r27 = body3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(25, 60).addBox(-1.5F, -15.0F, 3.0F, 1.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 41).addBox(-1.0F, -18.0F, 0.0F, 1.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.5F, -25.0F, -0.6545F, 0.0F, -0.7854F));

		PartDefinition cube_r28 = body3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(18, 23).addBox(0.0F, 0.0F, -5.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-5.0996F, -3.6298F, -4.9042F, 0.0F, 0.6109F, -0.1745F));

		PartDefinition cube_r29 = body3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(38, 44).addBox(-2.0F, 0.001F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(7, 58).addBox(-5.0F, 0.001F, -7.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.001F, -9.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition wing_6_right_r1 = body3.addOrReplaceChild("wing_6_right_r1", CubeListBuilder.create().texOffs(53, 59).addBox(-5.0F, 0.001F, 0.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-4.5F, -4.001F, -23.0F, 0.0F, 0.0F, -0.1309F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(MobEntityAnomalocaris pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}
}