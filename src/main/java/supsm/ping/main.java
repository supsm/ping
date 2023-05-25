package supsm.ping;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;


public class main implements ModInitializer
{
	@Override
	public void onInitialize()
	{
		// register command
		CommandRegistrationCallback.EVENT.register((CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registry_access, CommandManager.RegistrationEnvironment environment) ->
		{
			dispatcher.register(CommandManager.literal("ping").executes((CommandContext<ServerCommandSource> context) ->
			{
				ServerCommandSource source = context.getSource();
				ServerPlayerEntity player = source.getPlayer();
				if (player == null)
				{
					return 0;
				}
				source.sendMessage(Text.literal(player.pingMilliseconds + " ms"));
				return 1;
			}));
		});
	}
}
