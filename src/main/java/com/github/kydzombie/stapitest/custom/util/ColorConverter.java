package com.github.kydzombie.stapitest.custom.util;

import java.awt.*;

public class ColorConverter {
    public static int colorToInt(Color color) {
//        new Color(0xff6d6d);
//        System.out.println(color);
        return (((color.getRed() & 255) << 16) | ((color.getGreen() & 255) << 8) | ((color.getBlue() & 255) << 0));
    }
}
