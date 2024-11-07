package org.smart4j.framework.event.state;

import java.util.HashMap;
import java.util.Map;

public class StateMachine<S, E> {

	private final Map<S, Map<E, S>> transitions = new HashMap<>();
	private S currentState;

	public StateMachine(S initialState) {
		this.currentState = initialState;
	}

	public S getCurrentState() {
		return currentState;
	}

	public void setCurrentState(S state) {
		this.currentState = state;
	}

	public void handleEvent(E eventType) throws IllegalStateException {
		S nextState = getNextState(currentState, eventType);
		if (nextState != null) {
			currentState = nextState;
		} else {
			throw new IllegalStateException("Invalid transition for event: " + eventType);
		}
	}

	// 新增方法：根據當前狀態和事件取得下一個狀態（不改變 currentState）
	public S getNextState(S fromState, E eventType) {
		Map<E, S> stateTransitions = transitions.get(fromState);
		return (stateTransitions != null) ? stateTransitions.get(eventType) : null;
	}

	public void addTransition(S fromState, E eventType, S toState) {
		transitions.computeIfAbsent(fromState, k -> new HashMap<>()).put(eventType, toState);
	}

}
