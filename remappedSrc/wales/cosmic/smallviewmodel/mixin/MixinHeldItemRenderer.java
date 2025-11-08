package wales.cosmic.smallviewmodel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wales.cosmic.smallviewmodel.config.SmallViewModelConfig;


@Mixin(ItemInHandRenderer.class)
public abstract class MixinHeldItemRenderer {

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE",  target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void renderFirstPersonItem(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand, float swingProgress, ItemStack item, float equipProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, CallbackInfo info) {
        var config = SmallViewModelConfig.get();

        if (hand == InteractionHand.MAIN_HAND) {
            applyConfig(matrices, config.mainHand.rotation, config.mainHand.position, config.mainHand.scale);
        } else {
            applyConfig(matrices, config.offHand.rotation, config.offHand.position, config.offHand.scale);
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderArmHoldingItem(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IFFLnet/minecraft/util/Arm;)V"))
    private void onRenderArm(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand, float swingProgress, ItemStack item, float equipProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, CallbackInfo ci) {
        var config = SmallViewModelConfig.get();
        applyConfig(matrices, config.arm.rotation, config.arm.position, config.arm.scale);
    }

    @Unique
    private void applyConfig(PoseStack matrices, SmallViewModelConfig.Rotation rotation, SmallViewModelConfig.Position position, SmallViewModelConfig.Scale scale) {
//        if (rotation.enabled) {
            matrices.mulPose(Axis.XP.rotationDegrees(rotation.x));
            matrices.mulPose(Axis.YP.rotationDegrees(rotation.y));
            matrices.mulPose(Axis.ZP.rotationDegrees(rotation.z));
//        }
//        if (scale.enabled) {
            matrices.scale(scale.x, scale.y, scale.z);
//        }
//        if (position.enabled) {
            matrices.translate(position.x, position.y, position.z);
//        }
    }

}