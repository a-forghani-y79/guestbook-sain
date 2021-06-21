<%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestBookPermission" %>
<%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestBookModelPermission" %>
<%@ include file="./../init.jsp" %>

<%
    String mvcPath = ParamUtil.getString(request, "mvcPath");
    ResultRow resultRow = (ResultRow) request.getAttribute("SEARCH_CONTAINER_RESULT_ROW");
    GuestBook guestBook = (GuestBook) resultRow.getObject();
%>
<liferay-ui:icon-menu>
    <portlet:renderURL var="editURL">
        <portlet:param name="guestbookId" value="<%=String.valueOf(guestBook.getGuestbookId())%>"/>
        <portlet:param name="mvcPath" value="/guestbook_admin/edit_guestbook.jsp"/>
    </portlet:renderURL>
    <liferay-ui:icon icon="edit" message="Edit" url="<%=editURL.toString()%>"/>

    <c:if
            test="<%=GuestBookModelPermission.contains(permissionChecker, guestBook.getGuestbookId(), ActionKeys.DELETE) %>">


    <portlet:actionURL var="deleteURL" name="deleteGuestbook">
        <portlet:param name="guestbookId"
                       value="<%= String.valueOf(guestBook.getGuestbookId()) %>" />
        <portlet:param name="guestbookId" value="<%=String.valueOf(guestBook.getGuestbookId())%>"/>
    </portlet:actionURL>
    <liferay-ui:icon icon="delete" message="Delete" url="<%=deleteURL.toString()%>"/>
    </c:if>

    <c:if

                test="<%=GuestBookModelPermission.contains(permissionChecker, guestBook.getGuestbookId(), ActionKeys.PERMISSIONS) %>">

        <liferay-security:permissionsURL
                modelResource="<%= GuestBook.class.getName() %>"
                modelResourceDescription="<%= guestBook.getName() %>"
                resourcePrimKey="<%= String.valueOf(guestBook.getGuestbookId()) %>"
                var="permissionsURL" />

        <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

    </c:if>

</liferay-ui:icon-menu>