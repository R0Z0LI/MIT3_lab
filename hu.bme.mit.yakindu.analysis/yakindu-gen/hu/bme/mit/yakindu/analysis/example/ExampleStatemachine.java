/** Generated by YAKINDU Statechart Tools code generator. */
package hu.bme.mit.yakindu.analysis.example;

import hu.bme.mit.yakindu.analysis.ITimer;

public class ExampleStatemachine implements IExampleStatemachine {
	protected class SCInterfaceImpl implements SCInterface {
	
		private boolean go;
		
		public void raiseGo() {
			go = true;
		}
		
		private boolean red;
		
		public void raiseRed() {
			red = true;
		}
		
		private boolean green;
		
		public void raiseGreen() {
			green = true;
		}
		
		private long redTime;
		
		public long getRedTime() {
			return redTime;
		}
		
		public void setRedTime(long value) {
			this.redTime = value;
		}
		
		private long greenTime;
		
		public long getGreenTime() {
			return greenTime;
		}
		
		public void setGreenTime(long value) {
			this.greenTime = value;
		}
		
		protected void clearEvents() {
			go = false;
			red = false;
			green = false;
		}
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_Init,
		main_region_Green,
		main_region_Red,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[2];
	public ExampleStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setRedTime(60);
		
		sCInterface.setGreenTime(60);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
				"The state machine needs to be initialized first by calling the init() function."
			);
		}
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		enterSequence_main_region_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_Init:
				main_region_Init_react(true);
				break;
			case main_region_Green:
				main_region_Green_react(true);
				break;
			case main_region_Red:
				main_region_Red_react(true);
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
	public void exit() {
		exitSequence_main_region();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCInterface.clearEvents();
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_Init:
			return stateVector[0] == State.main_region_Init;
		case main_region_Green:
			return stateVector[0] == State.main_region_Green;
		case main_region_Red:
			return stateVector[0] == State.main_region_Red;
		default:
			return false;
		}
	}
	
	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correctly
	* executed.
	* 
	* @param timer
	*/
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}
	
	public void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public void raiseGo() {
		sCInterface.raiseGo();
	}
	
	public void raiseRed() {
		sCInterface.raiseRed();
	}
	
	public void raiseGreen() {
		sCInterface.raiseGreen();
	}
	
	public long getRedTime() {
		return sCInterface.getRedTime();
	}
	
	public void setRedTime(long value) {
		sCInterface.setRedTime(value);
	}
	
	public long getGreenTime() {
		return sCInterface.getGreenTime();
	}
	
	public void setGreenTime(long value) {
		sCInterface.setGreenTime(value);
	}
	
	/* Entry action for state 'Green'. */
	private void entryAction_main_region_Green() {
		timer.setTimer(this, 0, (1 * 1000), false);
	}
	
	/* Entry action for state 'Red'. */
	private void entryAction_main_region_Red() {
		timer.setTimer(this, 1, (1 * 1000), false);
	}
	
	/* Exit action for state 'Green'. */
	private void exitAction_main_region_Green() {
		timer.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'Red'. */
	private void exitAction_main_region_Red() {
		timer.unsetTimer(this, 1);
	}
	
	/* 'default' enter sequence for state Init */
	private void enterSequence_main_region_Init_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Init;
	}
	
	/* 'default' enter sequence for state Green */
	private void enterSequence_main_region_Green_default() {
		entryAction_main_region_Green();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Green;
	}
	
	/* 'default' enter sequence for state Red */
	private void enterSequence_main_region_Red_default() {
		entryAction_main_region_Red();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Red;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state Init */
	private void exitSequence_main_region_Init() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Green */
	private void exitSequence_main_region_Green() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_Green();
	}
	
	/* Default exit sequence for state Red */
	private void exitSequence_main_region_Red() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_Red();
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_Init:
			exitSequence_main_region_Init();
			break;
		case main_region_Green:
			exitSequence_main_region_Green();
			break;
		case main_region_Red:
			exitSequence_main_region_Red();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Init_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_Init_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (react()==false) {
				if (sCInterface.go) {
					exitSequence_main_region_Init();
					enterSequence_main_region_Red_default();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_Green_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (react()==false) {
				if (sCInterface.green) {
					exitSequence_main_region_Green();
					enterSequence_main_region_Red_default();
				} else {
					if (timeEvents[0]) {
						exitSequence_main_region_Green();
						sCInterface.setGreenTime(sCInterface.getGreenTime() - 1);
						
						enterSequence_main_region_Green_default();
					} else {
						did_transition = false;
					}
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_Red_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (react()==false) {
				if (sCInterface.red) {
					exitSequence_main_region_Red();
					enterSequence_main_region_Green_default();
				} else {
					if (timeEvents[1]) {
						exitSequence_main_region_Red();
						sCInterface.setRedTime(sCInterface.getRedTime() - 1);
						
						enterSequence_main_region_Red_default();
					} else {
						did_transition = false;
					}
				}
			}
		}
		return did_transition;
	}
	
}
