package dev.tylerm.khs.command;

import static dev.tylerm.khs.configuration.Config.errorPrefix;
import static dev.tylerm.khs.configuration.Localization.message;

import dev.tylerm.khs.Main;
import dev.tylerm.khs.command.util.ICommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Join implements ICommand {
    public void execute(Player sender, String[] args) {
        if (Main.getInstance().getGame().checkCurrentMap()) {
            sender.sendMessage(errorPrefix + message("GAME_SETUP"));
            return;
        }
        Player player = Bukkit.getServer().getPlayer(sender.getName());
        if (player == null) {
            sender.sendMessage(errorPrefix + message("COMMAND_ERROR"));
            return;
        }
        if (Main.getInstance().getBoard().contains(player)) {
            sender.sendMessage(errorPrefix + message("GAME_INGAME"));
            return;
        }
        Main.getInstance().getGame().join(player);
    }

    public String getLabel() {
        return "join";
    }

    public String getUsage() {
        return "<*map>";
    }

    public String getDescription() {
        return "Joins the lobby if game is set to manual join/leave";
    }

    public List<String> autoComplete(@NotNull String parameter, @NotNull String typed) {
        return null;
    }
}
