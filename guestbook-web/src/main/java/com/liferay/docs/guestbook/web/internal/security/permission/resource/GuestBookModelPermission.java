package com.liferay.docs.guestbook.web.internal.security.permission.resource;


import com.liferay.docs.guestbook.model.GuestBook;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class GuestBookModelPermission {

    private static ModelResourcePermission<GuestBook> guestBookModelResourcePermission;


    @Reference(target = "(resource.class.name=com.liferay.docs.guestbook.model.GuestBook",unbind = "-")
    protected void setGuestBookModelResourcePermission(ModelResourcePermission<GuestBook> guestBookModelResourcePermission) {
        GuestBookModelPermission.guestBookModelResourcePermission = guestBookModelResourcePermission;
    }

    public static boolean contains(PermissionChecker permissionChecker,long guestbookId, String actionId) throws PortalException {
        return guestBookModelResourcePermission.contains(permissionChecker, guestbookId, actionId);
    }
    public static boolean contains(PermissionChecker permissionChecker,GuestBook guestBook, String actionId) throws PortalException {
        return guestBookModelResourcePermission.contains(permissionChecker, guestBook, actionId);
    }
}
