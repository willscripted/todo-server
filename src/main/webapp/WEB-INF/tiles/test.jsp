<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section style="width:500px; ">

    <script type="text/javascript">

        dojo.registerModulePath("todo", "../../todo");


        require(["todo/widgets/TaskListWidget/TaskListWidget",
                    "dojo/store/JsonRest",
                    "dojo/store/Memory",
                    "dojo/query",
                    "dojo/_base/array",
                    "dojo/store/Cache", "dojo/behavior",
                    "dojo/domReady!"],
                function (TaskListWidget, JsonRest, Memory, Query, array,
                          Cache,
                          behavior) {

//                    var myBehavior = {
//                        // all <a class="noclick"></a> nodes:
//                        "td.complete":{
//                            // event names become event connections:
//                            onclick:function (e) {
//                                e.preventDefault();
//                                // stop the default event handler
//                                console.log('clicked! ', e.target);
//                            }
//                        },
//                        // all <span> nodes
//                        "td.remove":{
//                            // for each:
//                            onclick:function (n) {
//                                console.log('found', n);
//                            }
//                        }
//                    };
//                    behavior.add(myBehavior);


                    // Get Data
                    myStore = Cache(
                            JsonRest({target:"/api/tasks/<sec:authentication property="principal.username" />/current/"}),
                            Memory()
                    );

                    var q = myStore.query({});

                    // Get a reference to our container
                    var taskContainer = dojo.byId("taskListHolder");

                    var widget =
                            new TaskListWidget({query:q, store: myStore}).placeAt(taskContainer);

//                    widget.get("query").forEach(function(thing) {
//                       console.log(thing);
//                    });

//
//                    // Code to create widget pieces
//                    var createThing = function (thing) {
//                        var widget = new TaskListWidget(thing).placeAt(taskContainer);
//
//                    }
//
//                    q.forEach(createThing).then(function () {
////                        behavior.apply();
//                    });

                });

    </script>

    <link rel="stylesheet"
          href="/static/js/libs/todo/widgets/TaskListWidget/css/TaskRow.css"/>
<div id="taskListHolder">

</div>


</section>