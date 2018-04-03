package drawing;

import java.util.HashMap;

public class TextureCache {
	
	private HashMap<String, Texture> textureCache = new HashMap<String, Texture>();

	public TextureCache() {
		
	}
	
	public Texture getTexture(String filePath) {
		if(!textureCache.containsKey(filePath)) {
			textureCache.put(filePath, new Texture(filePath));
		} 
		return textureCache.get(filePath);
	}

}
