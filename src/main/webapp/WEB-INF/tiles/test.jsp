<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section style="width:500px; ">

    <script type="text/javascript">

        dojo.registerModulePath("todo", "../../todo");


        require(["todo/widgets/TaskListWidget/TaskListWidget",
                    "dojo/store/JsonRest",
                    "dojo/store/Memory",
                    "dojo/store/Cache","dojo/behavior",
                    "dojo/domReady!"],
                function (TaskListWidget, JsonRest, Memory, Cache, behavior) {

                    // Get Data
                    myStore = Cache(
                            JsonRest({target:"/api/tasks/<sec:authentication property="principal.username" />/current/"}),
                            Memory()
                    );

                    var q = myStore.query({});

                    // Get a reference to our container
                    var taskContainer = dojo.byId("taskListHolder");

                    // Code to create widget pieces
                    var createThing = function (thing) {
                        var widget =
                                new TaskListWidget(thing).placeAt(taskContainer);
                    }

                    // Do create
                    q.forEach(createThing);

                    var myBehavior = {
                        // all <a class="noclick"></a> nodes:
                        "td.complete":{
                            // event names become event connections:
                            onclick:function (e) {
                                e.preventDefault(); // stop the default event handler
                                console.log('clicked! ', e.target);
                            }
                        },
                        // all <span> nodes
                        "td.remove":{
                            // for each:
                            onclick:function (n) {
                                console.log('found', n);
                            }
                        }
                    };
                    behavior.add(myBehavior);
                    behavior.apply();


                });

        // dojo 1.7 (AMD)
        require(["dojo/behavior",
                    "dojo/domReady!"], function (behavior) {

        });

    </script>

    <link rel="stylesheet"
          href="/static/js/libs/todo/widgets/TaskListWidget/css/TaskRow.css"/>
    <table>

        <thead>
        </thead>
        <tbody id="taskListHolder">
        <tr class="taskRowWidget">
            <td></td>
            <td class="title" style="padding-bottom: .5em;">
                <input type="text" id="newTask" placeholder="New Task..."
                       style="width: 98%;"/>
            </td>
            <td></td>
        </tr>

        </tbody>
    </table>


</section>