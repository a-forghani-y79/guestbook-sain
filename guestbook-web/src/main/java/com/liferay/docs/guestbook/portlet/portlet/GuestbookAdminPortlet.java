package com.liferay.docs.guestbook.portlet.portlet;

import com.liferay.docs.guestbook.portlet.constants.GuestbookWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
        property = {"com.liferay.portlet,display-category=category.hidden",
                "com.liferay.portlet.scopeable=true",
                "javax.portlet.display-name=Guestbook",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.portlet-title-based-navigation=true",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/guestbook_admin/view.jsp",
                "javax.portlet.name=" + GuestbookWebPortletKeys.GUESTBOOK_ADMIN,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=administrator",
                "javax.portlet.supports.mime-type=text/html",
                "com.liferay.portlet.add-default-resource=true"})
public class GuestbookAdminPortlet extends MVCPortlet {
}
