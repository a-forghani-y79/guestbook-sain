package com.liferay.docs.guestbook.portlet;

import com.liferay.docs.guestbook.model.GuestBook;
import com.liferay.docs.guestbook.constants.GuestbookWebPortletKeys;
import com.liferay.docs.guestbook.service.GuestBookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component(immediate = true,
        property = {"com.liferay.portlet.display-category=category.hidden",
                "com.liferay.portlet.scopeable=true",
                "javax.portlet.display-name=Guestbooks",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.portlet-title-based-navigation=true",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/guestbook_admin/view.jsp",
                "javax.portlet.name=" + GuestbookWebPortletKeys.GUESTBOOK_ADMIN,
                "javax.portlet.resource-bundle=context.Language",
                "javax.portlet.security-role-ref=administrator",
                "javax.portlet.supports.mime-type=text/html",
                "com.liferay.portlet.add-default-resource=true"},
        service = Portlet.class)
public class GuestbookAdminPortlet extends MVCPortlet {


    @Reference
    private GuestBookLocalService guestBookLocalService;

    public void addGuestbook(ActionRequest request, ActionResponse response) throws PortalException {
        ServiceContext serviceContext = ServiceContextFactory.getInstance(GuestBook.class.getName(), request);
        String name = ParamUtil.getString(request, "name");
        try {
            guestBookLocalService.addGuestBook(serviceContext.getUserId(), name, serviceContext);
            //display message
            SessionMessages.add(request, "guestbookAdded");
        } catch (Exception e) {
            SessionErrors.add(request,e.getClass().getName());
            Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(Level.SEVERE, null, e);
            response.setRenderParameter("mvcPath", "/guestbook_admin/edit_guestbook.jsp");
        }
    }

    public void deleteGuestbook(ActionRequest request, ActionResponse response) throws PortalException {
        ServiceContext serviceContext = ServiceContextFactory.getInstance(GuestBook.class.getName(), request);
        long guestbookId = ParamUtil.getLong(request, "guestbookId");

        try {
            guestBookLocalService.deleteGuestbook(guestbookId, serviceContext);
            //display message
            SessionMessages.add(request, "guestbookDeleted");
        } catch (Exception e) {
            SessionErrors.add(request,e.getClass().getName());
            Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateGuestbook(ActionRequest request, ActionResponse response) throws PortalException {
        ServiceContext serviceContext = ServiceContextFactory.getInstance(GuestBook.class.getName(), request);
        long guestbookId = ParamUtil.getLong(request, "guestbookId");
        String name = ParamUtil.getString(request, "name");
        try {
            guestBookLocalService.updateGuestbook(serviceContext.getUserId(), guestbookId, name, serviceContext);
            //display message
            SessionMessages.add(request, "guestbookUpdated");
        } catch (Exception e) {
            SessionErrors.add(request,e.getClass().getName());
            Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(Level.SEVERE, null, e);
            response.setRenderParameter("mvcPath", "/guestbook_admin/edit_guestbook.jsp");
        }

    }
}
