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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for GuestBookEntry. This utility wraps
 * <code>com.liferay.docs.guestbook.service.impl.GuestBookEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author liferay
 * @see GuestBookEntryLocalService
 * @generated
 */
public class GuestBookEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.docs.guestbook.service.impl.GuestBookEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
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
	public static com.liferay.docs.guestbook.model.GuestBookEntry
		addGuestBookEntry(
			com.liferay.docs.guestbook.model.GuestBookEntry guestBookEntry) {

		return getService().addGuestBookEntry(guestBookEntry);
	}

	public static com.liferay.docs.guestbook.model.GuestBookEntry
			addGuestbookEntry(
				long userId, long guestbookId, String name, String email,
				String message,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addGuestbookEntry(
			userId, guestbookId, name, email, message, serviceContext);
	}

	/**
	 * Creates a new guest book entry with the primary key. Does not add the guest book entry to the database.
	 *
	 * @param entryId the primary key for the new guest book entry
	 * @return the new guest book entry
	 */
	public static com.liferay.docs.guestbook.model.GuestBookEntry
		createGuestBookEntry(long entryId) {

		return getService().createGuestBookEntry(entryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static com.liferay.docs.guestbook.model.GuestBookEntry
			deleteGuestbookEntry(
				com.liferay.docs.guestbook.model.GuestBookEntry entry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteGuestbookEntry(entry);
	}

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
	public static com.liferay.docs.guestbook.model.GuestBookEntry
		deleteGuestBookEntry(
			com.liferay.docs.guestbook.model.GuestBookEntry guestBookEntry) {

		return getService().deleteGuestBookEntry(guestBookEntry);
	}

	public static com.liferay.docs.guestbook.model.GuestBookEntry
			deleteGuestbookEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteGuestbookEntry(entryId);
	}

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
	public static com.liferay.docs.guestbook.model.GuestBookEntry
			deleteGuestBookEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteGuestBookEntry(entryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.docs.guestbook.model.GuestBookEntry
		fetchGuestBookEntry(long entryId) {

		return getService().fetchGuestBookEntry(entryId);
	}

	/**
	 * Returns the guest book entry matching the UUID and group.
	 *
	 * @param uuid the guest book entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public static com.liferay.docs.guestbook.model.GuestBookEntry
		fetchGuestBookEntryByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchGuestBookEntryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

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
	public static java.util.List
		<com.liferay.docs.guestbook.model.GuestBookEntry> getGuestBookEntries(
			int start, int end) {

		return getService().getGuestBookEntries(start, end);
	}

	public static java.util.List
		<com.liferay.docs.guestbook.model.GuestBookEntry> getGuestbookEntries(
			long groupId, long guestbookId) {

		return getService().getGuestbookEntries(groupId, guestbookId);
	}

	public static java.util.List
		<com.liferay.docs.guestbook.model.GuestBookEntry> getGuestbookEntries(
				long groupId, long guestbookId, int start, int end)
			throws com.liferay.portal.kernel.exception.SystemException {

		return getService().getGuestbookEntries(
			groupId, guestbookId, start, end);
	}

	public static java.util.List
		<com.liferay.docs.guestbook.model.GuestBookEntry> getGuestbookEntries(
			long groupId, long guestbookId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.docs.guestbook.model.GuestBookEntry> obc) {

		return getService().getGuestbookEntries(
			groupId, guestbookId, start, end, obc);
	}

	/**
	 * Returns all the guest book entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the guest book entries
	 * @param companyId the primary key of the company
	 * @return the matching guest book entries, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.liferay.docs.guestbook.model.GuestBookEntry>
			getGuestBookEntriesByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getGuestBookEntriesByUuidAndCompanyId(
			uuid, companyId);
	}

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
	public static java.util.List
		<com.liferay.docs.guestbook.model.GuestBookEntry>
			getGuestBookEntriesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.docs.guestbook.model.GuestBookEntry>
						orderByComparator) {

		return getService().getGuestBookEntriesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of guest book entries.
	 *
	 * @return the number of guest book entries
	 */
	public static int getGuestBookEntriesCount() {
		return getService().getGuestBookEntriesCount();
	}

	public static int getGuestbookEntriesCount(long groupId, long guestbookId) {
		return getService().getGuestbookEntriesCount(groupId, guestbookId);
	}

	public static com.liferay.docs.guestbook.model.GuestBookEntry
			getGuestbookEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getGuestbookEntry(entryId);
	}

	/**
	 * Returns the guest book entry with the primary key.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry
	 * @throws PortalException if a guest book entry with the primary key could not be found
	 */
	public static com.liferay.docs.guestbook.model.GuestBookEntry
			getGuestBookEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getGuestBookEntry(entryId);
	}

	/**
	 * Returns the guest book entry matching the UUID and group.
	 *
	 * @param uuid the guest book entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching guest book entry
	 * @throws PortalException if a matching guest book entry could not be found
	 */
	public static com.liferay.docs.guestbook.model.GuestBookEntry
			getGuestBookEntryByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getGuestBookEntryByUuidAndGroupId(uuid, groupId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

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
	public static com.liferay.docs.guestbook.model.GuestBookEntry
		updateGuestBookEntry(
			com.liferay.docs.guestbook.model.GuestBookEntry guestBookEntry) {

		return getService().updateGuestBookEntry(guestBookEntry);
	}

	public static com.liferay.docs.guestbook.model.GuestBookEntry
			updateGuestbookEntry(
				long userId, long guestbookId, long entryId, String name,
				String email, String message,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateGuestbookEntry(
			userId, guestbookId, entryId, name, email, message, serviceContext);
	}

	public static GuestBookEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<GuestBookEntryLocalService, GuestBookEntryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			GuestBookEntryLocalService.class);

		ServiceTracker<GuestBookEntryLocalService, GuestBookEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<GuestBookEntryLocalService, GuestBookEntryLocalService>(
						bundle.getBundleContext(),
						GuestBookEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}