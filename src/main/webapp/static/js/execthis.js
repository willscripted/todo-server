var buttonClick = function() {

	schema = {
  		type:"object",
  		properties:{
     			name:{type:"string"},
     			age:{type:"number"}
  		}
	};
instance = {
  name:"Kris",
  age:31
};
results = dojox.json.schema.validate(instance, schema);
print(results.valid);

}

buttonClick();
