<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <form action="/j_spring_security_check" method="POST">
    <fieldset>
        <div class="group">
            <div class="label">
                <label for="username">Username</label>
            </div>
            <div class="ctrl">
                <input type="text" name="j_username" id="username" autofocus/>
            </div>
        </div>
        <div class="group">
            <div class="label">
                <label for="pass">Password</label>
            </div>
            <div class="ctrl">
                <input type="password" name="j_password"
                       id="pass"/>
            </div>
        </div>
        <c:if test="${invalid == 'true'}">
            <div class="group no-label">
                <div class="ctrl">
                    <div class="errorMessage">Invalid
                        username/password combination
                    </div>
                </div>
            </div>
        </c:if>

        <div class="form-group no-label">
            <div class="ctrl">
                <input type="submit" value="Submit"/>
            </div>
        </div>
    </fieldset>
    </form>

</div>