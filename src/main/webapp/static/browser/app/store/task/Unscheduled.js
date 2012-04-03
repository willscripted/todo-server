Ext.define('BS.store.task.Unscheduled', {
    extend:'Ext.data.Store',
    storeId: 'taskUnscheduled',

    groupField: 'complete',
    fields: ['complete', 'title'],
    data:[
        { 'complete':true, "title":"Do Homework" },
        { 'complete':true, "title":"Debate" },
        { 'complete':false, "title":"Extend Panel" },
        { 'complete':false, "title":"Do work" }
    ],
    autoLoad: true
});