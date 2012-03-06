// custom TaskRowWidget
define(["dojo/_base/declare", "dijit/_WidgetBase", "dijit/_TemplatedMixin", "dojo/text!./templates/TaskRowWidget.html", "dojo/_base/connect"],
       function (declare, WidgetBase, TemplatedMixin, template, connect) {
           return declare([WidgetBase, TemplatedMixin], {
               title:"Untitled",
               templateString:template,
               baseClass:"taskRowWidget",
               objectStore: null,
               _deleteTask: function () {
                   console.log("Deleting Task...");
                   // Todo - need to xhr req delete this, store can't
                   this.objectStore.remove(this.task.id);
                   console.log("Object removed");
               },
               _markCompleted: function() {
                   console.log("Marking complete...");
                   this.objectStore.remove(this.task.id);
                   console.log(this.task.id);
                   console.log("Object removed");
               },
               constructor: function(params, srcNodeRef) {
                   this.task = params['item'];
                   this.objectStore = params['objectStore'];
               },
               postCreate: function() {
                   // Run any parent postCreate processes - can be done at any point
                   this.inherited(arguments);
               }
           });
       });