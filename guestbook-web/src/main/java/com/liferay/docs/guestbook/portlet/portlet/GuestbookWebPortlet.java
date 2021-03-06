package com.liferay.docs.guestbook.portlet.portlet;

import com.liferay.docs.guestbook.model.GuestBook;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.portlet.constants.GuestbookWebPortletKeys;

import com.liferay.docs.guestbook.service.GuestBookEntryLocalService;
import com.liferay.docs.guestbook.service.GuestBookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jinos
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.social",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=Guestbook",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/guestbook/view.jsp",
                "javax.portlet.name=" + GuestbookWebPortletKeys.GUESTBOOKWEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user",
                "javax.portlet.supports.mime=text/html"

        },
        service = Portlet.class
)
public class GuestbookWebPortlet extends MVCPortlet {
    @Reference
    private GuestBookLocalService guestBookLocalService;
    @Reference
    private GuestBookEntryLocalService guestBookEntryLocalService;

    public void addEntry(ActionRequest request, ActionResponse response) throws PortalException {
        ServiceContext serviceContext = ServiceContextFactory.getInstance(GuestBookEntry.class.getName(), request);

        //fetch data from request
        String name = ParamUtil.getString(request, "name");
        String email = ParamUtil.getString(request, "email");
        String message = ParamUtil.getString(request, "message");
        long entryId = ParamUtil.getLong(request, "entryId");
        long guestbookId = ParamUtil.getLong(request, "guestbookId");


        if (entryId > 0) {
            //that means we should update entry
            // shroud with try catch for if got exception, copy request params into response and render edit_entry.jsp
            try {
                guestBookEntryLocalService.updateGuestbookEntry(serviceContext.getUserId(), guestbookId, entryId, name, email, message, serviceContext);

                //find a replace for this
                response.setRenderParameter("guestbookId", Long.toString(guestbookId));

                //display message
                SessionMessages.add(request, "entryAdded");
            } catch (Exception e) {
                //display error
                SessionErrors.add(request, e.getClass().getName());
                PortalUtil.copyRequestParameters(request, response);
                response.setRenderParameter("mcvPath", "/guestbook/view.jsp");
            }

        } else {
            //that means we should create new entry
            try {
                guestBookEntryLocalService.addGuestbookEntry(serviceContext.getUserId(), guestbookId, name, email, message, serviceContext);
                response.setRenderParameter("guestbookId", Long.toString(guestbookId));
                //display message
                SessionMessages.add(request, "entryAdded");
            } catch (Exception e) {
                //display error
                SessionErrors.add(request, e.getClass().getName());
                PortalUtil.copyRequestParameters(request, response);
                response.setRenderParameter("mvcPath", "/guestbook/view.jsp");

            }
        }
    }

    public void deleteEntry(ActionRequest request, ActionResponse response) throws PortalException {
        long entryId = ParamUtil.getLong(request, "entryId");
        try {
            guestBookEntryLocalService.deleteGuestBookEntry(entryId);
            //display message
            SessionMessages.add(request, "entryDeleted");
        } catch (Exception e) {
            //display error
            SessionErrors.add(request, e.getClass().getName());
            Logger.getLogger(GuestbookWebPortlet.class.getName()).log(
                    Level.SEVERE, null, e);
        }
    }

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(GuestBook.class.getName(), renderRequest);
            long groupId = serviceContext.getScopeGroupId();
            long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

            List<GuestBook> guestBooks = guestBookLocalService.getGuestbooks(groupId);

            //create guest book with name Main if cant find any guestbook
            if (guestBooks.isEmpty()) {
                GuestBook guestBook = guestBookLocalService.addGuestBook(serviceContext.getUserId(), "Main", serviceContext);
                guestbookId = guestBook.getGuestbookId();
            }

            if (guestbookId == 0)
                guestbookId = guestBooks.get(0).getGuestbookId();

            renderRequest.setAttribute("guestbookId", guestbookId);

        } catch (Exception e) {
            throw new PortletException(e);
        }
        super.render(renderRequest, renderResponse);
    }
}