package shadowcraft.shadow;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;

public class ContainerRefinery extends Container{

	protected TileEntityShadowRefinery tileEntity;

	public ContainerRefinery(final InventoryPlayer inventoryPlayer, final TileEntityShadowRefinery te){
		tileEntity = te;
		addSlotToContainer(new Slot(tileEntity, 0, 120, 33));
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player){
		return tileEntity.isUseableByPlayer(player);
	}

	protected void bindPlayerInventory(final InventoryPlayer inventoryPlayer){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + (i * 9) + 9, 8 + (j * 18), 84 + (i * 18)));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + (i * 18), 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(final int slot){
		ItemStack stack = null;
		final Slot slotObject = (Slot) inventorySlots.get(slot);

		if ((slotObject != null) && slotObject.getHasStack()) {
			final ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			if (slot == 0) {
				if (!mergeItemStack(stackInSlot, 1, inventorySlots.size(), true)) {
					return null;
				}

			}

			else if (!mergeItemStack(stackInSlot, 0, 1, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}
		}

		return stack;
	}

}
