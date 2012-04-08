Ext.define('BS.view.PlanningTab', {

    extend:'Ext.container.Container',
    alias:'widget.planningtab',
    layout:{
        type:'hbox',
        align:'stretch'
    },
    items:[
        {
            xtype:'weekcapacitygrid',
            flex:1,
            padding:15
        },
        {
            xtype:'unplannedtasksgrid',
            flex:3,
            padding:15
        }
    ]
});