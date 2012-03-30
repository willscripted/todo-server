Ext.define('BSMobile.store.TasksToday', {
    extend:'Ext.data.Store',

    alias:'store.TasksToday',

    requires: ['BSMobile.model.Task', 'BSMobile.data.writer.JsonSchemaWriter'],

    config:{

        model:'BSMobile.model.Task',
//        proxy:{
//            type: 'rest',
//            url: '/static/mobile/app/data/tasks.json'
//        },
        proxy:{
            type:'rest',
            api: {
                create: '/api/tasks/',
                read: '/api/tasks/current',
                update: '/api/tasks/',
                destroy: '/api/tasks/'
            },
            writer: 'jsonschemawriter',
            reader: {
                type: 'json'
            },
            headers: {
                'Content-Type': 'application/todo.webapp.dto.TaskDTO+json'
            },
            batchActions: false
        },
        filter: {
            property: 'complete',
            value: true
        },
        autoLoad:true
    }
})
;