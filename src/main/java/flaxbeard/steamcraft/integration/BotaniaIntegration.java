package flaxbeard.steamcraft.integration;

import java.util.UUID;

import com.google.common.collect.Multimap;

import thaumcraft.common.config.ConfigItems;
import vazkii.botania.common.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import flaxbeard.steamcraft.Steamcraft;
import flaxbeard.steamcraft.SteamcraftItems;
import flaxbeard.steamcraft.api.CrucibleLiquid;
import flaxbeard.steamcraft.api.SteamcraftRegistry;
import flaxbeard.steamcraft.api.book.BookRecipeRegistry;
import flaxbeard.steamcraft.api.exosuit.ExosuitPlate;
import flaxbeard.steamcraft.api.exosuit.UtilPlates;
import flaxbeard.steamcraft.item.ItemExosuitArmor;
import flaxbeard.steamcraft.item.ItemExosuitUpgrade;
import flaxbeard.steamcraft.item.ItemExosuitArmor.ExosuitSlot;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BotaniaIntegration {
    public static Item floralLaurel;

    public static void addItems() {
    	//floralLaurel = new ItemExosuitUpgrade(ExosuitSlot.headHelm, "steamcraft:textures/models/armor/floralLaurel.png",null,5).setCreativeTab(Steamcraft.tab).setUnlocalizedName("steamcraft:floralLaurel").setTextureName("steamcraft:floralLaurel");
		//GameRegistry.registerItem(floralLaurel, "floralLaurel");

    }

	public static void displayThings(MovingObjectPosition pos, RenderGameOverlayEvent.Post event) {
//		Minecraft mc = Minecraft.getMinecraft();
//		Block block = mc.theWorld.getBlock(pos.blockX, pos.blockY, pos.blockZ);
//		if(block instanceof IWandHUD)
//			((IWandHUD) block).renderHUD(mc, event.resolution, mc.theWorld, pos.blockX, pos.blockY, pos.blockZ);
	}
	
	public static Item twigWand() {
		return null;
		//return ModItems.twigWand;
	}

	public static void addBotaniaLiquid() {
		CrucibleLiquid liquidTerrasteel = new CrucibleLiquid("terrasteel", new ItemStack(ModItems.manaResource,1,4), new ItemStack(SteamcraftItems.steamcraftPlate,1,6), null, null,64,191,13);
		SteamcraftRegistry.liquids.add(liquidTerrasteel);
		
		SteamcraftRegistry.registerSmeltThingOredict("ingotTerrasteel", liquidTerrasteel, 9);
		SteamcraftRegistry.registerSmeltThingOredict("nuggetTerrasteel", liquidTerrasteel, 1);
		SteamcraftRegistry.registerSmeltThingOredict("plateTerrasteel", liquidTerrasteel, 6);
		SteamcraftRegistry.addExosuitPlate(new ExosuitPlate("Terrasteel","plateTerrasteel","Terrasteel","Terrasteel","steamcraft.plate.terrasteel"));
		
		CrucibleLiquid liquidElementium = new CrucibleLiquid("elementium", new ItemStack(ModItems.manaResource,1,7), new ItemStack(SteamcraftItems.steamcraftPlate,1,7), null, null,230,66,247);
		SteamcraftRegistry.liquids.add(liquidElementium);

		SteamcraftRegistry.registerSmeltThing(ModItems.manaResource,7, liquidElementium, 9);
		SteamcraftRegistry.registerSmeltThingOredict("ingotElementium", liquidElementium, 9);
		SteamcraftRegistry.registerSmeltThingOredict("nuggetElementium", liquidElementium, 1);
		SteamcraftRegistry.registerSmeltThingOredict("plateElementium", liquidElementium, 6);
		SteamcraftRegistry.addExosuitPlate(new ExosuitPlate("Elementium","plateElementium","Elementum","Elementum","steamcraft.plate.elementum"));
	}

	public static Multimap addModifiers(Multimap map, ItemStack stack, int armorType) {
		if ((((ItemExosuitArmor)stack.getItem()).hasPlates(stack) && UtilPlates.getPlate(stack.stackTagCompound.getString("plate")).getIdentifier() == "Terrasteel")) {
			int hp = 1;
			switch (armorType) {
				case 0:
					hp = 2;
					break;
				case 1:
					hp = 4;
					break;
				case 2:
					hp = 2;
					break;
				case 4:
					hp = 2;
			}
			map.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), new AttributeModifier(new UUID(171328 /** Random number **/, armorType), "Armor modifier" + armorType, hp, 0));
		}
		return map;
	}

}
