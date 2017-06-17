package com.patrick;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class PunishCommand implements org.bukkit.event.Listener, CommandExecutor {
    static String tn;
    static Player target;

    public PunishCommand() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("punish.open")) {
            sender.sendMessage(ChatColor.RED + "You do not have enough permissions to do that.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /punish <player>");
            return true;
        }

        if (args.length == 1) {
            Player p = (Player) sender;
            target = Bukkit.getServer().getPlayer(args[0]);
            tn = target.getName();
            openGUI(p);
            return true;
        }
        return true;
    }

    public static void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 18, ChatColor.RED + "Punish Player: " + ChatColor.DARK_GRAY + target.getName());

        ItemStack caps = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta capsMeta = caps.getItemMeta();

        ItemStack spamflood = new ItemStack(Material.IRON_AXE);
        ItemMeta spamfloodMeta = spamflood.getItemMeta();

        ItemStack Hackusating = new ItemStack(Material.IRON_SPADE);
        ItemMeta HackusatingMeta = Hackusating.getItemMeta();

        ItemStack Arguing = new ItemStack(Material.IRON_SWORD);
        ItemMeta ArguingMeta = Arguing.getItemMeta();

        ItemStack Provoking = new ItemStack(Material.IRON_HOE);
        ItemMeta ProvokingMeta = Provoking.getItemMeta();

        ItemStack Trolling = new ItemStack(Material.IRON_ORE);
        ItemMeta TrollingMeta = Trolling.getItemMeta();

        ItemStack Advertising = new ItemStack(Material.SIGN);
        ItemMeta AdvertisingMeta = Advertising.getItemMeta();

        ItemStack SwearingS1 = new ItemStack(Material.RED_SANDSTONE);
        ItemMeta SwearingS1Meta = SwearingS1.getItemMeta();

        ItemStack SwearingS2 = new ItemStack(Material.RED_NETHER_BRICK);
        ItemMeta SwearingS2Meta = SwearingS2.getItemMeta();

        ItemStack SwearingS3 = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta SwearingS3Meta = SwearingS3.getItemMeta();

        ItemStack PlayerHistory = new ItemStack(Material.PAPER);
        ItemMeta PlayerHistoryMeta = PlayerHistory.getItemMeta();

        //add in the option to look at player history

        capsMeta.setDisplayName(ChatColor.RED + "Caps");
        caps.setItemMeta(capsMeta);

        spamfloodMeta.setDisplayName(ChatColor.RED + "Spam & Flood");
        spamflood.setItemMeta(spamfloodMeta);

        HackusatingMeta.setDisplayName(ChatColor.RED + "Hackusating");
        Hackusating.setItemMeta(HackusatingMeta);

        ArguingMeta.setDisplayName(ChatColor.RED + "Arguing");
        Arguing.setItemMeta(ArguingMeta);

        ProvokingMeta.setDisplayName(ChatColor.RED + "Provoking");
        Provoking.setItemMeta(ProvokingMeta);

        TrollingMeta.setDisplayName(ChatColor.RED + "Trolling");
        Trolling.setItemMeta(TrollingMeta);

        AdvertisingMeta.setDisplayName(ChatColor.RED + "Advertising");
        Advertising.setItemMeta(AdvertisingMeta);

        SwearingS1Meta.setDisplayName(ChatColor.RED + "Swearing S:1");
        SwearingS1.setItemMeta(SwearingS1Meta);

        SwearingS2Meta.setDisplayName(ChatColor.RED + "Swearing S:2");
        SwearingS2.setItemMeta(SwearingS2Meta);

        SwearingS3Meta.setDisplayName(ChatColor.RED + "Swearing S:3");
        SwearingS3.setItemMeta(SwearingS3Meta);

        PlayerHistoryMeta.setDisplayName(ChatColor.RED + "View Player History");
        PlayerHistory.setItemMeta(PlayerHistoryMeta);

        //row one
        inv.setItem(1, caps);
        inv.setItem(2, spamflood);
        inv.setItem(3, Hackusating);
        inv.setItem(4, Arguing);
        inv.setItem(5, Provoking);
        inv.setItem(6, Trolling);
        inv.setItem(7, Advertising);
        //row two
        inv.setItem(12, SwearingS1);
        inv.setItem(13, SwearingS2);
        inv.setItem(14, SwearingS3);
        //row three
        inv.setItem(17, PlayerHistory);

        player.openInventory(inv);
    }

    @EventHandler
    public static void clickable(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = String.format(event.getFormat(), player.getDisplayName(), event.getMessage());
        String playerName = event.getPlayer().getName();

        event.setCancelled(true);

            TextComponent clickable = new TextComponent(message);
            clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/punish " + playerName));
            event.getRecipients().forEach(receiver -> {
                receiver.spigot().sendMessage(clickable);
            });

    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Punish Player: " + target.getName())) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta())) {
            player.closeInventory();
            return;
        }
        if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Punish Player: " + target.getName())) {
            switch (event.getCurrentItem().getType()) {
                case IRON_PICKAXE:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 2h  Caps");
                    }
                    break;
                case IRON_AXE:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 4h  Spam/Flood");
                    }
                    break;
                case IRON_SPADE:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 6h  Hackusating");
                    }
                    break;
                case IRON_SWORD:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 12h  Arguing");
                    }
                    break;
                case IRON_HOE:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 6h  Provoking");
                    }
                    break;
                case IRON_ORE:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 12h  Chat Trolling");
                    }
                    break;
                case SIGN:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + target.getName() + " Advertising");
                    }
                    break;
                case RED_SANDSTONE:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 1d  Swearing/Harassment Severity 1");
                    }
                    break;
                case RED_NETHER_BRICK:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 36h  Swearing/Harassment Severity 2");
                    }
                    break;
                case REDSTONE_BLOCK:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempmute " + target.getName() + " 3d  Swearing/Harassment Severity 3");
                    }
                    break;
                case PAPER:
                    if (player.hasPermission("punish.open")) {

                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "bminfo " + target.getName() + " -m -b");
                    }
                    break;
                default:
                    player.closeInventory();
            }
        }
    }
}