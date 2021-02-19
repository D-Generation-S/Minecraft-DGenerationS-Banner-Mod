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

@Mod(DGenerationBannerMod.MODID)
public class DGenerationBannerMod {
	public static final String MODID = "dgenerationsbannermod";
	
	private final Logger log;  
    private final ItemGroup tab;
    private final RegistryHelper registry;
    
    
    public final BannerPattern dGenerationS;
    public final BannerPattern xanatos;
	
    //private final ItemGroup tab;
    //private final RegistryHelper registry;
	
	//this.registry = new RegistryHelper(MOD_ID, log).withItemGroup(tab);
	
	public DGenerationBannerMod() {
		this.log = LogManager.getLogger(MODID);  
		this.tab = new ItemGroupBase(MODID, Items.MOJANG_BANNER_PATTERN);
		this.registry = new RegistryHelper(MODID, log).withItemGroup(tab);
		
		this.dGenerationS = this.registry.banners.registerItemPattern("dgenerations", Rarity.EPIC);
		this.xanatos = this.registry.banners.registerItemPattern("xanatos", Rarity.EPIC);
        this.addRareTrade(this.xanatos);
		
		this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	private void addBasicTrade(BannerPattern... patterns) {
    	
    	for (BannerPattern pattern : patterns) {
    		
    		this.addBasicTrade(pattern);
    	}
    }
    
    private void addBasicTrade(BannerPattern pattern) {
    	
    	final Item item = this.registry.banners.getStencilItem(pattern);
    	
    	if (item != null) {
    		
        	this.registry.trades.addBasicWanderingTrade(new BasicTrade(12, new ItemStack(item), 1, 12));
    	}
    	
    	else {
    		
    		throw new IllegalStateException("The pattern " + pattern.getFileName() + " has no stencil item.");
    	}
    }
    
    private void addRareTrade(BannerPattern... patterns) {
    	
    	for (BannerPattern pattern : patterns) {
    		
    		this.addRareTrade(pattern);
    	}
    }
    
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

