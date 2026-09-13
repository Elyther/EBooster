package me.elythern.ebooster;

import org.bukkit.plugin.java.JavaPlugin;

public class EBooster extends JavaPlugin {

    private static EBooster instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        getCommand("booster")
                .setExecutor(new BoosterCommand(this));

        getServer()
                .getPluginManager()
                .registerEvents(
                new BoosterGUI(this), this);

        if(getServer().getPluginManager()
                .getPlugin("PlaceholderAPI") != null){

            new BoosterPlaceholder(this)
                    .register();
        }

        getLogger().info("EBooster aktiv!");
    }


    public static EBooster get(){
        return instance;
    }
}
