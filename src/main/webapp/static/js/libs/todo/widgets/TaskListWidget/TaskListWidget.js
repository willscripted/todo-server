// custom TaskListWidget
define(["dojo/_base/declare", "dijit/_WidgetBase", "dijit/_TemplatedMixin", "dojo/text!./templates/TaskListWidget.html", "dojo/_base/connect", "todo/widgets/TaskListWidget/TaskRowWidget", "dojo/_base/lang"],
       function (declare, WidgetBase, TemplatedMixin, template, connect, TaskRowWidget, lang) {
           return declare([WidgetBase, TemplatedMixin], {
               title:"\<\<Untitled!\>\>",
               templateString:template,
               baseClass:"taskListWidget",
               query: null,
               objectStore: null,
               constructor: function(params, srcNodeRef) {
                   this.query = params['query'];
                   this.objectStore = params['store'];
               },
               _viewResults: function(results) {

                   var appendTaskToContainer = lang.hitch(this, function(thing) {
                       // Create new row widget
                       var widget = new TaskRowWidget({item:thing, objectStore: this.objectStore});
                       widget.startup();

                       // Append
                       this.containerNode.appendChild(widget.domNode);
                   });

                   results.forEach(appendTaskToContainer);
               },
               postCreate:function () {

                   // Run any parent postCreate processes - can be done at any point
                   this.inherited(arguments);

                   this._viewResults(this.query);

               }
           });
       });