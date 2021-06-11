package Components.org.fxmisc.easybind.select;

import Components.org.fxmisc.easybind.monadic.MonadicBinding;
import javafx.beans.value.ObservableValue;

import java.util.function.Function;

public interface SelectBuilder<T> {
    <U> SelectBuilder<U> select(Function<? super T, ObservableValue<U>> selector);
    <U> MonadicBinding<U> selectObject(Function<? super T, ObservableValue<U>> selector);

    static <T> SelectBuilder<T> startAt(ObservableValue<T> selectionRoot) {
        return new RootSelectedBuilder<T>(selectionRoot);
    }
}