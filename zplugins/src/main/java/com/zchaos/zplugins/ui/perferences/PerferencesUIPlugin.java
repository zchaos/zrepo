package com.zchaos.zplugins.ui.perferences;

import java.util.StringTokenizer;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class PerferencesUIPlugin extends AbstractUIPlugin {
	private static PerferencesUIPlugin plugin;

	//The identifiers for the preferences 
	public static final String BAD_WORDS_PREFERENCE = "badwords";

	public static final String HIGHLIGHT_PREFERENCE = "highlight";

	//The default values for the preferences
	public static final String DEFAULT_BAD_WORDS = "bug;bogus;hack;";

	public static final int DEFAULT_HIGHLIGHT = SWT.COLOR_BLUE;

	public static final String PREFERENCE_DELIMITER = "\r\n";

	public PerferencesUIPlugin() {
		plugin = this;
	}

	
	public PerferencesUIPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		plugin = this;
	}

	public static PerferencesUIPlugin getDefault() {
		if (plugin == null) {
			plugin = new PerferencesUIPlugin();
		}
		return plugin;
	}

	protected void initializeDefaultPreferences(IPreferenceStore store) {
		System.out.println("initializeDefaultPreferences");
		//		store.setDefault(BAD_WORDS_PREFERENCE, DEFAULT_BAD_WORDS);
		//		Color color = Display.getDefault().getSystemColor(DEFAULT_HIGHLIGHT);
		//		PreferenceConverter.setDefault(store, HIGHLIGHT_PREFERENCE, color.getRGB());
	}

	/**
	 * Return the bad words preference default.
	 */
	public String[] getDefaultBadWordsPreference() {
		return convert(getPreferenceStore().getDefaultString(BAD_WORDS_PREFERENCE));
	}

	/**
	 * Returns the bad words preference.
	 */
	public String[] getBadWordsPreference() {
		return convert(getPreferenceStore().getString(BAD_WORDS_PREFERENCE));
	}

	/**
	 * Converts PREFERENCE_DELIMITER delimited String to a String array.
	 */
	private String[] convert(String preferenceValue) {
		StringTokenizer tokenizer = new StringTokenizer(preferenceValue, PREFERENCE_DELIMITER);
		int tokenCount = tokenizer.countTokens();
		String[] elements = new String[tokenCount];
		for (int i = 0; i < tokenCount; i++) {
			elements[i] = tokenizer.nextToken();
		}
		return elements;
	}

	/**
	 * Sets the bad words preference.
	 */
	public void setBadWordsPreference(String[] elements) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			buffer.append(elements[i]);
			buffer.append(PREFERENCE_DELIMITER);
		}
		//		getPreferenceStore().setValue(BAD_WORDS_PREFERENCE, buffer.toString());
	}
}
