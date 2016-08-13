package com.mgl.test.hugetest.utils.manager;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;

public class FontManager {

    private static final String TAG = FontManager.class.getName();
    public static FontManager instance;

    private static String fontTitle = "fonts/huge_agb_v5.ttf";
    private static String fontNormal = "fonts/Copernicus-Book.otf";
    private static String fontDescription = "fonts/Copernicus-BookItalic.otf";

    public static final String TITLE_FONT = "TITLE_FONT";
    public static final String NORMAL_FONT = "NORMAL_FONT";
    public static final String DESCRIPTION_FONT = "DESCRIPTION_FONT";


    private HashMap<String, Typeface> fontHash;


    private FontManager(Context context) {
        loadFonts(context);
    }

    private void loadFonts(Context context) {
        fontHash = new HashMap<>();
        loadFont(TITLE_FONT, fontTitle, context);
        loadFont(NORMAL_FONT, fontNormal, context);
        loadFont(DESCRIPTION_FONT, fontDescription, context);
    }

    private void loadFont(String fontId, String fontPath, Context context) {
        try {
            fontHash.put(fontId, Typeface.createFromAsset(context.getAssets(), fontPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FontManager getInstance() {
        if (instance == null) {
            Log.e(TAG, "ERROR FONTMANAGER NOT INITIALIZED");
        }
        return instance;
    }

    public static void init(Context context) {
        instance = new FontManager(context);
    }

    public Typeface getFont(String type) {
        return fontHash.get(type);
    }
}
