package com.github.kydzombie.stapitest.mixin;

import com.github.kydzombie.stapitest.events.init.StapiTest;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.item.StationItemStack;
import net.modificationstation.stationapi.api.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StationItemStack.class)
public interface TaggedItemMixin {
    @Inject(at = @At("HEAD"), method = "isIn(Lnet/modificationstation/stationapi/api/tag/TagKey;)Z", remap = false)
    private void isIn(TagKey<ItemBase> tag, CallbackInfoReturnable<Boolean> cir) {
        if (ItemInstance.class.cast(this).itemId == StapiTest.pickaxe.id) {
            cir.setReturnValue(true);
        }
    }
}
