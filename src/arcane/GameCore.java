package arcane;

public abstract class GameCore implements ApplicationListener {
    protected ApplicationListener[] children = new ApplicationListener[0];

    public void add(ApplicationListener child) {
        ApplicationListener[] expanded = new ApplicationListener[children.length + 1];
        System.arraycopy(children, 0, expanded, 0, children.length);
        expanded[children.length] = child;
        children = expanded;
    }

    @Override
    public void init() {
        for (ApplicationListener child : children) {
            child.init();
        }
    }

    @Override
    public void update() {
        for (ApplicationListener child : children) {
            child.update();
        }
    }

    @Override
    public void resize(int width, int height) {
        for (ApplicationListener child : children) {
            child.resize(width, height);
        }
    }

    @Override
    public void pause() {
        for (ApplicationListener child : children) {
            child.pause();
        }
    }

    @Override
    public void resume() {
        for (ApplicationListener child : children) {
            child.resume();
        }
    }

    @Override
    public void dispose() {
        for (ApplicationListener child : children) {
            child.dispose();
        }
    }
}
