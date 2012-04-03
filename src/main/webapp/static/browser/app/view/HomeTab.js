Ext.define('BS.view.HomeTab', {

    extend:'Ext.container.Container',
    alias: 'widget.hometab',
    layout:{
        type:'hbox',
        align:'stretch'
    },
    items:[
        {
            xtype:'daylist',
            flex:1,
            layout:{
                type:'hbox',
                align:'stretch'
            },
            padding:15
        },
        {
            xtype:'stats.overview',
            flex:3,
            title:'Stats',
            padding:15
        }
    ]
});