<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create</title>
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
<div>
    <textarea id="code_snippet"></textarea>
</div>
<div>
    <label for="time_restriction">Time restriction: </label>
    <input id="time_restriction" type="text"/>
</div>
<div>
    <label for="views_restriction">Views restriction: </label>
    <input id="views_restriction" type="text"/>
</div>
<div>
    <button id="send_snippet" type="submit" onClick="send()">Submit</button>
</div>
</body>
<script src="/js/script.js"></script>
</html>
