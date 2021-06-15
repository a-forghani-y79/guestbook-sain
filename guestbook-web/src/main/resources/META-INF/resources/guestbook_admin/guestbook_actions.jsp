<%@ page import="com.liferay.docs.guestbook.model.GuestBook" %>
<%@ include file="./../init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request,"mvcPath");
ResultRow resultRow = (ResultRow) request.getAttribute("SEARCH_CONTAINER_RESULT_ROW");
GuestBook guestBook = (GuestBook) resultRow.getObject();
%>
<liferay-ui:icon-menu>
    <portlet:renderURL var="editURL">
        <portlet:param name="guestbookId" value="<%=String.valueOf(guestBook.getGuestbookId())%>" />
        <portlet:param name="mvcPath" value="/guestbook_admin/edit_guestbook.jsp" />
    </portlet:renderURL>
    <liferay-ui:icon icon="edit" message="Edit" url="<%=editURL.toString()%>"/>
<%--    <portlet:renderURL var="deleteURL">--%>
<%--        <portlet:param name="guestbookId" value="<%=String.valueOf(guestBook.getGuestbookId())%>" />--%>
<%--    </portlet:renderURL>--%>
    <portlet:actionURL var="deleteURL" name="deleteGuestbook">
        <portlet:param name="guestbookId" value="<%=String.valueOf(guestBook.getGuestbookId())%>" />
    </portlet:actionURL>
    <liferay-ui:icon icon="delete" message="Delete" url="<%=deleteURL.toString()%>"/>
</liferay-ui:icon-menu>