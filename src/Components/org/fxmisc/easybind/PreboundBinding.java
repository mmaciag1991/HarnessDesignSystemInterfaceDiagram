package Components.org.fxmisc.easybind;

import Components.org.fxmisc.easybind.monadic.MonadicBinding;
import javafx.beans.Observable;
import javafx.beans.binding.ObjectBinding;

/**
 * Object binding that binds to its dependencies on creation
 * and unbinds from them on dispose.
 */
public abstract class PreboundBinding<T> extends ObjectBinding<T> implements MonadicBinding<T> {
    private final Observable[] dependencies;

    public PreboundBinding(Observable... dependencies) {
        this.dependencies = dependencies;
        bind(dependencies);
    }

    @Override
    public void dispose() {
        unbind(dependencies);
    }
}
