package me.BadBones69.CrazyEnchantments.Enchantments.Swords;

import me.BadBones69.CrazyEnchantments.Api;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Inquisitive implements Listener{
	@EventHandler
	public void onPlayerDamage(EntityDeathEvent e){
		if(Api.isFriendly(e.getEntity().getKiller(), e.getEntity()))return;
		if(!Api.isEnchantmentEnabled("Inquisitive"))return;
		if(e.getEntity().getKiller() instanceof Player){
			Player damager = (Player) e.getEntity().getKiller();
			if(Api.getItemInHand(damager).hasItemMeta()){
				if(Api.getItemInHand(damager).getItemMeta().hasLore()){
					for(String lore : Api.getItemInHand(damager).getItemMeta().getLore()){
						if(lore.contains(Api.getEnchName("Inquisitive"))){
							int chance=9-Api.getPower(lore, Api.getEnchName("Inquisitive"));
							if(Api.randomPicker(chance)){
								e.setDroppedExp(e.getDroppedExp()*2);
							}
						}
					}
				}
			}
		}
	}
}