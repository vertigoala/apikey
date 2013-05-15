<%@ page import="apikey.App" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta name="section" content="home"/>
    <title>API keys | Atlas of Living Australia</title>
</head>
<body>
<div class="row-fluid">
    <div class="span9" id="page-body" role="main">

        <h1>Get an API key</h1>
        <g:form action="submit" controller="getKey">
            <p>
               This is currently only available for ALA developers.<br/>
               These keys give <b>write</b> access to certain applications.
               If you think you should have access, send an email to
               <a href="mailto:support@ala.org.au">support@ala.org.au</a>
            </p>
            <label for="appName">App name:</label>
            <g:select optionKey="name" optionValue="name" name="appName" from="${App.findAll()}" /><br/>
            <g:submitButton name="Generate a key for this app" class="btn btn-ala"/>
        </g:form>

        <h1>Check an API key</h1>
        <p>To check the validity of a check, paste the key in the text box below and hit "Check key"</p>
        <g:form action="checkKey" controller="checkKey">
            <p>
               <input type="text" size="36" width="200" name="apikey"/>
            </p>
            <g:submitButton name="Check key" class="btn btn-ala"/>
        </g:form>

        <h1>Check an API key using webservices</h1>
        <p>
            To test the webservice check, use the form below.
            The response will be in JSON.
        </p>
        <g:form mapping="wsCheck" method="get">
            <p>
               <input type="text" size="36" width="200" name="apikey"/>
            </p>
            <g:submitButton name="submit" value="Check key via webservice" class="btn btn-ala"/>
        </g:form>

        <h1>Add an App</h1>
        <p> Please specify a unique name for you application</p>
        <g:form controller="App" action="addAnApp">
            <p>
               <input type="text" size="36" width="200" name="name"/>
            </p>
            <g:submitButton name="submit" value="Add App" class="btn btn-ala"/>
        </g:form>
    </div>
</div>
</body>
</html>
