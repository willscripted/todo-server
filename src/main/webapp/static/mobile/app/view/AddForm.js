Ext.define('BSMobile.view.AddForm', {

    extend:'Ext.form.Panel',

    require: ['BSMobile.model.Task'],

    xtype:'addform',

    config:{
        title:'Add',
        items:[
            {
                xtype:'fieldset',
                title:'New task',
                instructions:'Type new task',

                items:[
                    {
                        xtype:'textfield',
                        name:'title',
                        label:'Title'
                    }
                ]
            },
            {
                xtype:'button',
                text:'Add',
                action: 'save',
                ui:'confirm'
            }
        ],
        record: Ext.create('BSMobile.model.Task')
    }




});