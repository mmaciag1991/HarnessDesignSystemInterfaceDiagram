package Components.org.reactfx;

import Components.org.reactfx.util.NotificationAccumulator;

import java.util.function.Consumer;


class RecursiveStream<T> extends EventStreamBase<T> {
    private final EventStream<T> input;

    public RecursiveStream(
            EventStream<T> input,
            NotificationAccumulator<Consumer<? super T>, T, ?> pn) {
        super(pn);
        this.input = input;
    }

    @Override
    protected Subscription observeInputs() {
        return input.subscribe(this::emit);
    }
}