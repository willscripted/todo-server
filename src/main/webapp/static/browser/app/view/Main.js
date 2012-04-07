Ext.define('BS.view.Main', {

    extend:'Ext.container.Container',
    alias:'widget.main',
    layout:{
        type:'ux.center',
        align: 'stretch'
    },
    items:[
        {
            xtype:'container',
            border:false,
            widthRatio:.80,
            layout:{
                type:'anchor'
            },
            defaults:{
                anchor:'100%'
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
                                    tabItemId:'Planning'
                                },
                                {
                                    text:'Projects',
                                    tabItemId:'Projects'
                                },
                                {
                                    text:'Routines',
                                    tabItemId:'Routines'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype:'tabpanel',
                    plain:true,
                    border:false,
                    layout:{
                        type:'anchor'
                    },
                    defaults:{autoScroll:true},
                    tabBar:{
                        hidden:true
                    },
                    items:[
                        {
                            xtype:'hometab',
                            itemId:'Home'
                        },
                        {
                            xtype:'planningtab',
                            itemId:'Planning',
                            height:2000
                        },
                        {
                            xtype:'container',
                            title:'Projects',
                            html:'Projects',
                            itemId:'Projects'
                        },
                        {
                            xtype:'container',
                            title:'Routines',
                            html:'Routines',
                            itemId:'Routines'
                        }
                    ]
                }

            ]
        }
    ]



})
;