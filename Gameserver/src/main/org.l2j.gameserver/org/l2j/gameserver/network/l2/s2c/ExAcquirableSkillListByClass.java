package org.l2j.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.List;

import org.l2j.gameserver.model.base.AcquireType;

/**
 * Reworked: VISTALL
 */
public class ExAcquirableSkillListByClass extends L2GameServerPacket
{
	private AcquireType _type;
	private final List<Skill> _skills;

	class Skill
	{
		public int id;
		public int nextLevel;
		public int maxLevel;
		public int cost;
		public int requirements;
		public int subUnit;

		Skill(int id, int nextLevel, int maxLevel, int cost, int requirements, int subUnit)
		{
			this.id = id;
			this.nextLevel = nextLevel;
			this.maxLevel = maxLevel;
			this.cost = cost;
			this.requirements = requirements;
			this.subUnit = subUnit;
		}
	}

	public ExAcquirableSkillListByClass(AcquireType type, int size)
	{
		_skills = new ArrayList<Skill>(size);
		_type = type;
	}

	public void addSkill(int id, int nextLevel, int maxLevel, int Cost, int requirements, int subUnit)
	{
		_skills.add(new Skill(id, nextLevel, maxLevel, Cost, requirements, subUnit));
	}

	public void addSkill(int id, int nextLevel, int maxLevel, int Cost, int requirements)
	{
		_skills.add(new Skill(id, nextLevel, maxLevel, Cost, requirements, 0));
	}

	@Override
	protected final void writeImpl()
	{
		writeShort(_type.getId());
		writeShort(_skills.size());

		for(Skill temp : _skills)
		{
			writeInt(temp.id);
			writeShort(temp.nextLevel);
			writeShort(temp.maxLevel);
			writeByte(temp.requirements);
			writeLong(temp.cost);
			writeByte(0x01); // UNK
			if(_type == AcquireType.SUB_UNIT)
				writeShort(temp.subUnit);
		}
	}
}