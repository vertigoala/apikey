<%@ page import="apikey.App" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="${grailsApplication.config.skin.layout}"/>
    <meta name="section" content="home"/>
    <title>API keys |  ${grailsApplication.config.skin.orgNameLong}</title>
</head>
<body>
<div class="row">
    <div class="col-md-9" id="page-body" role="main">

        <h1>Get an API key</h1>
        <g:form action="submit" controller="getKey">
            <div class="form-group">
                <label for="appName">App name:</label>
                <g:select class="form-control" optionKey="name" optionValue="name" name="appName" from="${App.findAll()}" /><br/>
            </div>
            <p class="help-block">
                This is currently only available for ${grailsApplication.config.skin.orgNameLong} developers.<br/>
                These keys give <b>write</b> access to certain applications.
            </p>
            <g:submitButton name="Generate a key for this app" class="btn btn-primary"/>
        </g:form>

        <h1>Check an API key</h1>
        <g:form action="checkKey" controller="checkKey">
            <div class="form-group">
                <label>Key</label>
                <input class="form-control" type="text" size="36" width="200" name="apikey"/>
                <p class="help-block">To check the validity of a check, paste the key in the text box below and hit "Check key"</p>
            </div>
            <g:submitButton name="Check key" class="btn btn-primary"/>
        </g:form>

        <h1>Check an API key using webservices</h1>
        <g:form mapping="wsCheck" method="get">
            <div class="form-group">
                <label>Key</label>
               <input class="form-control" type="text" size="36" width="200" name="apikey"/>
                <p class="help-block">
                    To test the webservice check, use this form.
                    The response will be in JSON.
                </p>
            </div>
            <g:submitButton name="submit" value="Check key via webservice" class="btn btn-primary"/>
        </g:form>

        <h1>Add an App</h1>
        <g:form controller="App" action="addAnApp">
            <div class="form-group">
                <label>App name</label>
                <input class="form-control" type="text" size="36" width="200" name="name"/>
                <p>Please specify a unique name for you application</p>
            </div>
            <g:submitButton name="submit" value="Add App" class="btn btn-primary"/>
        </g:form>
    </div>
</div>
</body>
</html>
