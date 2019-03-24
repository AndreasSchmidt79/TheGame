package drawing;

public enum TextureFilepath {

    UI_BACKGROUND("./res/UI/bricks_bg.png"),
    UI_PANEL_FRAME("./res/UI/frame.png"),
    UI_TRANSPARENT_PANE("./res/UI/semi_transparent.png"),
    UI_LIGHTING("./res/lighting.png"),
    UI_TEXT_PANEL("./res/UI/box_3_1.png"),
    UI_PAPER_FILEPATH("./res/UI/paper_background.png"),
    UI_HEALTHBAR("./res/UI/healthbar.png"),
    UI_HEALTHBAR_BG("./res/UI/healthbar_bg.png"),
    UI_INVENTORY("./res/UI/inventory.png"),
    UI_TEXT_BUTTON("./res/UI/button_sprite.png"),
    UI_SIMPLE_BUTTON("./res/UI/button_square.png"),
    UI_CLOSE_BUTTON("./res/UI/button_close2.png"),

    UI_ICON_EQUIPMENT("./res/UI/icons/equipment_icon.png"),

    MOB_PLAYER("./res/human.png"),
    MOB_SPIDER("./res/mobs/spider.png"),
    MOB_GOBLIN("./res/mobs/goblin.png"),
    MOB_SKELETON("./res/mobs/skeleton.png"),
    MOB_WIZARD("./res/mobs/wizard.png"),
    MOB_BAT("./res/mobs/bat.png"),

    ITEM_DAGGER_INV("./res/UI/icons/DaggerIcon.png"),
    ITEM_DAGGER_EQUIP("./res/Items/weapons/dagger.png"),
    ITEM_SWORD_INV("./res/UI/icons/SwordIcon.png"),
    ITEM_SWORD_EQUIP("./res/Items/weapons/sword.png"),
    ITEM_STEEL_HELMET_INV("./res/UI/icons/SteelHelmetIcon.png"),
    ITEM_STEEL_HELMET_EQUIP("./res/Items/armour/SteelHelmet.png"),
    ITEM_ARMOUR_LEATHER_EQUIP("./res/Items/armour/LeatherArmor.png"),
    ITEM_ARMOUR_LEATHER_INV("./res/Items/armour/LeatherArmor.png");

    private final String filepath;

    TextureFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public String getFilepath()
    {
        return this.filepath;
    }
    
}
