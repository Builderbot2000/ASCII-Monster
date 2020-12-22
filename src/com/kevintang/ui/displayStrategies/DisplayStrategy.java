package com.kevintang.ui.displayStrategies;

import com.kevintang.ui.Pixel;

public interface DisplayStrategy {
    Pixel[][] run(Pixel[][] screen);
}
