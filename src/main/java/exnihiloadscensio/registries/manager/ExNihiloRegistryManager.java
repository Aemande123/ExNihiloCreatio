package exnihiloadscensio.registries.manager;

import java.util.ArrayList;

import lombok.Getter;

public class ExNihiloRegistryManager {
	
	@Getter
	private static ArrayList<ISieveDefaultRegistryProvider> defaultSieveRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<IHammerDefaultRegistryProvider> defaultHammerRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<ICompostDefaultRegistryProvider> defaultCompostRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<ICrookDefaultRegistryProvider> defaultCrookRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<ICrucibleDefaultRegistryProvider> defaultCrucibleRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<IFluidBlockDefaultRegistryProvider> defaultFluidBlockRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<IFluidTransformDefaultRegistryProvider> defaultFluidTransformRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<IFluidOnTopDefaultRegistryProvider> defaultFluidOnTopRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<IHeatDefaultRegistryProvider> defaultHeatRecipeHandlers = new ArrayList<>();
	@Getter
	private static ArrayList<IOreDefaultRegistryProvider> defaultOreRecipeHandlers = new ArrayList<>();
	
	public static void registerSieveDefaultRecipeHandler(ISieveDefaultRegistryProvider provider) {
		defaultSieveRecipeHandlers.add(provider);
	}
	
	public static void registerHammerDefaultRecipeHandler(IHammerDefaultRegistryProvider provider) {
		defaultHammerRecipeHandlers.add(provider);
	}
	
	public static void registerCompostDefaultRecipeHandler(ICompostDefaultRegistryProvider provider) {
		defaultCompostRecipeHandlers.add(provider);
	}
	
	public static void registerCrookDefaultRecipeHandler(ICrookDefaultRegistryProvider provider) {
		defaultCrookRecipeHandlers.add(provider);
	}
	
	public static void registerCrucibleDefaultRecipeHandler(ICrucibleDefaultRegistryProvider provider) {
		defaultCrucibleRecipeHandlers.add(provider);
	}
	
	public static void registerFluidBlockDefaultRecipeHandler(IFluidBlockDefaultRegistryProvider provider) {
		defaultFluidBlockRecipeHandlers.add(provider);
	}
	
	public static void registerFluidTransformDefaultRecipeHandler(IFluidTransformDefaultRegistryProvider provider) {
		defaultFluidTransformRecipeHandlers.add(provider);
	}

	public static void registerFluidOnTopDefaultRecipeHandler(IFluidOnTopDefaultRegistryProvider provider) {
		defaultFluidOnTopRecipeHandlers.add(provider);
	}
	
	public static void registerHeatDefaultRecipeHandler(IHeatDefaultRegistryProvider provider) {
		defaultHeatRecipeHandlers.add(provider);
	}
	
	public static void registerOreDefaultRecipeHandler(IOreDefaultRegistryProvider provider) {
		defaultOreRecipeHandlers.add(provider);
	}
}
