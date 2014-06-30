package com.zchaos.zplugins.perferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.zchaos.zplugins.ui.perferences.PerferencesUIPlugin;

public class PerferencesPage extends PreferencePage

implements IWorkbenchPreferencePage {
	//The list that displays the current bad words
	private List badWordList;

	//The newEntryText is the text where new bad words are specified
	private Text newEntryText;

	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite entryTable = new Composite(parent, SWT.NULL);
		//Create a data that takes up the extra space in the dialog .
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		entryTable.setLayoutData(data);
		GridLayout layout = new GridLayout();
		entryTable.setLayout(layout);
		//Add in a dummy label for spacing
		new Label(entryTable, SWT.NONE);
		badWordList = new List(entryTable, SWT.BORDER);
		badWordList.setItems(PerferencesUIPlugin.getDefault().getBadWordsPreference());
		//Create a data that takes up the extra space in the dialog and spans both columns.
		data = new GridData(GridData.FILL_BOTH);
		badWordList.setLayoutData(data);
		Composite buttonComposite = new Composite(entryTable, SWT.NULL);
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.numColumns = 2;
		buttonComposite.setLayout(buttonLayout);
		//Create a data that takes up the extra space in the dialog and spans both columns.
		data = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING);
		buttonComposite.setLayoutData(data);
		Button addButton = new Button(buttonComposite, SWT.PUSH | SWT.CENTER);
		addButton.setText("Add to List"); //$NON-NLS-1$
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				badWordList.add(newEntryText.getText(), badWordList.getItemCount());
			}
		});
		newEntryText = new Text(buttonComposite, SWT.BORDER);
		//Create a data that takes up the extra space in the dialog .
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		newEntryText.setLayoutData(data);
		Button removeButton = new Button(buttonComposite, SWT.PUSH | SWT.CENTER);
		removeButton.setText("Remove Selection"); //$NON-NLS-1$
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				int index = badWordList.getSelectionIndex();
				if (index >= 0) {
					badWordList.remove(index);
				}
			}
		});
		data = new GridData();
		data.horizontalSpan = 2;
		removeButton.setLayoutData(data);
		return entryTable;
	}

	/*
	 * @see IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		//Initialize the preference store we wish to use
		setPreferenceStore(PerferencesUIPlugin.getDefault().getPreferenceStore());
	}

	/**
	 * Performs special processing when this page's Restore Defaults button has been pressed.
	 * Sets the contents of the nameEntry field to
	 * be the default
	 */
	protected void performDefaults() {
		badWordList.setItems(PerferencesUIPlugin.getDefault().getDefaultBadWordsPreference());
	}

	/**
	 * Method declared on IPreferencePage. Save the
	 * author name to the preference store.
	 */
	public boolean performOk() {
		PerferencesUIPlugin.getDefault().setBadWordsPreference(badWordList.getItems());
		return super.performOk();

	}
}
