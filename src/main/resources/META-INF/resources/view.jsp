<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<%@ page import="java.util.Map" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");
%>

<aui:select label="profession-rule" name="profession" value='<%= GetterUtil.getBoolean(context.get("profession")) %>'>
	<aui:option label="teacher" value="teacher" />
	<aui:option label="artist" value="artist" />
</aui:select>