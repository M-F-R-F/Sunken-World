// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class custom_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart head;
	private final ModelPart tail;

	public custom_model(ModelPart root) {
		this.head = root.getChild("head");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 13.0F));

		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(19, 38).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 22).addBox(-2.0F, -1.0F, -13.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(21, 19).addBox(-3.0F, -1.0F, -11.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 17).addBox(-4.0F, -1.0F, -9.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 9).addBox(-5.0F, -1.0F, -8.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-6.0F, -1.0F, -6.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(29, 9).addBox(-5.0F, -2.0F, -3.0F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(11, 19).addBox(-0.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -2.5F, -9.0F, 0.3906F, 0.0338F, -0.3883F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -1.0F, 0.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -15.0F, 0.0F, 0.9599F, 0.0F));

		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(36, 31).addBox(-7.0F, -1.0F, 5.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-8.0F, -1.0F, 2.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-8.0F, -1.0F, 0.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.866F, -7.5718F, 0.409F, 0.2527F, 0.4636F));

		PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 3).addBox(-9.0F, 0.0F, 7.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 23).addBox(-8.0F, 0.0F, 6.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 33).addBox(-7.0F, 0.0F, 5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 36).addBox(-6.0F, 0.0F, 4.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(17, 4).addBox(-5.0F, 0.0F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 6).addBox(-4.0F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-3.0F, 0.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 22).addBox(-2.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.366F, 0.549F, -14.317F, -0.4636F, 0.2527F, 0.4636F));

		PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(5, 26).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(37, 25).addBox(-3.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 37).addBox(-5.0F, -1.0F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 33).addBox(-6.0F, -1.0F, 4.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 35).addBox(-7.0F, -1.0F, 5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-8.0F, -1.0F, 6.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 5).addBox(-9.0F, -1.0F, 7.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.366F, -0.549F, -14.317F, 0.4636F, 0.2527F, -0.4636F));

		PartDefinition cube_r6 = bone.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(30, 0).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 27).addBox(-8.0F, 0.0F, 2.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(32, 36).addBox(-7.0F, 0.0F, 5.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.866F, -7.5718F, -0.409F, 0.2527F, -0.4636F));

		PartDefinition cube_r7 = bone.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 13).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.866F, -7.5718F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r8 = bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(20, 9).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.605F, -5.5889F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r9 = bone.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(21, 19).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.605F, -5.5889F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r10 = bone.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(21, 23).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.866F, -7.5718F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r11 = bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(11, 19).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r12 = bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition bone3 = head.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(19, 38).mirror().addBox(0.0F, -1.0F, -15.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(11, 22).mirror().addBox(1.0F, -1.0F, -13.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 19).mirror().addBox(2.0F, -1.0F, -11.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(30, 17).mirror().addBox(3.0F, -1.0F, -9.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(20, 9).mirror().addBox(4.0F, -1.0F, -8.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 18).mirror().addBox(5.0F, -1.0F, -6.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(29, 9).mirror().addBox(0.0F, -2.0F, -3.0F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cube_r13 = bone3.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(11, 19).mirror().addBox(-1.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -2.5F, -9.0F, 0.3906F, -0.0338F, 0.3883F));

		PartDefinition cube_r14 = bone3.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.0F, 0.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, -15.0F, 0.0F, -0.9599F, 0.0F));

		PartDefinition cube_r15 = bone3.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(36, 31).mirror().addBox(2.0F, -1.0F, 5.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 4).mirror().addBox(1.0F, -1.0F, 2.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 29).mirror().addBox(0.0F, -1.0F, 0.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 4.866F, -7.5718F, 0.409F, -0.2527F, -0.4636F));

		PartDefinition cube_r16 = bone3.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(30, 3).mirror().addBox(1.0F, 0.0F, 7.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(30, 23).mirror().addBox(1.0F, 0.0F, 6.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(15, 33).mirror().addBox(1.0F, 0.0F, 5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(20, 36).mirror().addBox(1.0F, 0.0F, 4.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(17, 4).mirror().addBox(1.0F, 0.0F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(20, 6).mirror().addBox(1.0F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 25).mirror().addBox(1.0F, 0.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(15, 22).mirror().addBox(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.366F, 0.549F, -14.317F, -0.4636F, -0.2527F, -0.4636F));

		PartDefinition cube_r17 = bone3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(5, 26).mirror().addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(37, 25).mirror().addBox(1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 23).mirror().addBox(1.0F, -1.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(10, 37).mirror().addBox(1.0F, -1.0F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(36, 33).mirror().addBox(1.0F, -1.0F, 4.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 35).mirror().addBox(1.0F, -1.0F, 5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 32).mirror().addBox(1.0F, -1.0F, 6.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(30, 5).mirror().addBox(1.0F, -1.0F, 7.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.366F, -0.549F, -14.317F, 0.4636F, -0.2527F, 0.4636F));

		PartDefinition cube_r18 = bone3.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(30, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 27).mirror().addBox(1.0F, 0.0F, 2.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 36).mirror().addBox(2.0F, 0.0F, 5.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -4.866F, -7.5718F, -0.409F, -0.2527F, 0.4636F));

		PartDefinition cube_r19 = bone3.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(20, 13).mirror().addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.866F, -7.5718F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r20 = bone3.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(20, 9).mirror().addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.605F, -5.5889F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r21 = bone3.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(21, 19).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.605F, -5.5889F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r22 = bone3.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(21, 23).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.866F, -7.5718F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r23 = bone3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(11, 19).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r24 = bone3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 13.0F));

		PartDefinition bone4 = tail.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(30, 17).mirror().addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 34).mirror().addBox(0.0F, -2.0F, 3.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(5, 18).mirror().addBox(0.0F, -1.5F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 31).mirror().addBox(0.0F, -2.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)).mirror(false)
		.texOffs(16, 28).mirror().addBox(0.0F, 1.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cube_r25 = bone4.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(9, 9).mirror().addBox(0.0F, 1.0F, -2.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r26 = bone4.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(0.0F, -3.0F, -2.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r27 = bone4.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -5.5F, -3.5F, 0.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 7.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition bone2 = tail.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(30, 17).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-2.0F, -2.0F, 3.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(5, 18).addBox(-1.0F, -1.5F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 31).addBox(-3.0F, -2.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F))
		.texOffs(16, 28).addBox(-3.0F, 1.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cube_r28 = bone2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(9, 9).addBox(-1.0F, 1.0F, -2.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r29 = bone2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -3.0F, -2.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r30 = bone2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -5.5F, -3.5F, 0.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 7.5F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}