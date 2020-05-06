function Dashboard(){
	
}
var dashboard = new Dashboard();
var entityService = new EntityService();
Dashboard.prototype.errorHandler = function(){

}
Dashboard.prototype.getTiles = function(tileName,id){
    var tile = '<div class="col-xl-3 col-md-6 mb-4">';
    tile += '<div class="card border-left-primary shadow h-100 py-2">';
    tile += '<div class="card-body">';
    tile += '<div class="row no-gutters align-items-center">'
    tile += '<div class="col mr-2">'
    tile += '<div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Present Staff (Org)</div>'
    tile += '<a href="/bookSlot?id='+id+'"><div class="h5 mb-0 font-weight-bold text-gray-800">'+tileName+'</div></a>'
    tile += '</div><div class="col-auto"><i class="fas fa-calendar fa-2x text-gray-300"></i></div>'
    tile += '</div></div></div></div>'
    return tile;
}
Dashboard.prototype.displayTiles = function(entities){
    var tiles = '';
    for(var i =0 ; i < entities.length;i++){
        tiles += dashboard.getTiles(entities[i].name,entities[i].id);
    }
    $('#cards').html(tiles);
}

Dashboard.prototype.loadTiles = function(){
    entityService.getTop6EntitiesNearBy(null,dashboard.displayTiles,dashboard.errorHandler);
}

function init(){
    dashboard.loadTiles();
}
init();




