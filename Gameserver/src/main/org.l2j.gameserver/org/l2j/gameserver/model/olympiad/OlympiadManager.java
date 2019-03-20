package org.l2j.gameserver.model.olympiad;

import org.l2j.gameserver.Config;
import org.l2j.gameserver.enums.CategoryType;
import org.l2j.gameserver.instancemanager.AntiFeedManager;
import org.l2j.gameserver.model.actor.instance.L2PcInstance;
import org.l2j.gameserver.network.SystemMessageId;
import org.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import org.l2j.gameserver.network.serverpackets.SystemMessage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DS
 */
public class OlympiadManager {
    private final Set<Integer> _nonClassBasedRegisters = ConcurrentHashMap.newKeySet();
    private final Map<Integer, Set<Integer>> _classBasedRegisters = new ConcurrentHashMap<>();

    private OlympiadManager() {
    }

    public final Set<Integer> getRegisteredNonClassBased() {
        return _nonClassBasedRegisters;
    }

    public final Map<Integer, Set<Integer>> getRegisteredClassBased() {
        return _classBasedRegisters;
    }

    protected final List<Set<Integer>> hasEnoughRegisteredClassed() {
        List<Set<Integer>> result = null;
        for (Map.Entry<Integer, Set<Integer>> classList : _classBasedRegisters.entrySet()) {
            if ((classList.getValue() != null) && (classList.getValue().size() >= Config.ALT_OLY_CLASSED)) {
                if (result == null) {
                    result = new ArrayList<>();
                }

                result.add(classList.getValue());
            }
        }
        return result;
    }

    protected final boolean hasEnoughRegisteredNonClassed() {
        return _nonClassBasedRegisters.size() >= Config.ALT_OLY_NONCLASSED;
    }

    protected final void clearRegistered() {
        _nonClassBasedRegisters.clear();
        _classBasedRegisters.clear();
        AntiFeedManager.getInstance().clear(AntiFeedManager.OLYMPIAD_ID);
    }

    public final boolean isRegistered(L2PcInstance noble) {
        return isRegistered(noble, noble, false);
    }

    private boolean isRegistered(L2PcInstance noble, L2PcInstance player, boolean showMessage) {
        final Integer objId = Integer.valueOf(noble.getObjectId());
        if (_nonClassBasedRegisters.contains(objId)) {
            if (showMessage) {
                final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_ALL_CLASS_BATTLE);
                sm.addPcName(noble);
                player.sendPacket(sm);
            }
            return true;
        }

