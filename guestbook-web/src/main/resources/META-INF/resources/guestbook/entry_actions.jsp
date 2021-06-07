<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.taglib.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.docs.guestbook.model.GuestBookEntry" %>
<%@include file="../init.jsp" %>

<%
    String mvcPath = ParamUtil.getString(request, "mvcPath");
    //??
    ResultRow resultRow = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    GuestBookEntry entry = (GuestBookEntry) resultRow.getObject();
%>

<liferay-ui:icon-menu>
    <liferay-portlet:renderURL var="editURL">
        <portlet:param name="entryId" value="<%=String.valueOf(entry.getEntryId())%>" />
        <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp"/>
    </liferay-portlet:renderURL>
    <liferay-ui:icon image="edit" message="Edit" url="<%=editURL.toString()%>"/>

    <portlet:actionURL name="deleteEntry" var="deleteURL">
        <portlet:param name="entryId" value="<%=String.valueOf(entry.getEntryId())%>" />
        <portlet:param name="guestbookId" value="<%=String.valueOf(entry.getGuestbookId())%>" />
    </portlet:actionURL>
    <liferay-ui:icon image="delete" url="<%=deleteURL.toString()%>"/>
</liferay-ui:icon-menu>