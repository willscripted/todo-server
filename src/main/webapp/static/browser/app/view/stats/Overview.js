Ext.define('BS.view.stats.Overview', {

    extend: 'Ext.container.Container',
    alias: 'widget.stats.overview',
    layout: {
        type: 'anchor'
    },
    items: [
        {
            xtype: 'panel',
            title: 'Day',
            height: 400
        },
        {
            xtype: 'panel',
            title: 'Week',
            height: 400
        },
        {
            xtype: 'panel',
            title: 'Month',
            height: 400
        }
    ]

});