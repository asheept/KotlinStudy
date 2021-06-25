package com.asheept.study

import net.kyori.adventure.text.Component
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class StudyPlugin : JavaPlugin(), Listener {
    override fun onEnable()
    {

        server.pluginManager.registerEvents(StudyListener(this), this)
        getCommand("test")?.setExecutor(StudyCommand(this))


        val runnable = object : BukkitRunnable()
        {
            override fun run() {

                Bukkit.getOnlinePlayers().forEach { player ->

                    val component =
                        Component.text("\n ${ChatColor.AQUA}" + "Hello ${ChatColor.GOLD}" + "${player.name} \n")
                    player.sendPlayerListHeader(component)

                    val footerComponent = Component.text("\n ${Bukkit.getOnlinePlayers().size} 명 접속 중\n")
                    player.sendPlayerListFooter(footerComponent)
                }
            }
        }
        runnable.runTaskTimer(this, 0L, 1L)
    }





    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        player.sendMessage("hello join message")
    }
}