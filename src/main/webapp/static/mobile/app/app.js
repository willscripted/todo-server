Ext.application({
                    name:'BSMobile',

                    appFolder:'/static/mobile/app',

                    views:['Main', 'TaskList', 'AddForm'],

                    controllers:[
                        'Tasks'
                    ],

                    models:['Task'],

                    stores:['TasksToday'],

                    launch:function () {
                        Ext.Viewport.add(Ext.create('BSMobile.view.Main'));
                    }
                });
