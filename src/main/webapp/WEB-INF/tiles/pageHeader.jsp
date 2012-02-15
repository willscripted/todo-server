<!-- Start pageHeader.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="clearfix">
    <a href="/">
        <div id="logo">ToDo</div>
    </a>

    <span id="slogan">The secret of getting ahead is getting started. -
        Mark Twain</span>

    <div id="login-status">
        <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] != null}" >
            Hello, <sec:authentication property="principal.username" />.
            <a href="/logout/">[ Logout ]</a>
        </c:if>
        <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] == null}">

            <a href="/login/">[ Sign-in ]</a>
            <a href="/registration/">[ Create Account ]</a>

        </c:if>
    </div>
</div>
<!-- End pageHeader.jsp -->