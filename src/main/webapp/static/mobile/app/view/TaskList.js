Ext.define('BSMobile.view.TaskList', {
    extend:'Ext.List',

    xtype:'tasklist',

    config:{
        title:'Tasks',
        store:'TasksToday',
        itemCls:'TaskListItem',
        itemTpl:[
            '<img class="checkbox" alt="[ ]" src="/static/mobile/resources/checkbox.png">',
            '<span>{title}</span>',
            '<img class="delete" alt="X" src="/static/mobile/resources/delete.png" />'
        ].join(""),
        grouped:false
    }

});