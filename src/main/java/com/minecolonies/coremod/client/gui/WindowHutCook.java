package com.minecolonies.coremod.client.gui;

import com.google.common.collect.ImmutableList;
import com.minecolonies.api.crafting.ItemStorage;
import com.minecolonies.api.util.LanguageHandler;
import com.minecolonies.api.util.constant.Constants;
import com.minecolonies.blockout.views.View;
import com.minecolonies.coremod.colony.ColonyManager;
import com.minecolonies.coremod.colony.IColonyManager;
import com.minecolonies.coremod.colony.buildings.workerbuildings.BuildingCook;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.minecolonies.api.util.constant.TranslationConstants.COM_MINECOLONIES_REQUESTS_BURNABLE;

/**
 * Cook window class. Specifies the extras the composter has for its list.
 */
public class WindowHutCook extends AbstractHutFilterableLists
{
    /**
     * The resource string.
     */
    private static final String RESOURCE_STRING = ":gui/windowhutcook.xml";

    /**
     * View containing the list.
     */
    private static final String PAGE_ITEMS_VIEW = "fuel";

    /**
     * Constructor for the window of the worker building.
     *
     * @param building class extending
     */
    public WindowHutCook(final BuildingCook.View building)
    {
        super(building, Constants.MOD_ID + RESOURCE_STRING);
        final ViewFilterableList window = new ViewFilterableList(findPaneOfTypeByID(PAGE_ITEMS_VIEW, View.class),
          this,
          building,
          LanguageHandler.format(COM_MINECOLONIES_REQUESTS_BURNABLE),
          PAGE_ITEMS_VIEW,
          false);
        views.put(PAGE_ITEMS_VIEW, window);
    }

    /**
     * The classic block list.
     * @param filterPredicate the predicate filter.
     * @param id the id of the specific predicate.
     * @return the list of itemStorages.
     */
    public List<? extends ItemStorage> getBlockList(final Predicate<ItemStack> filterPredicate, final String id)
    {
        return ImmutableList.copyOf(IColonyManager.getInstance().getCompatibilityManager().getFuel().stream().filter(item -> filterPredicate.test(item.getItemStack())).collect(Collectors.toList()));
    }

    @Override
    public String getBuildingName()
    {
        return "com.minecolonies.coremod.gui.workerHuts.cook";
    }
}
