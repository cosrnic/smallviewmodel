package wales.cosmic.smallviewmodel.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wales.cosmic.smallviewmodel.config.SmallViewModelConfig;


@Mixin(HeldItemRenderer.class)
public abstract class MixinHeldItemRenderer {

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE",  target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo info) {
        var config = SmallViewModelConfig.get();

        if (hand == Hand.MAIN_HAND) {
            applyConfig(matrices, config.mainHand.rotation, config.mainHand.position, config.mainHand.scale);
        } else {
            applyConfig(matrices, config.offHand.rotation, config.offHand.position, config.offHand.scale);
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderArmHoldingItem(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IFFLnet/minecraft/util/Arm;)V"))
    private void onRenderArm(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        var config = SmallViewModelConfig.get();
        applyConfig(matrices, config.arm.rotation, config.arm.position, config.arm.scale);
    }

    @Unique
    private void applyConfig(MatrixStack matrices, SmallViewModelConfig.Rotation rotation, SmallViewModelConfig.Position position, SmallViewModelConfig.Scale scale) {
//        if (rotation.enabled) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotation.x));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation.y));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotation.z));
//        }
//        if (scale.enabled) {
            matrices.scale(scale.x, scale.y, scale.z);
//        }
//        if (position.enabled) {
            matrices.translate(position.x, position.y, position.z);
//        }
    }

}