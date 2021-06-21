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

package com.liferay.docs.guestbook.service.persistence.impl;

import com.liferay.docs.guestbook.exception.NoSuchGuestBookEntryException;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.model.impl.GuestBookEntryImpl;
import com.liferay.docs.guestbook.model.impl.GuestBookEntryModelImpl;
import com.liferay.docs.guestbook.service.persistence.GuestBookEntryPersistence;
import com.liferay.docs.guestbook.service.persistence.impl.constants.GBPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the guest book entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author liferay
 * @generated
 */
@Component(service = GuestBookEntryPersistence.class)
public class GuestBookEntryPersistenceImpl
	extends BasePersistenceImpl<GuestBookEntry>
	implements GuestBookEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GuestBookEntryUtil</code> to access the guest book entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GuestBookEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the guest book entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching guest book entries
	 */
	@Override
	public List<GuestBookEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<GuestBookEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<GuestBookEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<GuestBookEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<GuestBookEntry> list = null;

		if (useFinderCache) {
			list = (List<GuestBookEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GuestBookEntry guestBookEntry : list) {
					if (!uuid.equals(guestBookEntry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<GuestBookEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry findByUuid_First(
			String uuid, OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByUuid_First(
			uuid, orderByComparator);

		if (guestBookEntry != null) {
			return guestBookEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchGuestBookEntryException(sb.toString());
	}

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByUuid_First(
		String uuid, OrderByComparator<GuestBookEntry> orderByComparator) {

		List<GuestBookEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry findByUuid_Last(
			String uuid, OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByUuid_Last(
			uuid, orderByComparator);

		if (guestBookEntry != null) {
			return guestBookEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchGuestBookEntryException(sb.toString());
	}

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByUuid_Last(
		String uuid, OrderByComparator<GuestBookEntry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<GuestBookEntry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the guest book entries before and after the current guest book entry in the ordered set where uuid = &#63;.
	 *
	 * @param entryId the primary key of the current guest book entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry[] findByUuid_PrevAndNext(
			long entryId, String uuid,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		uuid = Objects.toString(uuid, "");

		GuestBookEntry guestBookEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			GuestBookEntry[] array = new GuestBookEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, guestBookEntry, uuid, orderByComparator, true);

			array[1] = guestBookEntry;

			array[2] = getByUuid_PrevAndNext(
				session, guestBookEntry, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected GuestBookEntry getByUuid_PrevAndNext(
		Session session, GuestBookEntry guestBookEntry, String uuid,
		OrderByComparator<GuestBookEntry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						guestBookEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GuestBookEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guest book entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (GuestBookEntry guestBookEntry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(guestBookEntry);
		}
	}

	/**
	 * Returns the number of guest book entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching guest book entries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"guestBookEntry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(guestBookEntry.uuid IS NULL OR guestBookEntry.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the guest book entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchGuestBookEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByUUID_G(uuid, groupId);

		if (guestBookEntry == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchGuestBookEntryException(sb.toString());
		}

		return guestBookEntry;
	}

	/**
	 * Returns the guest book entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the guest book entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof GuestBookEntry) {
			GuestBookEntry guestBookEntry = (GuestBookEntry)result;

			if (!Objects.equals(uuid, guestBookEntry.getUuid()) ||
				(groupId != guestBookEntry.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<GuestBookEntry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					GuestBookEntry guestBookEntry = list.get(0);

					result = guestBookEntry;

					cacheResult(guestBookEntry);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (GuestBookEntry)result;
		}
	}

	/**
	 * Removes the guest book entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the guest book entry that was removed
	 */
	@Override
	public GuestBookEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = findByUUID_G(uuid, groupId);

		return remove(guestBookEntry);
	}

	/**
	 * Returns the number of guest book entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching guest book entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"guestBookEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(guestBookEntry.uuid IS NULL OR guestBookEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"guestBookEntry.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the guest book entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching guest book entries
	 */
	@Override
	public List<GuestBookEntry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<GuestBookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

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
	@Override
	public List<GuestBookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<GuestBookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<GuestBookEntry> list = null;

		if (useFinderCache) {
			list = (List<GuestBookEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GuestBookEntry guestBookEntry : list) {
					if (!uuid.equals(guestBookEntry.getUuid()) ||
						(companyId != guestBookEntry.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<GuestBookEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (guestBookEntry != null) {
			return guestBookEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchGuestBookEntryException(sb.toString());
	}

	/**
	 * Returns the first guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		List<GuestBookEntry> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (guestBookEntry != null) {
			return guestBookEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchGuestBookEntryException(sb.toString());
	}

	/**
	 * Returns the last guest book entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<GuestBookEntry> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public GuestBookEntry[] findByUuid_C_PrevAndNext(
			long entryId, String uuid, long companyId,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		uuid = Objects.toString(uuid, "");

		GuestBookEntry guestBookEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			GuestBookEntry[] array = new GuestBookEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, guestBookEntry, uuid, companyId, orderByComparator,
				true);

			array[1] = guestBookEntry;

			array[2] = getByUuid_C_PrevAndNext(
				session, guestBookEntry, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected GuestBookEntry getByUuid_C_PrevAndNext(
		Session session, GuestBookEntry guestBookEntry, String uuid,
		long companyId, OrderByComparator<GuestBookEntry> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						guestBookEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GuestBookEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guest book entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (GuestBookEntry guestBookEntry :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(guestBookEntry);
		}
	}

	/**
	 * Returns the number of guest book entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching guest book entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"guestBookEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(guestBookEntry.uuid IS NULL OR guestBookEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"guestBookEntry.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByG_G;
	private FinderPath _finderPathWithoutPaginationFindByG_G;
	private FinderPath _finderPathCountByG_G;

	/**
	 * Returns all the guest book entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the matching guest book entries
	 */
	@Override
	public List<GuestBookEntry> findByG_G(long groupId, long guestbookId) {
		return findByG_G(
			groupId, guestbookId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<GuestBookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end) {

		return findByG_G(groupId, guestbookId, start, end, null);
	}

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
	@Override
	public List<GuestBookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		return findByG_G(
			groupId, guestbookId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<GuestBookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_G;
				finderArgs = new Object[] {groupId, guestbookId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_G;
			finderArgs = new Object[] {
				groupId, guestbookId, start, end, orderByComparator
			};
		}

		List<GuestBookEntry> list = null;

		if (useFinderCache) {
			list = (List<GuestBookEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GuestBookEntry guestBookEntry : list) {
					if ((groupId != guestBookEntry.getGroupId()) ||
						(guestbookId != guestBookEntry.getGuestbookId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

			sb.append(_FINDER_COLUMN_G_G_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(guestbookId);

				list = (List<GuestBookEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry findByG_G_First(
			long groupId, long guestbookId,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByG_G_First(
			groupId, guestbookId, orderByComparator);

		if (guestBookEntry != null) {
			return guestBookEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", guestbookId=");
		sb.append(guestbookId);

		sb.append("}");

		throw new NoSuchGuestBookEntryException(sb.toString());
	}

	/**
	 * Returns the first guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByG_G_First(
		long groupId, long guestbookId,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		List<GuestBookEntry> list = findByG_G(
			groupId, guestbookId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry
	 * @throws NoSuchGuestBookEntryException if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry findByG_G_Last(
			long groupId, long guestbookId,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByG_G_Last(
			groupId, guestbookId, orderByComparator);

		if (guestBookEntry != null) {
			return guestBookEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", guestbookId=");
		sb.append(guestbookId);

		sb.append("}");

		throw new NoSuchGuestBookEntryException(sb.toString());
	}

	/**
	 * Returns the last guest book entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchByG_G_Last(
		long groupId, long guestbookId,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		int count = countByG_G(groupId, guestbookId);

		if (count == 0) {
			return null;
		}

		List<GuestBookEntry> list = findByG_G(
			groupId, guestbookId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public GuestBookEntry[] findByG_G_PrevAndNext(
			long entryId, long groupId, long guestbookId,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			GuestBookEntry[] array = new GuestBookEntryImpl[3];

			array[0] = getByG_G_PrevAndNext(
				session, guestBookEntry, groupId, guestbookId,
				orderByComparator, true);

			array[1] = guestBookEntry;

			array[2] = getByG_G_PrevAndNext(
				session, guestBookEntry, groupId, guestbookId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected GuestBookEntry getByG_G_PrevAndNext(
		Session session, GuestBookEntry guestBookEntry, long groupId,
		long guestbookId, OrderByComparator<GuestBookEntry> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

		sb.append(_FINDER_COLUMN_G_G_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(guestbookId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						guestBookEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GuestBookEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the guest book entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the matching guest book entries that the user has permission to view
	 */
	@Override
	public List<GuestBookEntry> filterFindByG_G(
		long groupId, long guestbookId) {

		return filterFindByG_G(
			groupId, guestbookId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the guest book entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GuestBookEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guest book entries
	 * @param end the upper bound of the range of guest book entries (not inclusive)
	 * @return the range of matching guest book entries that the user has permission to view
	 */
	@Override
	public List<GuestBookEntry> filterFindByG_G(
		long groupId, long guestbookId, int start, int end) {

		return filterFindByG_G(groupId, guestbookId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the guest book entries that the user has permissions to view where groupId = &#63; and guestbookId = &#63;.
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
	 * @return the ordered range of matching guest book entries that the user has permission to view
	 */
	@Override
	public List<GuestBookEntry> filterFindByG_G(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G(
				groupId, guestbookId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_GUESTBOOKENTRY_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_GUESTBOOKENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_G_G_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_GUESTBOOKENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(GuestBookEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), GuestBookEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, GuestBookEntryImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, GuestBookEntryImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			queryPos.add(guestbookId);

			return (List<GuestBookEntry>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the guest book entries before and after the current guest book entry in the ordered set of guest book entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param entryId the primary key of the current guest book entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry[] filterFindByG_G_PrevAndNext(
			long entryId, long groupId, long guestbookId,
			OrderByComparator<GuestBookEntry> orderByComparator)
		throws NoSuchGuestBookEntryException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G_PrevAndNext(
				entryId, groupId, guestbookId, orderByComparator);
		}

		GuestBookEntry guestBookEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			GuestBookEntry[] array = new GuestBookEntryImpl[3];

			array[0] = filterGetByG_G_PrevAndNext(
				session, guestBookEntry, groupId, guestbookId,
				orderByComparator, true);

			array[1] = guestBookEntry;

			array[2] = filterGetByG_G_PrevAndNext(
				session, guestBookEntry, groupId, guestbookId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected GuestBookEntry filterGetByG_G_PrevAndNext(
		Session session, GuestBookEntry guestBookEntry, long groupId,
		long guestbookId, OrderByComparator<GuestBookEntry> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_GUESTBOOKENTRY_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_GUESTBOOKENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_G_G_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_GUESTBOOKENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(GuestBookEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(GuestBookEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), GuestBookEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, GuestBookEntryImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, GuestBookEntryImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(groupId);

		queryPos.add(guestbookId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						guestBookEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GuestBookEntry> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guest book entries where groupId = &#63; and guestbookId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 */
	@Override
	public void removeByG_G(long groupId, long guestbookId) {
		for (GuestBookEntry guestBookEntry :
				findByG_G(
					groupId, guestbookId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(guestBookEntry);
		}
	}

	/**
	 * Returns the number of guest book entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the number of matching guest book entries
	 */
	@Override
	public int countByG_G(long groupId, long guestbookId) {
		FinderPath finderPath = _finderPathCountByG_G;

		Object[] finderArgs = new Object[] {groupId, guestbookId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_GUESTBOOKENTRY_WHERE);

			sb.append(_FINDER_COLUMN_G_G_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(guestbookId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of guest book entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the number of matching guest book entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_G(long groupId, long guestbookId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_G(groupId, guestbookId);
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_GUESTBOOKENTRY_WHERE);

		sb.append(_FINDER_COLUMN_G_G_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), GuestBookEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			queryPos.add(guestbookId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_G_GROUPID_2 =
		"guestBookEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_G_GUESTBOOKID_2 =
		"guestBookEntry.guestbookId = ?";

	public GuestBookEntryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(GuestBookEntry.class);

		setModelImplClass(GuestBookEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the guest book entry in the entity cache if it is enabled.
	 *
	 * @param guestBookEntry the guest book entry
	 */
	@Override
	public void cacheResult(GuestBookEntry guestBookEntry) {
		entityCache.putResult(
			GuestBookEntryImpl.class, guestBookEntry.getPrimaryKey(),
			guestBookEntry);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				guestBookEntry.getUuid(), guestBookEntry.getGroupId()
			},
			guestBookEntry);
	}

	/**
	 * Caches the guest book entries in the entity cache if it is enabled.
	 *
	 * @param guestBookEntries the guest book entries
	 */
	@Override
	public void cacheResult(List<GuestBookEntry> guestBookEntries) {
		for (GuestBookEntry guestBookEntry : guestBookEntries) {
			if (entityCache.getResult(
					GuestBookEntryImpl.class, guestBookEntry.getPrimaryKey()) ==
						null) {

				cacheResult(guestBookEntry);
			}
		}
	}

	/**
	 * Clears the cache for all guest book entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GuestBookEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the guest book entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GuestBookEntry guestBookEntry) {
		entityCache.removeResult(GuestBookEntryImpl.class, guestBookEntry);
	}

	@Override
	public void clearCache(List<GuestBookEntry> guestBookEntries) {
		for (GuestBookEntry guestBookEntry : guestBookEntries) {
			entityCache.removeResult(GuestBookEntryImpl.class, guestBookEntry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(GuestBookEntryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		GuestBookEntryModelImpl guestBookEntryModelImpl) {

		Object[] args = new Object[] {
			guestBookEntryModelImpl.getUuid(),
			guestBookEntryModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, guestBookEntryModelImpl, false);
	}

	/**
	 * Creates a new guest book entry with the primary key. Does not add the guest book entry to the database.
	 *
	 * @param entryId the primary key for the new guest book entry
	 * @return the new guest book entry
	 */
	@Override
	public GuestBookEntry create(long entryId) {
		GuestBookEntry guestBookEntry = new GuestBookEntryImpl();

		guestBookEntry.setNew(true);
		guestBookEntry.setPrimaryKey(entryId);

		String uuid = PortalUUIDUtil.generate();

		guestBookEntry.setUuid(uuid);

		guestBookEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return guestBookEntry;
	}

	/**
	 * Removes the guest book entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry that was removed
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry remove(long entryId)
		throws NoSuchGuestBookEntryException {

		return remove((Serializable)entryId);
	}

	/**
	 * Removes the guest book entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the guest book entry
	 * @return the guest book entry that was removed
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry remove(Serializable primaryKey)
		throws NoSuchGuestBookEntryException {

		Session session = null;

		try {
			session = openSession();

			GuestBookEntry guestBookEntry = (GuestBookEntry)session.get(
				GuestBookEntryImpl.class, primaryKey);

			if (guestBookEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGuestBookEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(guestBookEntry);
		}
		catch (NoSuchGuestBookEntryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected GuestBookEntry removeImpl(GuestBookEntry guestBookEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(guestBookEntry)) {
				guestBookEntry = (GuestBookEntry)session.get(
					GuestBookEntryImpl.class,
					guestBookEntry.getPrimaryKeyObj());
			}

			if (guestBookEntry != null) {
				session.delete(guestBookEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (guestBookEntry != null) {
			clearCache(guestBookEntry);
		}

		return guestBookEntry;
	}

	@Override
	public GuestBookEntry updateImpl(GuestBookEntry guestBookEntry) {
		boolean isNew = guestBookEntry.isNew();

		if (!(guestBookEntry instanceof GuestBookEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(guestBookEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					guestBookEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in guestBookEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom GuestBookEntry implementation " +
					guestBookEntry.getClass());
		}

		GuestBookEntryModelImpl guestBookEntryModelImpl =
			(GuestBookEntryModelImpl)guestBookEntry;

		if (Validator.isNull(guestBookEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			guestBookEntry.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (guestBookEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				guestBookEntry.setCreateDate(now);
			}
			else {
				guestBookEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!guestBookEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				guestBookEntry.setModifiedDate(now);
			}
			else {
				guestBookEntry.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(guestBookEntry);
			}
			else {
				guestBookEntry = (GuestBookEntry)session.merge(guestBookEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			GuestBookEntryImpl.class, guestBookEntryModelImpl, false, true);

		cacheUniqueFindersCache(guestBookEntryModelImpl);

		if (isNew) {
			guestBookEntry.setNew(false);
		}

		guestBookEntry.resetOriginalValues();

		return guestBookEntry;
	}

	/**
	 * Returns the guest book entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the guest book entry
	 * @return the guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGuestBookEntryException {

		GuestBookEntry guestBookEntry = fetchByPrimaryKey(primaryKey);

		if (guestBookEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGuestBookEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return guestBookEntry;
	}

	/**
	 * Returns the guest book entry with the primary key or throws a <code>NoSuchGuestBookEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry
	 * @throws NoSuchGuestBookEntryException if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry findByPrimaryKey(long entryId)
		throws NoSuchGuestBookEntryException {

		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the guest book entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry, or <code>null</code> if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry fetchByPrimaryKey(long entryId) {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the guest book entries.
	 *
	 * @return the guest book entries
	 */
	@Override
	public List<GuestBookEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<GuestBookEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<GuestBookEntry> findAll(
		int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<GuestBookEntry> findAll(
		int start, int end, OrderByComparator<GuestBookEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<GuestBookEntry> list = null;

		if (useFinderCache) {
			list = (List<GuestBookEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GUESTBOOKENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GUESTBOOKENTRY;

				sql = sql.concat(GuestBookEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<GuestBookEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the guest book entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GuestBookEntry guestBookEntry : findAll()) {
			remove(guestBookEntry);
		}
	}

	/**
	 * Returns the number of guest book entries.
	 *
	 * @return the number of guest book entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_GUESTBOOKENTRY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "entryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_GUESTBOOKENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GuestBookEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the guest book entry persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new GuestBookEntryModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", GuestBookEntry.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByG_G = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "guestbookId"}, true);

		_finderPathWithoutPaginationFindByG_G = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_G",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "guestbookId"}, true);

		_finderPathCountByG_G = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_G",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "guestbookId"}, false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(GuestBookEntryImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = GBPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = GBPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = GBPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_GUESTBOOKENTRY =
		"SELECT guestBookEntry FROM GuestBookEntry guestBookEntry";

	private static final String _SQL_SELECT_GUESTBOOKENTRY_WHERE =
		"SELECT guestBookEntry FROM GuestBookEntry guestBookEntry WHERE ";

	private static final String _SQL_COUNT_GUESTBOOKENTRY =
		"SELECT COUNT(guestBookEntry) FROM GuestBookEntry guestBookEntry";

	private static final String _SQL_COUNT_GUESTBOOKENTRY_WHERE =
		"SELECT COUNT(guestBookEntry) FROM GuestBookEntry guestBookEntry WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"guestBookEntry.entryId";

	private static final String _FILTER_SQL_SELECT_GUESTBOOKENTRY_WHERE =
		"SELECT DISTINCT {guestBookEntry.*} FROM GB_GuestBookEntry guestBookEntry WHERE ";

	private static final String
		_FILTER_SQL_SELECT_GUESTBOOKENTRY_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {GB_GuestBookEntry.*} FROM (SELECT DISTINCT guestBookEntry.entryId FROM GB_GuestBookEntry guestBookEntry WHERE ";

	private static final String
		_FILTER_SQL_SELECT_GUESTBOOKENTRY_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN GB_GuestBookEntry ON TEMP_TABLE.entryId = GB_GuestBookEntry.entryId";

	private static final String _FILTER_SQL_COUNT_GUESTBOOKENTRY_WHERE =
		"SELECT COUNT(DISTINCT guestBookEntry.entryId) AS COUNT_VALUE FROM GB_GuestBookEntry guestBookEntry WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "guestBookEntry";

	private static final String _FILTER_ENTITY_TABLE = "GB_GuestBookEntry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "guestBookEntry.";

	private static final String _ORDER_BY_ENTITY_TABLE = "GB_GuestBookEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No GuestBookEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No GuestBookEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		GuestBookEntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(GBPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;
	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();

	private static class GuestBookEntryModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			GuestBookEntryModelImpl guestBookEntryModelImpl =
				(GuestBookEntryModelImpl)baseModel;

			long columnBitmask = guestBookEntryModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					guestBookEntryModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						guestBookEntryModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					guestBookEntryModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			GuestBookEntryModelImpl guestBookEntryModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						guestBookEntryModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = guestBookEntryModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}