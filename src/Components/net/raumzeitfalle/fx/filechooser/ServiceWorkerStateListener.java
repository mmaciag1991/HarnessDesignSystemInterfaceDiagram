package Components.net.raumzeitfalle.fx.filechooser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Worker.State;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

class ServiceWorkerStateListener<R> implements ChangeListener<State> {
	
	private final CompletableFuture<ServiceExecResult<R>> serviceTestResult = new CompletableFuture<>();
	
	private final Supplier<R> valueSource;
	
	public static <R> ServiceWorkerStateListener<R> with(Service<R> serviceUnderTest) {
		
		ServiceWorkerStateListener<R> listener = new ServiceWorkerStateListener<>(serviceUnderTest);
		serviceUnderTest.stateProperty().addListener(listener);
		
		return listener;
	}
	
	public ServiceWorkerStateListener(Service<R> serviceUnderTest) {
		this(()->serviceUnderTest.getValue());
	}
	
	public ServiceWorkerStateListener(Supplier<R> valueSource) {
		this.valueSource = valueSource;
	}
	
	@Override
	public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
		if (newValue == State.SUCCEEDED) {
			serviceTestResult.complete(new ServiceExecResult<R>(newValue, valueSource.get()));
		}
		
		if (newValue == State.CANCELLED) {
			serviceTestResult.complete(new ServiceExecResult<R>(newValue, valueSource.get()));
		}
		
		if (newValue == State.FAILED) {
			serviceTestResult.complete(new ServiceExecResult<R>(newValue, valueSource.get()));
		}
		
	}
	
	public CompletableFuture<ServiceExecResult<R>> getServiceResult() {
		return serviceTestResult;
	}
	
	static class ServiceExecResult<R> {
		
		private final State serviceState;
		
		private final R result;

		public ServiceExecResult(State serviceState, R result) {
			this.serviceState = serviceState;
			this.result = result;
		}
		
		public State getServiceState() {
			return serviceState;
		}

		public R getResult() {
			return result;
		}
	}
}
