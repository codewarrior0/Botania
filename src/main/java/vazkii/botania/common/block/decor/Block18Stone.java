/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Apr 10, 2015, 7:34:31 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.api.state.enums.FutureStoneVariant;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Block18Stone extends BlockMod implements ILexiconable {

	public Block18Stone() {
		super(Material.rock);
		setHardness(1.5F);
		setResistance(10F);
		setStepSound(soundTypeStone);
		setUnlocalizedName(LibBlockNames.STONE);
		setDefaultState(blockState.getBaseState().withProperty(BotaniaStateProps.FUTURESTONE_VARIANT, FutureStoneVariant.ANDESITE));
	}

	@Override
	public BlockState createBlockState() {
		return new BlockState(this, BotaniaStateProps.FUTURESTONE_VARIANT);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BotaniaStateProps.FUTURESTONE_VARIANT).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta >= FutureStoneVariant.values().length) {
			meta = 0;
		}
		return getDefaultState().withProperty(BotaniaStateProps.FUTURESTONE_VARIANT, FutureStoneVariant.values()[meta]);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for(int i = 0; i < 16; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public LexiconEntry getEntry(World world, BlockPos pos, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.stoneAlchemy;
	}

}
