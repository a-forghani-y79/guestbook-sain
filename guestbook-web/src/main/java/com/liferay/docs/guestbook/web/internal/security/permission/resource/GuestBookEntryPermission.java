package com.liferay.docs.guestbook.web.internal.security.permission.resource;

import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class GuestBookEntryPermission {
    private static ModelResourcePermission<GuestBookEntry> guestBookEntryModelResourcePermission;

    @Reference(target = "(model.class.name=com.liferay.docs.guestbook.model.guestBookEntry)",unbind = "-")
    protected void setGuestBookEntryModelResourcePermission(ModelResourcePermission<GuestBookEntry> guestBookEntryModelResourcePermission) {
        GuestBookEntryPermission.guestBookEntryModelResourcePermission = guestBookEntryModelResourcePermission;
    }

    public static boolean contains(PermissionChecker permissionChecker, GuestBookEntry guestBookEntry, String actionId) throws PortalException {
        return guestBookEntryModelResourcePermission.contains(permissionChecker, guestBookEntry, actionId);
    }
    public static boolean contains(PermissionChecker permissionChecker, long entryId, String actionId) throws PortalException {
        return guestBookEntryModelResourcePermission.contains(permissionChecker, entryId, actionId);
    }

}
