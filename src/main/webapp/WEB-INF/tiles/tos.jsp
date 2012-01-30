<!-- Start tos.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <h1>Terms of Service</h1>

    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus</p>

    <p>auctor lorem sed neque rhoncus eu gravida est adipiscing. Fusce at
        accumsan justo. Proin mollis pretium quam eu fringilla. Ut accumsan
        placerat neque non scelerisque. Sed massa ante, mollis a fringilla
        in, vestibulum sit amet dolor. Integer vestibulum metus id libero
        accumsan scelerisque. Proin ullamcorper facilisis dictum. Duis
        hendrerit lacus non urna lacinia vulputate. Class aptent taciti
        sociosqu ad litora torquent per conubia nostra, per inceptos
        himenaeos. Suspendisse sed neque nec metus luctus adipiscing sed
        eget enim. Suspendisse potenti. Vestibulum congue neque ac nisi
        aliquet sodales. Morbi nec sapien nunc, eget aliquet velit. Etiam
        non metus nunc. Cras rhoncus porttitor sollicitudin.</p>

    <p>Etiam purus metus, congue nec porttitor quis, pellentesque ac ante.
        Quisque non dolor id dui vehicula ultricies at et purus. Vivamus sem
        nunc, pellentesque ut consectetur non, eleifend at diam. Sed at
        tincidunt turpis. Pellentesque commodo tincidunt mi, vulputate
        tincidunt neque rutrum et. Ut imperdiet rutrum neque. Quisque felis
        ipsum, pharetra quis adipiscing a, porttitor non magna. Proin et
        justo eros. Donec sit amet sollicitudin ligula. Suspendisse potenti.
        Nullam risus sem, mattis sed scelerisque imperdiet, varius vitae
        erat. Class aptent taciti sociosqu ad litora torquent per conubia
        nostra, per inceptos himenaeos. Praesent scelerisque commodo odio,
        nec pellentesque enim consequat eu. Duis suscipit luctus libero vel
        suscipit. Quisque sodales tellus et velit rutrum ultricies.
    </p>

    <p>
        Curabitur vel odio ante, vel scelerisque arcu. Proin ornare
        fringilla mi, eget convallis neque molestie sit amet. Vestibulum eu
        consequat turpis. Donec mollis vehicula nulla, eget tincidunt nisi
        vestibulum a. Nullam ac volutpat neque. Suspendisse at porta neque.
        Suspendisse id quam in risus placerat aliquam.
    </p>

    <p>
        Phasellus malesuada, mi sit amet bibendum sollicitudin, urna urna
        bibendum urna, id malesuada diam purus sed risus. Aenean viverra
        tristique rutrum. Phasellus in accumsan mi. Phasellus nec lacus
        felis. In hac habitasse platea dictumst. Etiam dictum risus eu lacus
        aliquam facilisis. Nunc laoreet congue sem vel eleifend. Lorem ipsum
        dolor sit amet, consectetur adipiscing elit. Etiam mollis, magna a
        eleifend congue, elit purus sodales nunc, nec viverra sapien tortor
        quis orci. Vivamus et felis ut enim vulputate suscipit pulvinar quis
        neque. Nulla quis libero id dolor consectetur semper. Phasellus quis
        molestie erat. Vivamus vulputate posuere lorem, et blandit neque
        molestie sed. Suspendisse a nisi tortor, ac commodo lacus. Ut
        aliquet dui rhoncus lacus convallis porttitor. Curabitur feugiat
        egestas enim, eu tristique nulla tristique id.
    </p>

    <p>
        Pellentesque in enim lectus, sit amet commodo urna. Donec feugiat
        imperdiet rhoncus. Vivamus in euismod libero. Pellentesque ac purus
        a mi vulputate lobortis ac at est. Vestibulum vitae nunc vehicula
        eros suscipit ultricies in congue urna. Fusce faucibus tellus quis
        mi adipiscing aliquet. Quisque elementum nisi sed turpis hendrerit
        adipiscing. Quisque id mi et dui hendrerit congue. Sed eget quam
        est. Pellentesque semper, mi at pellentesque vestibulum, leo felis
        euismod odio, bibendum posuere dolor nulla interdum risus. Proin
        auctor posuere neque egestas pretium.
    </p>

    <c:if var="fromRegistration" test="${fromRegistration == true}">

        <button id="noted">Noted</button>
        <script type="text/javascript">
        dojo.connect(dojo.byId("noted"), "onclick", function (event) {
            dojo.stopEvent(event);
            window.close();

        });
        </script>

    </c:if>

</section>
<!-- End tos.jsp -->