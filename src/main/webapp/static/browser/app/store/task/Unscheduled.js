Ext.define('BS.store.task.Unscheduled', {
    extend:'Ext.data.Store',
    alias:'store.task.Unscheduled',

    requires: ['BS.model.Task'],
    model: 'BS.model.Task',

    data:[
        { 'complete':true, "title":"Do Homework" },
        { 'complete':true, "title":"Debate" },
        { 'complete':false, "title":"Extend Panel" },
        { 'complete':false, "title":"Do work" }
    ],
    autoLoad: true
});