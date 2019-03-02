package org.l2j.gameserver.network.l2.c2s;

import java.nio.ByteBuffer;
import java.util.concurrent.CopyOnWriteArrayList;

import org.l2j.gameserver.model.Player;
import org.l2j.gameserver.model.Request;
import org.l2j.gameserver.model.Request.L2RequestType;
import org.l2j.gameserver.model.items.TradeItem;
import org.l2j.gameserver.network.l2.components.SystemMsg;
import org.l2j.gameserver.network.l2.s2c.SystemMessagePacket;
import org.l2j.gameserver.network.l2.s2c.TradeDonePacket;
import org.l2j.gameserver.network.l2.s2c.TradeStartPacket;

public class AnswerTradeRequest extends L2GameClientPacket
{
	private int _response;

	@Override
	protected void readImpl(ByteBuffer buffer)
	{
		_response = buffer.getInt();
	}

	@Override
	protected void runImpl()
	{
		Player activeChar = client.getActiveChar();
		if(activeChar == null)
			return;

		Request request = activeChar.getRequest();
		if(request == null)
		{
			activeChar.sendActionFailed();
			return;
		}

		if(request.isTypeOf(L2RequestType.TRADE_REQUEST))
		{
			if(!request.isInProgress())
			{
				request.cancel();
				activeChar.sendActionFailed();
				return;
			}

			if(activeChar.isOutOfControl())
			{
				request.cancel();
				activeChar.sendActionFailed();
				return;
			}

			Player requestor = request.getRequestor();
			if(requestor == null)
			{
				request.cancel();
				activeChar.sendPacket(SystemMsg.THAT_PLAYER_IS_NOT_ONLINE);
				activeChar.sendActionFailed();
				return;
			}

			if(requestor.getRequest() != request)
			{
				request.cancel();
				activeChar.sendActionFailed();
				return;
			}

			// отказ
			if(_response == 0)
			{
				request.cancel();
				requestor.sendPacket(new SystemMessagePacket(SystemMsg.C1_HAS_DENIED_YOUR_REQUEST_TO_TRADE).addString(activeChar.getName()));
				return;
			}

			if(!activeChar.checkInteractionDistance(requestor))
			{
				request.cancel();
				activeChar.sendPacket(SystemMsg.YOUR_TARGET_IS_OUT_OF_RANGE);
				return;
			}

			if(requestor.isActionsDisabled())
			{
				request.cancel();
				activeChar.sendPacket(new SystemMessagePacket(SystemMsg.C1_IS_ON_ANOTHER_TASK).addString(requestor.getName()));
				activeChar.sendActionFailed();
				return;
			}

			if(requestor.isInTrainingCamp())
			{
				request.cancel();
				activeChar.sendPacket(SystemMsg.YOU_CANNOT_REQUEST_TO_A_CHARACTER_WHO_IS_ENTERING_THE_TRAINING_CAMP);
				activeChar.sendActionFailed();
				return;
			}

			try
			{
				new Request(L2RequestType.TRADE, activeChar, requestor);

				requestor.setTradeList(new CopyOnWriteArrayList<TradeItem>());
				requestor.sendPacket(new SystemMessagePacket(SystemMsg.YOU_BEGIN_TRADING_WITH_C1).addString(activeChar.getName()), new TradeStartPacket(1, requestor, activeChar), new TradeStartPacket(2, requestor, activeChar));
				activeChar.setTradeList(new CopyOnWriteArrayList<TradeItem>());
				activeChar.sendPacket(new SystemMessagePacket(SystemMsg.YOU_BEGIN_TRADING_WITH_C1).addString(requestor.getName()), new TradeStartPacket(1, activeChar, requestor), new TradeStartPacket(2, activeChar, requestor));
			}
			finally
			{
				request.done();
			}
		}
		else if(request.isTypeOf(L2RequestType.TRADE))
		{
			if(!request.isInProgress())
			{
				request.cancel(TradeDonePacket.FAIL);
				activeChar.sendActionFailed();
				return;
			}

			if(activeChar.isOutOfControl())
			{
				request.cancel(TradeDonePacket.FAIL);
				activeChar.sendActionFailed();
				return;
			}

			Player parthner = request.getOtherPlayer(activeChar);
			if(parthner == null)
			{
				request.cancel(TradeDonePacket.FAIL);
				activeChar.sendPacket(SystemMsg.THAT_PLAYER_IS_NOT_ONLINE);
				activeChar.sendActionFailed();
				return;
			}

			if(parthner.getRequest() != request)
			{
				request.cancel(TradeDonePacket.FAIL);
				activeChar.sendActionFailed();
				return;
			}

			request.cancel(TradeDonePacket.FAIL);
			parthner.sendPacket(new SystemMessagePacket(SystemMsg.C1_HAS_CANCELLED_THE_TRADE).addString(activeChar.getName()));
		}
		else
		{
			request.cancel();
			activeChar.sendActionFailed();
			return;
		}
	}
}