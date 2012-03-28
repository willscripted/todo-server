Ext.define('BSMobile.controller.Tasks', {

    extend:'Ext.app.Controller',

    stores:[
        'TasksToday'
    ],

    models:[
        'Task'
    ],

    views:[
        'TaskList'
    ],

    init:function () {
        this.control({
                         'tasklist':{
                             itemdoubletap:this.onDblTap,

                             itemsingletap:this.onCheckboxTap
                         }
                     });
    },

    onDblTap:function (dataview, idx, target, record, e) {
        if(e.target.tagName != "IMG") {
            console.log(record);
            console.log("double tapped index " + idx);
        }
    },

    onCheckboxTap: function(dataview, idx, target, record, e) {
        if(e.target.tagName == "IMG") {
            if(e.target.className == "checkbox") {
                console.log("checkbox handler");
            } else if(e.target.className == "delete") {
                console.log("delete handler");
            }
        }
    }




});