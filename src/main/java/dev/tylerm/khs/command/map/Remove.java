package dev.tylerm.khs.command.map;

import dev.tylerm.khs.Main;
import dev.tylerm.khs.command.util.ICommand;
import dev.tylerm.khs.configuration.Config;
import dev.tylerm.khs.configuration.Localization;
import dev.tylerm.khs.configuration.Map;
import dev.tylerm.khs.configuration.Maps;
import dev.tylerm.khs.game.util.Status;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Remove implements ICommand {
    public void execute(Player sender, String[] args) {
        if (Main.getInstance().getGame().getStatus() != Status.STANDBY) {
            sender.sendMessage(Config.errorPrefix + Localization.message("GAME_INPROGRESS"));
            return;
        }
        Map map = Maps.getMap(args[0]);
        if (map == null) {
            sender.sendMessage(Config.errorPrefix + Localization.message("INVALID_MAP"));
        } else if (!Maps.removeMap(args[0])) {
            sender.sendMessage(
                    Config.errorPrefix
                            + Localization.message("MAP_FAIL_DELETE").addAmount(args[0]));
        } else {
            sender.sendMessage(
                    Config.messagePrefix + Localization.message("MAP_DELETED").addAmount(args[0]));
        }
    }

    public String getLabel() {
        return "remove";
    }

    public String getUsage() {
        return "<map>";
    }

    public String getDescription() {
        return "Remove a map from the plugin!";
    }

    public List<String> autoComplete(@NotNull String parameter, @NotNull String typed) {
        if (parameter.equals("map")) {
            return Maps.getAllMaps().stream().map(Map::getName).collect(Collectors.toList());
        }
        return null;
    }
}
