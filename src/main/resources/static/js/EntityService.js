function EntityService(){
	this.url = 'http://localhost:8080/api/entity';
}

EntityService.prototype.getTop6EntitiesNearBy = function(request,success,ajaxError){
	ajax(this.url+'/items', 'GET', request, success, ajaxError);
}




