package inventory.armour;

import inventory.EquippableItem;
import inventory.Weapon.DamageRange;

public class Armour extends EquippableItem{

	protected int armourValue;
	
	public Armour(String name, int armourValue) {
		super(name);
		this.armourValue = armourValue;
	}
}
