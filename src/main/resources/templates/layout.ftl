<#macro layout title="" header="" footer="">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/dashboard.css" rel="stylesheet">
    <link href="css/common/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery.min.js"></script>
${header}
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">IP监控系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right" style="margin-right: 30px">
                <li><a href="#"><i class="glyphicon glyphicon-user"></i> 管理员</a></li>
            <#--<li><a href="#">Settings</a></li>-->
            <#--<li><a href="#">Profile</a></li>-->
            <#--<li><a href="#">Help</a></li>-->
            </ul>
        <#--<form class="navbar-form navbar-right">-->
        <#--<input type="text" class="form-control" placeholder="Search...">-->
        <#--</form>-->
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="monitor">监控列表</a></li>
            <#--<li><a href="#">Reports</a></li>-->
            <#--<li><a href="#">Analytics</a></li>-->
            <#--<li><a href="#">Export</a></li>-->
            </ul>
        <#--<ul class="nav nav-sidebar">-->
        <#--<li><a href="">Nav item</a></li>-->
        <#--<li><a href="">Nav item again</a></li>-->
        <#--<li><a href="">One more nav</a></li>-->
        <#--<li><a href="">Another nav item</a></li>-->
        <#--<li><a href="">More navigation</a></li>-->
        <#--</ul>-->
        <#--<ul class="nav nav-sidebar">-->
        <#--<li><a href="">Nav item again</a></li>-->
        <#--<li><a href="">One more nav</a></li>-->
        <#--<li><a href="">Another nav item</a></li>-->
        <#--</ul>-->
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <#nested/>
        </div>
    </div>
</div>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
${footer}
</body>
</html>
</#macro>