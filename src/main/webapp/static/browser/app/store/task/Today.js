Ext.define('BS.store.task.Today', {
    extend:'Ext.data.Store',
    storeId: 'taskToday',

    fields: ['complete', 'title'],
    data:[
        { 'complete':true, "title":"Do Homework" },
        { 'complete':true, "title":"Debate" },
        { 'complete':false, "title":"Extend Panel" },
        { 'complete':false, "title":"Do work" }
    ],
    autoLoad: true
});