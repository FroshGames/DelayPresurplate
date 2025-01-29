package am.FroshGames.delayPresurplate.DelayedPressurePlate.commands;

import am.FroshGames.delayPresurplate.DelayedPressurePlate.DelayedPressurePlate;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.List;

public class PlateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }
        Player player = (Player) sender;
        FileConfiguration config = DelayedPressurePlate.getInstance().getConfig();

        if (args.length < 1) {
            player.sendMessage("Uso: /delayedplate <setdelay|add|remove> [valor]");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "setdelay":
                if (args.length < 2) {
                    player.sendMessage("Uso: /delayedplate setdelay <segundos>");
                    return true;
                }
                try {
                    int seconds = Integer.parseInt(args[1]);
                    config.set("delay", seconds);
                    DelayedPressurePlate.getInstance().saveConfig();
                    player.sendMessage("El delay ha sido establecido en " + seconds + " segundos.");
                } catch (NumberFormatException e) {
                    player.sendMessage("El valor debe ser un número entero.");
                }
                break;
            case "add":
                Block block = player.getTargetBlockExact(5);
                if (block == null || !block.getType().name().endsWith("_PRESSURE_PLATE")) {
                    player.sendMessage("Debes mirar una placa de presión.");
                    return true;
                }
                List<String> plates = config.getStringList("plates");
                if (!plates.contains(block.getType().name())) {
                    plates.add(block.getType().name());
                    config.set("plates", plates);
                    DelayedPressurePlate.getInstance().saveConfig();
                }
                player.sendMessage("Placa de presión añadida a la lista.");
                break;
            case "remove":
                block = player.getTargetBlockExact(5);
                if (block == null || !config.getStringList("plates").contains(block.getType().name())) {
                    player.sendMessage("Debes mirar una placa de presión en la lista.");
                    return true;
                }
                plates = config.getStringList("plates");
                plates.remove(block.getType().name());
                config.set("plates", plates);
                DelayedPressurePlate.getInstance().saveConfig();
                player.sendMessage("Placa de presión eliminada de la lista.");
                break;
            default:
                player.sendMessage("Comando desconocido. Uso: /delayedplate <setdelay|add|remove> [valor]");
                break;
        }
        return true;
    }
}
