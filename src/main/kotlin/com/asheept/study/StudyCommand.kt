package com.asheept.study

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.scheduler.BukkitRunnable

class StudyCommand(private val plugin: StudyPlugin) : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        //Player player = (Player) sender
        try {
            val commandName = args[0]

            if ("hello".equals(commandName, true))
            {
                sender.sendMessage("hello commands")
            }
            else if("count".equals(commandName, true))
            {
                val countRunnable = object: BukkitRunnable()
                {
                    var ticks = 600
                    override fun run()
                    {
                        --ticks
                        val seonds = ticks / 20

                        for(players in Bukkit.getOnlinePlayers())
                        {
                            players.sendActionBar("  ${ChatColor.AQUA}${seonds} ${ChatColor.WHITE}초")
                        }

                        if (ticks == 0)
                        {
                            cancel()
                        }
                    }
                }
                countRunnable.runTaskTimer(plugin, 0L, 1L)
            }
        } catch (e: IndexOutOfBoundsException) {
            sender.sendMessage("제대로 입력해")
        }
        return true
    }
}