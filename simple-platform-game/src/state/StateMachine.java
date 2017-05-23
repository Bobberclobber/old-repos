package se.scramble_studios.nehro.simple_platform_game.java.state;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Josef on 28/03/2015.
 * <p>
 *     The state machine handles different states and can be a state itself
 * </p>
 */
public class StateMachine implements State {
    // Contains all states of this state machine
    private final Map<String, State> states;

    // Current active state
    private State currentState;

    // Creates a state machine
    public StateMachine() {
        states = new HashMap<>();
        currentState = new EmptyState();
        states.put(null, currentState);
    }

    // Adds a state with specified name
    public void add(String name, State state) {
        states.put(name, state);
    }

    // Changes the current state
    public void change(String name) {
        currentState.exit();
        currentState = states.get(name);
        currentState.enter();
    }

    // Handles input
    public void input(float delta) {
        currentState.input(delta);
    }

    // Updates the game
    public void update() {
        currentState.update();
    }

    public void update(float delta) {
        currentState.update(delta);
    }

    // Handles rendering
    public void render() {
        currentState.render();
    }

    public void render(float alpha) {
        currentState.render(alpha);
    }

    // Enters the state
    @Override
    public void enter() {
        currentState.enter();
    }

    // Exits the state
    @Override
    public void exit() {
        currentState.exit();
    }
}