        final Set<Integer> classed = _classBasedRegisters.get(getClassGroup(noble));
        if ((classed != null) && classed.contains(objId)) {
            if (showMessage) {
                final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST);
                sm.addPcName(noble);
                player.sendPacket(sm);
            }
            return true;
        }

        return false;
    }

    public final boolean isRegisteredInComp(L2PcInstance noble) {
        return isRegistered(noble, noble, false) || isInCompetition(noble, noble, false);
    }

    private boolean isInCompetition(L2PcInstance noble, L2PcInstance player, boolean showMessage) {
        if (!Olympiad._inCompPeriod) {
            return false;
        }

        AbstractOlympiadGame game;
        for (int i = OlympiadGameManager.getInstance().getNumberOfStadiums(); --i >= 0; ) {
            game = OlympiadGameManager.getInstance().getOlympiadTask(i).getGame();
            if (game == null) {
                continue;
            }

            if (game.containsParticipant(noble.getObjectId())) {
                if (!showMessage) {
                    return true;
                }

                switch (game.getType()) {
                    case CLASSED: {
                        final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST);
                        sm.addPcName(noble);
                        player.sendPacket(sm);
                        break;
                    }
                    case NON_CLASSED: {
                        final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_ALL_CLASS_BATTLE);
                        sm.addPcName(noble);
                        player.sendPacket(sm);
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final boolean registerNoble(L2PcInstance player, CompetitionType type) {
        if (!Olympiad._inCompPeriod) {
            player.sendPacket(SystemMessageId.THE_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS);
            return false;
        }

        if (Olympiad.getInstance().getMillisToCompEnd() < 600000) {
            player.sendPacket(SystemMessageId.PARTICIPATION_REQUESTS_ARE_NO_LONGER_BEING_ACCEPTED);
            return false;
        }

        final int charId = player.getObjectId();
        if (Olympiad.getInstance().getRemainingWeeklyMatches(charId) < 1) {
            player.sendPacket(SystemMessageId.THE_MAXIMUM_MATCHES_YOU_CAN_PARTICIPATE_IN_1_WEEK_IS_30);
            return false;
        }

        switch (type) {
            case CLASSED: {
                if (player.isOnEvent()) {
                    player.sendMessage("You can't join olympiad while participating on an Event.");
                    return false;
                }

                if ((Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP > 0) && !AntiFeedManager.getInstance().tryAddPlayer(AntiFeedManager.OLYMPIAD_ID, player, Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP)) {
                    final NpcHtmlMessage message = new NpcHtmlMessage(player.getLastHtmlActionOriginId());
                    message.setFile(player, "data/html/mods/OlympiadIPRestriction.htm");
                    message.replace("%max%", String.valueOf(AntiFeedManager.getInstance().getLimit(player, Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP)));
                    player.sendPacket(message);
                    return false;
                }

                if (Olympiad.getInstance().getRemainingWeeklyMatchesClassed(charId) < 1) {
                    player.sendPacket(SystemMessageId.YOU_CAN_ENTER_UP_TO_30_FREE_FOR_ALL_BATTLES_AND_30_CLASS_SPECIFIC_BATTLES_PER_WEEK);
                    return false;
                }

                _classBasedRegisters.computeIfAbsent(getClassGroup(player), k -> ConcurrentHashMap.newKeySet()).add(charId);
                player.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REGISTERED_FOR_THE_OLYMPIAD_WAITING_LIST_FOR_A_CLASS_BATTLE);
                break;
            }
            case NON_CLASSED: {
                if (player.isOnEvent()) {
                    player.sendMessage("You can't join olympiad while participating on TvT Event.");
                    return false;
                }

                if ((Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP > 0) && !AntiFeedManager.getInstance().tryAddPlayer(AntiFeedManager.OLYMPIAD_ID, player, Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP)) {
                    final NpcHtmlMessage message = new NpcHtmlMessage(player.getLastHtmlActionOriginId());
                    message.setFile(player, "data/html/mods/OlympiadIPRestriction.htm");
                    message.replace("%max%", String.valueOf(AntiFeedManager.getInstance().getLimit(player, Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP)));
                    player.sendPacket(message);
                    return false;
                }

                if (Olympiad.getInstance().getRemainingWeeklyMatchesNonClassed(charId) < 1) {
                    player.sendPacket(SystemMessageId.YOU_CAN_ENTER_UP_TO_30_FREE_FOR_ALL_BATTLES_AND_30_CLASS_SPECIFIC_BATTLES_PER_WEEK);
                    return false;
                }

                _nonClassBasedRegisters.add(charId);
                player.sendPacket(SystemMessageId.YOU_ARE_CURRENTLY_REGISTERED_FOR_A_1V1_CLASS_IRRELEVANT_MATCH);
                break;
            }
        }
        return true;
    }

    public final boolean unRegisterNoble(L2PcInstance noble) {
        if (!Olympiad._inCompPeriod) {
            noble.sendPacket(SystemMessageId.THE_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS);
            return false;
        }

        if ((!noble.isInCategory(CategoryType.THIRD_CLASS_GROUP) && !noble.isInCategory(CategoryType.FOURTH_CLASS_GROUP)) || (noble.getLevel() < 55)) // Classic noble equivalent check.
        {
            final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.CHARACTER_C1_DOES_NOT_MEET_THE_CONDITIONS_ONLY_CHARACTERS_WHO_HAVE_CHANGED_TWO_OR_MORE_CLASSES_CAN_PARTICIPATE_IN_OLYMPIAD);
            sm.addString(noble.getName());
            noble.sendPacket(sm);
            return false;
        }

        if (!isRegistered(noble, noble, false)) {
            noble.sendPacket(SystemMessageId.YOU_ARE_NOT_CURRENTLY_REGISTERED_FOR_THE_OLYMPIAD);
            return false;
        }

        if (isInCompetition(noble, noble, false)) {
            return false;
        }

        final Integer objId = Integer.valueOf(noble.getObjectId());
        if (_nonClassBasedRegisters.remove(objId)) {
            if (Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP > 0) {
                AntiFeedManager.getInstance().removePlayer(AntiFeedManager.OLYMPIAD_ID, noble);
            }

            noble.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REMOVED_FROM_THE_OLYMPIAD_WAITING_LIST);
            return true;
        }

        final Set<Integer> classed = _classBasedRegisters.get(getClassGroup(noble));
        if ((classed != null) && classed.remove(objId)) {
            if (Config.DUALBOX_CHECK_MAX_OLYMPIAD_PARTICIPANTS_PER_IP > 0) {
                AntiFeedManager.getInstance().removePlayer(AntiFeedManager.OLYMPIAD_ID, noble);
            }

            noble.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REMOVED_FROM_THE_OLYMPIAD_WAITING_LIST);
            return true;
        }

        return false;
    }

    public final void removeDisconnectedCompetitor(L2PcInstance player) {
        final OlympiadGameTask task = OlympiadGameManager.getInstance().getOlympiadTask(player.getOlympiadGameId());
        if ((task != null) && task.isGameStarted()) {
            task.getGame().handleDisconnect(player);
        }

        final Integer objId = Integer.valueOf(player.getObjectId());
        if (_nonClassBasedRegisters.remove(objId)) {
            return;
        }

        _classBasedRegisters.getOrDefault(getClassGroup(player), Collections.emptySet()).remove(objId);
    }

    public int getCountOpponents() {
        return _nonClassBasedRegisters.size() + _classBasedRegisters.size();
    }

    private int getClassGroup(L2PcInstance player) {
        if (player.isInCategory(CategoryType.SIXTH_TIR_GROUP)) {
            return 1001;
        } else if (player.isInCategory(CategoryType.SIXTH_SIGEL_GROUP)) {
            return 1002;
        } else if (player.isInCategory(CategoryType.SIXTH_OTHEL_GROUP)) {
            return 1003;
        } else if (player.isInCategory(CategoryType.SIXTH_FEOH_GROUP)) {
            return 1004;
        } else if (player.isInCategory(CategoryType.SIXTH_IS_GROUP)) {
            return 1005;
        } else if (player.isInCategory(CategoryType.SIXTH_EOLH_GROUP)) {
            return 1006;
        } else if (player.isInCategory(CategoryType.SIXTH_WYNN_GROUP)) {
            return 1007;
        } else if (player.isInCategory(CategoryType.SIXTH_YR_GROUP)) {
            return 1008;
        } else if (player.isInCategory(CategoryType.ERTHEIA_FOURTH_CLASS_GROUP)) {
            return 1009;
        } else {
            return player.getBaseClass();
        }
    }

    public static OlympiadManager getInstance() {
        return Singleton.INSTANCE;
    }

    private static class Singleton {
        private static final OlympiadManager INSTANCE = new OlympiadManager();
    }
}