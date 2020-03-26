package org.silnith.text.layout;

import java.awt.Font;
import java.awt.Image;
import java.awt.font.ImageGraphicAttribute;
import java.awt.font.TextAttribute;
import java.awt.font.TransformAttribute;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ContentPanelFactory {

	private final Image image;

	public ContentPanelFactory() throws IOException {
		super();
		image = ImageIO.read(Example.class.getResourceAsStream("/mog.png")).getScaledInstance(-1, 64, Image.SCALE_SMOOTH);
	}
	
	public JComponent getContentPanel() {
		return new CustomTextPanel(createAttributedString());
	}

	private AttributedString createAttributedString() {
			final Map<AttributedCharacterIterator.Attribute, Object> attributes = new HashMap<AttributedCharacterIterator.Attribute, Object>();
			attributes.put(AttributedCharacterIterator.Attribute.LANGUAGE, Locale.ENGLISH);
	//		attributes.put(AttributedCharacterIterator.Attribute.READING, new Annotation("This is a test."));
			
			// Attributes constant for the paragraph.
			attributes.put(TextAttribute.JUSTIFICATION, TextAttribute.JUSTIFICATION_FULL);
			attributes.put(TextAttribute.NUMERIC_SHAPING, null);
			attributes.put(TextAttribute.RUN_DIRECTION, TextAttribute.RUN_DIRECTION_LTR);
			
			// Attributes that can vary.
			attributes.put(TextAttribute.BACKGROUND, null);
	//		attributes.put(TextAttribute.BIDI_EMBEDDING, null);
	//		attributes.put(TextAttribute.CHAR_REPLACEMENT, new ImageGraphicAttribute(null, ImageGraphicAttribute.ROMAN_BASELINE));
			attributes.put(TextAttribute.FAMILY, Font.SERIF);
			attributes.put(TextAttribute.FOREGROUND, null);
			attributes.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
			attributes.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON);
			attributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_REGULAR);
			attributes.put(TextAttribute.SIZE, 14.0f);
			attributes.put(TextAttribute.STRIKETHROUGH, false);
			attributes.put(TextAttribute.SUPERSCRIPT, 0);
			attributes.put(TextAttribute.SWAP_COLORS, false);
			attributes.put(TextAttribute.TRACKING, 0.0f);
			attributes.put(TextAttribute.TRANSFORM, TransformAttribute.IDENTITY);
			attributes.put(TextAttribute.UNDERLINE, -1);
			attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
			attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
			
			final int count = 25;
			
			final String string = "This is a test. \uFFFC ";
			final String text = string.repeat(count);
			final AttributedString attributedString = new AttributedString(text, attributes);
			final char replacement = '\uFFFC';
			
			attributedString.addAttribute(TextAttribute.SIZE, 24.0f, 0, 1);
			
			final ImageGraphicAttribute imageGraphicAttribute = new ImageGraphicAttribute(image, ImageGraphicAttribute.ROMAN_BASELINE, 0, 63);
			for (int i = 0; i < count; i += 1) {
				attributedString.addAttribute(TextAttribute.CHAR_REPLACEMENT, imageGraphicAttribute, string.length() * i + string.indexOf(replacement), string.length() * i + string.indexOf(replacement) + 1);
			}
			
			for (int i = 1; i < count; i += 2) {
				attributedString.addAttribute(TextAttribute.FAMILY, Font.SANS_SERIF, string.length() * i, string.length() * (i + 1));
			}
			
			for (int i = 2; i < count; i += 4) {
				attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT, string.length() * i, string.length() * (i + 1));
			}
			
			for (int i = 3; i < count; i += 4) {
				attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, string.length() * i, string.length() * (i + 1));
			}
			
			for (int i = 1; i < count; i += 4) {
				attributedString.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, string.length() * i, string.length() * (i + 1));
			}
			
			for (int i = 0; i < count; i += 4) {
				attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, string.length() * i, string.length() * (i + 1));
			}
			
			for (int i = 3; i < count; i += 4) {
				attributedString.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, string.length() * i, string.length() * (i + 1));
			}
			
			return attributedString;
		}
	
}