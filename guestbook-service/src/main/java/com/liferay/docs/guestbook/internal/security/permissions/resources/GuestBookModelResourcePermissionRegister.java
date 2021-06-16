package com.liferay.docs.guestbook.internal.security.permissions.resources;

import com.liferay.docs.guestbook.constants.GuestBookConstants;
import com.liferay.docs.guestbook.constants.GuestbookWebPortletKeys;
import com.liferay.docs.guestbook.model.GuestBook;
import com.liferay.docs.guestbook.service.GuestBookLocalService;
import com.liferay.docs.guestbook.service.persistence.GuestBookUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.*;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import java.util.Dictionary;

@Component(immediate = true)
public class GuestBookModelResourcePermissionRegister {
    @Reference
    private GuestBookLocalService guestBookLocalService;
    @Reference(target = "(resource.name="+ GuestBookConstants.RESOURCE_NAME +")")
    private PortletResourcePermission portletResourcePermission;
    @Reference
    private StagingPermission stagingPermission;
    @Reference
    private WorkflowPermission workflowPermission;
    @Reference
    private GroupLocalService groupLocalService;

    private ServiceRegistration<ModelResourcePermission> serviceRegistration;
    @Activate
    public void activate(BundleContext bundleContext){
        Dictionary<String,Object> properties = new HashMapDictionary<>();
        properties.put("model.class.name", GuestBook.class.getName());

        serviceRegistration= bundleContext.registerService(
                ModelResourcePermission.class,
                ModelResourcePermissionFactory.create(
                        GuestBook.class,
                        GuestBook::getGuestbookId,
                        guestBookLocalService::getGuestBook,
                        portletResourcePermission,(modelResourcePermission,consumer)->{
                            consumer.accept(new StagedModelPermissionLogic<>(stagingPermission, GuestbookWebPortletKeys.GUESTBOOKWEB, GuestBook::getGuestbookId));
                            consumer.accept(new WorkflowedModelPermissionLogic<>(workflowPermission,modelResourcePermission,groupLocalService,GuestBook::getGuestbookId));
                        }),properties);
    }
    @Deactivate
    public void deactivate(){
        serviceRegistration.unregister();
    }
}
