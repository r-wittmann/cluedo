package language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LanguageManager {
	// private static ResourceBundle bundle;
	private final static String baseName = "languageResources/Language_";
	private static Map<String, StringProperty> dictionary = new HashMap<String, StringProperty>();
	private static List<ILanguageChangeListener> listeners = new ArrayList<ILanguageChangeListener>();

	public LanguageManager() {
		buildHashMap(SupportedLanguages.de);
		buildHashMap(SupportedLanguages.en);
		buildHashMap(SupportedLanguages.fr);
		buildHashMap(SupportedLanguages.it);

	}

	public enum SupportedLanguages {
		de, en, fr, it, es
	}

	public static String getString(String key) {
		return dictionary.get(key).getValue();
	}

	public static void setLanguage(SupportedLanguages language) {
		buildHashMap(language);
		updateListeners();
	}

	private static void buildHashMap(SupportedLanguages language) {
		ResourceBundle bundle;
		try {
			bundle = ResourceBundle.getBundle(baseName + language.toString());
		} catch (MissingResourceException e) {
			throw e;
		}
		if (dictionary.size() == 0) {
			for (String key : bundle.keySet()) {
				StringProperty value = new SimpleStringProperty();
				value.setValue(bundle.getString(key));
				dictionary.put(key, value);
			}
		} else {
			for (String key : bundle.keySet()) {

				dictionary.get(key).setValue(bundle.getString(key));

			}
		}
	}

	public static StringProperty getStringProperty(String key) {
			return dictionary.get(key);
	}
	
	
	public static void addChangeListener(ILanguageChangeListener listener){
		listeners.add(listener);
	}
	
	private static void updateListeners(){
		for(ILanguageChangeListener listener:listeners){
			listener.updateLanguage();
		}
	}

}

// statische hashmap mit keys aus properties
// private methode buildhashmap nimmt string entgegen
// aus konstruktor und setLanguage aufgerufen

