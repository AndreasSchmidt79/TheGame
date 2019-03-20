package drawing;

import drawing.button.Button;
import gameMap.Scaling;

import java.util.ArrayList;

public class BaseDrawing {

    protected ArrayList<Button> activeButtons = new ArrayList<>();

    protected int mapSizeInTiles;
    protected TextureCache textureCache = new TextureCache();

    public BaseDrawing(int mapSizeInTiles) {
        this.mapSizeInTiles = mapSizeInTiles;
    }

    public void render(Texture tex, float[] vertices, float[] textureCoords) {
        Model model = new Model(vertices, textureCoords);
        tex.bind();
        model.render();
    }

    public void drawRectangle(Position pos, int width, int height, Texture tex) {
        drawRectangle(pos, width, height, tex, false, new Scaling(1, 1));
    }

    public void drawRectangle(Position pos, int width, int height, Texture tex, boolean inversX, Scaling scaling) {
        centerScaledPosition(pos, scaling, width, height);
        float[] vertices = calculateVertices(pos, width, height, scaling);
        float[] texture = inversX ? getInversXTexture() : getDefaultTexture();
        render(tex, vertices, texture);
    }

    public void drawRectangleFromSprite(Position pos, int width, int height, Texture tex, SpriteElement spriteElement) {
        float[] vertices = calculateVertices(pos, width, height, new Scaling(1, 1));
        float[] texture = getSpriteTextures(spriteElement, tex);
        render(tex, vertices, texture);
    }

    public void drawRectangleZoomTexture(Position pos, int width, int height, Texture tex, float zoom) {
        render(tex, calculateVertices(pos, width, height, new Scaling(1, 1)), getZoomTexture(zoom));
    }

    private float[] calculateVertices(Position pos, int width, int height, Scaling scaling) {
        float posX = -1 + (float) pos.getX() * 2 / (float) Screen.WIDTH;
        float posY = 1 - (float) pos.getY() * 2 / (float) Screen.HEIGHT;
        float facX = (float) width / (float) Screen.WIDTH;
        float facY = (float) height / (float) Screen.HEIGHT;
        return getVertices(posX, posY, facX * scaling.getScaleX(), facY * scaling.getScaleY());
    }

    private void centerScaledPosition(Position pos, Scaling scaling, int width, int height) {
        pos.setX(pos.getX() + Math.round(((1 - scaling.getScaleX()) * width) / 2));
        pos.setY(pos.getY() + Math.round(((1 - scaling.getScaleY()) * height) / 2));
    }

    private float[] getDefaultTexture() {
        return new float[]{0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0};
    }

    private float[] getZoomTexture(float z) {
        return new float[]{1 - z, 1 - z, z, 1 - z, z, z, z, z, 1 - z, z, 1 - z, 1 - z};
    }

    private float[] getInversXTexture() {
        return new float[]{1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0};
    }

    private float[] getSpriteTextures(SpriteElement sprite, Texture tex) {

        float spritePosX = (float) sprite.getPos().getX() / tex.getWidth();
        float spritePosY = (float) sprite.getPos().getY() / tex.getHeight();

        float spriteWidth = (float) sprite.getWidth() / tex.getWidth();
        float spriteHeight = (float) sprite.getHeight() / tex.getHeight();

        return new float[]{
                spritePosX, spritePosY,
                spritePosX + spriteWidth, spritePosY,
                spritePosX + spriteWidth, spritePosY + spriteHeight,

                spritePosX + spriteWidth, spritePosY + spriteHeight,
                spritePosX, spritePosY + spriteHeight,
                spritePosX, spritePosY
        };
    }

    public float[] getVertices(float posX, float posY, float facX, float facY) {
        float[] vertices = new float[]{
                0 * facX + posX, 0 * facY + posY,
                2 * facX + posX, 0 * facY + posY,
                2 * facX + posX, -2 * facY + posY,

                2 * facX + posX, -2 * facY + posY,
                0 * facX + posX, -2 * facY + posY,
                0 * facX + posX, 0 * facY + posY};
        return vertices;
    }

}
