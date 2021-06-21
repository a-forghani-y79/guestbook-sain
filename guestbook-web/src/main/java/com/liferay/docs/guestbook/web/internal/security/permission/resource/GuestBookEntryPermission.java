package com.liferay.docs.guestbook.web.internal.security.permission.resource;

import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class GuestBookEntryPermission {

    public static boolean contains(
            PermissionChecker permissionChecker, GuestBookEntry entry, String actionId) throws PortalException {
        return _guestbookEntryModelResourcePermission.contains(permissionChecker, entry, actionId);
    }

    public static boolean contains(
            PermissionChecker permissionChecker, long entryId, String actionId) throws PortalException {

        return _guestbookEntryModelResourcePermission.contains(permissionChecker, entryId, actionId);
    }

    @Reference(
            target = "(model.class.name=com.liferay.docs.guestbook.model.GuestBookEntry)",
            unbind = "-")
    protected void setEntryModelPermission(ModelResourcePermission<GuestBookEntry> modelResourcePermission) {
        _guestbookEntryModelResourcePermission = modelResourcePermission;
    }

    private static ModelResourcePermission<GuestBookEntry>_guestbookEntryModelResourcePermission;

}