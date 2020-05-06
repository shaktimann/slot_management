function BookSlot(){
	var currentURL =  new URL(location.href);
	this.entityId = currentURL.searchParams.get('id');
	$("#datepicker").datepicker().datepicker("setDate", new Date());
	this.bookingDate = $('#datepicker').datepicker({ dateFormat: 'mm/dd/yyyy' }).val();
	this.bookSlotFrom = '';
	this.bookSlotTo = '';
}
var slot = new BookSlot();
var slotService = new SlotService();
BookSlot.prototype.getSlots = function(){
	function successHandler(data){
		console.log(data);
		slot.displaySlot(data)
	}
	var request = {
		id : slot.entityId,
		date : $('#datepicker').datepicker({ dateFormat: 'mm/dd/yyyy' }).val()
	}
	$('#loader').show();
	console.log(request)
	slotService.getAvailableSlots(request,successHandler);
	setTimeout(function() { 
		$('#loader').hide(); 
    }, 1000); 
}

BookSlot.prototype.displaySlot = function(slots){
	var slot = '';
	$.each(slots, function(i, value) {
		slot += '<a href="javascript:openModelConfirmation('+i+','+value+')" class="btn btn-primary btn-icon-split m-1">	<span class="icon text-white-50"><i class="fas fa-flag"></i></span><span class="text">'+timeConversion(i) +' - '+timeConversion(value)+'</span> </a>';
	});
	$('#slots').html(slot);
}
BookSlot.prototype.book = function(){
	function successHandler(data){
		console.log(data);
		slot.getSlots();
		setTimeout(function() { 
			$('#loader_book').hide(); 
			$('#successMsg').show()
	    }, 1000); 
	}
	var request = {
		entityId : slot.entityId,
		dateOfRequest : $('#datepicker').datepicker({ dateFormat: 'mm/dd/yyyy' }).val(),
		startTime: slot.bookSlotFrom,
		endTime:slot.bookSlotTo
	}
	$('#loader_book').show();
	slotService.book(request,successHandler)
}
function timeConversion(duration) {
	var milliseconds = parseInt((duration%1000)/100)
	, seconds = parseInt((duration/1000)%60)
	, minutes = parseInt((duration/(1000*60))%60)
	, hours = parseInt((duration/(1000*60*60))%24);

	hours = (hours < 10) ? "0" + hours : hours;
	minutes = (minutes < 10) ? "0" + minutes : minutes;
	seconds = (seconds < 10) ? "0" + seconds : seconds;

	return hours + ":" + minutes ;
}
function init(){
	slot.bookingDate = $('#datepicker').datepicker({ dateFormat: 'mm/dd/yyyy' }).val();
	slot.getSlots();
}
init();
function openModelConfirmation(from , to){
	$('#successMsg').hide()
	$('#exampleModal').modal('show'); 
	$('#Slot_book_confirmation').html(timeConversion(from)+' - '+timeConversion(to));
	slot.bookSlotFrom = from;
	slot.bookSlotTo = to;
}
