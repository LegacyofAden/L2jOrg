package org.l2j.gameserver.network.l2.c2s;

import org.l2j.gameserver.model.Player;

public class RequestChangeAttributeCancel extends L2GameClientPacket
{
	@Override
	protected void readImpl()
	{
		//
	}

	@Override
	protected void runImpl()
	{
		Player activeChar = getClient().getActiveChar();
		if(activeChar == null)
			return;

		//TODO: [Bonux] Проверить, вдруг посылается какое-то сообщение.
		activeChar.sendActionFailed();
	}
}