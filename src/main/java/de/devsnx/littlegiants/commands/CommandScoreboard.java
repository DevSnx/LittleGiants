package de.devsnx.littlegiants.commands;

import de.devsnx.littlegiants.LittleGiants;
import jdk.internal.foreign.ArenaAllocator;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

/**
 * @author DevSnx
 * @since 28.06.2023
 */
public class CommandScoreboard implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        Player player = ((Player) sender).getPlayer();

        if(!(args.length == 1)){
            player.sendMessage("§7Benutze §5/scoreboard §7<§aon§7/§coff§7>");
            return true;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("on")){
                if(!(LittleGiants.getScores().contains(player))){
                    LittleGiants.getScores().add(player);
                    player.sendMessage("§7Du hast das Scoreboard §aaktiviert§7.");
                    LittleGiants.getScoreboardManager().createNewScoreboard(player);
                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
                    return true;
                }else{
                    player.sendMessage("§cDu hast das Scoreboard bereits §aaktiviert§7.");
                    player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1.0F, 1.0F);
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("off")){
                if(LittleGiants.getScores().contains(player)){
                    LittleGiants.getScores().remove(player);
                    player.sendMessage("§7Du hast das Scoreboard §4deaktiviert§7.");
                    LittleGiants.getScoreboardManager().removePlayerScoreboard(player);
                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
                    return true;
                }else{
                    player.sendMessage("§cDu hast das Scoreboard bereits §4deaktiviert§7.");
                    player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1.0F, 1.0F);
                    return true;
                }
            }
        }
        return true;
    }
}
