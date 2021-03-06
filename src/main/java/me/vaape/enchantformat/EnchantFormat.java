package me.vaape.enchantformat;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class EnchantFormat extends JavaPlugin implements Listener {

    public static EnchantFormat plugin;

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    public void onEnable() {
        plugin = this;
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "EnchantFormat has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        plugin = null;
    }

    @EventHandler
    public void onInteract (PlayerInteractEvent event) {
        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
        if (isUnique(hand)) return;
        event.getPlayer().getInventory().setItemInMainHand(fixFormat(hand));
    }

    public ItemStack fixFormat(ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            if (item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();

                //Hide default enchantment lore
                if (!itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }

                List<String> lore = new ArrayList<String>();
                if (itemMeta.getLore() != null) {
                    lore = itemMeta.getLore();
                }

                List<String> newLore = new ArrayList<String>();

                for (Enchantment enchantmentRawName : item.getEnchantments().keySet()) {

                    String enchantmentName =
                            enchantmentRawName.toString().split(",")[1].replace(" ", "").replace("]", ""); //Extract official enchant name eg ARROW_DAMAGE

                    //Removes current custom enchantment lore
                    Iterator<String> iterator = lore.iterator();

                    while (iterator.hasNext()) {
                        String line = iterator.next();

                        if (line.contains("Infinity")) {
                            iterator.remove();
                        } else if (line.contains("Power")) {
                            iterator.remove();
                        } else if (line.contains("Flame")) {
                            iterator.remove();
                        } else if (line.contains("Punch")) {
                            iterator.remove();
                        } else if (line.contains("Sharpness")) {
                            iterator.remove();
                        } else if (line.contains("Bane of Arthropods")) {
                            iterator.remove();
                        } else if (line.contains("Smite")) {
                            iterator.remove();
                        } else if (line.contains("Depth Strider")) {
                            iterator.remove();
                        } else if (line.contains("Efficiency")) {
                            iterator.remove();
                        } else if (line.contains("Unbreaking")) {
                            iterator.remove();
                        } else if (line.contains("Fire Aspect")) {
                            iterator.remove();
                        } else if (line.contains("Frost Walker")) {
                            iterator.remove();
                        } else if (line.contains("Knockback")) {
                            iterator.remove();
                        } else if (line.contains("Fortune")) {
                            iterator.remove();
                        } else if (line.contains("Looting")) {
                            iterator.remove();
                        } else if (line.contains("Luck of the Sea")) {
                            iterator.remove();
                        } else if (line.contains("Lure")) {
                            iterator.remove();
                        } else if (line.contains("Mending")) {
                            iterator.remove();
                        } else if (line.contains("Respiration")) {
                            iterator.remove();
                        } else if (line.split(" ")[0].contains("Protection")) { //Split to look at first word so
                            // don't remove blast protection instead
                            iterator.remove();
                        } else if (line.contains("Blast Protection")) {
                            iterator.remove();
                        } else if (line.contains("Feather Falling")) {
                            iterator.remove();
                        } else if (line.contains("Fire Protection")) {
                            iterator.remove();
                        } else if (line.contains("Projectile Protection")) {
                            iterator.remove();
                        } else if (line.contains("Silk Touch")) {
                            iterator.remove();
                        } else if (line.contains("Thorns")) {
                            iterator.remove();
                        } else if (line.contains("Aqua Affinity")) {
                            iterator.remove();
                        } else if (line.contains("Sweeping Edge")) {
                            iterator.remove();
                        } else if (line.contains("Riptide")) {
                            iterator.remove();
                        } else if (line.contains("Impaling")) {
                            iterator.remove();
                        } else if (line.contains("Loyalty")) {
                            iterator.remove();
                        } else if (line.contains("Quick Charge")) {
                            iterator.remove();
                        } else if (line.contains("Multishot")) {
                            iterator.remove();
                        }
                    }

                    //Add to lore
                    if (enchantmentName.contains("ARROW_INFINITE")) {
                        newLore.add(ChatColor.GRAY + "Infinity " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("ARROW_DAMAGE")) {
                        newLore.add(ChatColor.GRAY + "Power " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("ARROW_FIRE")) {
                        newLore.add(ChatColor.GRAY + "Flame " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("ARROW_KNOCKBACK")) {
                        newLore.add(ChatColor.GRAY + "Punch " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("DAMAGE_ALL")) {
                        newLore.add(ChatColor.GRAY + "Sharpness " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("DAMAGE_ARTHROPODS")) {
                        newLore.add(ChatColor.GRAY + "Bane of Arthropods " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("DAMAGE_UNDEAD")) {
                        newLore.add(ChatColor.GRAY + "Smite " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("DEPTH_STRIDER")) {
                        newLore.add(ChatColor.GRAY + "Depth Strider " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("DIG_SPEED")) {
                        newLore.add(ChatColor.GRAY + "Efficiency " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("DURABILITY")) {
                        newLore.add(ChatColor.GRAY + "Unbreaking " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("FIRE_ASPECT")) {
                        newLore.add(ChatColor.GRAY + "Fire Aspect " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("FROST_WALKER")) {
                        newLore.add(ChatColor.GRAY + "Frost Walker " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("KNOCKBACK")) {
                        newLore.add(ChatColor.GRAY + "Knockback " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("LOOT_BONUS_BLOCKS")) {
                        newLore.add(ChatColor.GRAY + "Fortune " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("LOOT_BONUS_MOBS")) {
                        newLore.add(ChatColor.GRAY + "Looting " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("LUCK")) {
                        newLore.add(ChatColor.GRAY + "Luck of the Sea " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("LURE")) {
                        newLore.add(ChatColor.GRAY + "Lure " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("MENDING")) {
                        newLore.add(ChatColor.GRAY + "Mending " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("OXYGEN")) {
                        newLore.add(ChatColor.GRAY + "Respiration " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("PROTECTION_ENVIRONMENTAL")) {
                        newLore.add(ChatColor.GRAY + "Protection " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("PROTECTION_EXPLOSIONS")) {
                        newLore.add(ChatColor.GRAY + "Blast Protection " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("PROTECTION_FALL")) {
                        newLore.add(ChatColor.GRAY + "Feather Falling " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("PROTECTION_PROJECTILE")) {
                        newLore.add(ChatColor.GRAY + "Projectile Protection " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("PROTECTION_FIRE")) {
                        newLore.add(ChatColor.GRAY + "Fire Protection " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("SILK_TOUCH")) {
                        newLore.add(ChatColor.GRAY + "Silk Touch " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("THORNS")) {
                        newLore.add(ChatColor.GRAY + "Thorns " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("WATER_WORKER")) {
                        newLore.add(ChatColor.GRAY + "Aqua Affinity " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("SWEEPING_EDGE")) {
                        newLore.add(ChatColor.GRAY + "Sweeping Edge " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("RIPTIDE")) {
                        newLore.add(ChatColor.GRAY + "Riptide " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("IMPALING")) {
                        newLore.add(ChatColor.GRAY + "Impaling " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("LOYALTY")) {
                        newLore.add(ChatColor.GRAY + "Loyalty " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("QUICK_CHARGE")) {
                        newLore.add(ChatColor.GRAY + "Quick Charge " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    } else if (enchantmentName.contains("MULTISHOT")) {
                        newLore.add(ChatColor.GRAY + "Multishot " + toRoman(item.getEnchantments().get(enchantmentRawName)));
                    }
                }

                for (String line : lore) {
                    newLore.add(line);
                }

                itemMeta.setLore(newLore);
                item.setItemMeta(itemMeta);
                return item;
            }
            return item;
        }
        return null;
    }

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }

    public static boolean isUnique(ItemStack stack) {
        if (stack != null && stack.getType() != Material.AIR) {
            if (stack.hasItemMeta()) {
                if (stack.getItemMeta().hasLore()) {
                    List<String> lore = stack.getItemMeta().getLore();
                    if (lore.contains(ChatColor.ITALIC + "Curses the souls of enemies")) { //Runeblade
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}