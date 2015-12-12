package org.ld34.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import org.ld34.core.Main;

public class MainDesktop {

    private static final boolean PACK_TEXTURES_ON_START = true;
    private static final int MAX_TEXTURE_SIZE = 512;

    public static void main (String[] args) {

        // Pack textures if requested
        if (PACK_TEXTURES_ON_START) {
            System.out.println("Packing textures");
            TexturePacker.Settings settings = new TexturePacker.Settings();
            settings.maxWidth = MAX_TEXTURE_SIZE;
            settings.maxHeight = MAX_TEXTURE_SIZE;
            TexturePacker.process(settings, "../asset-sources/textures", "../assets/textures", "textures");
        }

        // Start application
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);
	}
}
