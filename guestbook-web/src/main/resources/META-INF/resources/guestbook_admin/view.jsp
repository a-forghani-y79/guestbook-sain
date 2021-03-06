<%@ include file="./../init.jsp" %>
<%--can't get message from language.properties--%>
<%--<liferay-ui:success key="guestbookAdded" message="guestbook-added"/>--%>
<%--<liferay-ui:success key="guestbookDeleted" message="guestbook-deleted"/>--%>
<%--<liferay-ui:success key="guestbookUpdated" message="guestbook-updated"/>--%>


<liferay-ui:success key="guestbookAdded" message="Guestbook added successfully."/>
<liferay-ui:success key="guestbookDeleted" message="Guestbook deleted successfully."/>
<liferay-ui:success key="guestbookUpdated" message="Guestbook updated successfully."/>

<liferay-ui:search-container total="<%= GuestBookLocalServiceUtil.getGuestBooksCount(scopeGroupId)%>">
    <liferay-ui:search-container-results
            results="<%= GuestBookLocalServiceUtil.getGuestbooks(scopeGroupId, searchContainer.getStart(),
                                                                 searchContainer.getEnd())%>"/>

    <liferay-ui:search-container-row className="com.liferay.docs.guestbook.model.GuestBook" modelVar="guestbook">
        <liferay-ui:search-container-column-text property="name"/>
<%--        <liferay-ui:search-container-column-status property="status"/>--%>
        <liferay-ui:search-container-column-jsp path="/guestbook_admin/guestbook_actions.jsp" align="right"/>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator/>
</liferay-ui:search-container>

<aui:button-row cssClass="guestbook-admin-buttons">
    <portlet:renderURL var="addGuestbookURL">
        <portlet:param name="mvcPath" value="/guestbook_admin/edit_guestbook.jsp"/>
        <portlet:param name="redirect" value='<%= "currentURL" %>'/>
    </portlet:renderURL>

    <aui:button onClick="<%= addGuestbookURL %>" value="Add Guestbook"/>
</aui:button-row>