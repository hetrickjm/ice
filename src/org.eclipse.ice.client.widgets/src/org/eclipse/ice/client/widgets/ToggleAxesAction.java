package org.eclipse.ice.client.widgets;

import org.eclipse.ice.client.widgets.jme.ViewAppState;
import org.eclipse.jface.action.Action;

/**
 * This <code>Action</code> toggles the axes for a jME {@link ViewAppState}.
 * 
 * @author Jordan Deyton
 * 
 */
public class ToggleAxesAction extends Action {

	/**
	 * The jME view whose axes can be toggled.
	 */
	private final ViewAppState view;

	/**
	 * The default constructor.
	 * 
	 * @param view
	 *            The jME view whose axes can be toggled.
	 */
	public ToggleAxesAction(ViewAppState view) {
		// Set the ViewAppState and update the action's text and tool tip.
		this.view = view;
		updateStrings();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		// Toggle the view's HUD and update the text and tool tip.
		view.setDisplayAxes(!view.getDisplayAxes());
		updateStrings();
	}

	/**
	 * Updates the text and tool tip of the action depending on whether the HUD
	 * is visible.
	 */
	private void updateStrings() {

		// If the HUD is displayed, set the text for hiding the HUD.
		if (view.getDisplayAxes()) {
			setText("Hide Axes");
			setToolTipText("Hide the axes in the view");
		}
		// Otherwise, set the text for showing the HUD.
		else {
			setText("Show Axes");
			setToolTipText("Show the axes in the view");
		}

		return;
	}
}
