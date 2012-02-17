// custom TaskListWidget
define(["dojo/_base/declare","dijit/_WidgetBase", "dijit/_TemplatedMixin", "dojo/text!./templates/TaskRowWidget.html"],
       function(declare, WidgetBase, TemplatedMixin, template){
           return declare([WidgetBase, TemplatedMixin], {
                title: "\<\<\Untitled!>\>",
               templateString: template,
               baseClass: "taskRowWidget"
           });
       });