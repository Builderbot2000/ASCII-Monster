package com.kevintang.ui.displayStrategies;

import com.kevintang.ui.Pixel;

/**
 * The method with which the model will be projected onto view
 */
public interface DisplayStrategy {
    Pixel[][] generateDisplayStrategy(Pixel[][] screen);
}
