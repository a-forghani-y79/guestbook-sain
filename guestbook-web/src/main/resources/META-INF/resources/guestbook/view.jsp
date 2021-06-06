<%@ include file="../init.jsp"%>

<liferay-portlet:renderURL var="addEntryURL">
    <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp" />
</liferay-portlet:renderURL>

<aui:button-row>
    <aui:button value="Add Entry" onClick="<%= addEntryURL.toString()%>"/>
</aui:button-row>