package inventory.Weapon;

import inventory.EquippableItem;

public class Weapon extends EquippableItem {
	
	protected DamageRange damageRange;
	
	public Weapon(String name, DamageRange damageRange) {
		super(name);
		this.damageRange = damageRange;
	}
	
}
