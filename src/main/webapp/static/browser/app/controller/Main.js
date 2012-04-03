Ext.define('BS.controller.Main', {

    extend:'Ext.app.Controller',

    views:['Main', 'NavButton', 'HomeTab'],
    init:function () {

        this.control({
                         'navbutton':{
                             click:this.navChange
                         }
                     });
    },
    navChange:function (button) {
        tabPanel = button.up('main').down('tabpanel');
        tabPanel.setActiveTab(button.tabItemId);
    }

});