<!doctype html>
<html>
<head>
    <meta name="layout" content="${grailsApplication.config.skin.layout}"/>
    <meta name="section" content="home"/>
    <title>API Key created |  ${grailsApplication.config.skin.orgNameLong}</title>
</head>
<body>
<div class="row">
    <div class="col-md-9" id="page-body" role="main">
        <h1>Created key</h1>
        <h3>${key?.apikey}</h3>
    </div>
</div>
</body>
</html>
