Ext.define('BSMobile.view.AddForm', {

    extend:'Ext.form.Panel',

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
                        name:'task',
                        label:'Title'
                    }
                ]
            },
            {
                xtype:'button',
                text:'Add',
                ui:'confirm',
                handler: function() {
                    var values = this.up('addform').getValues();
                    console.log(values);
                }
            }
        ]
    }


});