package gu_android_1089.simplecalculator.main_logic;

import gu_android_1089.simplecalculator.R;

public enum ThemesVariants {
    THEME_GREEN("themeGreen", R.style.Theme_myGreenTheme),
    THEME_PUNK("themePunk", R.style.Theme_myPunkTheme),
    THEME_NIGHT("themeNight", R.style.Theme_myNightTheme),
    THEME_BLUE_STANDARD("themeBlueStandard", R.style.Theme_SimpleCalculator);

    public String getThemeKey() {
        return themeKey;
    }

    private String themeKey;

    public int getResource() {
        return resource;
    }

    private int resource;

    ThemesVariants(String themeKey, int resource) {
        this.themeKey = themeKey;
        this.resource = resource;
    }
}
