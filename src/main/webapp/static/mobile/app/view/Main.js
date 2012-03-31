Ext.define('BSMobile.view.Main', {
    extend: 'Ext.TabPanel',

    config: {
        fullscreen:true,
        dockedItems:[
            {xtype:'toolbar', title:'Blockstep Mobile'}
        ],
        defaults:{
            styleHtmlContent:true
        },
        tabBar:{
            ui:'light',
            layout:{
                pack:'center'
            }
        },
        items:[
            {
                xtype: 'tasklist'
            },
            {
                xtype: 'addform'
            }
        ]
    }



});