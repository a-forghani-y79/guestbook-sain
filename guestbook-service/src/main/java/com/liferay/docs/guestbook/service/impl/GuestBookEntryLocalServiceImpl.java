/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.service.impl;

import com.liferay.docs.guestbook.exception.GuestbookEntryEmailException;
import com.liferay.docs.guestbook.exception.GuestbookEntryMessageException;
import com.liferay.docs.guestbook.exception.GuestbookEntryNameException;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.service.base.GuestBookEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

/**
 * The implementation of the guest book entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.docs.guestbook.service.GuestBookEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author liferay
 * @see GuestBookEntryLocalServiceBaseImpl
 */
@Component(
        property = "model.class.name=com.liferay.docs.guestbook.model.GuestBookEntry",
        service = AopService.class
)
public class GuestBookEntryLocalServiceImpl
        extends GuestBookEntryLocalServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Use <code>com.liferay.docs.guestbook.service.GuestBookEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.docs.guestbook.service.GuestBookEntryLocalServiceUtil</code>.
     */

    public GuestBookEntry addGuestbookEntry(long userId, long guestbookId, String name,
                                            String email, String message, ServiceContext serviceContext) throws PortalException {
        long groupId = serviceContext.getScopeGroupId();
        User user = userLocalService.getUser(userId);
        Date date = new Date();
        validate(name, email, message);
        long entryId = counterLocalService.increment();
        GuestBookEntry entry = guestBookEntryPersistence.create(entryId);
        entry.setUuid(serviceContext.getUuid());
        entry.setUserId(userId);
        entry.setGroupId(groupId);
        entry.setCompanyId(user.getCompanyId());
        entry.setUserName(user.getFullName());
        entry.setCreateDate(serviceContext.getCreateDate(date));
        entry.setModifiedDate(serviceContext.getModifiedDate(date));
        entry.setExpandoBridgeAttributes(serviceContext);
        entry.setGuestbookId(guestbookId);
        entry.setName(name);
        entry.setEmail(email);
        entry.setMessage(message);
        guestBookEntryPersistence.update(entry);

        return entry;

    }

    private void validate(String name, String email, String message) throws PortalException {
        if (Validator.isNull(name))
            throw new GuestbookEntryNameException();
        if (!Validator.isEmailAddress(email))
            throw new GuestbookEntryEmailException();
        if (Validator.isNull(message))
            throw new GuestbookEntryMessageException();
    }
}