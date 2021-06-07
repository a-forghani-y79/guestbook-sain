<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ include file="../init.jsp"%>


<%
long guestbookId = Long.parseLong((String) request.getAttribute("guestbookId"));
%>

<aui:button-row cssClass="guestbook-buttons">
    <liferay-portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/gesubook/edit_entry.jsp" />
        <portlet:param name="guestbookId" value="<%=String.valueOf(guestbookId)%>" />
    </liferay-portlet:renderURL>
    <aui:button value="Add Entry" onClick="<%= addEntryURL.toString()%>"/>
</aui:button-row>

<liferay-ui:search-container-row className="com.liferay.docs.guestbook.model.GuestBookEntry" modelVar="entry">
    <liferay-ui:search-container-column-text property="name"/>
    <liferay-ui:search-container-column-text property="email"/>
    <liferay-ui:search-container-column-text property="message"/>
    <liferay-ui:search-container-column-jsp path="/gusetbook/entry_action.jsp" align="right"/>
</liferay-ui:search-container-row>
<%--???--%>
<liferay-ui:search-iterator/>

