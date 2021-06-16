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

import com.liferay.docs.guestbook.exception.GuestbookNameException;
import com.liferay.docs.guestbook.model.GuestBook;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.service.GuestBookEntryLocalService;
import com.liferay.docs.guestbook.service.base.GuestBookLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the guest book local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.docs.guestbook.service.GuestBookLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author liferay
 * @see GuestBookLocalServiceBaseImpl
 */
@Component(
        property = "model.class.name=com.liferay.docs.guestbook.model.GuestBook",
        service = AopService.class
)
public class GuestBookLocalServiceImpl extends GuestBookLocalServiceBaseImpl {

    @Reference
    private GuestBookEntryLocalService guestBookEntryLocalService;
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Use <code>com.liferay.docs.guestbook.service.GuestBookLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.docs.guestbook.service.GuestBookLocalServiceUtil</code>.
     */

    public GuestBook addGuestBook(long userId, String name, ServiceContext serviceContext) throws PortalException {
        long groupId = serviceContext.getScopeGroupId();
        User user = userLocalService.getUserById(userId);
        Date date = new Date();
        validate(name);
        long guestbookId = counterLocalService.increment();
        GuestBook guestBook = guestBookPersistence.create(guestbookId);
        guestBook.setUuid(serviceContext.getUuid());
        guestBook.setUserId(userId);
        guestBook.setGroupId(groupId);
        guestBook.setCompanyId(user.getCompanyId());
        guestBook.setUserName(user.getFullName());
        guestBook.setCreateDate(serviceContext.getCreateDate(date));
        guestBook.setModifiedDate(serviceContext.getModifiedDate(date));
        guestBook.setName(name);
        // i dont know what is this !!!
        guestBook.setExpandoBridgeAttributes(serviceContext);
        guestBookPersistence.update(guestBook);

        //register resource for permission system
        resourceLocalService.addResources(user.getCompanyId(), groupId, userId, GuestBook.class.getName(), guestbookId, false, true, true);
        return guestBook;
    }


    //methods for getting guestbooks
    //depends on G_G finder
    public List<GuestBook> getGuestbooks(long groupId) {
        return guestBookPersistence.findByGroupId(groupId);
    }

    public List<GuestBook> getGuestbooks(long groupId, int start, int end, OrderByComparator<GuestBook> obc) {
        return guestBookPersistence.findByGroupId(groupId, start, end, obc);
    }

    public List<GuestBook> getGuestbooks(long groupId, int start, int end) {
        return guestBookPersistence.findByGroupId(groupId, start, end);
    }

    public int getGuestBooksCount(long groupId) {
        return guestBookPersistence.countByGroupId(groupId);
    }

    private void validate(String name) throws GuestbookNameException {
        if (Validator.isNull(name))
            throw new GuestbookNameException();
    }

    public GuestBook updateGuestbook(long userId, long guestbookId, String name, ServiceContext serviceContext) throws PortalException {
        Date now = new Date();
        validate(name);
        User user = userLocalService.getUser(userId);
        GuestBook guestBook = getGuestBook(guestbookId);

        guestBook.setName(name);
        guestBook.setUserName(user.getFullName());
        guestBook.setUserId(userId);
        guestBook.setModifiedDate(serviceContext.getModifiedDate(now));
        guestBook.setExpandoBridgeAttributes(serviceContext);
        guestBookPersistence.update(guestBook);
        resourceLocalService.updateResources(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), GuestBook.class.getName(), guestbookId, serviceContext.getModelPermissions());
        return guestBook;
    }

    public GuestBook deleteGuestbook(long guestbookId, ServiceContext serviceContext) throws PortalException {
        GuestBook guestBook = getGuestBook(guestbookId);
        List<GuestBookEntry> entryList = guestBookEntryLocalService.getGuestbookEntries(serviceContext.getScopeGroupId(), guestbookId);
        for (GuestBookEntry entry : entryList) {
            guestBookEntryLocalService.deleteGuestBookEntry(entry.getEntryId());
        }
        guestBookPersistence.remove(guestbookId);
        resourceLocalService.deleteResource(serviceContext.getCompanyId(), GuestBook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, guestbookId);
        return guestBook;

    }
}