<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section style="width:500px; ">

    <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] != null}">

    <script type="text/javascript">
        dojo.registerModulePath("json", "../../json");
        dojo.registerModulePath("todo", "../../todo");

        var jsonStore;

        require([
                    "todo/widgets/TaskListWidget/TaskListWidget",
                    "dojox/rpc/OfflineRest",
                    "dojox/data/JsonRestStore",
                    "dojo/domReady!"],
                function ( TaskListWidget,
                          OfflineRest,
                          JsonRestStore) {

                    $.ajax("/static/json/schema/todo.webapp.dto.TaskDTO", {
                        dataType:"json",
                        success:function (schema) {

                            <%--var restService = Rest("/api/tasks/<sec:authentication property="principal.username" />/current/",true);--%>
                            jsonStore = new JsonRestStore({
                                target: "/api/tasks/<sec:authentication property="principal.username" />/current/"
                                                                     });

                            // Offline Rest
                            OfflineRest.addStore(jsonStore, "");

                            // Get a reference to our container
                            var taskContainer = dojo.byId("taskListHolder");

                            var widget = new TaskListWidget(
                                    {
                                        data:jsonStore
                                    }
                            ).placeAt(taskContainer);
                            widget.startup();
                        }
                    });

                });

    </script>

    <link rel="stylesheet"
          href="/static/js/libs/todo/widgets/TaskListWidget/css/TaskRow.css"/>
    <div id="taskListHolder">

    </div>

    </c:if>
    <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] == null}">

        <h1>Hello World</h1>
        Some test content for the world.

    </c:if>
</section>