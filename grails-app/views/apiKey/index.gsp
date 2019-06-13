<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="${grailsApplication.config.skin.layout}"/>
    <meta name="section" content="home"/>
    <meta name="breadcrumbParent" content="${g.createLink(uri:'/')},API Keys Home" />
    <meta name="breadcrumb" content="API Keys" />
    <title>API keys |  ${grailsApplication.config.skin.orgNameLong}</title>
    <asset:stylesheet src="application.css" />
</head>
<body>

<div class="row">
    <div class="col-md-12">
        <table class="table table-striped table-compact table-hover">
            <thead>
                <tr>
                    <g:sortableColumn property="app.name" title="App Name"/>
                    <g:sortableColumn property="userId" title="User ID"/>
                    <g:sortableColumn property="userEmail" title="User Email"/>
                    <g:sortableColumn property="dateCreated" title="Date Created"/>
                    <g:sortableColumn property="apikey" title="API Key"/>
                    <g:sortableColumn property="lastUsed" title="Last Used"/>
                    <g:sortableColumn property="lastRemoteAddr" title="Remote Address"/>
                    <g:sortableColumn property="enabled" title="Enabled"/>
                </tr>
            </thead>
            <tbody>
            <g:each in="${APIKeyList}">
                <tr>
                    <td>${it.app.name}</td>
                    <td><u:link baseProperty="userdetails.url" paths="['admin','user','show',it.userId]">${it.userId}</u:link></td>
                    <td>${it.userEmail}</td>
                    <td><g:formatDate date="${it.dateCreated}" /></td>
                    <td>${it.apikey}</td>
                    <td><g:formatDate date="${it.lastUsed}" /></td>
                    <td>${it.lastRemoteAddr}</td>
                    <td>
                        <g:form action="enableKey" useToken="true">
                            <g:hiddenField name="key" value="${it.apikey}"/>
                            <g:hiddenField name="enabled" value="${!it.enabled}"/>
                            <g:hiddenField name="max" value="${params.max}"/>
                            <g:hiddenField name="offset" value="${params.offset}"/>
                            <g:hiddenField name="sort" value="${params.sort}"/>
                            <g:hiddenField name="order" value="${params.order}"/>
                            <g:submitButton class="btn btn-link" name="submit" value="${it.enabled}" />
                        </g:form>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="col-md-12 text-center">
        <hf:paginate total="${APIKeyList?.totalCount ?: 0}" max="100"/>
    </div>
</div>

</body>
</html>