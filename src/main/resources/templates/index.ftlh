<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页</title>
    <#assign path="${springMacroRequestContext.getContextPath()}">
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script src="${path}/js/regsubmit.js"></script>
    <link rel="stylesheet" href="${path}/css/common.css">
    <link rel="stylesheet" href="${path}/css/index.css">
    <style>
        *{
            margin:0;
            padding:0;
        }
    </style>
</head>
<body>
    <iframe id="top-f" src="${path}/html/topbar.html" frameborder="0"></iframe>
    <div class="content">
        <iframe name="log-f" id="log-f" class="pa" src="${path}/html/login.html" frameborder="0"></iframe>
    </div>
    <iframe id="foot-f" src="${path}/html/footer.html" frameborder="0"></iframe>
</body>