package survivalblock.tmm_nonether.mixin;

import dev.doctor4t.trainmurdermystery.client.gui.LobbyPlayersRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(value = LobbyPlayersRenderer.class, remap = false)
public class LobbyPlayersRendererMixin {

	@Inject(method = "renderHud", at = @At("HEAD"), cancellable = true, remap = true)
	private static void cancelIfNotOverworld(TextRenderer renderer, ClientPlayerEntity player, DrawContext context, CallbackInfo ci) {
        if (player.getWorld().getRegistryKey().equals(World.OVERWORLD)) {
            return;
        }
        ci.cancel();
	}
}