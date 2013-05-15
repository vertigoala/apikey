<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta name="section" content="home"/>
    <title>Add an App | Atlas of Living Australia</title>
</head>
<body>
<div class="row-fluid">
    <div class="span9" id="page-body" role="main">
        <h1>${success ? 'App added' : 'App already exists'}</h1>
        <p>
          <g:if test="${success}">
            <h3>App name: ${app.name}</h3>
            <h3>Created: ${app.dateCreated}</h3><br/>
          </g:if>
        </p>

        <g:link url="/apikey" class="btn btn-ala">Back to options</g:link>
    </div>

</div>
</body>
</html>
