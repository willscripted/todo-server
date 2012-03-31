Ext.define('BSMobile.controller.Tasks', {

    extend:'Ext.app.Controller',

    stores:[
        'TasksToday'
    ],

    models:[
        'Task'
    ],

    views:[
        'TaskList',
        'AddForm'
    ],

    init:function () {
        this.control({
                         'tasklist':{
                             itemdoubletap:this.onDblTap,

                             itemsingletap:this.onCheckboxTap
                         },
                         'addform button[action=save]':{
                             tap:this.newTask
                         }
                     });
    },

    onDblTap:function (dataview, idx, target, record, e) {
        if (e.target.tagName != "IMG") {
            console.log(record);
            console.log("double tapped index " + idx);
        }
    },

    onCheckboxTap:function (dataview, idx, target, record, e) {
        var store = Ext.getStore('TasksToday');
        console.log(store);
        if (e.target.tagName == "IMG") {
            if (e.target.className == "checkbox") {
                record.set('complete', true);
                store.sync();
                store.load();
            } else if (e.target.className == "delete") {
                store.remove(record);
                store.sync();
            }
        }
    },
    newTask: function(button) {

        var values = button.up('addform').getValues();
        values['complete'] = false;

        var task = Ext.create('BSMobile.model.Task', values);
        var store = Ext.getStore('TasksToday');
        store.add(task);
        store.sync();

        button.up('addform').setValues({title: ""});

    }




});