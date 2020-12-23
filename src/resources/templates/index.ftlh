<#-- @ftlvariable name="programs" type="java.util.List<platform.model.ProgramDto>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><#if springMacroRequestContext.getRequestUri()?contains("/latest")>Latest<#else>Code</#if></title>
    <link href="/css/style.css" rel="stylesheet">
    <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.1/styles/default.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.1/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list programs as program>
    <span id="load_date">${program.date}</span>
    <#if 0 < program.getTime()>
        <div><span id="time_restriction">
        The code will be available for ${program.time} seconds</span></div></#if>
    <pre id="code_snippet"><code>${program.code}</code></pre>
</#list>
</body>
</html>