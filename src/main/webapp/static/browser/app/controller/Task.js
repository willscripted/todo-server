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
                             keyup:this.onKeyPress
                         }Ad
                     });
    },
    onKeyPress:function (field, e) {
        if(e.getCharCode() === 13) {
            console.log("Enter button pressed");
            console.log(field.getValue());
        }
    }


});