Ext.define('BS.store.task.TodolistsByDay', {
    extend:'Ext.data.Store',
    groupField: 'complete',
    data:[
        { 'complete':true, "title":"Do Homework" },
        { 'complete':true, "title":"Debate" },
        { 'complete':false, "title":"Extend Panel" },
        { 'complete':false, "title":"Do work" }
    ],
    autoLoad: true
});