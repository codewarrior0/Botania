/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 19, 2014, 3:28:21 PM (GMT)]
 */
package vazkii.botania.common.item.material;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import vazkii.botania.api.item.IPetalApothecary;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.Item16Colors;
import vazkii.botania.common.lib.LibItemNames;

public class ItemPetal extends Item16Colors implements IFlowerComponent {

	public ItemPetal() {
		super(LibItemNames.PETAL);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, BlockPos pos, EnumFacing side, float par8, float par9, float par10) {
		ItemStack stackToPlace = new ItemStack(ModBlocks.buriedPetals, 1, par1ItemStack.getItemDamage());
		stackToPlace.onItemUse(par2EntityPlayer, par3World, pos, side, par8, par9, par10);

		if(stackToPlace.stackSize == 0) {
			if(!par2EntityPlayer.capabilities.isCreativeMode)
				par1ItemStack.stackSize--;

			return true;
		}
		return false;
	}

	@Override
	public boolean canFit(ItemStack stack, IPetalApothecary apothecary) {
		return true;
	}

	@Override
	public int getParticleColor(ItemStack stack) {
		return getColorFromItemStack(stack, 0);
	}
}
