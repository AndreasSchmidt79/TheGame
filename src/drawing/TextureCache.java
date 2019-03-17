package drawing;

import java.util.HashMap;

public class TextureCache {

    private HashMap<String, Texture> textureCacheMap = new HashMap<>();

    public TextureCache() {
        initAllTextures();
    }

    public Texture getTexture(String filePath) {
        if (!textureCacheMap.containsKey(filePath)) {
            textureCacheMap.put(filePath, new Texture(filePath));
        }
        return textureCacheMap.get(filePath);
    }

    private void initAllTextures() {
        for (TextureFilepath filepath : TextureFilepath.values()) {
            textureCacheMap.put(filepath.getFilepath(), new Texture(filepath.getFilepath()));
        }
    }
}
