package org.l2j.gameserver.network.l2.s2c;

import org.l2j.gameserver.model.Player;
import org.l2j.gameserver.model.items.ItemInstance;

public class GMViewItemListPacket extends L2GameServerPacket
{
	private int _size;
	private ItemInstance[] _items;
	private int _limit;
	private String _name;
	private Player _player;

	public GMViewItemListPacket(Player cha, ItemInstance[] items, int size)
	{
		_size = size;
		_items = items;
		_name = cha.getName();
		_limit = cha.getInventoryLimit();
		_player = cha;
	}

	@Override
	protected final void writeImpl()
	{
		writeString(_name);
		writeInt(_limit); //c4?
		writeShort(1); // show window ??

		writeShort(_size);
		for(ItemInstance temp : _items)
		{
			if(!temp.getTemplate().isQuest())
				writeItemInfo(_player, temp);
		}
	}
}