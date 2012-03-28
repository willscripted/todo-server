Ext.define('BSMobile.store.TasksToday', {
    extend:'Ext.data.Store',

    alias:'store.TasksToday',

    requires:'BSMobile.model.Task',

    config:{

        model:'BSMobile.model.Task',
        proxy:{
            type: 'rest',
            url: '/static/mobile/app/data/tasks.json'
        },
        autoLoad: true
//        data:[
//            {id: 1, title:"some taks", complete:true},
//            {id: 2, title:"another taks", complete:true},
//            {id: 3, title:"hello world", complete:true},
//            {id: 4, title:"do something", complete:true},
//            {id: 5, title:"do something else", complete:true},
//            {id: 6, title:"do a third thing", complete:true},
//            {id: 7, title:"omg it works!!!!", complete:true}
//        ]
    }
})
;