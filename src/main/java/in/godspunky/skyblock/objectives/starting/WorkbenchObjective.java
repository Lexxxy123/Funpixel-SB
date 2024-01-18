package in.godspunky.skyblock.objectives.starting;


import in.godspunky.skyblock.event.SkyBlockCraftEvent;
import in.godspunky.skyblock.item.SMaterial;
import in.godspunky.skyblock.objectives.Objective;
import org.bukkit.event.EventHandler;

public class WorkbenchObjective extends Objective {

    public WorkbenchObjective() {
        super("craft_workbench", "Craft a workbench");
    }

    @EventHandler
    public void onCraft(SkyBlockCraftEvent e) {
        if (!isThisObjective(e.getPlayer())) return;

        if (e.getRecipe().getResult().getType().equals(SMaterial.CRAFTING_TABLE)) complete(e.getPlayer());
    }
}