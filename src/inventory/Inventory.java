package inventory;

import java.util.ArrayList;

public class Inventory {
	
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<EquippableItem> equipment = new ArrayList<EquippableItem>();
	
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> equipment) {
		this.items = equipment;
	}
	public void addItem(Item item) {
		this.items.add(item);
	}
	public void removeItem(Item item) {
		if(this.items.contains(item)) {
			this.items.remove(item);
		}
	}
	public void removeItem(int index) {
		if(this.items.size()>=index){
			this.items.remove(index);
		}
	}
		
	public ArrayList<EquippableItem> getEquipment() {
		return equipment;
	}
	public void setEquipment(ArrayList<EquippableItem> equipment) {
		this.equipment = equipment;
	}
	public void addEquipment(EquippableItem equipment) {
		this.equipment.add(equipment);
	}
	public void removeEquipment(EquippableItem equipment) {
		if(this.equipment.contains(equipment)) {
			this.equipment.remove(equipment);
		}
	}
	public void removeEquipment(int index) {
		if(this.equipment.size()>=index){
			this.equipment.remove(index);
		}
	}

}
