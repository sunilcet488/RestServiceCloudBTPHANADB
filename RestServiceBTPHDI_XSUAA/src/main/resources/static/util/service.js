sap.ui.define(
	["sap/ui/thirdparty/jquery"],
	function(jquery){
	 return{
		 callService: function(sUrl,sMethod,oPayload){
			 return new Promise(function(resolve,reject){
				 switch(sMethod.toUpperCase()){
					 
					 case "GET":
						 jquery.ajax(sUrl,{
							 type: "GET",
							 success: function(data){
								 resolve(data);
							 },
							 error: function(err){
								 reject(err);
							 }
						 })
						 break;
						 
				     case "POST":
						 
						 jquery.ajax(sUrl,{
							 type: "POST",
							 contentType: "application/json",
							 data: JSON.stringify(oPayload),
							 success: function(data){
								 resolve(data);
							 },
							 error: function(err){
								 reject(err);
							 }
						 })						 
						 
						 break;
					 
				 }
			 });
		 }
	 }
});