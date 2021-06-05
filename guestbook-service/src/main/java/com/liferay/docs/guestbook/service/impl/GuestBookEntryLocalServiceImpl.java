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
import com.liferay.docs.guestbook.exception.NoSuchGuestBookEntryException;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.service.base.GuestBookEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

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
    public GuestBookEntry updateGuestbookEntry(long userId , long guestbookId, long entryId,
                                               String name , String email , String message,
                                               ServiceContext serviceContext) throws PortalException {
        User user = userLocalService.getUserById(userId);
        Date date = new Date();
        validate(name, email, message);
        GuestBookEntry entry = guestBookEntryPersistence.findByPrimaryKey(guestbookId);
        entry.setUserId(userId);
        entry.setUserName(user.getFullName());
        entry.setModifiedDate(serviceContext.getModifiedDate(date));
        entry.setName(name);
        entry.setEmail(email);
        entry.setMessage(message);
        entry.setExpandoBridgeAttributes(serviceContext);
        guestBookEntryPersistence.update(entry);
        return entry;
    }
    public GuestBookEntry deleteGuestbookEntry(GuestBookEntry entry){
        guestBookEntryPersistence.remove(entry);
        return entry;
    }
    public GuestBookEntry deleteGuestbookEntry(long entryId) throws NoSuchGuestBookEntryException {
        GuestBookEntry entry = guestBookEntryPersistence.findByPrimaryKey(entryId);
        return deleteGuestbookEntry(entry);
    }

    public List<GuestBookEntry> getGuestbookEntries(long groupId, long guestbookId){
        return guestBookEntryPersistence.findByG_G(groupId,guestbookId);
    }
    public List<GuestBookEntry> getGuestbookEntries(long groupId, long guestbookId,
                                                    int start, int end) throws SystemException {

        return guestBookEntryPersistence.findByG_G(groupId, guestbookId, start,
                end);
    }

    public List<GuestBookEntry> getGuestbookEntries(long groupId, long guestbookId,
                                                    int start, int end, OrderByComparator<GuestBookEntry> obc) {

        return guestBookEntryPersistence.findByG_G(groupId, guestbookId, start,
                end, obc);
    }

    public GuestBookEntry getGuestbookEntry(long entryId) throws PortalException {
        return guestBookEntryPersistence.findByPrimaryKey(entryId);
    }

    public int getGuestbookEntriesCount(long groupId, long guestbookId) {
        return guestBookEntryPersistence.countByG_G(groupId, guestbookId);
    }
}
