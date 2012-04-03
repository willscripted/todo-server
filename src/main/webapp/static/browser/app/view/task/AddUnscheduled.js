Ext.define('BS.view.task.AddUnscheduled', {

    extend: 'Ext.container.Container',
    alias: 'widget.task.addunscheduled',
    height: 40,
    layout: {
        type: 'fit'
    },
    items: [
        {
            xtype: 'textfield',
            emptyText: 'Add Task...',
            minLength: 1,
            enableKeyEvents: true,
            fieldStyle: {
                padding: '0 0 0 10',
                'font-size': '1.2em'
            },
            flex: 1
        }
    ]

});