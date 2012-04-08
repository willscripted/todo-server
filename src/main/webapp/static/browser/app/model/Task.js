Ext.define('BS.model.Task', {

    extend:'Ext.data.Model',
    fields:[
        {name: 'title', type: 'string'},
        {name: 'complete', type: 'boolean', defaultValue: false},
        {name: 'id'}
    ]
});