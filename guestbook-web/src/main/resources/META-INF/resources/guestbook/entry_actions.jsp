<%@include file="../init.jsp" %>

<%
    String mvcPath = ParamUtil.getString(request, "mvcPath");

    ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

    GuestBookEntry entry = (GuestBookEntry) row.getObject();
%>

<liferay-ui:icon-menu>
    <c:if test="<%=GuestBookEntryPermission.contains(permissionChecker,entry.getEntryId(),ActionKeys.UPDATE)%>">

        <portlet:renderURL var="editURL">
            <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>"/>
            <portlet:param name="guestbookId" value="<%=String.valueOf(entry.getGuestbookId())%>"/>
            <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp"/>
        </portlet:renderURL>

        <liferay-ui:icon image="edit" message="Edit"
                         url="<%=editURL.toString() %>"/>
    </c:if>
    <c:if test="<%=GuestBookEntryPermission.contains(permissionChecker,entry.getEntryId(),ActionKeys.PERMISSIONS)%>">
        <liferay-security:permissionsURL
                modelResource="<%=GuestBookEntry.class.getName()%>"
                modelResourceDescription="<%=entry.setMessage();%>"
                resourcePrimKey="<%=String.valueOf(entry.getEntryId())%>"
                var="permissionsURL"/>
        <liferay-ui:icon icon="permissions" url="<%=permissionsURL.toString()%>"/>
    </c:if>
    <c:if test="<%=GuestBookEntryPermission.contains(permissionChecker,entry.getEntryId(),ActionKeys.DELETE)%>">
        <portlet:actionURL name="deleteEntry" var="deleteURL">
            <portlet:param name="entryId"
                           value="<%= String.valueOf(entry.getEntryId()) %>"/>
            <portlet:param name="guestbookId"
                           value="<%= String.valueOf(entry.getGuestbookId()) %>"/>
        </portlet:actionURL>

        <liferay-ui:icon-delete url="<%=deleteURL.toString() %>"/>
    </c:if>
</liferay-ui:icon-menu>