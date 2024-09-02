package com.github.vaporizor.vaporsqol.mixin;

import com.github.vaporizor.vaporsqol.VaporsQOLConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.sounds.SoundSource;

@Mixin(SoundEngine.class)
class SoundEngineMixin {
    @Inject(method = "getVolume", at = @At("HEAD"), cancellable = true)
    private void getModifiedVolume(SoundSource soundSource, CallbackInfoReturnable<Float> cir) {
        if (!Minecraft.getInstance().isWindowActive())
            cir.setReturnValue(VaporsQOLConfig.get().idleConfig().audio().limit());
    }
}
