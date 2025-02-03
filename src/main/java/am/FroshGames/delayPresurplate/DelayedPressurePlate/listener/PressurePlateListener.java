package am.FroshGames.delayPresurplate.DelayedPressurePlate.listener;

import am.FroshGames.delayPresurplate.DelayedPressurePlate.DelayedPressurePlate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PressurePlateListener implements Listener {

    private final Set<Material> affectedPlates = new HashSet<>();
    private int delayTicks;

    public PressurePlateListener() {
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = DelayedPressurePlate.getInstance().getConfig();
        delayTicks = config.getInt("delay", 10) * 20; // Convertir segundos a ticks
        affectedPlates.clear();
        List<String> plates = config.getStringList("plates");

        for (String plate : plates) {
            try {
                affectedPlates.add(Material.valueOf(plate));
            } catch (IllegalArgumentException ignored) {
                DelayedPressurePlate.getInstance().getLogger().warning("Material inválido en la configuración: " + plate);
            }
        }
    }

    @EventHandler
    public void onRedstoneChange(BlockRedstoneEvent event) {
        Block block = event.getBlock();

        // Recargar la lista de placas en cada evento para asegurar que esté actualizada
        loadConfig();

        if (affectedPlates.contains(block.getType()) && event.getNewCurrent() > 0) {
            event.setNewCurrent(0);
            Bukkit.getScheduler().runTaskLater(DelayedPressurePlate.getInstance(), () -> {
                block.getState().update(true, false);
            }, delayTicks);
        }
    }
}
