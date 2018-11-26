package org.l2j.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.l2j.gameserver.model.Player;
import org.l2j.gameserver.model.items.ItemInfo;
import org.l2j.gameserver.model.items.ItemInstance;
import org.l2j.gameserver.model.items.Warehouse.ItemClassComparator;
import org.l2j.gameserver.model.items.Warehouse.WarehouseType;

public class WareHouseDepositListPacket extends L2GameServerPacket
{
	private int _whtype;
	private long _adena;
	private List<ItemInfo> _itemList;
	private int _depositedItemsCount;

	public WareHouseDepositListPacket(Player cha, WarehouseType whtype)
	{
		_whtype = whtype.ordinal();
		_adena = cha.getAdena();

		ItemInstance[] items = cha.getInventory().getItems();
		Arrays.sort(items, ItemClassComparator.getInstance());
		_itemList = new ArrayList<ItemInfo>(items.length);
		for(ItemInstance item : items)
			if(item.canBeStored(cha, _whtype == 1))
				_itemList.add(new ItemInfo(item, item.getTemplate().isBlocked(cha, item)));

		switch(whtype)
		{
			case PRIVATE:
				_depositedItemsCount = cha.getWarehouse().getSize();
				break;
			case FREIGHT:
				_depositedItemsCount = cha.getFreight().getSize();
				break;
			case CLAN:
			case CASTLE:
				_depositedItemsCount = cha.getClan().getWarehouse().getSize();
				break;
			default:
				_depositedItemsCount = 0;
				return;
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeShort(_whtype);
		writeLong(_adena);
		writeShort(_depositedItemsCount); //Количество вещей которые уже есть в банке.
		writeInt(0);//TODO [Bonux]
		writeShort(_itemList.size());
		for(ItemInfo item : _itemList)
		{
			writeItemInfo(item);
			writeInt(item.getObjectId());
		}
	}
}