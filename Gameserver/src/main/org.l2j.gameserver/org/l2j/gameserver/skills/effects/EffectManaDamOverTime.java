package org.l2j.gameserver.skills.effects;

import org.l2j.gameserver.model.actor.instances.creature.Abnormal;
import org.l2j.gameserver.network.l2.components.SystemMsg;
import org.l2j.gameserver.network.l2.s2c.SystemMessagePacket;
import org.l2j.gameserver.stats.Env;
import org.l2j.gameserver.templates.skill.EffectTemplate;

public class EffectManaDamOverTime extends Effect
{
	public EffectManaDamOverTime(Abnormal abnormal, Env env, EffectTemplate template)
	{
		super(abnormal, env, template);
	}

	@Override
	public boolean onActionTime()
	{
		if(getEffected().isDead())
			return false;

		double manaDam = getValue();
		if(manaDam > getEffected().getCurrentMp() && getSkill().isToggle())
		{
			getEffected().sendPacket(SystemMsg.NOT_ENOUGH_MP);
			getEffected().sendPacket(new SystemMessagePacket(SystemMsg.THE_EFFECT_OF_S1_HAS_BEEN_REMOVED).addSkillName(getSkill().getId(), getSkill().getDisplayLevel()));
			return false;
		}

		getEffected().reduceCurrentMp(manaDam, null);
		return true;
	}
}