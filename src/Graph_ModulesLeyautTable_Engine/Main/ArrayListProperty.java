package Graph_ModulesLeyautTable_Engine.Main;

import com.sun.javafx.binding.ExpressionHelper;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;

public class ArrayListProperty<E> extends ArrayList<E> implements ObservableValue<Number> {
    private ExpressionHelper<Number> helper = null;
    private SimpleIntegerProperty sizeProperty;

    public ArrayListProperty(){
        super();
        sizeProperty = new SimpleIntegerProperty(0);
    }

    @Override
    public boolean add(E e) {
        boolean returnValue = super.add(e);
        sizeProperty.set(size());
        fireValueChangedEvent();
        return returnValue;
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
        sizeProperty.set(size());
        fireValueChangedEvent();
    }

    @Override
    public E remove(int index) {
        E returnValue = super.remove(index);
        sizeProperty.set(size());
        fireValueChangedEvent();
        return returnValue;
    }

    @Override
    public boolean remove(Object o) {
        boolean returnValue = super.remove(o);
        if(returnValue){
            sizeProperty.set(size());
            fireValueChangedEvent();
        }
        return returnValue;
    }

    protected void fireValueChangedEvent(){
        ExpressionHelper.fireValueChangedEvent(helper);
    }

    @Override
    public void addListener(ChangeListener<? super Number> listener) {
        helper = ExpressionHelper.addListener(helper, sizeProperty, listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Number> listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }

    @Override
    public Number getValue() {
        return null;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        helper = ExpressionHelper.addListener(helper, sizeProperty, listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }
}