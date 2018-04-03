package drawing.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlyphDataReader {

    public HashMap<String, GlyphData> glyphs = new HashMap<String, GlyphData>();

    public GlyphDataReader(String fileName) {
    	try {
			readGlyphData(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void readGlyphData(String fileName) throws IOException{
        FileReader fontReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fontReader);

        String infoLine = reader.readLine(); //info line
        String commonLine = reader.readLine();
        String pageLine = reader.readLine();
        String charsLine = reader.readLine(); //chars line

        String line = reader.readLine();
        while(line != null) {
            this.addLineToGlyphs(line);
            line = reader.readLine();
        }

        reader.close();
    }

    private void addLineToGlyphs(String lineString) throws IOException {
        if (lineString != null) {
            String[] lineFragments = lineString.split(" ");
            List<String> formattedStrings = new ArrayList<String>();

            //remove new line, space, and return characters
            //because there are wacky spaces in between the text of the .fnt file
            //and when you split on space, it adds new line type characters
            if (lineFragments[0].equals("char")) {
                for (int i = 0; i < lineFragments.length; i++) {
                    String string = lineFragments[i];
                    string = string.replace(" ", "");
                    string = string.replace("\n", "");
                    string = string.replace("\r", "");
                    //cant just reassign, because we need to remove empties
                    //and we want to directly assign based on index because we know the format
                    if (!string.isEmpty()) {
                        formattedStrings.add(string);
                    }
                }

                GlyphData data = new GlyphData(formattedStrings);
                String character = Character.toString ((char) data.id);
                this.glyphs.put(character, data);
            }
        }
    }
}