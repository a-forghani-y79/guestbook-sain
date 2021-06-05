/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.model.impl;

import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.service.GuestBookEntryLocalServiceUtil;

/**
 * The extended model base implementation for the GuestBookEntry service. Represents a row in the &quot;GB_GuestBookEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GuestBookEntryImpl}.
 * </p>
 *
 * @author liferay
 * @see GuestBookEntryImpl
 * @see GuestBookEntry
 * @generated
 */
public abstract class GuestBookEntryBaseImpl
	extends GuestBookEntryModelImpl implements GuestBookEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a guest book entry model instance should use the <code>GuestBookEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			GuestBookEntryLocalServiceUtil.addGuestBookEntry(this);
		}
		else {
			GuestBookEntryLocalServiceUtil.updateGuestBookEntry(this);
		}
	}

}