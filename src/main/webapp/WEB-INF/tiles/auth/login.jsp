<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <form action="/j_spring_security_check" method="POST">
        <div class="clearfix">
            <label for="username">Username</label>
            <input type="text" name="j_username" id="username" autofocus/>
        </div>

        <div class="clearfix">
            <label for="pass">Password</label>
            <input type="password" name="j_password"
                   id="pass"/>
        </div>

        <c:if test="${invalid == 'true'}">
            <div class="clearfix">
                <div class="errors">Invalid
                    username/password combination
                </div>
            </div>
        </c:if>

        <input type="submit" value="Submit"/>
    </form>

</div>