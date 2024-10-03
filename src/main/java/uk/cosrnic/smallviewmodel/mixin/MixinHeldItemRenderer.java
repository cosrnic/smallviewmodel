package uk.cosrnic.smallviewmodel.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.cosrnic.smallviewmodel.config.SmallViewModelConfig;


@Mixin(HeldItemRenderer.class)
public abstract class MixinHeldItemRenderer {

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE",  target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo info) {
        if (hand == Hand.MAIN_HAND) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(SmallViewModelConfig.get().rotXMain));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(SmallViewModelConfig.get().rotYMain));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(SmallViewModelConfig.get().rotZMain));
            matrices.scale(SmallViewModelConfig.get().scaleXMain, SmallViewModelConfig.get().scaleYMain, SmallViewModelConfig.get().scaleZMain);
            matrices.translate(SmallViewModelConfig.get().posXMain, SmallViewModelConfig.get().posYMain, SmallViewModelConfig.get().posZMain);
        } else {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(SmallViewModelConfig.get().rotXOff));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(SmallViewModelConfig.get().rotYOff));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(SmallViewModelConfig.get().rotZOff));
            matrices.scale(SmallViewModelConfig.get().scaleXOff, SmallViewModelConfig.get().scaleYOff, SmallViewModelConfig.get().scaleZOff);
            matrices.translate(SmallViewModelConfig.get().posXOff, SmallViewModelConfig.get().posYOff, SmallViewModelConfig.get().posZOff);
        }
    }

//    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderArmHoldingItem(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IFFLnet/minecraft/util/Arm;)V"))
//    private void onRenderArm(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
//        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(SmallViewModelConfig.get().rotXArm));
//        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(SmallViewModelConfig.get().rotYArm));
//        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(SmallViewModelConfig.get().rotZArm));
//        matrices.scale(SmallViewModelConfig.get().scaleXArm, SmallViewModelConfig.get().scaleYArm, SmallViewModelConfig.get().scaleZArm);
//        matrices.translate(SmallViewModelConfig.get().posXArm, SmallViewModelConfig.get().posYArm, SmallViewModelConfig.get().posZArm);
//    }

    @ModifyExpressionValue(
            method = "updateHeldItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F"
            )
    )
    public float attackCooldown(
            float original
    ) {
        return 1f;
    }


}