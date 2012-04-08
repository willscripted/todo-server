Ext.define('BS.view.planning.WeekCapacityGrid', {

    extend:'Ext.grid.Panel',
    alias:'widget.weekcapacitygrid',
    title:'Week Capacity',
    columns:[
        {
            header:'Day',
            dataIndex:'title',
            flex:1
        }
    ],
    align:'stretch',
    enableColumnHide: false,
    enableColumnMove: false,
    enableColumnResize: false,
    sortableColumns: false

});