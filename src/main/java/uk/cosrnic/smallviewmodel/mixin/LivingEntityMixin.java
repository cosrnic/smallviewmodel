package uk.cosrnic.smallviewmodel.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static uk.cosrnic.smallviewmodel.config.SmallViewModelConfig.get;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    private int smallviewmodel$ticks;

    @Shadow
    public float handSwingProgress;

    @Inject(
            method = "tickMovement",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;tickNewAi()V",
                    shift = AFTER
            )
    )
    public void tickAnimation(
            CallbackInfo callbackInfo
    ) {

        if (get().swingAnimationDuration == 6)
            return;

        if (this.smallviewmodel$ticks > get().swingAnimationDuration)
            this.smallviewmodel$ticks = 0;

        if (this.smallviewmodel$ticks == 0)
            this.handSwingProgress = 1f;
        else {
            this.handSwingProgress = (this.smallviewmodel$ticks - 1f) / get().swingAnimationDuration;
            this.smallviewmodel$ticks++;
        }

    }

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;Z)V", at = @At("HEAD"))
    public void onSwing(
            Hand hand,
            boolean fromServerPlayer,
            CallbackInfo callbackInfo
    ) {
        if (this.smallviewmodel$ticks == 0)
            this.smallviewmodel$ticks = 1;
    }

}
