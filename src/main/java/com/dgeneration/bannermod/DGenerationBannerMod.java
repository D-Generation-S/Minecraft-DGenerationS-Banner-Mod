package com.dgeneration.bannermod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.bookshelf.item.ItemGroupBase;
import net.darkhax.bookshelf.registry.RegistryHelper;

/**
 * The main entry point for this mod
 * This will register all the banners
 */
@Mod(DGenerationBannerMod.MODID)
public class DGenerationBannerMod {
	/**
	 * Name of the mod
	 */
	public static final String MODID = "dgenerationsbannermod";
	
	/**
	 * The logger to use
	 */
	public final Logger log;
	
	/**
	 * The item tab group to use
	 */
    private final ItemGroup tab;
    
    /**
     * Registry helper for this mod
     */
    private final RegistryHelper registry;
    
    /**
     * DGeneration-S Banner
     */
    public final BannerPattern dGenerationS;
    
    /**
     * Xanatos Banner
     */
    public final BannerPattern xanatos;
	
    /**
     * Create a new instance of this class
     */
	public DGenerationBannerMod() {
		this.log = LogManager.getLogger(MODID);  
		this.tab = new ItemGroupBase(MODID, Items.MOJANG_BANNER_PATTERN);
		this.registry = new RegistryHelper(MODID, log).withItemGroup(tab);
		
		this.dGenerationS = this.registry.banners.registerItemPattern("dgenerations", Rarity.EPIC);
		this.xanatos = this.registry.banners.registerItemPattern("xanatos", Rarity.EPIC);
		this.addBasicTrade();
        this.addRareTrade(this.xanatos, this.dGenerationS);
		
		this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	/**
	 * Add items for basic trading
	 * @param patterns All the items to add
	 */
	private void addBasicTrade(BannerPattern... patterns) {
    	
    	for (BannerPattern pattern : patterns) {
    		
    		this.addBasicTrade(pattern);
    	}
    }
    
	/**
	 * Add a single item for basic trading
	 * @param pattern The pattern to add
	 */
    private void addBasicTrade(BannerPattern pattern) {
    	
    	final Item item = this.registry.banners.getStencilItem(pattern);
    	
    	if (item != null) {
    		
        	this.registry.trades.addBasicWanderingTrade(new BasicTrade(12, new ItemStack(item), 1, 12));
    	}
    	
    	else {
    		
    		throw new IllegalStateException("The pattern " + pattern.getFileName() + " has no stencil item.");
    	}
    }
    
    /**
     * Add items for rare trading
     * @param patterns The patterns to add for rare trading
     */
    private void addRareTrade(BannerPattern... patterns) {
    	
    	for (BannerPattern pattern : patterns) {
    		
    		this.addRareTrade(pattern);
    	}
    }
    
    /**
     * Add item for rare trading
     * @param pattern The pattern to add for trading
     */
    private void addRareTrade(BannerPattern pattern) {
    	
    	final Item item = this.registry.banners.getStencilItem(pattern);
    	
    	if (item != null) {
    		
        	this.registry.trades.addRareWanderingTrade(new BasicTrade(12, new ItemStack(item), 1, 24));
    	}
    	
    	else {
    		
    		throw new IllegalStateException("The pattern " + pattern.getFileName() + " has no stencil item.");
    	}
    }
}

