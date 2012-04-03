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
                            xtype:'container',
                            html:'<div id="logo">Blockstep</div><div id="tm">&trade;</div>',
                            columnWidth:.25,
                            padding:'15 0 0 0'
                        },
                        {
                            xtype:'container',
                            html:'<span id="quote">The secret of getting ahead is getting started. - Mark Twain</span>',
                            columnWidth:.5,
                            padding:'50 0 0 0'
                        }                        ,
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