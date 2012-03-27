// custom TaskListWidget
define(["dojo/_base/declare",
           "dijit/_WidgetBase",
           "dijit/_TemplatedMixin",
           "dojo/text!./templates/TaskListWidget.html",
           "dojo/_base/connect",
           "todo/widgets/TaskListWidget/TaskRowWidget",
           "dojo/_base/lang"],
       function (declare, WidgetBase, TemplatedMixin, template, connect,
                 TaskRowWidget, lang) {
           return declare([WidgetBase, TemplatedMixin], {
               title:"Untitled!",
               templateString:template,
               baseClass:"taskListWidget",
               data:null,
               _submit:function (event) {
                   dojo.stopEvent(event);

                   var input = $(this.domNode).find("input[name='task']");
                   var title = input.val();
                   input.val("");

                   // Create unique id

                   var date = new Date;
                   var task = {
                       title:title,
                       description:"",
                       complete:false
                   };

                   task = this.data.newItem(task);
                   this.data.put(task)
                   this.insertRow(task);
               },
               constructor:function (params, srcNodeRef) {
                   this.data = params['data'];
               },
               insertRow:function (item) {
                   // Create new row widget
                   var widget = new TaskRowWidget({item:item, data:this.data});
                   widget.startup();

                   // Append
                   this.containerNode.appendChild(widget.domNode);
               },
               postCreate:function () {

                   // Run any parent postCreate processes - can be done at any point
                   this.inherited(arguments);

                   var processResults = lang.hitch(this, function (results) {
                       console.log(results);
                       for (var i = 0; i < results.length; i++) {
                           this.insertRow(results[i]);
                       }
                   });

                   var results = this.data.fetch({query:"", onComplete:processResults});

               }
           });
       });