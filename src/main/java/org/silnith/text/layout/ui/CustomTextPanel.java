package org.silnith.text.layout.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 * A custom panel that renders styled flowed text.
 */
public class CustomTextPanel extends JPanel implements Scrollable {

    private final AttributedString content;

    private List<TextLayout> layouts;

    private final Map<RenderingHints.Key, Object> renderingHints;

    public CustomTextPanel(final AttributedString content) {
        super(true);
        if (content == null) {
            throw new IllegalArgumentException("Content is null.");
        }
        this.content = content;
        this.layouts = Collections.emptyList();

        this.renderingHints = new HashMap<RenderingHints.Key, Object>();
        this.renderingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.renderingHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        this.renderingHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
        this.renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        this.renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        this.renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.renderingHints.put(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
        this.renderingHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        this.renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        this.setPreferredSize(new Dimension(640, 1024));
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 10;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 100;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2 = getGraphics2D(g);

        g2.addRenderingHints(renderingHints);

        float x = 0;
        float y = 0;
        for (final TextLayout textLayout : layouts) {
            y += textLayout.getAscent();
            if (textLayout.isLeftToRight()) {
                textLayout.draw(g2, 0, y);
            } else {
                textLayout.draw(g2, 320 - textLayout.getAdvance(), y);
            }
            y += textLayout.getDescent();
            y += textLayout.getLeading();
        }
    }

    private Graphics2D getGraphics2D(final Graphics g) {
        final Graphics2D g2 = Graphics2D.class.cast(g.create());
        final GraphicsConfiguration deviceConfiguration = g2.getDeviceConfiguration();
        final AffineTransform normalizingTransform = deviceConfiguration.getNormalizingTransform();
        g2.transform(normalizingTransform);
        return g2;
    }

    @Override
    public void doLayout() {
        // TODO: Get Locale of the rendered content.
        final AttributedCharacterIterator iterator = content.getIterator();
        final BreakIterator lineInstance = BreakIterator.getLineInstance(Locale.ENGLISH);
        final Graphics2D g2 = getGraphics2D(getGraphics());
        final FontRenderContext frc = g2.getFontRenderContext();
        final LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(iterator, lineInstance, frc);

        lineBreakMeasurer.setPosition(iterator.getBeginIndex());

//		final float wrappingWidth = getWidth();
        final GraphicsConfiguration deviceConfiguration = g2.getDeviceConfiguration();
        final AffineTransform normalizingTransform = deviceConfiguration.getNormalizingTransform();
        final float wrappingWidth;
        try {
            final Point2D inverseTransform = normalizingTransform.inverseTransform(new Point2D.Float(getWidth(), getHeight()), null);
            wrappingWidth = (float) inverseTransform.getX();
        } catch (final NoninvertibleTransformException e) {
            e.printStackTrace();

            final System.Logger logger = System.getLogger(CustomTextPanel.class.getName());
            logger.log(System.Logger.Level.ERROR, "Failed.", e);

            return;
        }

        final List<TextLayout> layouts = new ArrayList<TextLayout>();
        while (lineBreakMeasurer.getPosition() < iterator.getEndIndex()) {
            /*
             * Make sure width is positive in case the window hasn't been made visible yet.
             */
            final TextLayout nextLayout = lineBreakMeasurer.nextLayout(Math.max(wrappingWidth, 1.0f));

            assert nextLayout != null;

            layouts.add(nextLayout);
        }

        this.layouts = layouts;

        super.doLayout();
    }

}
