Ext.define('BS.view.Main', {

    extend:'Ext.container.Container',
    alias:'widget.main',
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items:[
        {
            xtype:'container',
            title:'left gutter',
            flex: 1
        },
        {
            xtype:'container',
            title:'main',
            flex: 5,
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'container',
                    height: 80,
                    layout: 'hbox',
                    items: [
                        {
                            xtype: 'container',
                            html: '<div id="logo">Blockstep</div>',
                            flex: 1
                        },
                        {
                            xtype: 'container',
                            html: 'The secret of getting ahead is getting started. - Mark Twain',
                            flex: 2
                        },
                        {
                            xtype: 'container',
                            html: 'Navigation....',
                            flex: 2
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    flex: 6,
                    title: 'content',
                    layout: {
                        type: 'hbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'daylist',
                            flex: 1
                        },
                        {
                            xtype: 'stats.overview',
                            flex: 3,
                            title: 'Stats'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'footer',
                    flex: 1
                }
            ]
        },
        {
            xtype:'container',
            title:'right gutter',
            flex: 1
        }
    ]



});