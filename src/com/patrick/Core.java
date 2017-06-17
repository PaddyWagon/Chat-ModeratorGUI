package com.patrick;

import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener
{
    public Core() {}

    public void onEnable()
    {
        PluginDescriptionFile pdffile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        logger.info(pdffile.getFullName() + pdffile.getVersion() + " was enabled");
        registerCommands();
        getServer().getPluginManager().registerEvents(new PunishCommand(), this);
    }


    public void onDisable()
    {
        PluginDescriptionFile pdffile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        logger.info(pdffile.getFullName() + pdffile.getVersion() + " was disabled");
    }

    private void registerCommands()
    {
        getCommand("punish").setExecutor(new PunishCommand());
    }
}