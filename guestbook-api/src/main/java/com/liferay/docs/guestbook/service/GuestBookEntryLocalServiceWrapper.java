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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GuestBookEntryLocalService}.
 *
 * @author liferay
 * @see GuestBookEntryLocalService
 * @generated
 */
public class GuestBookEntryLocalServiceWrapper
	implements GuestBookEntryLocalService,
			   ServiceWrapper<GuestBookEntryLocalService> {

	public GuestBookEntryLocalServiceWrapper(
		GuestBookEntryLocalService guestBookEntryLocalService) {

		_guestBookEntryLocalService = guestBookEntryLocalService;
	}

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
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry addGuestBookEntry(
		com.liferay.docs.guestbook.model.GuestBookEntry guestBookEntry) {

		return _guestBookEntryLocalService.addGuestBookEntry(guestBookEntry);
	}

	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry addGuestbookEntry(
			long userId, long guestbookId, String name, String email,
			String message,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.addGuestbookEntry(
			userId, guestbookId, name, email, message, serviceContext);
	}

	/**
	 * Creates a new guest book entry with the primary key. Does not add the guest book entry to the database.
	 *
	 * @param entryId the primary key for the new guest book entry
	 * @return the new guest book entry
	 */
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry createGuestBookEntry(
		long entryId) {

		return _guestBookEntryLocalService.createGuestBookEntry(entryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry deleteGuestbookEntry(
		com.liferay.docs.guestbook.model.GuestBookEntry entry) {

		return _guestBookEntryLocalService.deleteGuestbookEntry(entry);
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
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry deleteGuestBookEntry(
		com.liferay.docs.guestbook.model.GuestBookEntry guestBookEntry) {

		return _guestBookEntryLocalService.deleteGuestBookEntry(guestBookEntry);
	}

	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry deleteGuestbookEntry(
			long entryId)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestBookEntryException {

		return _guestBookEntryLocalService.deleteGuestbookEntry(entryId);
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
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry deleteGuestBookEntry(
			long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.deleteGuestBookEntry(entryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _guestBookEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _guestBookEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _guestBookEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _guestBookEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _guestBookEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _guestBookEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry fetchGuestBookEntry(
		long entryId) {

		return _guestBookEntryLocalService.fetchGuestBookEntry(entryId);
	}

	/**
	 * Returns the guest book entry matching the UUID and group.
	 *
	 * @param uuid the guest book entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry
		fetchGuestBookEntryByUuidAndGroupId(String uuid, long groupId) {

		return _guestBookEntryLocalService.fetchGuestBookEntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _guestBookEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _guestBookEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
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
	@Override
	public java.util.List<com.liferay.docs.guestbook.model.GuestBookEntry>
		getGuestBookEntries(int start, int end) {

		return _guestBookEntryLocalService.getGuestBookEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.GuestBookEntry>
		getGuestbookEntries(long groupId, long guestbookId) {

		return _guestBookEntryLocalService.getGuestbookEntries(
			groupId, guestbookId);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.GuestBookEntry>
			getGuestbookEntries(
				long groupId, long guestbookId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _guestBookEntryLocalService.getGuestbookEntries(
			groupId, guestbookId, start, end);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.GuestBookEntry>
		getGuestbookEntries(
			long groupId, long guestbookId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.docs.guestbook.model.GuestBookEntry> obc) {

		return _guestBookEntryLocalService.getGuestbookEntries(
			groupId, guestbookId, start, end, obc);
	}

	/**
	 * Returns all the guest book entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the guest book entries
	 * @param companyId the primary key of the company
	 * @return the matching guest book entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.docs.guestbook.model.GuestBookEntry>
		getGuestBookEntriesByUuidAndCompanyId(String uuid, long companyId) {

		return _guestBookEntryLocalService.
			getGuestBookEntriesByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<com.liferay.docs.guestbook.model.GuestBookEntry>
		getGuestBookEntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.docs.guestbook.model.GuestBookEntry>
					orderByComparator) {

		return _guestBookEntryLocalService.
			getGuestBookEntriesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of guest book entries.
	 *
	 * @return the number of guest book entries
	 */
	@Override
	public int getGuestBookEntriesCount() {
		return _guestBookEntryLocalService.getGuestBookEntriesCount();
	}

	@Override
	public int getGuestbookEntriesCount(long groupId, long guestbookId) {
		return _guestBookEntryLocalService.getGuestbookEntriesCount(
			groupId, guestbookId);
	}

	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry getGuestbookEntry(
			long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.getGuestbookEntry(entryId);
	}

	/**
	 * Returns the guest book entry with the primary key.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry
	 * @throws PortalException if a guest book entry with the primary key could not be found
	 */
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry getGuestBookEntry(
			long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.getGuestBookEntry(entryId);
	}

	/**
	 * Returns the guest book entry matching the UUID and group.
	 *
	 * @param uuid the guest book entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching guest book entry
	 * @throws PortalException if a matching guest book entry could not be found
	 */
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry
			getGuestBookEntryByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.getGuestBookEntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _guestBookEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _guestBookEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry updateGuestBookEntry(
		com.liferay.docs.guestbook.model.GuestBookEntry guestBookEntry) {

		return _guestBookEntryLocalService.updateGuestBookEntry(guestBookEntry);
	}

	@Override
	public com.liferay.docs.guestbook.model.GuestBookEntry updateGuestbookEntry(
			long userId, long guestbookId, long entryId, String name,
			String email, String message,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _guestBookEntryLocalService.updateGuestbookEntry(
			userId, guestbookId, entryId, name, email, message, serviceContext);
	}

	@Override
	public GuestBookEntryLocalService getWrappedService() {
		return _guestBookEntryLocalService;
	}

	@Override
	public void setWrappedService(
		GuestBookEntryLocalService guestBookEntryLocalService) {

		_guestBookEntryLocalService = guestBookEntryLocalService;
	}

	private GuestBookEntryLocalService _guestBookEntryLocalService;

}