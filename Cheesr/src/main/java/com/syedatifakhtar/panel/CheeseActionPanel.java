package com.syedatifakhtar.panel;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.service.CheeseService;

public class CheeseActionPanel extends Panel {

	@SpringBean(name = "cheeseService")
	private CheeseService cheeseService;
	private Button editCheeseButton;
	private Button deleteCheeseButton;
	private Button saveCheeseButton;
	private Label cheeseName;
	private Label cheeseDescription;
	private TextField<Cheese> cheeseNameTextField;
	private TextField<Cheese> cheeseDescriptionTextField;
	private TextField<Cheese> cheesePriceTextField;
	private Form<Void> cheeseActionForm;
	private Cheese cheese;

	public enum Mode {
		SHOW, EDIT, CREATE
	}

	private Mode mode;

	public CheeseActionPanel(String id, Cheese cheese) {
		super(id);
		this.cheese = cheese;
		this.mode = Mode.SHOW;
		init();
		toggleVisibility();
		attachComponents();
	}

	public CheeseActionPanel(String id, Cheese cheese, Mode mode) {
		super(id);
		this.cheese = cheese;
		this.mode = mode;
		init();
		toggleVisibility();
		attachComponents();
	}

	/*
	 * Toggles component visibility and layout Show EDIT layout when the admin
	 * wants to create/edit a cheese field Display only labels on MODE.SHOW
	 */
	private void toggleVisibility() {

		// Append display:block or display:hidden to hide content
		AttributeAppender showComponent = new AttributeAppender("class",
				new Model<String>("unhide"), " ");
		AttributeAppender hideComponent = new AttributeAppender("class",
				new Model<String>("hide"), " ");
		switch (mode) {
		case SHOW:
			cheeseName.setVisible(true);
			cheeseName.add(showComponent);
			cheeseDescription.setVisible(true);
			cheeseDescription.add(showComponent);
			cheeseNameTextField.setVisible(false);
			cheeseNameTextField.add(hideComponent);
			cheeseDescriptionTextField.setVisible(false);
			cheeseDescriptionTextField.add(hideComponent);
			cheesePriceTextField.setVisible(true);
			cheesePriceTextField.setEnabled(false);
			editCheeseButton.setVisible(true);
			editCheeseButton.add(showComponent);
			deleteCheeseButton.setVisible(true);
			deleteCheeseButton.add(showComponent);
			saveCheeseButton.setVisible(false);
			saveCheeseButton.add(hideComponent);
			break;
		case CREATE:
			;
		case EDIT:
			cheeseName.setVisible(false);
			cheeseName.add(hideComponent);
			cheeseDescription.setVisible(false);
			cheeseDescription.add(hideComponent);
			cheeseNameTextField.setVisible(true);
			cheeseNameTextField.add(showComponent);
			cheeseDescriptionTextField.setVisible(true);
			cheeseDescriptionTextField.add(showComponent);
			cheesePriceTextField.setVisible(true);
			cheesePriceTextField.setEnabled(true);
			editCheeseButton.setVisible(false);
			editCheeseButton.add(hideComponent);
			deleteCheeseButton.setVisible(false);
			deleteCheeseButton.add(hideComponent);
			saveCheeseButton.setVisible(true);
			saveCheeseButton.add(showComponent);
		}
	}

	/*
	 * Initialize all fields
	 */
	private void init() {
		cheeseActionForm = new Form<Void>("cheeseActionForm");
		cheeseActionForm.setOutputMarkupId(true);

		cheeseName = new Label("cheeseName", new PropertyModel<Cheese>(cheese,
				"name"));
		cheeseDescription = new Label("cheeseDescription",
				new PropertyModel<Cheese>(cheese, "description"));
		cheeseDescription.setOutputMarkupId(true);

		cheeseNameTextField = new TextField<Cheese>("cheeseNameTextField",
				new PropertyModel<Cheese>(cheese, "name"));
		cheeseNameTextField.setOutputMarkupId(true);

		cheeseDescriptionTextField = new TextField<Cheese>(
				"cheeseDescriptionTextField", new PropertyModel<Cheese>(cheese,
						"description"));
		cheeseDescriptionTextField.setOutputMarkupId(true);

		cheesePriceTextField = new TextField<Cheese>("cheesePriceTextField",
				new PropertyModel<Cheese>(cheese, "price"));
		cheesePriceTextField.setOutputMarkupId(true);

		editCheeseButton = new Button("editCheeseButton") {
			public void onSubmit() {
				mode = Mode.EDIT;
				toggleVisibility();
			};
		};
		editCheeseButton.setOutputMarkupId(true);

		deleteCheeseButton = new Button("deleteCheeseButton") {
			public void onSubmit() {
				cheeseService.deleteCheese(cheese);
				cheeseActionForm.add(new AttributeModifier("style",
						"display:none")); // Hide the record as a quick measure
			}
		};
		deleteCheeseButton.setOutputMarkupId(true);

		saveCheeseButton = new Button("saveCheeseButton") {
			public void onSubmit() {
				if (mode == mode.CREATE) {
					cheeseService.saveCheese(cheese);
				} else if (mode == mode.EDIT) {
					cheeseService.updateCheese(cheese);
				}
				mode = Mode.SHOW;
				toggleVisibility();
			}
		};
	}

	/**
	 * Attach components to form and form to the page
	 */

	private void attachComponents() {
		cheeseActionForm.add(cheeseName);
		cheeseActionForm.add(cheeseDescription);
		cheeseActionForm.add(cheeseNameTextField);
		cheeseActionForm.add(cheeseDescriptionTextField);
		cheeseActionForm.add(cheesePriceTextField);
		cheeseActionForm.add(editCheeseButton);
		cheeseActionForm.add(deleteCheeseButton);
		cheeseActionForm.add(saveCheeseButton);
		add(cheeseActionForm);
	}

}
