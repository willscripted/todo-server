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
                    xtype:'stats.overview',
                    flex: 1
                }
            ]
        }

    ]
});