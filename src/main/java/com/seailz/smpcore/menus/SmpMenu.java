package com.seailz.smpcore.menus;

import com.seailz.smpcore.SmpCorePlugin;
import games.negative.framework.gui.GUI;
import games.negative.framework.inputlistener.InputListener;
import games.negative.framework.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class SmpMenu extends GUI {
    public SmpMenu() {
        super("SMP Menu", 6);

        for (World world : Bukkit.getWorlds()) {
            ItemStack worldItem = new ItemBuilder(Material.PAPER).setName(world.getName()).setLore("&bClick to enter").build();
            addItemClickEvent(player -> worldItem, (player, event) -> {
                FileConfiguration config = SmpCorePlugin.getInstance().getConfig();
                if (config.getString(world.getName() + "." + player.getUniqueId()) != null) {
                    player.teleport(config.getLocation(world.getName() + "." + player.getUniqueId()));
                } else {
                    player.teleport(world.getSpawnLocation());
                }
            });
        }

        addItemClickEvent(player -> {
            if (player.hasPermission("smpcore.admin")) {
                return new ItemBuilder(Material.DIAMOND_PICKAXE).setName("&b&lCreate world").setLore("&fClick to enter name").build();
            }
            return new ItemBuilder(Material.AIR).build();
        }, (player, event) -> {
            if (player.hasPermission("smpcore.admin")) {
                player.sendTitle("Please enter the name", "");
                InputListener.listen(player.getUniqueId(), e -> {
                    WorldCreator wc = new WorldCreator(e.getMessage());

                    wc.environment(World.Environment.NORMAL);
                    wc.type(WorldType.NORMAL);

                    wc.createWorld();
                });
            }
        });
    }
}
