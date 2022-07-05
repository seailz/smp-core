package com.seailz.smpcore.commands;

import com.seailz.smpcore.menus.SmpMenu;
import games.negative.framework.command.Command;
import games.negative.framework.command.annotation.CommandInfo;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "smp",
        playerOnly = true
)
public class CommandSmp extends Command {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        new SmpMenu().open((Player) sender);
    }
}
