Ext.define('BS.view.Main', {

    extend:'Ext.container.Container',
    alias:'widget.main',
    layout:{
        type:'hbox',
        align:'stretch'
    },
    items:[
        {
            xtype:'container',
            title:'left gutter',
            flex:1
        },
        {
            xtype:'container',
            border:false,
            flex:5,
            layout:{
                type:'vbox',
                align:'stretch'
            },
            items:[
                {
                    xtype:'container',
                    height:80,
                    layout:{
                        type:'column',
                        align:'middle'
                    },
                    items:[
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
                            xtype:'container',
                            columnWidth:.25,
                            margin:'5',
                            layout:{
                                type:'hbox',
                                align:'stretchmax'
                            },
                            defaults:{
                                xtype:'navbutton',
                                margin:'20 5 0 0',
                                flex:1
                            },
                            items:[
                                {
                                    text:'Home',
                                    tabItemId:'Home'
                                },
                                {
                                    text:'Planning',
                                    tabItemId: 'Planning'
                                },
                                {
                                    text:'Projects',
                                    tabItemId: 'Projects'
                                },
                                {
                                    text:'Routines',
                                    tabItemId: 'Routines'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype:'tabpanel',
                    flex:1,
                    plain:true,
                    border: false,
                    tabBar: {
                        hidden: true
                    },
                    items:[
                        {
                            xtype:'hometab',
                            itemId: 'Home'
                        },
                        {
                            xtype:'planningtab',
                            itemId: 'Planning'
                        },
                        {
                            xtype:'container',
                            title:'Projects',
                            html:'Projects',
                            itemId: 'Projects'
                        },
                        {
                            xtype:'container',
                            title:'Routines',
                            html:'Routines',
                            itemId: 'Routines'
                        }
                    ]
                }
            ]
        },
        {
            xtype:'container',
            title:'right gutter',
            flex:1
        }
    ]



})
;