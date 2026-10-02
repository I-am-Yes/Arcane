package arcane;

public interface Cancellable {
    void cancel();
    boolean isCancelled();
}
