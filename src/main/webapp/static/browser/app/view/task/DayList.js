Ext.define('BS.view.task.DayList', {

    extend:'Ext.grid.Panel',

    store:'task.Today',
    alias:'widget.daylist',
    title:'Today',
    columns:[
        {
            xtype:'checkcolumn',
            dataIndex:'complete',
            width:40
        },
        {
            header:'Task',
            dataIndex:'title',
            flex:1,
            editor:{
                type:'textfield',
                style:{
                    margin:'4 0'
                }
            }
        },
        {
            xtype:'actioncolumn',
            width:30,
            items:[
                {
                    icon:'/static/browser/resources/images/delete.png',
                    handler: function(grid, rowIndex, colIndex) {
                        console.log("Clicked");
                        grid.getStore().removeAt(rowIndex);
                    }
                }
            ]
        }
    ],
    align:'stretch',
    plugins:[
        Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToEdit:1
        })
    ],
    enableColumnHide:false,
    enableColumnMove:false,
    enableColumnResize:false,
    sortableColumns:false,

    dockedItems:[
        {
            xtype:'textfield',
            height:30,
            emptyText:'Add task to today...',
            minLength:1,
            dock:'top',
            enableKeyEvents:true,
            fieldStyle:{
                padding:'0 0 0 10',
                'font-size':'1.1em'
            }
        }
    ]

});