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

import java.util.Map;

import org.eclipse.core.runtime.preferences.IPreferenceFilter;
import org.eclipse.core.runtime.preferences.InstanceScope;

//--------------------------------------------------------------------------
/**
 * Filter used when importing preferences.
 * 
 * @author Tony Allevato
 */
public class InstancePreferenceFilter implements IPreferenceFilter
{
    // ----------------------------------------------------------
	public String[] getScopes()
	{
		return new String[] { InstanceScope.SCOPE };
	}


    // ----------------------------------------------------------
	@SuppressWarnings("rawtypes")
	public Map getMapping(String scope)
	{
		return null;
	}
}