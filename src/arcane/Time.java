package arcane;

import arcane.math.MathUtils;
import arcane.extra.TimeEx;
import com.badlogic.gdx.Gdx;

public class Time {
    private static float gameSpeed = 1.0f; //default x1 speed
    private static boolean paused;

    public static final int MAX_TICK_PER_FRAME = 5; // Maximum number of ticks to process per frame
    public static final float UPDATE_INTERVAL = 1f / 60f; // 60 updates per second
    public static final float maxDeltaUpdate = 0.25f;
    public static final float maxGameSpeed = Float.MAX_VALUE; //maximum game speed multiplier

    private static float realDelta;
    private static float gameDelta;

    ////game accumulated ticks
    protected static float tickAccumulated;

    //TODO: implement next tick/previous tick update

    public static int accelerate() {
        if (!paused) {

            updateDelta();
            updateGameDelta(delta(), getGameSpeed());

            int ticks = 0;
            if (!isPaused()) {
                ticks = accelerateTick();
            }

            TimeEx.accumulate();
            return ticks;
        }

        return 0;
    }

    public static int accelerateTick() {
        tickAccumulated += gameDelta();

        int tick = 0;
        while (tickAccumulated >= UPDATE_INTERVAL && tick < MAX_TICK_PER_FRAME) {
            tickAccumulated -= UPDATE_INTERVAL;
            tick++;
        }

        TimeEx.accumulateTick(tick, delta());
        return tick;
    }

    public static int getTargetUPS() {
        return Math.round(1f / UPDATE_INTERVAL);
    }

    public static void setGameSpeed(float gameSpeed) {
        Time.gameSpeed = MathUtils.clamp(gameSpeed, 0f, Time.maxGameSpeed);
    }

    public static float getGameSpeed() {
        return Time.gameSpeed;
    }

    public static void pause() {
        Time.paused = true;
    }

    public static void resume() {
        Time.paused = false;
    }

    public static boolean isPaused() {
        return Time.paused;
    }

    private static float GdxDelta() {
        return Gdx.graphics.getDeltaTime();
    }

    private static void updateDelta() {
        Time.realDelta = Math.min(GdxDelta(), Time.maxDeltaUpdate);
    }

    private static void updateGameDelta(float delta, float gameSpeed) {
        Time.gameDelta = delta * gameSpeed;
    }

    public static float delta() {
        return realDelta;
    }

    public static float gameDelta() {
        return gameDelta;
    }
}
