/*==========================================================================*\
 |  Copyright (C) 2012 Virginia Tech
 |
 |  This file is part of Web-CAT Eclipse Plugins.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU General Public License as published by
 |  the Free Software Foundation; either version 2 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU General Public License along
 |  with Web-CAT; if not, see <http://www.gnu.org/licenses/>.
\*==========================================================================*/

package org.webcat.eclipse.projectlink.importer;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import org.eclipse.jface.preference.IPreferenceStore;
import org.webcat.eclipse.projectlink.Activator;
import org.webcat.eclipse.projectlink.preferences.IPreferencesConstants;

//--------------------------------------------------------------------------
/**
 * Keeps track of preferences that have been imported during an assignment
 * import process so that they aren't imported multiple times.
 * 
 * @author Ellen Boyd, Tony Allevato
 */
public class ImportedPreferences
{
	private static ImportedPreferences instance;

	private Properties history;


	// ----------------------------------------------------------
	private ImportedPreferences()
	{
		history = new Properties();
		loadPluginPreferences();
	}


	// ----------------------------------------------------------
	public synchronized static ImportedPreferences getInstance()
	{
		if (instance == null)
		{
			instance = new ImportedPreferences();
		}
		
		return instance;
	}
	
	
	// ----------------------------------------------------------
	public Date uriLastImported(String uri)
	{
		if (history.containsKey(uri))
		{
			long timestamp = Long.parseLong(history.getProperty(uri));
			return new Date(timestamp);
		}
		else
		{
			return null;
		}
	}
	
	
	// ----------------------------------------------------------
	public void trackUri(String uri)
	{
		history.setProperty(uri, Long.toString(new Date().getTime()));
		updatePluginPreferences();
	}
	

	// ----------------------------------------------------------
	private void loadPluginPreferences()
	{
		IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
		
		String historyString =
				prefs.getString(IPreferencesConstants.IMPORTED_PREFERENCES);

		if (historyString.length() > 0)
		{
			StringReader reader = new StringReader(historyString);
			history = new Properties();
			
			try
			{
				history.load(reader);
			}
			catch (IOException e)
			{
				// Do nothing.
			}
		}
	}


	// ----------------------------------------------------------
	private void updatePluginPreferences()
	{
		IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();

		try
		{
			StringWriter writer = new StringWriter();
			history.store(writer, null);

			prefs.setValue(IPreferencesConstants.IMPORTED_PREFERENCES,
					writer.toString());
		}
		catch (IOException e)
		{
			// Do nothing.
		}
	}
}
