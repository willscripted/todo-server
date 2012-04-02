Ext.define('BS.view.task.DayList', {

    extend:'Ext.grid.Panel',
    store: 'task.Today',
    alias: 'widget.daylist',
    title:'Today',
    columns:[
        { header:'X', dataIndex:'complete' },
        { header:'Task', dataIndex:'title', flex: 1 }
    ],
    align: 'stretch'

});