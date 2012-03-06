<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section style="width:500px; ">

    <script type="text/javascript">

        dojo.registerModulePath("json", "../../json");
        dojo.registerModulePath("todo", "../../todo");


        require(["todo/widgets/TaskListWidget/TaskListWidget",
                    "todo/store/JsonSchemaRest",
                    "dojo/store/Memory",
                    "dojo/query",
                    "dojo/_base/array",
                    "dojo/store/Cache", "dojo/behavior",
                "dojo/store/Observable",
                    "dojo/domReady!"],
                function (TaskListWidget, JsonSchemaRest, Memory, Query, array,
                          Cache,
                          behavior, Observable) {

                    $.ajax("/static/json/schema/todo.webapp.dto.TaskDTO", {
                                    dataType:"json",
                                    success:function (schema) {

                                        var schema = schema;
                                        var contentType =
                                                "application/todo.webapp.dto.TaskDTO+json";

                                        masterStore =
                                        Observable(JsonSchemaRest(schema,
                                                                  contentType,
                                                                  {target:"/api/tasks/<sec:authentication property="principal.username" />/current/"}));

                                        cachedStore = Cache(
                                                masterStore,
                                                Memory()
                                        );


                                        // Get a reference to our container
                                        var taskContainer = dojo.byId("taskListHolder");

                                        var widget =
                                                new TaskListWidget({store:
                                                        masterStore}).placeAt(taskContainer);
                                        widget.startup();
                                    }
                                });

                });

    </script>

    <link rel="stylesheet"
          href="/static/js/libs/todo/widgets/TaskListWidget/css/TaskRow.css"/>
<div id="taskListHolder">

</div>


</section>