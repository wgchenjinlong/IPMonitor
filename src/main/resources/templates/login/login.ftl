<#import "../lib.ftl" as lib>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login/signin.css" rel="stylesheet">
    <link href="css/common/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="js/vendor/jquery.min.js"></script>
    <script src="js/vendor/parsley/parsley.js"></script>
    <script src="js/vendor/parsley/parsley.remote.js"></script>
    <script src="js/vendor/parsley/zh_cn.js"></script>
    <style>

        html {
            background: url('image/login_background.jpg') no-repeat center center fixed;
            background-size: cover;
        }


    </style>
</head>
<body>

<div class="container">
    <form class="form-signin" role="form" action="/login" method="post" data-parsley-validate>
        <h2 class="form-signin-heading">登录</h2>
        <div class="form-group">
            <input type="text" name="username" class="form-control" value="${login.username!}" placeholder="用户名"
                 required data-parsley-required-message="不能为空"   autofocus>
        <#if error??>
            <@lib.error for="username" message="${error.username!}" />
        </#if>

        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" value="${login.password!}" placeholder="密码"
                   required data-parsley-required-message="不能为空">
        <#if error??>
            <@lib.error for="password" message="${error.password!}" />
        </#if>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

</div>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/vendor/bootstrap.min.js"></script>
</body>
</html>