package com.example.calculator_oct_21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PREFERENCES_NAME = "main";
    public static final String THEME_NAME = "theme";
    public static final int AppThemeLightCodeStyle = 0;
    public static final int AppThemeDarkCodeStyle = 1;
    public static final int AppThemeDefault = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(loadAppTheme());

        setContentView(R.layout.activity_main);

        findViewById(R.id.radioButtonMeCoolStyle).setOnClickListener(v -> {
            saveAppTheme(AppThemeDefault);
            recreate();
        });
        findViewById(R.id.radioButtonDark).setOnClickListener(v -> {
            saveAppTheme(AppThemeDarkCodeStyle);
            recreate();
        });
        findViewById(R.id.radioButtonLight).setOnClickListener(v -> {
            saveAppTheme(AppThemeLightCodeStyle);
            recreate();
        });

        Button buttonOk = (Button) findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(this);


        }

    private int codeStyleToStyleID(int codeStyle) {
        switch (codeStyle) {
            case AppThemeLightCodeStyle:
                return R.style.AppThemeLight;
            case AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
            case AppThemeDefault:
            default:
                return R.style.MyCoolStyle;
        }
    }

    private int loadAppTheme() {
        int codeTheme = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
                .getInt(THEME_NAME, AppThemeDefault);

        return codeStyleToStyleID(codeTheme);
    }

    private void saveAppTheme(int code) {
        getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
                .edit()
                .putInt(THEME_NAME, code)
                .apply();

    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
        startActivity(intent);

    }
}



