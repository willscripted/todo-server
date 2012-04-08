Ext.define('BS.store.task.Today', {
    extend:'Ext.data.Store',
    alias:'store.task.Today',
    requires:['BS.model.Task', 'BS.data.proxy.JsonSchemaRest'],
    model:'BS.model.Task',
    sortOnLoad: true,
    sorters:[{
        property: 'complete',
        direction: 'ASC'
    }],
    proxy:{
        type:'JsonSchemaRest',
        api:{
            create:'/api/tasks/',
            read:'/api/tasks/',
            update:'/api/tasks/',
            destroy:'/api/tasks/'
        },
        reader:{
            type:'json',
            root: 'tasks'
        },
        headers:{
            'Content-Type':'application/todo.webapp.dto.TaskDTO+json'
        },
        batchActions:false
    },
    listeners: {
        update: function(store, opts) {
            store.sort("complete", "ASC");
        }
    },
    autoLoad:true,
    autoSync:true
});