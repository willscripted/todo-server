// custom TaskRowWidget
define(["dojo/_base/declare", "dijit/_WidgetBase", "dijit/_TemplatedMixin", "dojo/text!./templates/TaskRowWidget.html", "dojo/_base/connect", "dojo/_base/lang"],
       function (declare, WidgetBase, TemplatedMixin, template, connect, lang) {
           return declare([WidgetBase, TemplatedMixin], {
               title:"Untitled",
               templateString:template,
               baseClass:"taskRowWidget",
               data: null,
               task: null,
               _deleteTask: lang.partial(function () {
                   console.log("Deleting Task...");
                   var id = this.task.id ? this.task.id : this.task.__id;
                   this.data.deleteItem(id);
                   this.destroy();
                   console.log("Object removed");
               }, this),
               _markCompleted: lang.partial(function() {
                   console.log("Marking complete...");
                   var id = this.task.id ? this.task.id : this.task.__id;
                   this.data.deleteItem(id);
                   this.destroy();
                   console.log("Object removed");
               }, this),
               constructor: function(params, srcNodeRef) {
                   this.task = params['item'];
                   this.data = params['data'];
               },
               postCreate: function() {
                   // Run any parent postCreate processes - can be done at any point
                   this.inherited(arguments);
               }
           });
       });