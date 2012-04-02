Ext.define('BS.view.stats.Overview', {

    extend: 'Ext.container.Container',
    alias: 'widget.stats.overview',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'panel',
            title: 'Day',
            flex: 1
        },
        {
            xtype: 'panel',
            title: 'Week',
            flex: 1
        },
        {
            xtype: 'panel',
            title: 'Month',
            flex: 1
        }
    ]

});