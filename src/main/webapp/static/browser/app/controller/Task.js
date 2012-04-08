Ext.define('BS.controller.Task', {

    extend:'Ext.app.Controller',
    views:[
        'task.DayList',
        'HomeTab',
        'task.AddUnscheduled',
        'PlanningTab',
        'planning.UnplannedTasksGrid',
        'planning.WeekCapacityGrid'
    ],
    stores:['task.Today', 'task.Unscheduled'],
    requires:['Ext.ux.CheckColumn'],

    init:function () {
        this.control({
                         '[xtype="task.addunscheduled"] > textfield':{
                             keyup:this.addUnscheduled
                         },
                         '[xtype="daylist"] > textfield':{
                             keyup:this.addToday
                         }
                     });
    },
    _addTaskToStore:function (field, storeName) {

        // Get title & reset field
        var title = field.getValue();
        field.setValue("");

        // Get store
        var store = Ext.getStore(storeName);

        // Create task
        var task = Ext.create('BS.model.Task', {title: title, complete: false});

        // Add
        store.add(task);
    },
    addUnscheduled: function(field, e) {
        if (e.getCharCode() === 13) {
            this._addTaskToStore(field, 'task.Unscheduled');
        }
    },
    addToday: function(field, e) {
        if (e.getCharCode() === 13) {
            this._addTaskToStore(field, 'task.Today');
        }
    }


});