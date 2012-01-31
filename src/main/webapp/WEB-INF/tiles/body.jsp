<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Start body.jsp -->
<section style="width: 500px;">
    <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] != null}" >

        <h1>Hello</h1>
<p>
        Looking for more than a basic registration / login system? Check back
    soon if you are on the web. Or if running this on your own system, get
    some updated source code!
</p><p>
        To report a bug or send some feedback, <br />
        contact Will (whobrien@colby.edu).
</p>

    </c:if>
    <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] == null}">

        <h1>Hello World</h1>
        Some test content for the world.

    </c:if>


</section>
<!-- End body.jsp -->