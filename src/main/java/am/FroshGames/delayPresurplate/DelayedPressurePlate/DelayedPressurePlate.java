package am.FroshGames.delayPresurplate.DelayedPressurePlate;

import am.FroshGames.delayPresurplate.DelayedPressurePlate.listener.PressurePlateListener;
import am.FroshGames.delayPresurplate.DelayedPressurePlate.commands.PlateCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class DelayedPressurePlate extends JavaPlugin {

    private static DelayedPressurePlate instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PressurePlateListener(), this);
        getCommand("delayedplate").setExecutor(new PlateCommand());
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public static DelayedPressurePlate getInstance() {
        return instance;
    }
}
//Desarrollado por [Froshy]
