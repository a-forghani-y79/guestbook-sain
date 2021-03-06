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

package com.liferay.docs.guestbook.service;

import com.liferay.docs.guestbook.exception.NoSuchGuestBookEntryException;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for GuestBookEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author liferay
 * @see GuestBookEntryLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface GuestBookEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.docs.guestbook.service.impl.GuestBookEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the guest book entry local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link GuestBookEntryLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the guest book entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GuestBookEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param guestBookEntry the guest book entry
	 * @return the guest book entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public GuestBookEntry addGuestBookEntry(GuestBookEntry guestBookEntry);

	public GuestBookEntry addGuestbookEntry(
			long userId, long guestbookId, String name, String email,
			String message, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new guest book entry with the primary key. Does not add the guest book entry to the database.
	 *
	 * @param entryId the primary key for the new guest book entry
	 * @return the new guest book entry
	 */
	@Transactional(enabled = false)
	public GuestBookEntry createGuestBookEntry(long entryId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public GuestBookEntry deleteGuestbookEntry(GuestBookEntry entry);

	/**
	 * Deletes the guest book entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GuestBookEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param guestBookEntry the guest book entry
	 * @return the guest book entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public GuestBookEntry deleteGuestBookEntry(GuestBookEntry guestBookEntry);

	public GuestBookEntry deleteGuestbookEntry(long entryId)
		throws NoSuchGuestBookEntryException;

	/**
	 * Deletes the guest book entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GuestBookEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry that was removed
	 * @throws PortalException if a guest book entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public GuestBookEntry deleteGuestBookEntry(long entryId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.docs.guestbook.model.impl.GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.docs.guestbook.model.impl.GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GuestBookEntry fetchGuestBookEntry(long entryId);

	/**
	 * Returns the guest book entry matching the UUID and group.
	 *
	 * @param uuid the guest book entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GuestBookEntry fetchGuestBookEntryByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	/**
	 * Returns a range of all the guest book entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.docs.guestbook.model.impl.GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @return the range of guest book entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GuestBookEntry> getGuestBookEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GuestBookEntry> getGuestbookEntries(
		long groupId, long guestbookId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GuestBookEntry> getGuestbookEntries(
			long groupId, long guestbookId, int start, int end)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GuestBookEntry> getGuestbookEntries(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<GuestBookEntry> obc);

	/**
	 * Returns all the guest book entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the guest book entries
	 * @param companyId the primary key of the company
	 * @return the matching guest book entries, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GuestBookEntry> getGuestBookEntriesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of guest book entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the guest book entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching guest book entries, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GuestBookEntry> getGuestBookEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator);

	/**
	 * Returns the number of guest book entries.
	 *
	 * @return the number of guest book entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGuestBookEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGuestbookEntriesCount(long groupId, long guestbookId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GuestBookEntry getGuestbookEntry(long entryId)
		throws PortalException;

	/**
	 * Returns the guest book entry with the primary key.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry
	 * @throws PortalException if a guest book entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GuestBookEntry getGuestBookEntry(long entryId)
		throws PortalException;

	/**
	 * Returns the guest book entry matching the UUID and group.
	 *
	 * @param uuid the guest book entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching guest book entry
	 * @throws PortalException if a matching guest book entry could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GuestBookEntry getGuestBookEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Updates the guest book entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GuestBookEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param guestBookEntry the guest book entry
	 * @return the guest book entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public GuestBookEntry updateGuestBookEntry(GuestBookEntry guestBookEntry);

	public GuestBookEntry updateGuestbookEntry(
			long userId, long guestbookId, long entryId, String name,
			String email, String message, ServiceContext serviceContext)
		throws PortalException;

}