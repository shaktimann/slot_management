function SlotService(){
    this.url = 'http://localhost:8080/api/entity/slots/';
}

SlotService.prototype.getAvailableSlots = function(request,success,ajaxError) {
    ajax(this.url+'availability/'+request.id, 'GET', request, success, ajaxError);
}

SlotService.prototype.book = function(request,success,ajaxError) {
    ajax('/api/slotRequest/', 'POST', JSON.stringify(request), success, ajaxError);
}