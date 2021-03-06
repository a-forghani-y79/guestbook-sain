<%@include file="../init.jsp" %>
<liferay-ui:success key="entryAdded" message="entry-added"/>
<liferay-ui:success key="entryDeleted" message="entry-deleted" />


<%
    long guestbookId = Long.valueOf((Long) request
            .getAttribute("guestbookId"));
%>

<aui:nav cssClass="nav-tabs">
    <%
        List<GuestBook> guestBooks = GuestBookLocalServiceUtil.getGuestbooks(scopeGroupId);
        for (int i = 0; i < guestBooks.size(); i++) {
            GuestBook guestBook = guestBooks.get(i);
            String cssClass = StringPool.BLANK;
            if (guestBook.getGuestbookId() == guestbookId) {
                cssClass = "active";
            }

    %>
    <portlet:renderURL var="viewPageURL">
        <portlet:param name="mcvPath" value="/guestbook/view.jsp"/>
        <portlet:param name="guestbookId" value="<%=String.valueOf(guestBook.getGuestbookId())%>"/>
    </portlet:renderURL>
    <aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
                  label="<%=HtmlUtil.escape(guestBook.getName())%>"/>
    <%
        }%>

</aui:nav>

<aui:button-row cssClass="guestbook-buttons">

    <portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp"/>
        <portlet:param name="guestbookId"
                       value="<%=String.valueOf(guestbookId)%>"/>
    </portlet:renderURL>

    <aui:button onClick="<%=addEntryURL.toString()%>" value="Add Entry"/>

</aui:button-row>

<liferay-ui:search-container total="<%=GuestBookEntryLocalServiceUtil.getGuestBookEntriesCount()%>">
    <liferay-ui:search-container-results
            results="<%=GuestBookEntryLocalServiceUtil.getGuestbookEntries(scopeGroupId,
                    guestbookId, searchContainer.getStart(),
                    searchContainer.getEnd())%>"/>

    <liferay-ui:search-container-row
            className="com.liferay.docs.guestbook.model.GuestBookEntry" modelVar="entry">
        <liferay-ui:search-container-column-text property="name"/>

        <liferay-ui:search-container-column-text property="message"/>
        <liferay-ui:search-container-column-text property="email"/>
        <liferay-ui:search-container-column-date property="modifiedDate"/>


        <liferay-ui:search-container-column-jsp
                align="right"
                path="/guestbook/entry_actions.jsp"/>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator/>

</liferay-ui:search-container>