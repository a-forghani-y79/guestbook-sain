<%@ page import="com.liferay.docs.guestbook.model.GuestBook" %>
<%@ page import="com.liferay.docs.guestbook.service.GuestBookLocalServiceUtil" %>
<%@ include file="./../init.jsp" %>
<%
long guestbookId = ParamUtil.getLong(request,"guestbookId");
    GuestBook guestBook = null;
    if (guestbookId>0){
        guestBook = GuestBookLocalServiceUtil.getGuestBook(guestbookId);
    }
%>
<portlet:renderURL var="viewURL">
    <portlet:param name="mcvPath" value="/guestbook_admin/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name="<%=guestBook==null?"addGuestbook":"updateGuestbook"%>" var="editGuestbookURL"/>

<aui:form action="<%=editGuestbookURL%>" name="fm">
    <aui:model-context bean="<%=guestBook%>" model="<%=GuestBook.class%>"/>
    <aui:input name="guestbookId" type="hidden" value="<%=guestBook==null?"":guestBook.getGuestbookId()%>"/>
    <aui:fieldset>
        <aui:input name="name"/>
    </aui:fieldset>
    <aui:button-row>
        <aui:button type="submit"/>
        <aui:button type="cancel" onClick="<%=viewURL%>"/>
    </aui:button-row>

</aui:form>
