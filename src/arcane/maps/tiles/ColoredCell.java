package arcane.maps.tiles;

import arcane.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class ColoredCell extends TiledMapTileLayer.Cell {
    private final Color color = new Color();

    public ColoredCell(Color color) {
        this.color.set(color);
    }

    public Color getColor() {
        return color;
    }
}