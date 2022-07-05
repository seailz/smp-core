package com.seailz.smpcore;

import com.seailz.smpcore.core.Locale;
import com.seailz.smpcore.core.Logger;
import games.negative.framework.BasePlugin;
import lombok.Getter;
import lombok.Setter;

public final class SmpCorePlugin extends BasePlugin {

    @Getter
    @Setter
    public static SmpCorePlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        long start = System.currentTimeMillis();

        setInstance(this);

        // Set details and register things
        register(RegisterType.COMMAND);
        register(RegisterType.LISTENER);

        Locale.init(this);
        saveDefaultConfig();

        long finish = System.currentTimeMillis() - start;
        Logger.log(Logger.LogLevel.SUCCESS, "Started in " + finish + "ms!");
    }

    public void register(RegisterType type) {
        switch (type) {
            case COMMAND:
                registerCommands(
                        // Insert commands
                );
                break;
            case LISTENER:
                registerListeners(
                        // Register Listeners
                );
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public enum RegisterType {COMMAND, LISTENER}
}
