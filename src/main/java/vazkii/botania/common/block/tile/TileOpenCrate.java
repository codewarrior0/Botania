/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [May 4, 2014, 12:51:05 PM (GMT)]
 */
package vazkii.botania.common.block.tile;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.MethodHandles;
import vazkii.botania.common.lib.LibBlockNames;

public class TileOpenCrate extends TileSimpleInventory {

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		if(oldState.getBlock() != newState.getBlock())
			return true;
			if(oldState.getBlock() != ModBlocks.openCrate || newState.getBlock() != ModBlocks.openCrate)
				return true;
			return oldState.getValue(BotaniaStateProps.CRATE_VARIANT) != newState.getValue(BotaniaStateProps.CRATE_VARIANT);
		}

		@Override
		public int getSizeInventory() {
			return 1;
		}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		boolean redstone = false;
		for(EnumFacing dir : EnumFacing.VALUES) {
			int redstoneSide = worldObj.getRedstonePower(pos.offset(dir), dir);
			if(redstoneSide > 0) {
				redstone = true;
				break;
			}
		}

		if(canEject()) {
			ItemStack stack = itemHandler.getStackInSlot(0);
			if(stack != null)
				eject(stack, redstone);
		}
	}

	public boolean canEject() {
		Block blockBelow = worldObj.getBlockState(pos.down()).getBlock();
		return blockBelow.isAir(worldObj, pos.down()) || blockBelow.getCollisionBoundingBox(worldObj, pos.down(), worldObj.getBlockState(pos.down())) == null;
	}

	public void eject(ItemStack stack, boolean redstone) {
		EntityItem item = new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5, stack);
		item.motionX = 0;
		item.motionY = 0;
		item.motionZ = 0;

		if(redstone) {
			try {
				MethodHandles.itemAge_setter.invokeExact(item, -200);
			} catch (Throwable ignored) {}
		}


		itemHandler.setStackInSlot(0, null);
		worldObj.spawnEntityInWorld(item);
	}

	public boolean onWanded(EntityPlayer player, ItemStack stack) {
		return false;
	}

	public int getSignal() {
		return 0;
	}
}
