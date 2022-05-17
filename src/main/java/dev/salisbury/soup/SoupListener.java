package dev.salisbury.soup;

import com.google.common.base.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SoupListener extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!e.getAction().name().contains("RIGHT_CLICK")) { return; }
        if (Objects.equal(e.getMaterial(), Material.MUSHROOM_SOUP)) {
            if (p.getHealth() == p.getMaxHealth()) { return; }
            e.setCancelled(true);

            double HEAL_AMOUNT = 3.5D * 2.5D;
            double newHealth = p.getHealth() + HEAL_AMOUNT;

            p.setHealth(Math.min(newHealth, p.getMaxHealth()));
            p.setItemInHand(new ItemStack(Material.BOWL));
            p.updateInventory();
        }
    }

}
