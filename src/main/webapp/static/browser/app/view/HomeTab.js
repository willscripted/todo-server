Ext.define('BS.view.HomeTab', {

    extend:'Ext.container.Container',
    alias: 'widget.hometab',
    height: 1500,
    layout:{
        type:'hbox',
        align:'stretch'
    },
    items:[
        {
            xtype:'daylist',
            flex:1,
            height: 800,
            layout:{
                type:'hbox',
                align:'stretch'
            },
            padding:15
        },
        {
            xtype: 'container',
            flex:3,
            padding:15,
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'textfield',
                    height: 40,
                    emptyText: 'Add Task...',
                    minLength: 1,
                    enableKeyEvents: true,
                    fieldStyle: {
                        padding: '0 0 0 10',
                        'font-size': '1.2em'
                    }
                },
                {
                    xtype:'container',
                    items: [{xtype: 'stats.overview'}],
                    flex: 0
                }
            ]
        }

    ]
});