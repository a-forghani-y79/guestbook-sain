package com.liferay.docs.guestbook.web.internal.security.permission.resource;


import com.liferay.docs.guestbook.constants.GuestBookConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class GuestBookPermission {

    private static PortletResourcePermission portletResourcePermission;

    public static boolean contains(PermissionChecker permissionChecker, long groupId, String actionId){
        return portletResourcePermission.contains(permissionChecker,groupId,actionId);
    }

    @Reference(target = "(resource.name="+ GuestBookConstants.RESOURCE_NAME +")",
    unbind = "-")
    protected void setPortletResourcePermission(PortletResourcePermission portletResourcePermission){
        GuestBookPermission.portletResourcePermission =portletResourcePermission;
    }
}
