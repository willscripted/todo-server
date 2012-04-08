Ext.define('BS.view.planning.UnplannedTasksGrid', {

    extend:'Ext.grid.Panel',
    alias:'widget.unplannedtasksgrid',
    title:'Unscheduled Tasks',
    columns:[
        {
            header:'Task',
            dataIndex:'title',
            flex:1,
            editor: 'textfield'
        }
    ],
    align:'stretch',
    plugins: [
        Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToEdit: 2
        })
    ],
    enableColumnHide: false,
    enableColumnMove: false,
    enableColumnResize: false,
    sortableColumns: false

});