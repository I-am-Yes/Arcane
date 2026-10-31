package arcane.graphics;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import static arcane.graphics.Color.*;
import static arcane.Cores.*;

public class Artist {

    private static SpriteBatch activeBatch;
    private static ShapeRenderer activeShape;

    private static void useBatch(SpriteBatch target) {
        if (activeBatch == target && target.isDrawing()) {
            return;
        }
        flush();

        target.begin();
        activeBatch = target;
    }

    private static void useShape(ShapeRenderer target, ShapeRenderer.ShapeType type) {

        if (activeShape == target && target.getCurrentType() == type) {
            return;
        }
        flush();

        target.begin(type);
        activeShape = target;
    }

    public static void flush() {
        if (activeBatch != null) {
            SpriteBatch previous = activeBatch;
            activeBatch = null;
            previous.end();
        }

        if (activeShape != null) {
            ShapeRenderer previous = activeShape;
            activeShape = null;
            previous.end();
        }
    }

    public static void screenClear(Viewport viewport) {
        flush();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
    }

    public static void screenClear() {
        flush();
        ScreenUtils.clear(BLACK);
    }

    public static void DrawBatch(
            SpriteBatch batch, TextureRegion region,
            float x, float y, float width, float height,
            Color color) {

        if (region == null) return;

        useBatch(batch);

        float oldColor = batch.getPackedColor();
        try {
            batch.setColor(color == null ? WHITE : color);
            batch.draw(region, x, y, width, height);
        } finally {
            batch.setPackedColor(oldColor);
        }
    }

    public static void DrawBatch(
            SpriteBatch batch, TextureRegion region,
            float x, float y, float originX, float originY,
            float width, float height,
            float scaleX, float scaleY, float rotation,
            Color color) {

        if (region == null) return;

        useBatch(batch);

        float oldColor = batch.getPackedColor();
        try {
            batch.setColor(color == null ? WHITE : color);
            batch.draw(
                region, x, y, originX, originY,
                width, height, scaleX, scaleY, rotation
            );
        } finally {
            batch.setPackedColor(oldColor);
        }
    }

    public static void DrawSprite(Sprite sprite) {
        DrawSprite(sprite, batch);
    }

    public static void DrawSprite(Sprite sprite, SpriteBatch batch) {

        if (sprite == null || batch == null) return;

        useBatch(batch);


        sprite.draw(batch);
    }

    public static void DrawRect(
            float x, float y, float width, float height,
            Color color) {

        DrawRect(shape, x, y, width, height, color);
    }

    public static void DrawRect(
            ShapeRenderer shape,
            float x, float y, float width, float height,
            Color color) {

        useShape(shape, ShapeRenderer.ShapeType.Filled);

        shape.setColor(color == null ? WHITE : color);
        shape.rect(x, y, width, height);
    }

    public static void DrawLine(
            float x, float y, float x2, float y2,
            float thickness, Color color) {

        DrawLine(shape, x, y, x2, y2, thickness, color);
    }

    public static void DrawLine(
            ShapeRenderer shape,
            float x, float y, float x2, float y2,
            float thickness, Color color) {

        useShape(shape, ShapeRenderer.ShapeType.Line);

        shape.setColor(color == null ? WHITE : color);
        shape.rectLine(x, y, x2, y2, thickness);
    }
}
