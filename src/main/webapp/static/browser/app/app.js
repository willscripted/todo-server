Ext.Loader.setConfig({
                         enabled:true,
                         paths:{
                             'Ext':'/static/browser/lib/extjs/src'
                         }
                     });
Ext.application({
                    name:'BS',

                    appFolder:'/static/browser/app',

                    views:['Main'],

                    controllers:[
                        'Task',
                        'Stats',
                        'Main'
                    ],

                    models:[],

                    stores:[],

                    launch:function () {
                        Ext.create('Ext.container.Viewport', {
                            layout:'fit',
                            items:[
                                {
                                    xtype: 'main',
                                    autoScroll: true
                                }
                            ]


                        });
                    }
                });
