Ext.define('BS.controller.Task', {

    extend: 'Ext.app.Controller',
    views: ['task.DayList', 'HomeTab'],
    stores: ['task.Today'],
    requires: ['Ext.ux.CheckColumn']


});