package gu_android_1089.simplecalculator.ui;

import android.content.Context;
import android.content.SharedPreferences;

import gu_android_1089.simplecalculator.main_logic.ThemesVariants;

public class ThemeSaver {
    private static final String THEME_KEY = "THEME_KEY";

    private Context context;
    private SharedPreferences sharedPreferences;

    public ThemeSaver(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("ThemeChoice", context.MODE_PRIVATE);
    }

    public ThemesVariants getCurrentTheme() {
        String key = sharedPreferences.getString(THEME_KEY,
                ThemesVariants.THEME_BLUE_STANDARD.getThemeKey());

        for (ThemesVariants elem : ThemesVariants.values()) {
            if (elem.getThemeKey().equals(key)) {
                return elem;
            }
        }

        throw new RuntimeException("Some error, think about it");
    }

    public void setCurrentTheme(ThemesVariants currentTheme) {
        sharedPreferences.edit().putString(THEME_KEY, currentTheme.getThemeKey()).apply();

    }
}
