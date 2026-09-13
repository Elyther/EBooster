package me.elythern.ebooster;

import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;

public class BoosterTask {


    public BoosterTask(EBooster plugin){


        new BukkitRunnable(){


            @Override
            public void run(){


                for(String key :
                        plugin.getConfig()
                        .getConfigurationSection("players")
                        .getKeys(false)){


                    UUID uuid =
                    UUID.fromString(key);



                    double balance =
                    DataManager.get(uuid);



                    if(balance <= 0)
                        continue;



                    double rate =
                    balance *
                    plugin.getConfig()
                    .getDouble(
                    "booster.rate");



                    double newBalance =
                    balance + rate;



                    DataManager.set(
                    uuid,
                    newBalance);

                }


            }


        }.runTaskTimer(
                plugin,
                20L,
                20L
        );

    }

}
