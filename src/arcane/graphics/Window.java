package arcane.graphics;

import arcane.*;

public class Window {

    private int windowWidth = 1280;
    private int windowHeight = 720;
    private int foregroundFPS = 180;

    private boolean vSync;
    private boolean isBorderlessFullscreen = false;

    public Window() {}

    public void init(boolean VSync, int foregroundFPS, int windowWidth, int windowHeight) {
        Gdx.graphics.setVSync(true);
        setForegroundFPS(foregroundFPS);
        setWindowedMode(windowWidth, windowHeight);
    }

    public void init() {
        Gdx.graphics.setVSync(true);
        setForegroundFPS(foregroundFPS);
        setWindowedMode(windowWidth, windowHeight);
    }

    public static int getWidth() {
        return Gdx.graphics.getWidth();
    }

    public static int getHeight() {
        return Gdx.graphics.getHeight();
    }

    public void setWindowedMode() {
        setWindowedMode(windowWidth, windowHeight);
    }

    public void setWindowedMode(int width, int height) {
        windowWidth = width;
        windowHeight = height;
        Gdx.graphics.setWindowedMode(windowWidth, windowHeight);
    }

    public void setWindowFullscreen() {
        setWindowFullscreen(!isFullscreen());
    }

    public void setWindowFullscreen(boolean fullscreen) {
        if (fullscreen) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        } else {
            Gdx.graphics.setWindowedMode(windowWidth, windowHeight);
        }
    }

    public void setBorderlessFullscreen() {
        setBorderlessFullscreen(!isBorderlessFullscreen());
    }

    public void setBorderlessFullscreen(boolean borderlessFullscreen) {
        if (borderlessFullscreen) {
            Graphics.DisplayMode mode = Gdx.graphics.getDisplayMode();
            Gdx.graphics.setWindowedMode(mode.width, mode.height);
            isBorderlessFullscreen = true;
            return;
        }
        Gdx.graphics.setWindowedMode(windowWidth, windowHeight);
        isBorderlessFullscreen = false;
    }

    public void exitFullscreen() {
        setWindowFullscreen(false);
    }

    public void setForegroundFPS(int fps) {
        this.foregroundFPS = fps;
        Gdx.graphics.setForegroundFPS(fps);
    }

    public int getForegroundFPS() {
        return foregroundFPS;
    }

    public boolean isFullscreen() {
        return Gdx.graphics.isFullscreen();
    }

    public boolean isBorderlessFullscreen() {
        return isBorderlessFullscreen;
    }

    public void setVSync(boolean vSync) {
        this.vSync = vSync;
        Gdx.graphics.setVSync(vSync);
    }

    public boolean isVSync() {
        return vSync;
    }

    private float averageTimer;
    private float weightedFPSTotal;
    private float averageFPS;

    /** Call once per rendered frame. avgTime is in milliseconds. */
    public float getAverageFrameRate(float avgTime) {
        int fps = Gdx.graphics.getFramesPerSecond();

        if (avgTime <= 0f) {
            return fps;
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        averageTimer += deltaTime;
        weightedFPSTotal += fps * deltaTime;

        if (averageTimer >= avgTime / 1000f) {
            averageFPS = weightedFPSTotal / averageTimer;
            averageTimer = 0f;
            weightedFPSTotal = 0f;
        }

        return averageFPS;
    }

    public int getFrameRate() {
        return Gdx.graphics.getFramesPerSecond();
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public int getTotalMemory(float delay) {
        if (delayHelper(delay)) {
            return getTotalMemory();
        }
        return 0;
    }
    public int getUsedMemory(float delay) {
        if (delayHelper(delay)) {
            return getUsedMemory();
        }
        return 0;
    }
    public String getUsedMemoryWithTotal(float delay) {
        if (delayHelper(delay)) {
            return getUsedMemoryWithTotal();
        }
        return "0/0";
    }
    public int getMaxMemory(float delay) {
        if (delayHelper(delay)) {
            return getMaxMemory();
        }
        return 0;
    }

    public int getTotalMemory() {
        return Math.toIntExact(Runtime.getRuntime().totalMemory() / (1024 * 1024));
    }

    public int getUsedMemory() {
        return Math.toIntExact((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024));
    }
    public String getUsedMemoryWithTotal() {
        return getUsedMemory() + "/" + getTotalMemory();
    }
    public int getMaxMemory() {
        return Math.toIntExact(Runtime.getRuntime().maxMemory() / (1024 * 1024));
    }

    private float delayHelperTimer;
    private boolean delayHelper(float delay) {
        if (delay <= 0f) {
            return true;
        }
        delayHelperTimer += Gdx.graphics.getDeltaTime();
        float delaySec = delay / 1000f;
        if (delayHelperTimer >= delaySec) {
            delayHelperTimer %= delaySec;
            return true;
        }
        return false;
    }

}
