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

package com.liferay.docs.guestbook.service.persistence;

import com.liferay.docs.guestbook.exception.NoSuchGuestBookEntryException;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the guest book entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author liferay
 * @see GuestBookEntryUtil
 * @generated
 */
@ProviderType
public interface GuestBookEntryPersistence
	extends BasePersistence<GuestBookEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GuestBookEntryUtil} to access the guest book entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the guest book entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid(String uuid);

	/**
	 * Returns a range of all the guest book entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @return the range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the guest book entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the guest book entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	public GuestBookEntry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	public GuestBookEntry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns the guest book entries before and after the current guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param entryId the primary key of the current guest book entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	public GuestBookEntry[] findByUuid_PrevAndNext(
			long entryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Removes all the guest book entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of guest book entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching guest book entries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the guest book entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchGuestBookEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	public GuestBookEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the guest book entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the guest book entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the guest book entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the guest book entry that was removed
	 */
	public GuestBookEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the number of guest book entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching guest book entries
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the guest book entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the guest book entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @return the range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the guest book entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the guest book entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	public GuestBookEntry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	public GuestBookEntry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns the guest book entries before and after the current guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param entryId the primary key of the current guest book entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	public GuestBookEntry[] findByUuid_C_PrevAndNext(
			long entryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Removes all the guest book entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of guest book entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching guest book entries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the guest book entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByG_G(
		long groupId, long guestbookId);

	/**
	 * Returns a range of all the guest book entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @return the range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end);

	/**
	 * Returns an ordered range of all the guest book entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the guest book entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching guest book entries
	 */
	public java.util.List<GuestBookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	public GuestBookEntry findByG_G_First(
			long groupId, long guestbookId,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the first guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByG_G_First(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns the last guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	public GuestBookEntry findByG_G_Last(
			long groupId, long guestbookId,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the last guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	public GuestBookEntry fetchByG_G_Last(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns the guest book entries before and after the current guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param entryId the primary key of the current guest book entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	public GuestBookEntry[] findByG_G_PrevAndNext(
			long entryId, long groupId, long guestbookId,
			com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
				orderByComparator)
		throws NoSuchGuestBookEntryException;

	/**
	 * Removes all the guest book entries where groupId = &#63; and guestbookId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 */
	public void removeByG_G(long groupId, long guestbookId);

	/**
	 * Returns the number of guest book entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the number of matching guest book entries
	 */
	public int countByG_G(long groupId, long guestbookId);

	/**
	 * Caches the guest book entry in the entity cache if it is enabled.
	 *
	 * @param guestBookEntry the guest book entry
	 */
	public void cacheResult(GuestBookEntry guestBookEntry);

	/**
	 * Caches the guest book entries in the entity cache if it is enabled.
	 *
	 * @param guestBookEntries the guest book entries
	 */
	public void cacheResult(java.util.List<GuestBookEntry> guestBookEntries);

	/**
	 * Creates a new guest book entry with the primary key. Does not add the guest book entry to the database.
	 *
	 * @param entryId the primary key for the new guest book entry
	 * @return the new guest book entry
	 */
	public GuestBookEntry create(long entryId);

	/**
	 * Removes the guest book entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry that was removed
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	public GuestBookEntry remove(long entryId)
		throws NoSuchGuestBookEntryException;

	public GuestBookEntry updateImpl(GuestBookEntry guestBookEntry);

	/**
	 * Returns the guest book entry with the primary key or throws a <code>NoSuchGuestBookEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	public GuestBookEntry findByPrimaryKey(long entryId)
		throws NoSuchGuestBookEntryException;

	/**
	 * Returns the guest book entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry, or <code>null</code> if a guest book entry with the primary key could not be found
	 */
	public GuestBookEntry fetchByPrimaryKey(long entryId);

	/**
	 * Returns all the guest book entries.
	 *
	 * @return the guest book entries
	 */
	public java.util.List<GuestBookEntry> findAll();

	/**
	 * Returns a range of all the guest book entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @return the range of guest book entries
	 */
	public java.util.List<GuestBookEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the guest book entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of guest book entries
	 */
	public java.util.List<GuestBookEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the guest book entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of guest book entries
	 */
	public java.util.List<GuestBookEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestBookEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the guest book entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of guest book entries.
	 *
	 * @return the number of guest book entries
	 */
	public int countAll();

}