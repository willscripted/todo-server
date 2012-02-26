// custom TaskListWidget
define(["dojo/_base/declare", "dijit/_WidgetBase", "dijit/_TemplatedMixin", "dojo/text!./templates/TaskRowWidget.html", "dojo/_base/connect"],
       function (declare, WidgetBase, TemplatedMixin, template, connect) {
           return declare([WidgetBase, TemplatedMixin], {
               title:"\<\<Untitled!\>\>",
               templateString:template,
               baseClass:"taskRowWidget",
               objectStore: null,
               _deleteTask: function () {
                    console.log("Deleting Task...");
               },
               _markCompleted: function() {
                    console.log("Marking Task completed...");
               },
               constructor: function(params, srcNodeRef) {
                   this.task = params['item'];
                   this.objectStore = params['objectStore'];
                   console.log(this.objectStore);
               },
               postCreate: function() {
                   // Run any parent postCreate processes - can be done at any point
                   this.inherited(arguments);
               }
           });
       });