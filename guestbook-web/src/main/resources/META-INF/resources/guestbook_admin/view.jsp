<%@ page import="com.liferay.docs.guestbook.service.GuestBookLocalServiceUtil" %>
<%@ include file="./../init.jsp" %>

<liferay-ui:search-container total="<%=GuestBookLocalServiceUtil.getGuestBooksCount(scopeGroupId)%>">
    <liferay-ui:search-container-results
            results="<%=GuestBookLocalServiceUtil.getGuestbooks(scopeGroupId,searchContainer.getStart(),searchContainer.getEnd())%>"/>
    <liferay-ui:search-container-row className="com.liferay.docs.guestbook.model.GuestBook" modelVar="guestbook">
        <liferay-ui:search-container-column-text property="name"/>
        <liferay-ui:search-container-column-date property="createDate"/>
        <liferay-ui:search-container-column-jsp path="./guestbook_admin/guestbook_actions.jsp" align="right"/>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator/>
</liferay-ui:search-container>

<aui:button-row cssClass="guestbook-admin-button">
    <portlet:renderURL var="addGuestbookURL">
        <portlet:param name="mvcPath" value="./guestbook_admin/edit_guestbook.jsp" />
        <portlet:param name="redirect" value="<%="currentURL"%>" />
    </portlet:renderURL>
<aui:button value="Add GuestBook" onClick="<%=addGuestbookURL.toString()%>"></aui:button>
</aui:button-row>