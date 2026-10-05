package arcane.input;

import com.badlogic.gdx.math.Vector2;

import static arcane.Cores.*;

public class UiInputGate extends InputHandler {

    private final Vector2 point = new Vector2();

    private boolean pointerOverGUI;

    private boolean updateUiHit(int screenX, int screenY) {
        point.set(screenX, screenY);
        viewport.unproject(point);
        pointerOverGUI = uiStage.hit(point.x, point.y, true) != null;
        return pointerOverGUI;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        updateUiHit(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        updateUiHit(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        updateUiHit(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        updateUiHit(screenX, screenY);
        return false;
    }

    public boolean isPointerOverGUI() {
        return pointerOverGUI;
    }

}
