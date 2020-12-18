<#-- @ftlvariable name="programs" type="java.util.List<platform.model.ProgramDto>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Code</title>
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
<#list programs as program>
    <span id="load_date">${program.date}</span>
    <pre id="code_snippet">${program.code}</pre>
</#list>
</body>
</html>