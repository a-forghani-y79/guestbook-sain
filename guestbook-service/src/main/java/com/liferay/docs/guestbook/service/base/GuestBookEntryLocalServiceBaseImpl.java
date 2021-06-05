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

package com.liferay.docs.guestbook.service.base;

import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.service.GuestBookEntryLocalService;
import com.liferay.docs.guestbook.service.persistence.GuestBookEntryPersistence;
import com.liferay.docs.guestbook.service.persistence.GuestBookPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the guest book entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.docs.guestbook.service.impl.GuestBookEntryLocalServiceImpl}.
 * </p>
 *
 * @author liferay
 * @see com.liferay.docs.guestbook.service.impl.GuestBookEntryLocalServiceImpl
 * @generated
 */
public abstract class GuestBookEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, GuestBookEntryLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>GuestBookEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.docs.guestbook.service.GuestBookEntryLocalServiceUtil</code>.
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
	@Override
	public GuestBookEntry addGuestBookEntry(GuestBookEntry guestBookEntry) {
		guestBookEntry.setNew(true);

		return guestBookEntryPersistence.update(guestBookEntry);
	}

	/**
	 * Creates a new guest book entry with the primary key. Does not add the guest book entry to the database.
	 *
	 * @param entryId the primary key for the new guest book entry
	 * @return the new guest book entry
	 */
	@Override
	@Transactional(enabled = false)
	public GuestBookEntry createGuestBookEntry(long entryId) {
		return guestBookEntryPersistence.create(entryId);
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
	@Indexable(type = IndexableType.DELETE)
	@Override
	public GuestBookEntry deleteGuestBookEntry(long entryId)
		throws PortalException {

		return guestBookEntryPersistence.remove(entryId);
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
	@Indexable(type = IndexableType.DELETE)
	@Override
	public GuestBookEntry deleteGuestBookEntry(GuestBookEntry guestBookEntry) {
		return guestBookEntryPersistence.remove(guestBookEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			GuestBookEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return guestBookEntryPersistence.findWithDynamicQuery(dynamicQuery);
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
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return guestBookEntryPersistence.findWithDynamicQuery(
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
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return guestBookEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return guestBookEntryPersistence.countWithDynamicQuery(dynamicQuery);
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
		DynamicQuery dynamicQuery, Projection projection) {

		return guestBookEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public GuestBookEntry fetchGuestBookEntry(long entryId) {
		return guestBookEntryPersistence.fetchByPrimaryKey(entryId);
	}

	/**
	 * Returns the guest book entry matching the UUID and group.
	 *
	 * @param uuid the guest book entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching guest book entry, or <code>null</code> if a matching guest book entry could not be found
	 */
	@Override
	public GuestBookEntry fetchGuestBookEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return guestBookEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the guest book entry with the primary key.
	 *
	 * @param entryId the primary key of the guest book entry
	 * @return the guest book entry
	 * @throws PortalException if a guest book entry with the primary key could not be found
	 */
	@Override
	public GuestBookEntry getGuestBookEntry(long entryId)
		throws PortalException {

		return guestBookEntryPersistence.findByPrimaryKey(entryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(guestBookEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(GuestBookEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("entryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			guestBookEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(GuestBookEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("entryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(guestBookEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(GuestBookEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("entryId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<GuestBookEntry>() {

				@Override
				public void performAction(GuestBookEntry guestBookEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, guestBookEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(GuestBookEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return guestBookEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return guestBookEntryLocalService.deleteGuestBookEntry(
			(GuestBookEntry)persistedModel);
	}

	public BasePersistence<GuestBookEntry> getBasePersistence() {
		return guestBookEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return guestBookEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the guest book entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the guest book entries
	 * @param companyId the primary key of the company
	 * @return the matching guest book entries, or an empty list if no matches were found
	 */
	@Override
	public List<GuestBookEntry> getGuestBookEntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return guestBookEntryPersistence.findByUuid_C(uuid, companyId);
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
	public List<GuestBookEntry> getGuestBookEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GuestBookEntry> orderByComparator) {

		return guestBookEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
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
	public GuestBookEntry getGuestBookEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return guestBookEntryPersistence.findByUUID_G(uuid, groupId);
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
	public List<GuestBookEntry> getGuestBookEntries(int start, int end) {
		return guestBookEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of guest book entries.
	 *
	 * @return the number of guest book entries
	 */
	@Override
	public int getGuestBookEntriesCount() {
		return guestBookEntryPersistence.countAll();
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
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public GuestBookEntry updateGuestBookEntry(GuestBookEntry guestBookEntry) {
		return guestBookEntryPersistence.update(guestBookEntry);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			GuestBookEntryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		guestBookEntryLocalService = (GuestBookEntryLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return GuestBookEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return GuestBookEntry.class;
	}

	protected String getModelClassName() {
		return GuestBookEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = guestBookEntryPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@Reference
	protected GuestBookPersistence guestBookPersistence;

	protected GuestBookEntryLocalService guestBookEntryLocalService;

	@Reference
	protected GuestBookEntryPersistence guestBookEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}