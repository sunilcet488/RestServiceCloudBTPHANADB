sap.ui.define(
	["sap/ui/core/mvc/Controller",
	  "myFirstApp/util/service",
	  "sap/m/MessageBox"],     //or use module jquery.sap.global
	function(controller,service,MessageBox){
	
	return controller.extend("myFirstApp.controller.Main",{
		
		onInit: function(){
				var oModel = new sap.ui.model.json.JSONModel();
				oModel.setData({
					"postPayload":{
					    "name": "",
					    "website": "",
					    "email": "",
					    "status": "A",
					    "gstNo": "",						
					},
					"vendor": {}
				});
				sap.ui.getCore().setModel(oModel);			
		},
		
		onLoadData: function(){
			//alert("on Data load");
			var that = this;
			service.callService("/vendor","GET",{}).then(function(data){
			     var oModel = sap.ui.getCore().getModel();
				 oModel.setProperty("/vendor",data);
				 that.getView().byId("tableId").bindRows("/vendor");
				 				
			}).catch(function(error){
				
			});
			
			
			
			/*Jquery.ajax("/vendor",{
				type:"GET",
				success : function(data){
					//console.log(data);
				 var oModel = new sap.ui.model.json.JSONModel();
				 oModel.setData(data);
				 sap.ui.getCore().setModel(oModel);
				 
				 that.getView().byId("tableId").bindRows("/");
				 	
				},
				error: function(err){
					
				}
			});*/
		},
		
		onSave:function(){
			var oModel = sap.ui.getCore().getModel();
			var oPayload = oModel.getProperty("/postPayload");
			service.callService("/vendor","POST",oPayload).then(function(){
				MessageBox.confirm("Data save Successfully");
			}).catch(function(error){
				MessageBox.error("Error in Data saving");
			});
		}
	});
		
});