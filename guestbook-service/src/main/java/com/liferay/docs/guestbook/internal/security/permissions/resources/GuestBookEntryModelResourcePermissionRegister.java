package com.liferay.docs.guestbook.internal.security.permissions.resources;

import com.liferay.docs.guestbook.constants.GuestBookConstants;
import com.liferay.docs.guestbook.constants.GuestbookWebPortletKeys;
import com.liferay.docs.guestbook.model.GuestBook;
import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.docs.guestbook.service.GuestBookEntryLocalService;
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
public class GuestBookEntryModelResourcePermissionRegister {

    @Activate
    public void activate(BundleContext bundleContext) {
        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("model.class.name", GuestBookEntry.class.getName());

        serviceRegistration = bundleContext.registerService(
                ModelResourcePermission.class,
                ModelResourcePermissionFactory.create(
                        GuestBookEntry.class, GuestBookEntry::getEntryId,
                        guestBookEntryLocalService::getGuestbookEntry, portletResourcePermission,
                        (modelResourcePermission, consumer) -> {
                            consumer.accept(
                                    new StagedModelPermissionLogic<>(
                                            stagingPermission, GuestbookWebPortletKeys.GUESTBOOKWEB,
                                            GuestBookEntry::getEntryId));
                            consumer.accept(
                                    new WorkflowedModelPermissionLogic<>(
                                            workflowPermission, modelResourcePermission,
                                            groupLocalService, GuestBookEntry::getEntryId));
                        }),
                properties);
    }
    @Deactivate
    public void deactivate(){
        serviceRegistration.unregister();
    }

    @Reference
    public GuestBookEntryLocalService guestBookEntryLocalService;

    @Reference(target = "(resource.name"+ GuestBookConstants.RESOURCE_NAME+")")
    private PortletResourcePermission portletResourcePermission;

    private ServiceRegistration<ModelResourcePermission> serviceRegistration;

    @Reference
    private StagingPermission stagingPermission;

    @Reference
    private WorkflowPermission workflowPermission;

    @Reference
    private GroupLocalService groupLocalService;
    
}
