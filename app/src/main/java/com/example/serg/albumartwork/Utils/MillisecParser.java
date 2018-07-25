package com.example.serg.albumartwork.Utils;

import java.util.concurrent.TimeUnit;

public class MillisecParser {
    public static String getString(int millisec){
        String m_s = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millisec),
                TimeUnit.MILLISECONDS.toSeconds(millisec) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisec))
                );
        return m_s;
    }
}
