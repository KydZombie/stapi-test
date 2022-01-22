package com.github.kydzombie.stapitest.util;

import java.awt.*;

public class ColorConverter {
    public static int colorToInt(Color color) {
        return ((color.getAlpha() & 255) << 24) | ((color.getRed() & 255) << 16) | ((color.getGreen() & 255) << 8) | (color.getBlue() & 255);
    }
}
