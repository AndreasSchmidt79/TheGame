package drawing.text;

import java.util.List;

public class GlyphData {

    public final String character;
    public final int id;
    public final int x;
    public final int y;
    public final int width;
    public final int height;
    public final int xoffset;
    public final int yoffset;
    public final int xadvance;
    public final String page;
    public final String chnl;

    public GlyphData(List<String> glyphDataFragments) {

        String character = glyphDataFragments.get(0); //keyword for font language
        String id = glyphDataFragments.get(1);
        String x = glyphDataFragments.get(2);
        String y = glyphDataFragments.get(3);
        String width = glyphDataFragments.get(4);
        String height = glyphDataFragments.get(5);
        String xoffset = glyphDataFragments.get(6);
        String yoffset = glyphDataFragments.get(7);
        String xadvance = glyphDataFragments.get(8);
        String page = glyphDataFragments.get(9);
        String chnl = glyphDataFragments.get(10);

        this.id = Integer.valueOf(id.replace("id=", ""));
        this.x = Integer.valueOf(x.replace("x=", ""));
        this.y = Integer.valueOf(y.replace("y=", ""));
        this.width = Integer.valueOf(width.replace("width=", ""));
        this.height = Integer.valueOf(height.replace("height=", ""));
        this.xoffset = Integer.valueOf(xoffset.replace("xoffset=", ""));
        this.yoffset = Integer.valueOf(yoffset.replace("yoffset=", ""));
        this.xadvance = Integer.valueOf(xadvance.replace("xadvance=", ""));
        
        //unused
        this.character = character;
        this.page = page;
        this.chnl = chnl;
    }
}