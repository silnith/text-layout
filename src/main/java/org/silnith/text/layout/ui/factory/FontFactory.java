package org.silnith.text.layout.ui.factory;

import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.font.TextAttribute;
import java.awt.font.TransformAttribute;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Map;

/**
 * Creates fonts scaled properly for the device.
 */
public class FontFactory {

    private static final String dialogFontFamily = Font.DIALOG;

    private static final float defaultFontSize = 12.0f;

    public Font getDialogFont() {
        return getDialogFont(getDefaultGraphicsConfiguration());
    }

    public Font getDialogFont(final GraphicsConfiguration graphicsConfiguration) {
        return getFont(graphicsConfiguration, dialogFontFamily, defaultFontSize);
    }

    public Font getFont(final GraphicsConfiguration graphicsConfiguration, final String fontFamily,
            final float fontSize) {
        final Map<AttributedCharacterIterator.Attribute, Object> attributes = new HashMap<AttributedCharacterIterator.Attribute, Object>();
        attributes.put(TextAttribute.FAMILY, fontFamily);
        attributes.put(TextAttribute.SIZE, fontSize);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
        attributes.put(TextAttribute.TRANSFORM,
                new TransformAttribute(graphicsConfiguration.getNormalizingTransform()));

        return Font.getFont(attributes);
    }

    public Font getFont(final String fontFamily, final float fontSize) {
        final Map<AttributedCharacterIterator.Attribute, Object> attributes = new HashMap<AttributedCharacterIterator.Attribute, Object>();
        attributes.put(TextAttribute.FAMILY, fontFamily);
        attributes.put(TextAttribute.SIZE, fontSize);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
        attributes.put(TextAttribute.TRANSFORM,
                new TransformAttribute(getDefaultGraphicsConfiguration().getNormalizingTransform()));

        return Font.getFont(attributes);
    }

    public GraphicsConfiguration getDefaultGraphicsConfiguration() {
        final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        final GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
        return graphicsConfiguration;
    }

}
