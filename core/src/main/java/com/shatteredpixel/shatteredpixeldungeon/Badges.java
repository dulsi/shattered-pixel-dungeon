/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;
import com.identicalsoftware.javalibgamerzilla.Gamerzilla;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Badges {
	
	public enum Badge {
		MASTERY_WARRIOR,
		MASTERY_MAGE,
		MASTERY_ROGUE,
		MASTERY_HUNTRESS,
		FOUND_RATMOGRIFY,

		//bronze
		UNLOCK_MAGE                 ( 1, "Mage Unlocked" ),
		UNLOCK_ROGUE                ( 2, "Rogue Unlocked" ),
		UNLOCK_HUNTRESS             ( 3, "Huntress Unlocked" ),
		MONSTERS_SLAIN_1            ( 4, "Basic Slayer" ),
		MONSTERS_SLAIN_2            ( 5, "Expert Slayer" ),
		GOLD_COLLECTED_1            ( 6, "Some Gold" ),
		GOLD_COLLECTED_2            ( 7, "More Gold" ),
		ITEM_LEVEL_1                ( 8, "Item (Level 3) Acquired" ),
		LEVEL_REACHED_1             ( 9, "Level 6" ),
		STRENGTH_ATTAINED_1         ( 10, "Strength 13 Attained" ),
		FOOD_EATEN_1                ( 11, "Breakfast" ),
		POTIONS_COOKED_1            ( 12, "3 Potions Cooked" ),
		BOSS_SLAIN_1                ( 13, "First Boss Slain" ),
		DEATH_FROM_FIRE             ( 14, "Death from Fire" ),
		DEATH_FROM_POISON           ( 15, "Death from Poison" ),
		DEATH_FROM_GAS              ( 16, "Death from Toxic Gas" ),
		DEATH_FROM_HUNGER           ( 17, "Death from Hunger" ),
		DEATH_FROM_FALLING          ( 18, "Death from Falling" ),

		//silver
		NO_MONSTERS_SLAIN           ( 32, "Peace" ),
		GRIM_WEAPON                 ( 33, "Grim Death" ),
		MONSTERS_SLAIN_3            ( 34, "Champion Slayer" ),
		MONSTERS_SLAIN_4            ( 35, "Master Slayer" ),
		GOLD_COLLECTED_3            ( 36, "Lots of Gold" ),
		GOLD_COLLECTED_4            ( 37, "All the Gold" ),
		ITEM_LEVEL_2                ( 38, "Item (Level 6) Acquired" ),
		ITEM_LEVEL_3                ( 39, "Item (Level 9) Acquired" ),
		LEVEL_REACHED_2             ( 40, "Level 12" ),
		LEVEL_REACHED_3             ( 41, "Level 18" ),
		STRENGTH_ATTAINED_2         ( 42, "Strength 15 Attained" ),
		STRENGTH_ATTAINED_3         ( 43, "Strength 17 Attained" ),
		FOOD_EATEN_2                ( 44, "Lunch" ),
		FOOD_EATEN_3                ( 45, "Dinner" ),
		POTIONS_COOKED_2            ( 46, "6 Potions Cooked" ),
		POTIONS_COOKED_3            ( 47, "9 Potions Cooked" ),
		BOSS_SLAIN_2                ( 48, "Second Boss Slain" ),
		BOSS_SLAIN_3                ( 49, "Third Boss Slain" ),
		ALL_POTIONS_IDENTIFIED      ( 50, "Potion Master" ),
		ALL_SCROLLS_IDENTIFIED      ( 51, "Scroll Lorekeeper" ),
		DEATH_FROM_GLYPH            ( 52, "Deferred Death" ),
		BOSS_SLAIN_1_WARRIOR,
		BOSS_SLAIN_1_MAGE,
		BOSS_SLAIN_1_ROGUE,
		BOSS_SLAIN_1_HUNTRESS,
		BOSS_SLAIN_1_ALL_CLASSES    ( 53, "First Boss Slain Again", true ),
		GAMES_PLAYED_1              ( 54, "Casual Gamer", true ),

		//gold
		PIRANHAS                    ( 64, "Piranhas" ),
		//these names are a bit outdated, but it doesn't really matter.
		BAG_BOUGHT_SEED_POUCH,
		BAG_BOUGHT_SCROLL_HOLDER,
		BAG_BOUGHT_POTION_BANDOLIER,
		BAG_BOUGHT_WAND_HOLSTER,
		ALL_BAGS_BOUGHT             ( 65, "Bagger" ),
		MASTERY_COMBO               ( 66, "Combo Master" ),
		ITEM_LEVEL_4                ( 67, "Item (Level 12) Acquired" ),
		LEVEL_REACHED_4             ( 68, "Level 24" ),
		STRENGTH_ATTAINED_4         ( 69, "Strength 19 Attained" ),
		FOOD_EATEN_4                ( 70, "Dessert" ),
		POTIONS_COOKED_4            ( 71, "12 Potions Cooked" ),
		BOSS_SLAIN_4                ( 72, "Fourth Boss Slain" ),
		ALL_WEAPONS_IDENTIFIED      ( 73, "Weaponsmith" ),
		ALL_ARMOR_IDENTIFIED        ( 74, "Armorsmith" ),
		ALL_WANDS_IDENTIFIED        ( 75, "Wandmaker" ),
		ALL_RINGS_IDENTIFIED        ( 76, "Ring Expert" ),
		ALL_ARTIFACTS_IDENTIFIED    ( 77, "Archeologist" ),
		VICTORY                     ( 78, "Victory" ),
		YASD                        ( 79, "YASD", true ),
		BOSS_SLAIN_3_GLADIATOR,
		BOSS_SLAIN_3_BERSERKER,
		BOSS_SLAIN_3_WARLOCK,
		BOSS_SLAIN_3_BATTLEMAGE,
		BOSS_SLAIN_3_FREERUNNER,
		BOSS_SLAIN_3_ASSASSIN,
		BOSS_SLAIN_3_SNIPER,
		BOSS_SLAIN_3_WARDEN,
		BOSS_SLAIN_3_ALL_SUBCLASSES ( 80, "Third Boss Slain Again Again", true ),
		GAMES_PLAYED_2              ( 81, "Novice Gamer", true ),

		//platinum
		HAPPY_END                   ( 96,"Happy End" ),
		ALL_ITEMS_IDENTIFIED        ( 97, "Keeper of Secrets", true ),
		VICTORY_WARRIOR,
		VICTORY_MAGE,
		VICTORY_ROGUE,
		VICTORY_HUNTRESS,
		VICTORY_ALL_CLASSES         ( 98, "Victory Again Again Again", true ),
		GAMES_PLAYED_3              ( 99, "Hardcore Gamer", true ),
		CHAMPION_1                  ( 100, "Champion" ),

		//diamond
		GAMES_PLAYED_4              ( 112, "Never Sleep Gamer", true ),
		CHAMPION_2                  ( 113, "Champion II" ),
		CHAMPION_3                  ( 114, "Champion III" );

		public boolean meta;

		public int image;

		public String gamerzillaName;
		
		Badge( int image, String gamerzillaName ) {
			this( image, gamerzillaName, false );
		}
		
		Badge( int image, String gamerzillaName, boolean meta ) {
			this.image = image;
			this.meta = meta;
			this.gamerzillaName = gamerzillaName;
		}

		public String desc(){
			return Messages.get(this, name());
		}
		
		Badge() {
			this( -1, "" );
		}
	}
	
	private static HashSet<Badge> global;
	private static HashSet<Badge> local = new HashSet<>();
	private static Gamerzilla gamerzilla;
	private static int gameid = -1;
	
	private static boolean saveNeeded = false;

	public static void reset() {
		local.clear();
		loadGlobal();
	}
	
	public static final String BADGES_FILE	= "badges.dat";
	private static final String BADGES		= "badges";
	
	private static final HashSet<String> removedBadges = new HashSet<>();
	static{
		//no recently removed badges
	}

	private static final HashMap<String, String> renamedBadges = new HashMap<>();
	static{
		//no recently renamed badges
	}

	public static HashSet<Badge> restore( Bundle bundle ) {
		HashSet<Badge> badges = new HashSet<>();
		if (bundle == null) return badges;
		
		String[] names = bundle.getStringArray( BADGES );
		for (int i=0; i < names.length; i++) {
			try {
				if (renamedBadges.containsKey(names[i])){
					names[i] = renamedBadges.get(names[i]);
				}
				if (!removedBadges.contains(names[i])){
					badges.add( Badge.valueOf( names[i] ) );
				}
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}

		addReplacedBadges(badges);
	
		return badges;
	}
	
	public static void store( Bundle bundle, HashSet<Badge> badges ) {
		addReplacedBadges(badges);

		int count = 0;
		String names[] = new String[badges.size()];
		
		for (Badge badge:badges) {
			names[count++] = badge.toString();
		}
		bundle.put( BADGES, names );
	}
	
	public static void loadLocal( Bundle bundle ) {
		local = restore( bundle );
	}
	
	public static void saveLocal( Bundle bundle ) {
		store( bundle, local );
	}
	
	public static void loadGlobal() {
		if (global == null) {
			try {
				Bundle bundle = FileUtils.bundleFromFile( BADGES_FILE );
				global = restore( bundle );

			} catch (IOException e) {
				global = new HashSet<>();
			}
			gamerzilla = new Gamerzilla();
			gamerzilla.Start(false, FileUtils.getGamerzillaSavePath());
			gameid = gamerzilla.SetGameFromFile(FileUtils.getGamerzillaDataPath() + "shatteredpixeldungeon.game", FileUtils.getGamerzillaDataPath());
		}
	}

	public static void saveGlobal() {
		if (saveNeeded) {
			
			Bundle bundle = new Bundle();
			store( bundle, global );
			
			try {
				FileUtils.bundleToFile(BADGES_FILE, bundle);
				saveNeeded = false;
			} catch (IOException e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}
	}

	public static int unlocked(boolean global){
		if (global) return Badges.global.size();
		else        return Badges.local.size();
	}

	public static void validateMonstersSlain() {
		Badge badge = null;
		
		if (!local.contains( Badge.MONSTERS_SLAIN_1 ) && Statistics.enemiesSlain >= 10) {
			badge = Badge.MONSTERS_SLAIN_1;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_2 ) && Statistics.enemiesSlain >= 50) {
			badge = Badge.MONSTERS_SLAIN_2;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_3 ) && Statistics.enemiesSlain >= 150) {
			badge = Badge.MONSTERS_SLAIN_3;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_4 ) && Statistics.enemiesSlain >= 250) {
			badge = Badge.MONSTERS_SLAIN_4;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateGoldCollected() {
		Badge badge = null;
		
		if (!local.contains( Badge.GOLD_COLLECTED_1 ) && Statistics.goldCollected >= 100) {
			badge = Badge.GOLD_COLLECTED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_2 ) && Statistics.goldCollected >= 500) {
			badge = Badge.GOLD_COLLECTED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_3 ) && Statistics.goldCollected >= 2500) {
			badge = Badge.GOLD_COLLECTED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_4 ) && Statistics.goldCollected >= 7500) {
			badge = Badge.GOLD_COLLECTED_4;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateLevelReached() {
		Badge badge = null;
		
		if (!local.contains( Badge.LEVEL_REACHED_1 ) && Dungeon.hero.lvl >= 6) {
			badge = Badge.LEVEL_REACHED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_2 ) && Dungeon.hero.lvl >= 12) {
			badge = Badge.LEVEL_REACHED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_3 ) && Dungeon.hero.lvl >= 18) {
			badge = Badge.LEVEL_REACHED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_4 ) && Dungeon.hero.lvl >= 24) {
			badge = Badge.LEVEL_REACHED_4;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateStrengthAttained() {
		Badge badge = null;
		
		if (!local.contains( Badge.STRENGTH_ATTAINED_1 ) && Dungeon.hero.STR >= 13) {
			badge = Badge.STRENGTH_ATTAINED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_2 ) && Dungeon.hero.STR >= 15) {
			badge = Badge.STRENGTH_ATTAINED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_3 ) && Dungeon.hero.STR >= 17) {
			badge = Badge.STRENGTH_ATTAINED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_4 ) && Dungeon.hero.STR >= 19) {
			badge = Badge.STRENGTH_ATTAINED_4;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateFoodEaten() {
		Badge badge = null;
		
		if (!local.contains( Badge.FOOD_EATEN_1 ) && Statistics.foodEaten >= 10) {
			badge = Badge.FOOD_EATEN_1;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_2 ) && Statistics.foodEaten >= 20) {
			badge = Badge.FOOD_EATEN_2;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_3 ) && Statistics.foodEaten >= 30) {
			badge = Badge.FOOD_EATEN_3;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_4 ) && Statistics.foodEaten >= 40) {
			badge = Badge.FOOD_EATEN_4;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validatePotionsCooked() {
		Badge badge = null;
		
		if (!local.contains( Badge.POTIONS_COOKED_1 ) && Statistics.potionsCooked >= 3) {
			badge = Badge.POTIONS_COOKED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.POTIONS_COOKED_2 ) && Statistics.potionsCooked >= 6) {
			badge = Badge.POTIONS_COOKED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.POTIONS_COOKED_3 ) && Statistics.potionsCooked >= 9) {
			badge = Badge.POTIONS_COOKED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.POTIONS_COOKED_4 ) && Statistics.potionsCooked >= 12) {
			badge = Badge.POTIONS_COOKED_4;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validatePiranhasKilled() {
		Badge badge = null;
		
		if (!local.contains( Badge.PIRANHAS ) && Statistics.piranhasKilled >= 6) {
			badge = Badge.PIRANHAS;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateItemLevelAquired( Item item ) {
		
		// This method should be called:
		// 1) When an item is obtained (Item.collect)
		// 2) When an item is upgraded (ScrollOfUpgrade, ScrollOfWeaponUpgrade, ShortSword, WandOfMagicMissile)
		// 3) When an item is identified

		// Note that artifacts should never trigger this badge as they are alternatively upgraded
		if (!item.levelKnown || item instanceof Artifact) {
			return;
		}
		
		Badge badge = null;
		if (!local.contains( Badge.ITEM_LEVEL_1 ) && item.level() >= 3) {
			badge = Badge.ITEM_LEVEL_1;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_2 ) && item.level() >= 6) {
			badge = Badge.ITEM_LEVEL_2;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_3 ) && item.level() >= 9) {
			badge = Badge.ITEM_LEVEL_3;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_4 ) && item.level() >= 12) {
			badge = Badge.ITEM_LEVEL_4;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateAllBagsBought( Item bag ) {
		
		Badge badge = null;
		if (bag instanceof VelvetPouch) {
			badge = Badge.BAG_BOUGHT_SEED_POUCH;
		} else if (bag instanceof ScrollHolder) {
			badge = Badge.BAG_BOUGHT_SCROLL_HOLDER;
		} else if (bag instanceof PotionBandolier) {
			badge = Badge.BAG_BOUGHT_POTION_BANDOLIER;
		} else if (bag instanceof MagicalHolster) {
			badge = Badge.BAG_BOUGHT_WAND_HOLSTER;
		}
		
		if (badge != null) {
			
			local.add( badge );
			
			if (!local.contains( Badge.ALL_BAGS_BOUGHT ) &&
				local.contains( Badge.BAG_BOUGHT_SEED_POUCH ) &&
				local.contains( Badge.BAG_BOUGHT_SCROLL_HOLDER ) &&
				local.contains( Badge.BAG_BOUGHT_POTION_BANDOLIER ) &&
				local.contains( Badge.BAG_BOUGHT_WAND_HOLSTER )) {
						
					badge = Badge.ALL_BAGS_BOUGHT;
					local.add( badge );
					displayBadge( badge );
			}
		}
	}
	
	public static void validateItemsIdentified() {
		
		for (Catalog cat : Catalog.values()){
			if (cat.allSeen()){
				Badge b = Catalog.catalogBadges.get(cat);
				if (!global.contains(b)){
					displayBadge(b);
				}
			}
		}
		
		if (!global.contains( Badge.ALL_ITEMS_IDENTIFIED ) &&
			global.contains( Badge.ALL_WEAPONS_IDENTIFIED ) &&
			global.contains( Badge.ALL_ARMOR_IDENTIFIED ) &&
			global.contains( Badge.ALL_WANDS_IDENTIFIED ) &&
			global.contains( Badge.ALL_RINGS_IDENTIFIED ) &&
			global.contains( Badge.ALL_ARTIFACTS_IDENTIFIED ) &&
			global.contains( Badge.ALL_POTIONS_IDENTIFIED ) &&
			global.contains( Badge.ALL_SCROLLS_IDENTIFIED )) {
			
			displayBadge( Badge.ALL_ITEMS_IDENTIFIED );
		}
	}
	
	public static void validateDeathFromFire() {
		Badge badge = Badge.DEATH_FROM_FIRE;
		local.add( badge );
		displayBadge( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromPoison() {
		Badge badge = Badge.DEATH_FROM_POISON;
		local.add( badge );
		displayBadge( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromGas() {
		Badge badge = Badge.DEATH_FROM_GAS;
		local.add( badge );
		displayBadge( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromHunger() {
		Badge badge = Badge.DEATH_FROM_HUNGER;
		local.add( badge );
		displayBadge( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromGlyph() {
		Badge badge = Badge.DEATH_FROM_GLYPH;
		local.add( badge );
		displayBadge( badge );

		validateYASD();
	}
	
	public static void validateDeathFromFalling() {
		Badge badge = Badge.DEATH_FROM_FALLING;
		local.add( badge );
		displayBadge( badge );

		validateYASD();
	}
	
	private static void validateYASD() {
		if (global.contains( Badge.DEATH_FROM_FIRE ) &&
			global.contains( Badge.DEATH_FROM_POISON ) &&
			global.contains( Badge.DEATH_FROM_GAS ) &&
			global.contains( Badge.DEATH_FROM_HUNGER) &&
			global.contains( Badge.DEATH_FROM_GLYPH) &&
			global.contains( Badge.DEATH_FROM_FALLING)) {
			
			Badge badge = Badge.YASD;
			displayBadge( badge );
		}
	}
	
	public static void validateBossSlain() {
		Badge badge = null;
		switch (Dungeon.depth) {
		case 5:
			badge = Badge.BOSS_SLAIN_1;
			break;
		case 10:
			badge = Badge.BOSS_SLAIN_2;
			break;
		case 15:
			badge = Badge.BOSS_SLAIN_3;
			break;
		case 20:
			badge = Badge.BOSS_SLAIN_4;
			break;
		}
		
		if (badge != null) {
			local.add( badge );
			displayBadge( badge );
			
			if (badge == Badge.BOSS_SLAIN_1) {
				switch (Dungeon.hero.heroClass) {
				case WARRIOR:
					badge = Badge.BOSS_SLAIN_1_WARRIOR;
					break;
				case MAGE:
					badge = Badge.BOSS_SLAIN_1_MAGE;
					break;
				case ROGUE:
					badge = Badge.BOSS_SLAIN_1_ROGUE;
					break;
				case HUNTRESS:
					badge = Badge.BOSS_SLAIN_1_HUNTRESS;
					break;
				}
				local.add( badge );
				addGlobal(badge);
				
				if (global.contains( Badge.BOSS_SLAIN_1_WARRIOR ) &&
					global.contains( Badge.BOSS_SLAIN_1_MAGE ) &&
					global.contains( Badge.BOSS_SLAIN_1_ROGUE ) &&
					global.contains( Badge.BOSS_SLAIN_1_HUNTRESS)) {
					
					badge = Badge.BOSS_SLAIN_1_ALL_CLASSES;
					if (!global.contains( badge )) {
						displayBadge( badge );
					}
				}
			} else
			if (badge == Badge.BOSS_SLAIN_3) {
				switch (Dungeon.hero.subClass) {
				case GLADIATOR:
					badge = Badge.BOSS_SLAIN_3_GLADIATOR;
					break;
				case BERSERKER:
					badge = Badge.BOSS_SLAIN_3_BERSERKER;
					break;
				case WARLOCK:
					badge = Badge.BOSS_SLAIN_3_WARLOCK;
					break;
				case BATTLEMAGE:
					badge = Badge.BOSS_SLAIN_3_BATTLEMAGE;
					break;
				case FREERUNNER:
					badge = Badge.BOSS_SLAIN_3_FREERUNNER;
					break;
				case ASSASSIN:
					badge = Badge.BOSS_SLAIN_3_ASSASSIN;
					break;
				case SNIPER:
					badge = Badge.BOSS_SLAIN_3_SNIPER;
					break;
				case WARDEN:
					badge = Badge.BOSS_SLAIN_3_WARDEN;
					break;
				default:
					return;
				}
				local.add( badge );
				addGlobal(badge);
				
				if (global.contains( Badge.BOSS_SLAIN_3_GLADIATOR ) &&
					global.contains( Badge.BOSS_SLAIN_3_BERSERKER ) &&
					global.contains( Badge.BOSS_SLAIN_3_WARLOCK ) &&
					global.contains( Badge.BOSS_SLAIN_3_BATTLEMAGE ) &&
					global.contains( Badge.BOSS_SLAIN_3_FREERUNNER ) &&
					global.contains( Badge.BOSS_SLAIN_3_ASSASSIN ) &&
					global.contains( Badge.BOSS_SLAIN_3_SNIPER ) &&
					global.contains( Badge.BOSS_SLAIN_3_WARDEN )) {
					
					badge = Badge.BOSS_SLAIN_3_ALL_SUBCLASSES;
					if (!global.contains( badge )) {
						displayBadge( badge );
					}
				}
			}
		}
	}
	
	public static void validateMastery() {
		
		Badge badge = null;
		switch (Dungeon.hero.heroClass) {
		case WARRIOR:
			badge = Badge.MASTERY_WARRIOR;
			break;
		case MAGE:
			badge = Badge.MASTERY_MAGE;
			break;
		case ROGUE:
			badge = Badge.MASTERY_ROGUE;
			break;
		case HUNTRESS:
			badge = Badge.MASTERY_HUNTRESS;
			break;
		}
		
		addGlobal(badge);
	}

	public static void validateRatmogrify(){
		addGlobal(Badge.FOUND_RATMOGRIFY);
	}
	
	public static void validateMageUnlock(){
		if (Statistics.upgradesUsed >= 1 && !global.contains(Badge.UNLOCK_MAGE)){
			displayBadge( Badge.UNLOCK_MAGE );
		}
	}
	
	public static void validateRogueUnlock(){
		if (Statistics.sneakAttacks >= 10 && !global.contains(Badge.UNLOCK_ROGUE)){
			displayBadge( Badge.UNLOCK_ROGUE );
		}
	}
	
	public static void validateHuntressUnlock(){
		if (Statistics.thrownAssists >= 15 && !global.contains(Badge.UNLOCK_HUNTRESS)){
			displayBadge( Badge.UNLOCK_HUNTRESS );
		}
	}
	
	public static void validateMasteryCombo( int n ) {
		if (!local.contains( Badge.MASTERY_COMBO ) && n == 10) {
			Badge badge = Badge.MASTERY_COMBO;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateVictory() {

		Badge badge = Badge.VICTORY;
		displayBadge( badge );

		switch (Dungeon.hero.heroClass) {
		case WARRIOR:
			badge = Badge.VICTORY_WARRIOR;
			break;
		case MAGE:
			badge = Badge.VICTORY_MAGE;
			break;
		case ROGUE:
			badge = Badge.VICTORY_ROGUE;
			break;
		case HUNTRESS:
			badge = Badge.VICTORY_HUNTRESS;
			break;
		}
		local.add( badge );
		addGlobal(badge);
		
		if (global.contains( Badge.VICTORY_WARRIOR ) &&
			global.contains( Badge.VICTORY_MAGE ) &&
			global.contains( Badge.VICTORY_ROGUE ) &&
			global.contains( Badge.VICTORY_HUNTRESS )) {
			
			badge = Badge.VICTORY_ALL_CLASSES;
			displayBadge( badge );
		}
	}

	public static void validateNoKilling() {
		if (!local.contains( Badge.NO_MONSTERS_SLAIN ) && Statistics.completedWithNoKilling) {
			Badge badge = Badge.NO_MONSTERS_SLAIN;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateGrimWeapon() {
		if (!local.contains( Badge.GRIM_WEAPON )) {
			Badge badge = Badge.GRIM_WEAPON;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateGamesPlayed() {
		Badge badge = null;
		if (Rankings.INSTANCE.totalNumber >= 10) {
			badge = Badge.GAMES_PLAYED_1;
		}
		if (Rankings.INSTANCE.totalNumber >= 50) {
			badge = Badge.GAMES_PLAYED_2;
		}
		if (Rankings.INSTANCE.totalNumber >= 250) {
			badge = Badge.GAMES_PLAYED_3;
		}
		if (Rankings.INSTANCE.totalNumber >= 1000) {
			badge = Badge.GAMES_PLAYED_4;
		}
		
		displayBadge( badge );
	}
	
	//necessary in order to display the happy end badge in the surface scene
	public static void silentValidateHappyEnd() {
		if (!local.contains( Badge.HAPPY_END )){
			local.add( Badge.HAPPY_END );
		}
	}
	
	public static void validateHappyEnd() {
		displayBadge( Badge.HAPPY_END );
	}

	public static void validateChampion( int challenges ) {
		if (challenges == 0) return;
		Badge badge = null;
		if (challenges >= 1) {
			badge = Badge.CHAMPION_1;
		}
		if (challenges >= 3){
			addGlobal(badge);
			badge = Badge.CHAMPION_2;
		}
		if (challenges >= 6){
			addGlobal(badge);
			badge = Badge.CHAMPION_3;
		}
		local.add(badge);
		displayBadge( badge );
	}
	
	private static void displayBadge( Badge badge ) {
		
		if (badge == null) {
			return;
		}
		
		if (!addGlobal(badge)) {
			
			if (!badge.meta) {
				GLog.h( Messages.get(Badges.class, "endorsed", badge.desc()) );
			}
			
		} else {
			
			if (badge.meta) {
				GLog.h( Messages.get(Badges.class, "new_super", badge.desc()) );
			} else {
				GLog.h( Messages.get(Badges.class, "new", badge.desc()) );
			}
			PixelScene.showBadge( badge );
		}
	}
	
	public static boolean isUnlocked( Badge badge ) {
		return global.contains( badge );
	}
	
	public static HashSet<Badge> allUnlocked(){
		loadGlobal();
		return new HashSet<>(global);
	}
	
	public static void disown( Badge badge ) {
		loadGlobal();
		global.remove( badge );
		saveNeeded = true;
	}
	
	public static boolean addGlobal( Badge badge ){
		boolean added = !global.contains(badge);
		if (added){
			global.add( badge );
			saveNeeded = true;
			if (!badge.gamerzillaName.isEmpty())
				gamerzilla.SetTrophy(gameid, badge.gamerzillaName);
		}
		return added;
	}

	public static List<Badge> filterReplacedBadges( boolean global ) {

		ArrayList<Badge> badges = new ArrayList<>(global ? Badges.global : Badges.local);

		Iterator<Badge> iterator = badges.iterator();
		while (iterator.hasNext()) {
			Badge badge = iterator.next();
			if ((!global && badge.meta) || badge.image == -1) {
				iterator.remove();
			}
		}

		Collections.sort(badges);

		return filterReplacedBadges(badges);

	}

	private static final Badge[][] tierBadgeReplacements = new Badge[][]{
			{Badge.MONSTERS_SLAIN_1, Badge.MONSTERS_SLAIN_2, Badge.MONSTERS_SLAIN_3, Badge.MONSTERS_SLAIN_4},
			{Badge.GOLD_COLLECTED_1, Badge.GOLD_COLLECTED_2, Badge.GOLD_COLLECTED_3, Badge.GOLD_COLLECTED_4},
			{Badge.ITEM_LEVEL_1, Badge.ITEM_LEVEL_2, Badge.ITEM_LEVEL_3, Badge.ITEM_LEVEL_4},
			{Badge.LEVEL_REACHED_1, Badge.LEVEL_REACHED_2, Badge.LEVEL_REACHED_3, Badge.LEVEL_REACHED_4},
			{Badge.STRENGTH_ATTAINED_1, Badge.STRENGTH_ATTAINED_2, Badge.STRENGTH_ATTAINED_3, Badge.STRENGTH_ATTAINED_4},
			{Badge.FOOD_EATEN_1, Badge.FOOD_EATEN_2, Badge.FOOD_EATEN_3, Badge.FOOD_EATEN_4},
			{Badge.POTIONS_COOKED_1, Badge.POTIONS_COOKED_2, Badge.POTIONS_COOKED_3, Badge.POTIONS_COOKED_4 },
			{Badge.BOSS_SLAIN_1, Badge.BOSS_SLAIN_2, Badge.BOSS_SLAIN_3, Badge.BOSS_SLAIN_4},
			{Badge.GAMES_PLAYED_1, Badge.GAMES_PLAYED_2, Badge.GAMES_PLAYED_3, Badge.GAMES_PLAYED_4},
			{Badge.CHAMPION_1, Badge.CHAMPION_2, Badge.CHAMPION_3}
	};

	private static final Badge[][] metaBadgeReplacements = new Badge[][]{
			{Badge.DEATH_FROM_FIRE, Badge.YASD},
			{Badge.DEATH_FROM_GAS, Badge.YASD},
			{Badge.DEATH_FROM_HUNGER, Badge.YASD},
			{Badge.DEATH_FROM_POISON, Badge.YASD},
			{Badge.DEATH_FROM_GLYPH, Badge.YASD},
			{Badge.DEATH_FROM_FALLING, Badge.YASD },

			{Badge.ALL_WEAPONS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_ARMOR_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_WANDS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_RINGS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_ARTIFACTS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_POTIONS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_SCROLLS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED}
	};
	
	public static List<Badge> filterReplacedBadges( List<Badge> badges ) {

		leaveBest( badges, Badge.MONSTERS_SLAIN_1, Badge.MONSTERS_SLAIN_2, Badge.MONSTERS_SLAIN_3, Badge.MONSTERS_SLAIN_4 );
		leaveBest( badges, Badge.GOLD_COLLECTED_1, Badge.GOLD_COLLECTED_2, Badge.GOLD_COLLECTED_3, Badge.GOLD_COLLECTED_4 );
		leaveBest( badges, Badge.BOSS_SLAIN_1, Badge.BOSS_SLAIN_2, Badge.BOSS_SLAIN_3, Badge.BOSS_SLAIN_4 );
		leaveBest( badges, Badge.LEVEL_REACHED_1, Badge.LEVEL_REACHED_2, Badge.LEVEL_REACHED_3, Badge.LEVEL_REACHED_4 );
		leaveBest( badges, Badge.STRENGTH_ATTAINED_1, Badge.STRENGTH_ATTAINED_2, Badge.STRENGTH_ATTAINED_3, Badge.STRENGTH_ATTAINED_4 );
		leaveBest( badges, Badge.FOOD_EATEN_1, Badge.FOOD_EATEN_2, Badge.FOOD_EATEN_3, Badge.FOOD_EATEN_4 );
		leaveBest( badges, Badge.ITEM_LEVEL_1, Badge.ITEM_LEVEL_2, Badge.ITEM_LEVEL_3, Badge.ITEM_LEVEL_4 );
		leaveBest( badges, Badge.POTIONS_COOKED_1, Badge.POTIONS_COOKED_2, Badge.POTIONS_COOKED_3, Badge.POTIONS_COOKED_4 );
		leaveBest( badges, Badge.GAMES_PLAYED_1, Badge.GAMES_PLAYED_2, Badge.GAMES_PLAYED_3, Badge.GAMES_PLAYED_4 );
		leaveBest( badges, Badge.CHAMPION_1, Badge.CHAMPION_2, Badge.CHAMPION_3 );

		for (Badge[] tierReplace : tierBadgeReplacements){
			leaveBest( badges, tierReplace );
		}

		for (Badge[] metaReplace : metaBadgeReplacements){
			leaveBest( badges, metaReplace );
		}
		
		return badges;
	}
	
	private static void leaveBest( Collection<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}

	public static List<Badge> filterHigherIncrementalBadges(List<Badges.Badge> badges ) {

		for (Badge[] tierReplace : tierBadgeReplacements){
			leaveWorst( badges, tierReplace );
		}

		Collections.sort( badges );

		return badges;
	}

	private static void leaveWorst( Collection<Badge> list, Badge...badges ) {
		for (int i=0; i < badges.length; i++) {
			if (list.contains( badges[i])) {
				for (int j=i+1; j < badges.length; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}

	public static Collection<Badge> addReplacedBadges(Collection<Badges.Badge> badges ) {

		for (Badge[] tierReplace : tierBadgeReplacements){
			addLower( badges, tierReplace );
		}

		for (Badge[] metaReplace : metaBadgeReplacements){
			addLower( badges, metaReplace );
		}

		return badges;
	}

	private static void addLower( Collection<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.add( badges[j] );
				}
				break;
			}
		}
	}
}
