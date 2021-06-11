package Components.org.fxmisc.easybind.select;

import Components.org.fxmisc.easybind.monadic.MonadicBinding;
import javafx.beans.value.ObservableValue;

class RootSelectedBuilder<T> implements ParentSelectedBuilder<T> {
    private final ObservableValue<T> root;

    public RootSelectedBuilder(ObservableValue<T> root) {
        this.root = root;
    }

    @Override
    public <U> MonadicBinding<U> create(
            NestedSelectionElementFactory<T, U> nestedSelectionFactory) {
        return new SelectObjectBinding<T, U>(root, nestedSelectionFactory);
    }
}