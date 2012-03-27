<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en" manifest="<tiles:getAsString name="manifest" />" /><!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title><tiles:insertAttribute name="title"/></title>
    <meta name="description" content="" />
    <meta name="author" content="Will O'Brien" />

    <meta name="viewport" content="width=device-width,initial-scale=1" />

    <link rel="stylesheet" href="/static/css/style.css" />
    <link rel="stylesheet" href="/static/css/site.css" />
    <link href="/static/images/favicon.ico" rel="icon" type="image/x-icon" />

    <script src="/static/js/libs/modernizr-2.0.6.min.js"></script>
    <script type="text/javascript">
        var dojoConfig = {
            parseOnLoad: true,
            isDebug: true
        };
    </script>

    <%--<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.7.1/dojo/dojo.js" type="text/javascript"></script>--%>
    <%--<script>window.dojo ||--%>
            <%--document.write('<script src="/static/js/libs/dojo-release-1.7.1/dojo/dojo.js"><\/script>')</script>--%>
    <script src="/static/js/libs/dojo172/dojo/dojo.js"></script>
    <script src="/static/js/libs/jquery-1.7.1.min.js">
    <%--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>--%>
    <%--<script>window.jQuery || document.write('<script src="/static/js/libs/jquery-1.7.1.min.js"><\/script>')</script>--%>


    <script type="text/javascript">
        dojo.require('dojo/parser');
    </script>
    <tiles:insertAttribute name="head"/>
</head>
<body>

<div id="container">
    <header>
        <tiles:insertAttribute name="pageHeader" />
    </header>
    <div id="main" role="main">
        <tiles:insertAttribute name="body"/>
    </div>
    <footer>
        <tiles:insertAttribute name="footer"/>
    </footer>
</div> <!--! end of #container -->

<!--[if lt IE 7 ]>
<script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.2/CFInstall.min.js"></script>
<script>window.attachEvent("onload",function(){CFInstall.check({mode:"overlay"})})</script>
<![endif]-->

<tiles:insertAttribute name="js" />
</body>
</html>