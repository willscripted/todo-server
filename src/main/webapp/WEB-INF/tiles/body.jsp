<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Start body.jsp -->
<section style="width: 500px;">
    <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] != null}">

        <h1>Hello</h1>



        <p>
            To report a bug or send some feedback, <br/>
            contact Will (whobrien@colby.edu).
        </p>

        <div class="clearfix">

            <form id="createTask"
                  json-schema="/static/json/schema/todo.webapp.dto.TaskDTO">
                <div>
                    <label for="task">Task</label>
                    <input id="task" name="title" autofocus placeholder="New Task..." type="text">

                    <div class="errors"></div>
                </div>

            </form>
        </div>

        <script type="text/javascript">

            dojo.registerModulePath("json", "../../json");
            dojo.registerModulePath("todo", "../../todo");
            dojo.require("json.schema.lib.validate");
            dojo.require("todo.Form");


            var responseHandlers = {
                201:function () {
                    console.log("Task Created");
                }
            }

            var todoForm = new todo.Form({'formId':"createTask"});
            todoForm.setContentType("application/todo.webapp.dto.TaskDTO+json");
            todoForm.setResponseHandler(responseHandlers);
            todoForm.getURI = function () {
                return "/api/tasks/<sec:authentication property="principal.username" />";
            }
            todoForm.setMethod("POST");

        </script>

        <div id="myFirstGrid"></div>


        <link rel="stylesheet" href="/static/js/libs/dojo171/dojox/grid/resources/Grid.css"/>
        <script type="text/javascript" charset="">
            <%--dojo.require("dojox/grid/cells");--%>
            <%--dojo.require("dojox/grid/cells/dijit");--%>




            <%--var myStore;--%>
            <%--var dataStore;--%>

            <%--function formatter(datum) {--%>
            <%--var bool = new dojox.grid.cells.Bool();--%>
            <%--bool.doclick = function(e) {--%>
            <%--console.log("Deleting...");--%>
            <%--dataStore.deleteItem(datum);--%>
            <%--dataStore.save();--%>
            <%--};--%>

            <%--bool._destoryOnRemove = true;--%>
            <%--return bool.formatEditing();--%>
            <%--}--%>

            <%--var structure = [--%>

            <%--{name:" ",--%>
            <%--field:"_item",--%>
            <%--width:3,--%>
            <%--formatter: formatter,--%>
            <%--styles:'text-align: center;'},--%>
            <%--{name:"Task",--%>
            <%--field:"title",--%>
            <%--width:"200px",--%>
            <%--editable:true}--%>

            <%--];--%>

            <%--require(["dojo/store/JsonRest",--%>
            <%--"dojo/store/Memory",--%>
            <%--"dojo/store/Cache",--%>
            <%--"dojox/grid/DataGrid",--%>
            <%--"dojo/data/ObjectStore",--%>
            <%--"dojo/domReady!"],--%>
            <%--function (JsonRest, Memory, Cache, DataGrid, ObjectStore) {--%>
            <%--myStore = Cache(--%>
            <%--JsonRest({target:"/api/tasks/<sec:authentication property="principal.username" />/current/"}),--%>
            <%--Memory()--%>
            <%--);--%>

            <%--grid = new DataGrid({--%>
            <%--store:dataStore =--%>
            <%--ObjectStore({objectStore:myStore}),--%>
            <%--structure:structure--%>
            <%--}, "myFirstGrid");--%>
            <%--grid.startup();--%>

            <%--});--%>

            var myStore;

            require(["dojo/store/JsonRest",
                        "dojo/store/Memory",
                        "dojo/store/Cache",
                        "todo/widgets/TaskListWidget/TaskListWidget",
                        "dojo/_base/array",
                        "dojo/domReady!"],
                    function (JsonRest, Memory, Cache, TaskListWidget,
                              arrayUtil) {
                        myStore = Cache(
                                JsonRest({target:"/api/tasks/<sec:authentication property="principal.username" />/current/"}),
                                Memory()
                        );


                        var data = [
                            {"id":1, "title":"asdf", "description":null, "complete":false},
                            {"id":2, "title":"asdf", "description":null, "complete":false},
                            {"id":3, "title":"asdf", "description":null, "complete":false},
                            {"id":4, "title":"asdf", "description":null, "complete":false},
                            {"id":5, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":6, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":7, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":8, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":9, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":10, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":11, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":12, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":13, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":14, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":15, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":16, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":17, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":18, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":19, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":20, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":21, "title":"asdfasdf", "description":null, "complete":false},
                            {"id":22, "title":"asdfasdf", "description":null, "complete":true},
                            {"id":23, "title":"hello?", "description":null, "complete":false}
                        ]
                        console.log(data);


                        var container = dojo.byId("putStuffHere");

                        arrayUtil.forEach(data, function (task) {
                            // Create our widget and place it
                            var widget =
                                    new TaskListWidget(task).placeAt(putStuffHere);
                        });

                    });

            require(["todo/widgets/TaskListWidget/TaskListWidget",
                        "dojo/domReady!"],
                    function (TaskListWidget) {


                    });


        </script>

        <table>
            <thead>

            </thead>
            <tbody id="putStuffHere"></tbody>
        </table>

        <!-- Desired List Format -->


    </c:if>
    <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'] == null}">

        <h1>Hello World</h1>
        Some test content for the world.

    </c:if>


</section>
<!-- End body.jsp -->