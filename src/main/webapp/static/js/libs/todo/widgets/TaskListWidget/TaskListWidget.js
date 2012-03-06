// custom TaskListWidget
define(["dojo/_base/declare",
           "dijit/_WidgetBase",
           "dijit/_TemplatedMixin",
           "dojo/text!./templates/TaskListWidget.html",
           "dojo/_base/connect",
           "todo/widgets/TaskListWidget/TaskRowWidget",
           "dojo/_base/lang",
           "dijit/WidgetSet"],
       function (declare, WidgetBase, TemplatedMixin, template, connect,
                 TaskRowWidget, lang, WidgetSet) {
           return declare([WidgetBase, TemplatedMixin], {
               title:"Untitled!",
               templateString:template,
               baseClass:"taskListWidget",
               query:null,
               objectStore:null,
               widgetSet:WidgetSet(),
               _submit:function (event) {
                   dojo.stopEvent(event);

                   console.log(event);

                   var input = $(this.domNode).find("input[name='task']");
                   var title = input.val();
                   input.val("");

                   var task = {
                       title:title,
                       description:"",
                       complete:false
                   };

                   this.objectStore.add(task)
                   console.log("Form Submitted.");
               },
               constructor:function (params, srcNodeRef) {
                   this.objectStore = params['store'];
                   this.query = this.objectStore.query({});

                   var observationHandler = lang.hitch(
                       this,
                       function (item, removedIndex, insertedIndex) {
                           if (removedIndex > -1) {
                               console.log("Observed removed index");
                               this.removeRow(item.id);
                           }
                           if (insertedIndex > -1) {
                               console.log("Observed inserted index");
                               this.insertRow(item, insertedIndex);
                           }
                       });
                   this.query.observe(observationHandler, true);
               },
               insertRow:function (item, insertedIndex) {
                   // Create new row widget
                   var widget = new TaskRowWidget({item:item, objectStore:this.objectStore});
                   widget.startup();

                   this.widgetSet.add(widget);

                   // Append
                   this.containerNode.appendChild(widget.domNode);
               },
               removeRow:function (taskId) {
                   // Filter set function
                   var filter = function (id, w) {
                       if (w && w.item && w.item.id) {
                           return (w.item.id === id);
                       } else {
                           return false;
                       }
                   };

                   var doFilter = lang.partial(filter, taskId);

                   // Destory widget function
                   var handler = lang.hitch(this, function (w) {
                       this.widgetSet.remove(w);
                       w.destroy();
                   });

                   // Apply destroy to filtered widgets
                   this.widgetSet
                       .filter(doFilter)
                       .forEach(handler);
               },
               _viewResults:function (results) {

                   var appendTaskToContainer = lang.hitch(
                       this,
                       function (thing) {
                           // Create new row widget
                           var widget = new TaskRowWidget({item:thing, objectStore:this.objectStore});
                           widget.startup();

                           this.widgetSet.add(widget);

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