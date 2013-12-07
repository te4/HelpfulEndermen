package me.te3.helpfulendermen;

import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Enderman;

/**
 *
 * @author te3
 */
public class HelpfulEndermen extends JavaPlugin implements Listener {

    JavaPlugin plugin = this;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEndermanActivity(EntityChangeBlockEvent blockchange) {
        Enderman enderman;
        
        if (blockchange.getEntityType() == EntityType.ENDERMAN) {
            enderman = (Enderman) blockchange.getEntity();
            Material newmaterial = enderman.getCarriedMaterial().getItemType();

            if (newmaterial == Material.DIRT || newmaterial == Material.GRASS) {
                Block placed = blockchange.getBlock();
                World world = placed.getWorld();

                if (placed.getY() < world.getMaxHeight() - 1) {
                    world.getBlockAt(placed.getX(), placed.getY() + 1, placed.getZ()).setType(Material.SAPLING);
                }
            }
        }
    }
}