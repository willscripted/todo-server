// custom TaskListWidget
define(["dojo/_base/declare", "dijit/_WidgetBase", "dijit/_TemplatedMixin", "dojo/text!./templates/TaskListWidget.html", "dojo/_base/connect", "todo/widgets/TaskListWidget/TaskRowWidget", "dojo/_base/lang"],
       function (declare, WidgetBase, TemplatedMixin, template, connect,
                 TaskRowWidget, lang) {
           return declare([WidgetBase, TemplatedMixin], {
               title:"\<\<Untitled!\>\>",
               templateString:template,
               baseClass:"taskListWidget",
               query:null,
               objectStore:null,
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

                   this.query.observe(function (item, removedIndex,
                                                insertedIndex) {
                       if (removedIndex > -1) {
                           console.log("Observed removed index");
                           // removeRow(removedIndex);
                       }
                       if (insertedIndex > -1) {
                           console.log("Observed inserted index");
                           //insertRow(item, insertedIndex);
                       }
                   }, true);
               },
               _viewResults:function (results) {

                   var appendTaskToContainer = lang.hitch(this,
                                                          function (thing) {
                                                              // Create new row widget
                                                              var widget = new TaskRowWidget({item:thing, objectStore:this.objectStore});
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