function checkQuantity(element){
	if($(element).val()>100){
		$(element).val(100);
		$(element).focus();
		alert("Value must be in range(0, 100)");
	}
	else if($(element).val()<0){
		$(element).val(0);
		$(element).focus();
		alert("Value must be in range(0, 100)");
	}	
}
function setQuantity(element){
	$(element).mouseup(function(){
		checkQuantity(this);
	});
	$(element).keyup(function(){
		checkQuantity(this);
	});
	return $(element);
}
function checkPrice(element){
	if($(element).val()<0){
		$(element).val(0);
		$(element).focus();
		alert("Price must be larger than 0");
	}	
}
function setPrice(element){
	$(element).mouseup(function(){
		checkPrice(this);
	});
	$(element).keyup(function(){
		checkPrice(this);
	});
}