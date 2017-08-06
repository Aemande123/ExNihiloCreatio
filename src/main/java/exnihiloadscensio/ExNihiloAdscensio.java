package exnihiloadscensio;

import exnihiloadscensio.capabilities.ENCapabilities;
import exnihiloadscensio.command.CommandReloadConfig;
import exnihiloadscensio.config.Config;
import exnihiloadscensio.enchantments.ENEnchantments;
import exnihiloadscensio.entities.ENEntities;
import exnihiloadscensio.handlers.HandlerCrook;
import exnihiloadscensio.handlers.HandlerHammer;
import exnihiloadscensio.networking.PacketHandler;
import exnihiloadscensio.proxy.CommonProxy;
import exnihiloadscensio.registries.*;
import exnihiloadscensio.registries.manager.ExNihiloDefaultRecipes;
import exnihiloadscensio.util.LogUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@Mod(modid = ExNihiloAdscensio.MODID, name = "Ex Nihilo Adscensio", version = "0.1.0")
public class ExNihiloAdscensio {

    public static final String MODID = "exnihiloadscensio";

    @SidedProxy(serverSide = "exnihiloadscensio.proxy.ServerProxy", clientSide = "exnihiloadscensio.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Instance(MODID)
    public static ExNihiloAdscensio instance;

    public static File configDirectory;

    public static boolean configsLoaded = false;

    public static ExNihiloDefaultRecipes defaultRecipes;
    public static CreativeTabs tabExNihilo = new CreativeTabs("exNihilo") {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(Item.getItemFromBlock(ModBlocks.sieve), 1);
        }
    };

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        LogUtil.setup();

        configDirectory = new File(event.getModConfigurationDirectory(), "exnihiloadscensio");
        configDirectory.mkdirs();

        Config.doNormalConfig(new File(configDirectory, "ExNihiloAdscensio.cfg"));


        OreRegistry.loadJson(new File(configDirectory, "OreRegistry.json"));
        proxy.registerConfigs(configDirectory);
        loadConfigs();

        ENCapabilities.init();
        ENEntities.init();
        ENEnchantments.init();

        PacketHandler.initPackets();

        defaultRecipes = new ExNihiloDefaultRecipes();

        MinecraftForge.EVENT_BUS.register(new HandlerHammer());

        MinecraftForge.EVENT_BUS.register(new HandlerCrook());

        if (Config.enableBarrels) {
            BarrelModeRegistry.registerDefaults();
        }
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);

        Recipes.init();
        OreRegistry.doRecipes();

    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        if (Loader.isModLoaded("tconstruct") && Config.doTICCompat) {
            // CompatTConstruct.postInit();
        }
        if (Loader.isModLoaded("EnderIO") && Config.doEnderIOCompat) {
            // CompatEIO.postInit();
        }
    }

    public static void loadConfigs() {
        configsLoaded = true;

        CompostRegistry.loadJson(new File(configDirectory, "CompostRegistry.json"));
        HammerRegistry.loadJson(new File(configDirectory, "HammerRegistry.json"));
        FluidBlockTransformerRegistry.loadJson(new File(configDirectory, "FluidBlockTransformerRegistry.json"));
        FluidOnTopRegistry.loadJson(new File(configDirectory, "FluidOnTopRegistry.json"));
        HeatRegistry.loadJson(new File(configDirectory, "HeatRegistry.json"));
        CrucibleRegistry.loadJson(new File(configDirectory, "CrucibleRegistry.json"));
        SieveRegistry.loadJson(new File(configDirectory, "SieveRegistry.json"));
        CrookRegistry.loadJson(new File(configDirectory, "CrookRegistry.json"));
        FluidTransformRegistry.loadJson(new File(configDirectory, "FluidTransformRegistry.json"));
        BarrelLiquidBlacklistRegistry.loadJson(new File(configDirectory, "BarrelLiquidBlacklistRegistry.json"));

    }

    @EventHandler
    public static void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandReloadConfig());
    }

    @EventHandler
    public static void modMapping(FMLModIdMappingEvent event) {

    }

}
