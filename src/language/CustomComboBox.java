package language;

import javafx.scene.control.ComboBox;

public class CustomComboBox extends ComboBox<String> implements
		ILanguageChangeListener {
	private String[] keys;
	private String promptText;

	public CustomComboBox(String[] keys, String promptText) {
		this.keys = keys;
		this.promptText = promptText;
		LanguageManager.addChangeListener(this);
		updateLanguage();
	}

	@Override
	public void updateLanguage() {
		this.setPromptText(LanguageManager.getString(promptText));
		this.getItems().clear();
		for (String key : keys) {
			this.getItems().add(LanguageManager.getString(key));
		}
	}

}
