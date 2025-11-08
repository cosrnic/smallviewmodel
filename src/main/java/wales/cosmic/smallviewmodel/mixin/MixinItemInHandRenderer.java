package wales.cosmic.smallviewmodel.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wales.cosmic.smallviewmodel.config.SmallViewModelConfig;

@Mixin(ItemInHandRenderer.class)
public abstract class MixinItemInHandRenderer {

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE",  target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V"))
    private void renderArmWithItem(
            AbstractClientPlayer player,
           float partialTick,
           float pitch,
           InteractionHand hand,
           float swingProgress,
           ItemStack item,
           float equippedProgress,
           PoseStack poseStack,
           SubmitNodeCollector nodeCollector,
           int packedLight,
           CallbackInfo ci
    ) {
        var config = SmallViewModelConfig.get();

        if (hand == InteractionHand.MAIN_HAND) {
            applyConfig(poseStack, config.mainHand.rotation, config.mainHand.position, config.mainHand.scale);
        } else {
            applyConfig(poseStack, config.offHand.rotation, config.offHand.position, config.offHand.scale);
        }
    }

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderPlayerArm(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IFFLnet/minecraft/world/entity/HumanoidArm;)V"))
    private void onRenderArm(
            AbstractClientPlayer player,
            float partialTick,
            float pitch,
            InteractionHand hand,
            float swingProgress,
            ItemStack item,
            float equippedProgress,
            PoseStack poseStack,
            SubmitNodeCollector nodeCollector,
            int packedLight,
            CallbackInfo ci
    ) {
        var config = SmallViewModelConfig.get();
        applyConfig(poseStack, config.arm.rotation, config.arm.position, config.arm.scale);
    }

    @Unique
    private void applyConfig(PoseStack poseStack, SmallViewModelConfig.Rotation rotation, SmallViewModelConfig.Position position, SmallViewModelConfig.Scale scale) {
        if (rotation.enabled) {
            poseStack.mulPose(Axis.XP.rotationDegrees(rotation.x));
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation.y));
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotation.z));
        }
        if (scale.enabled) {
            poseStack.scale(scale.x, scale.y, scale.z);
        }
        if (position.enabled) {
            poseStack.translate(position.x, position.y, position.z);
        }
    }

}