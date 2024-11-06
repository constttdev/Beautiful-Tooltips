package de.constt.beautiful_tooltips.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(Item.class)
public class ToolTipMixin {
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        tooltip.add(Text.literal(" "));
        if(stack.isDamageable()) {
            int currentDmg = stack.getMaxDamage() - stack.getDamage();
            tooltip.add(Text.literal("⚔ ᴅᴜʀᴀʙʟɪᴛʏ - "+currentDmg+"/"+stack.getMaxDamage()));
        }
        if(stack.getDamage() > 0) {
            tooltip.add(Text.literal("❤ ᴅᴀᴍᴀɢᴇ - "+stack.getDamage()));
        }
        if(stack.getComponents().contains(DataComponentTypes.FOOD)) {
            tooltip.add(Text.literal("🍖 ꜱᴀᴛᴜʀᴀᴛɪᴏɴ - "+ Math.round(Objects.requireNonNull(stack.getComponents().get(DataComponentTypes.FOOD)).saturation())));
        }
    }
}
