package de.devsnx.littlegiants.manager;

import de.devsnx.littlegiants.LittleGiants;
import de.devsnx.littlegiants.commands.CommandScoreboard;
import de.devsnx.littlegiants.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author DevSnx
 * @since 26.06.2023
 */
public class ScoreboardManager {

    private Map<Player, Scoreboard> playerScoreboards;

    public ScoreboardManager() {
        this.playerScoreboards = new HashMap<Player, Scoreboard>();
        updateAllScoreboards(true, true);
        startSidebarUpdater();
    }
    public void createNewScoreboard(Player p) {
        if (this.playerScoreboards.containsKey(p)){
            return;
        }

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Team teamAdmin = board.registerNewTeam("ATeamAdmin");
        Team teamSpieler = board.registerNewTeam("BTeamSpieler");

        teamAdmin.setPrefix("§c§lCrew §8| §7");
        teamSpieler.setPrefix("§b§lCrew §8| §7");

        Objective sidebar = board.registerNewObjective("Sidebar", "dummy");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        sidebar.setDisplayName("§l§f─═ §5§lL§7ittle§b§lG§7iants §f§l═─");


        Team teamRang = board.registerNewTeam("rang");
        Team onlineplayer = board.registerNewTeam("onlineplayer");
        Team teamPlaytime = board.registerNewTeam("playtime");
        Team teamzeit = board.registerNewTeam("zeit");

        teamRang.addEntry(" §1§8»");
        onlineplayer.addEntry(" §2§8»");
        teamPlaytime.addEntry(" §3§8»");
        teamzeit.addEntry(" §4§8»");

        sidebar.getScore("§1").setScore(12);
        sidebar.getScore("§a✔ §5Rang:").setScore(11);
        sidebar.getScore(" §1§8»").setScore(10);
        sidebar.getScore("§2").setScore(9);
        sidebar.getScore("§c❤ §bOnline:").setScore(8);
        sidebar.getScore(" §2§8»").setScore(7);
        sidebar.getScore("§3").setScore(6);
        sidebar.getScore("§5✦ §5Spielzeit:").setScore(5);
        sidebar.getScore(" §3§8»").setScore(4);
        sidebar.getScore("§5").setScore(3);
        sidebar.getScore("§5◕ §bUhrzeit:").setScore(2);
        sidebar.getScore(" §4§8»").setScore(1);
        sidebar.getScore("§6").setScore(0);
        p.setScoreboard(board);
        this.playerScoreboards.put(p, board);
        updateTeamlistForPlayer(p);
        updateSidebar(p);
    }

    public void updateSidebar(Player forWhom) {
        if (!this.playerScoreboards.containsKey(forWhom))
            if(LittleGiants.getScores().contains(forWhom)){
                createNewScoreboard(forWhom);
            }else{
                removePlayerScoreboard(forWhom);
            }
        Scoreboard board = this.playerScoreboards.get(forWhom);

        Team onlineplayer = board.getTeam("onlineplayer");
        Team teamPlaytime = board.getTeam("playtime");
        Team teamRang = board.getTeam("rang");
        Team teamzeit = board.getTeam("zeit");


        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        teamzeit.setSuffix(" §7" + simpleDateFormat.format(new Date()));
        onlineplayer.setSuffix(" §d" + Bukkit.getOnlinePlayers().size()+ "§8/§7128");
        teamPlaytime.setSuffix(" " + Utils.formatTime(forWhom.getStatistic(Statistic.PLAY_ONE_MINUTE)));
        if(forWhom.isOp()){
            teamRang.setSuffix(" §8" + "§c§lCrew");
        }else{
            teamRang.setSuffix(" §8" + "§b§lCrew");
        }
    }

    private Team getTeamForPlayer(Scoreboard board, Player forWhom) {
        if (forWhom.isOp())
            return board.getTeam("ATeamAdmin");
        return board.getTeam("BTeamSpieler");
    }

    private Team searchTeamsForEntry(Player forWhom, String entry) {
        if (!this.playerScoreboards.containsKey(forWhom))
            if(LittleGiants.getScores().contains(forWhom)){
                createNewScoreboard(forWhom);
            }else{
                removePlayerScoreboard(forWhom);
            }
        Scoreboard board = this.playerScoreboards.get(forWhom);
        for (Team team : board.getTeams()) {
            if (team.hasEntry(entry))
                return team;
        }
        return null;
    }

    public void removePlayerScoreboard(Player p) {
        if (!this.playerScoreboards.containsKey(p))
            return;
        Scoreboard board = this.playerScoreboards.get(p);
        for (Objective scoreOb : board.getObjectives())
            scoreOb.unregister();
        for (Team scoreTeam : board.getTeams())
            scoreTeam.unregister();
        this.playerScoreboards.remove(p);
        p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    private void startSidebarUpdater() {
        (new BukkitRunnable() {
            public void run() {
                ScoreboardManager.this.updateAllScoreboards(true, true);
            }
        }).runTaskTimer((Plugin) LittleGiants.getInstance(), 60L, 60L);
    }

    public void updateAllScoreboards(boolean teamList, boolean sidebar) {
        if (teamList)
            for (Player all : this.playerScoreboards.keySet())
                updateTeamlistForPlayer(all);
        if (sidebar)
            for (Player all : this.playerScoreboards.keySet())
                updateSidebar(all);

    }

    public void updateTeamlistForPlayer(Player forWhom) {
        if (!this.playerScoreboards.containsKey(forWhom))
            if(LittleGiants.getScores().contains(forWhom)){
                createNewScoreboard(forWhom);
            }
        Scoreboard board = this.playerScoreboards.get(forWhom);
        List<String> onlineName = new ArrayList<String>();
        for (Player all : Bukkit.getOnlinePlayers()) {
            onlineName.add(all.getName());
            Team playerTeam = getTeamForPlayer(board, all);
            if (!playerTeam.hasEntry(all.getName()))
                playerTeam.addEntry(all.getName());
        }
        for (String entry : board.getEntries()) {
            if (!onlineName.contains(entry)) {
                Team team = searchTeamsForEntry(forWhom, entry);
                if (team != null &&
                        !team.getName().equals("rang") &&
                        !team.getName().equals("onlineplayer") &&
                        !team.getName().equals("playtime") &&
                        !team.getName().equals("zeit"))
                    team.removeEntry(entry);
            }
        }
    }
}