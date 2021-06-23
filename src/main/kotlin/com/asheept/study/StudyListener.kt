package com.asheept.study

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import java.time.Duration
import java.time.temporal.ChronoUnit
import kotlin.math.abs

class StudyListener(private val plugin: StudyPlugin): Listener
{
    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent)
    {
        val player = event.player

        val movX = event.from.blockX - event.to.blockX
        val movZ = event.from.blockZ - event.to.blockZ

        if(abs(movX) > 0 || abs(movZ) > 0)
        {
            player.sendMessage("move")
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent)
    {
        if(event.hand == EquipmentSlot.HAND)
        {
            if(event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK)
            {
                if(event.player.inventory.itemInMainHand.type == Material.CLOCK)
                {
                    val handitem = event.player.inventory.itemInMainHand

                    handitem.amount -=1

                    val item = ItemStack(Material.STONE)
                    val meta = item.itemMeta

                    meta.setDisplayName("hello")
                    item.itemMeta = meta
                    event.player.inventory.addItem(item)

                    val title  = Title.title(
                        text("click clock").color(TextColor.color(0xB2F6F6)).decorate(TextDecoration.BOLD),
                        text("show subtitle"),
                        Title.Times.of(Duration.of(1, ChronoUnit.MILLIS), Duration.of(5, ChronoUnit.SECONDS), Duration.of(1, ChronoUnit.SECONDS)))

                    Bukkit.getServer().showTitle(title)

                }
            }
        }

    }

}