package com.liferay.docs.guestbook.portlet.portlet;

import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.portlet.constants.GuestbookWebPortletKeys;

import com.liferay.docs.guestbook.service.GuestBookEntryLocalService;
import com.liferay.docs.guestbook.service.GuestBookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
        ServiceContext serviceContext = ServiceContextFactory.getInstance(GuestBookEntry.class.getName(),request);

        //fetch data from request
        String name = ParamUtil.getString(request,"name");
        String email = ParamUtil.getString(request,"email");
        String message = ParamUtil.getString(request,"message");
        long entryId = ParamUtil.getLong(request,"entryId");
        long guestbookId = ParamUtil.getLong(request,"guestbookId");


        if (entryId>0){
            //that means we should update entry
            // shroud with try catch for if got exception, copy request params into response and render edit_entry.jsp
            try {
                guestBookEntryLocalService.updateGuestbookEntry(serviceContext.getUserId(), guestbookId, entryId, name, email, message, serviceContext);

                //find a replace for this
                response.setRenderParameter("guestbookId", Long.toString(guestbookId));
            }catch (Exception e){
                System.out.println(e);
                PortalUtil.copyRequestParameters(request,response);
                response.setRenderParameter("mcvPath", "/guestbook/edit_entry.jsp");
            }

        }else {
            //that means we should create new entry
            try{
                guestBookEntryLocalService.addGuestbookEntry(serviceContext.getUserId(),guestbookId,name,email,message,serviceContext);
                response.setRenderParameter("guestbookId",Long.toString(guestbookId));
            }catch (Exception e){
                System.out.println(e);
                PortalUtil.copyRequestParameters(request,response);
                response.setRenderParameter("mvcPath","/guestbook/edit_entry.jsp");
            }
        }
    }

}