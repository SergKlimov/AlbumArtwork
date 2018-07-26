package com.example.serg.albumartwork.Utils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MillisecParser {
    public static String getString(int millisec){
        return String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millisec),
                TimeUnit.MILLISECONDS.toSeconds(millisec) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisec))
                );
    }
}
