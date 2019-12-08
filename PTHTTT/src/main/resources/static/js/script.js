$("document").ready(function() {

    $('.minus-btnn').on('click', function(e) {
  		e.preventDefault();
  		var $this = $(this);
  		var $input = $this.closest('div').find('.quantityy');
  		var value = parseInt($input.val());

  		if (value > 1) {
  			value = value - 1;
  		} else {
  			value = 0;
  		}

      $input.val(value);

  	});

  	$('.plus-btnn').on('click', function(e) {
  		e.preventDefault();
  		var $this = $(this);
  		var $input = $this.closest('div').find('.quantityy');
  		var value = parseInt($input.val());

  		if (value < 100) {
    		value = value + 1;
  		} else {
  			value =100;
  		}

  		$input.val(value);
  	});

    $('.like-btn').on('click', function() {
      $(this).toggleClass('is-active');
    });

  
	$.ajax({
        url: "/numOfCart",
        type: 'GET',
        dataType: 'json',
        success : function(ketqua) {
//        	alert("ok ne");
//        	alert(ketqua.length);
        	$(".itemss").html('');
        	var kq = "";
        	
       	 	if(ketqua.length != 0){
       	 		var numCart = 0;
	       	 	for (i = 0; i <= ketqua.length -1 ; i++) {
	        		numCart += ketqua[i].number;
	        	}
	        	kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
	       	 	kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i> <span class="text-danger countCart th:text="${countCart}">'+numCart+'</span></button>';
	        	kq += '<ul class="dropdown-menu dropdown-cart addItems" role="menu">';
        		kq += '<input type="hidden" value="'+ ketqua[0].idCart + '" id="idCart" class="idCart">';
        	
        	var tong = 0;
        	
        	for (i = 0; i <= ketqua.length -1 ; i++) {
        		tong += ketqua[i].priceSell*ketqua[i].number;
        		kq += '<li>';
        		kq += '<span class="item">';
        		kq += '<span class="item-left">';
        		kq += '<img src="' + ketqua[i].images + '" alt="" />';
        		kq += '<span class="item-info mt-2 text-primary">';
        		kq += '<span>' + ketqua[i].name + '</span>';
        		kq += '<span class="text-danger">Đơn Giá: ' +  ketqua[i].priceSell + ' vnd</span>';
        		kq += '<span>Số Lượng: ' +  ketqua[i].number +'</span>';
        		kq += '</span>';
        		kq += '</span>'; 
        		kq += '</span>';
        		kq += '</li>';
        		kq += '<li class="divider"><hr></li>';
        	}
        	kq += '<li class="pl-2"><span class="text-secondary">Total: ';
        	kq += '<span class="text-danger">' + tong +'</span>';
        	kq += '</span></li>';
        	kq += '<li class="pl-2">';
        	kq += '<a class="text-center text-primary" href="/detailsCart/'+ketqua[0].idCart+'">View Cart</a>';
        	kq += '</li></ul>';
        	$(".itemss").html(kq);
        	}else {
        		kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
	       	 	kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i> <span class="text-danger countCart">0</span></button>';
	       	 	$(".itemss").html(kq);
	        	
        	}
        }
	
	
     });


	$(".imgProduct").hover(function() {
						$(this).css("transform", "scale(1.05");
						$(this).parent(".product").find("p").css("color", "red");
					},function() {
						$(".imgProduct").css("transform","scale(1.0)");
						$(this).parent(".product").find("p").css("color", "black");
					});

	$('.btn-number').click(function(e) {
						e.preventDefault();
						fieldName = $(this).attr('data-field');
						type = $(this).attr('data-type');
						var input = $("input[name='"+ fieldName + "']");
						var currentVal = parseInt(input.val());
						if (!isNaN(currentVal)) {
							if (type == 'minus') {
								if (currentVal > input.attr('min')) {
									input.val(currentVal - 1).change();
								}
								if (parseInt(input.val()) == input
										.attr('min')) {
									$(this).attr('disabled',true);
								}

							} else if (type == 'plus') {
								if (currentVal < input.attr('max')) {
									input.val(currentVal + 1).change();
								}
								if (parseInt(input.val()) == input
										.attr('max')) {
									$(this).attr('disabled',
											true);
								}

							}
						} else {
							input.val(0);
						}
					});
	$('.input-number').focusin(function() {
		$(this).data('oldValue', $(this).val());
	});
	$('.input-number')
			.change(
					function() {

						minValue = parseInt($(this).attr('min'));
						maxValue = parseInt($(this).attr('max'));
						valueCurrent = parseInt($(this).val());

						name = $(this).attr('name');
						if (valueCurrent >= minValue) {
							$(
									".btn-number[data-type='minus'][data-field='"
											+ name + "']")
									.removeAttr('disabled')
						} else {
							alert('Sorry, the minimum value was reached');
							$(this).val(
									$(this).data('oldValue'));
						}
						if (valueCurrent <= maxValue) {
							$(
									".btn-number[data-type='plus'][data-field='"
											+ name + "']")
									.removeAttr('disabled')
						} else {
							alert('Sorry, the maximum value was reached');
							$(this).val(
									$(this).data('oldValue'));
						}

					});
	$(".input-number")
			.keydown(
					function(e) {
						// Allow: backspace, delete, tab,
						// escape, enter and .
						if ($.inArray(e.keyCode, [ 46, 8, 9,
								27, 13, 190 ]) !== -1
								||
								// Allow: Ctrl+A
								(e.keyCode == 65 && e.ctrlKey === true)
								||
								// Allow: home, end, left, right
								(e.keyCode >= 35 && e.keyCode <= 39)) {
							// let it happen, don't do anything
							return;
						}
						// Ensure that it is a number and stop the keypress
						if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57))
								&& (e.keyCode < 96 || e.keyCode > 105)) {
							e.preventDefault();
						}
					});


	$(".addCart").click(function(){
		//alert("ok");
		
		var idBook = $("#idBook").val();
		var quantity = $(".input-number").val();
		var idCart = $("#idCart").val();
		$.ajax({
            url: "/addToCart",
            type: 'GET',
            dataType: 'json',
            data: {
            	cartId: idCart,
                bookId: idBook,
                quantity: quantity
            },
            success : function(ketqua) {
            	//alert("ok ne");
            	$(".itemss").html('');
            	var kq = "";
           	 	if(ketqua.length != 0){
	           	 	var numCart = 0;
		       	 	for (i = 0; i <= ketqua.length -1 ; i++) {
		        		numCart += ketqua[i].number;
		        	}

		       	 	kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
		       	 	kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i> <span class="text-danger countCart th:text="${countCart}">'+numCart+'</span></button>';
		        	kq += '<ul class="dropdown-menu dropdown-cart addItems" role="menu">';
	        		kq += '<input type="hidden" value="'+ ketqua[0].idCart + '" id="idCart" class="idCart">';
       
            	
            		var tong = 0;
	            	for (i = 0; i <= ketqua.length -1 ; i++) {
	            		tong += ketqua[i].priceSell*ketqua[i].number;
	            		//numCart += ketqua[i].number;
	            		kq += '<li>';
	            		kq += '<span class="item">';
	            		kq += '<span class="item-left">';
	            		kq += '<img src="' + ketqua[i].images + '" alt="" />';
	            		kq += '<span class="item-info mt-2 text-primary">';
	            		kq += '<span>' + ketqua[i].name + '</span>';
	            		kq += '<span class="text-danger">Đơn Giá: ' +  ketqua[i].priceSell + ' vnd</span>';
	            		kq += '<span>Số Lượng: ' +  ketqua[i].number +'</span>';
	            		kq += '</span>';
	            		kq += '</span>'; 
	            		kq += '</span>';
	            		kq += '</li>';
	            		kq += '<li class="divider"><hr></li>';
	            	}
	            	kq += '<li class="pl-2"><span class="text-secondary">Total: ';
	            	kq += '<span class="text-danger">' + tong +'</span>';
	            	kq += '</span></li>';
	            	kq += '<li class="pl-2">';
	            	kq += '<a class="text-center text-primary" href="/detailsCart/'+ketqua[0].idCart+'">View Cart</a>';
	            	kq += '</li></ul>';
	            	$(".itemss").html(kq);
	            	$(".countCart").text(numCart);
            	}
           	 	setTimeout(function(){
           		 	$(".alert-addCart").fadeIn(2000);
        	 		$(".alert-addCart").css("visibility", "visible");
        	 		$(".alert-addCart").delay(3000).fadeOut(2000);
        	 	}, 1000);
           	 	
           	 	
            }
		});
	});
	
//	$(".dropCart").click(function(){
//		var idCart = $("#idCart").val();
//		var kq = "";
//		if(idCart == 0){
//			
//			kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
//			kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i>'; 
//			kq += '<span class="text-danger countCart"><span class="soluong"></span></span></button>';
//			kq += '<input type="hidden" value="0" id="idCart">';
//			$(".itemss").html(kq);
//		}
//		
//	});
	
	$(".btnD").click(function(){
		var idCart = $(this).find('.idCartt').val();
		var idBook = $(this).find('.idBookk').first().val();
		var quantity = $(this).parent().find(".quantityy").first().val();
		$.ajax({
            url: "/changeQuantity",
            type: 'GET',
            dataType: 'json',
            data: {
            	cartId: idCart,
                bookId: idBook,
                quantity: quantity
            },
            success : function(ketqua) {
            	$(".itemss").html('');
            	var kq = "";
           	 	if(ketqua.length != 0)
            	{
           	 		
	           	 	var numCart = 0;
		       	 	for (i = 0; i <= ketqua.length -1 ; i++) {
		        		numCart += ketqua[i].number;
		        	}
		       	 	kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
		       	 	kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i> <span class="text-danger countCart th:text="${countCart}">'+numCart+'</span></button>';
		        	kq += '<ul class="dropdown-menu dropdown-cart addItems" role="menu">';
	        		kq += '<input type="hidden" value="'+ ketqua[0].idCart + '" id="idCart" class="idCart">';
		            var tong = 0;
		            //var numCart = 0;
	            	for (i = 0; i < ketqua.length ; i++) {
	            		tong += ketqua[i].priceSell*ketqua[i].number;
	            		//numCart += ketqua[i].number;
	            		kq += '<li>';
	            		kq += '<span class="item">';
	            		kq += '<span class="item-left">';
	            		kq += '<img src="' + ketqua[i].images + '" alt="" />';
	            		kq += '<span class="item-info mt-2 text-primary">';
	            		kq += '<span>' + ketqua[i].name + '</span>';
	            		kq += '<span class="text-danger">Đơn Giá: ' +  ketqua[i].priceSell + ' vnd</span>';
	            		kq += '<span>Số Lượng: ' +  ketqua[i].number +'</span>';
	            		kq += '</span>';
	            		kq += '</span>'; 
	            		kq += '</span>';
	            		kq += '</li>';
	            		kq += '<li class="divider"><hr></li>';
	            	}
	            	kq += '<li class="pl-2"><span class="text-secondary">Total: ';
	            	kq += '<span class="text-danger">' + tong +'</span>';
	            	kq += '</span></li>';
	            	kq += '<li class="pl-2">';
	            	kq += '<a class="text-center text-primary" href="/detailsCart/'+ketqua[0].idCart+'">View Cart</a>';
	            	kq += '</li></ul>';
	            	$(".itemss").html(kq);
	            	$(".countCart").text(numCart);
            	}
            	
            }
        });
            
	});
	
	$(".btn-delete").click(function(){
		//alert("ok");
		var idCart = $(this).find(".idCartt").first().val();
		var idBook = $(this).find(".idBookk").first().val();
		
		$.ajax({
            url: "/deleteBookInCart",
            type: 'GET',
            dataType: 'json',
            data: {
            	cartId: idCart,
                bookId: idBook
            },
            success : function(ketqua) {
            	if(ketqua.length != 0) {
            	window.location = "//localhost:8888/detailsCart/"+ketqua[0].idCart;
            	}else {
            		$(".tableBody").html('');
                    var tablee = "";

               	 	$(".itemss").html('');
               	 	var kq = "";
               	 	var numCart = 0;
            		kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
    	       	 	kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i> <span class="text-danger countCart">' + numCart +'</span></button>';
           		 
           		 
    	       	 	tablee +=   '<tr>';
                    tablee +=   '<td colspan="6">Not Have Book In Your Cart</td>';
                    tablee +=	 '</tr>';
                    
                    $(".itemss").html(kq);
               	 	$(".countCart").text(numCart);
                    $(".tableBody").html(tablee);
                    $(".btnOrder").html('');
            	}
                /* $(".tableBody").html('');
                 var tablee = "";

            	 $(".itemss").html('');
            	 var kq = "";
            	 var numCart = 0;
            	 if(ketqua.length != 0) {
 		       	 	for (i = 0; i <= ketqua.length -1 ; i++) {
 		        		numCart += ketqua[i].number;
 		        	}
 		       	 	kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
 		       	 	kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i> <span class="text-danger countCart th:text="${countCart}">'+numCart+'</span></button>';
 		        	kq += '<ul class="dropdown-menu dropdown-cart addItems" role="menu">';
 	        		kq += '<input type="hidden" value="'+ ketqua[0].idCart + '" id="idCart" class="idCart">';

            	 	var tong = 0;
                  	for (i = 0; i <= ketqua.length -1 ; i++) {
                  	 	tong += ketqua[i].priceSell*ketqua[i].number;
                  	 	kq += '<li>';
                  	 	kq += '<span class="item">';
                  	 	kq += '<span class="item-left">';
                  	 	kq += '<img src="' + ketqua[i].images + '" alt="" />';
                  	 	kq += '<span class="item-info mt-2 text-primary">';
                  	 	kq += '<span>' + ketqua[i].name + '</span>';
                  	 	kq += '<span class="text-danger">Đơn Giá: ' +  ketqua[i].priceSell + ' vnd</span>';
                  	 	kq += '<span>Số Lượng: ' +  ketqua[i].number +'</span>';
                  	 	kq += '</span>';
                  	 	kq += '</span>'; 
                  	 	kq += '</span>';
                  	 	kq += '</li>';
                  	 	kq += '<li class="divider"><hr></li>';


                        tablee +=   '<tr>';
                        tablee +=   '<td>'+(i+1)+'</td>';
                        tablee +=   '<td><img style="width: 150px; height: 200px;" src="' + ketqua[i].images + '"> </td>';
                        tablee +=   '<td>'+ketqua[i].name+' </td>';
                        tablee +=   '<td>'+ketqua[i].priceSell+'</td>';
                        tablee +=   '<td style="width: 200px;">';
                   
                        
                        tablee += '<div class="quantity" style="width: 100%;">';
                        tablee += '<button class="plus-btnn btnD" type="button" name="button">';
                        tablee += '<input type="hidden" class="idCartt" value="' + ketqua[i].idCart + '">'
                        tablee += '<input type="hidden" class="idBookk" value="' + ketqua[i].idBook +'">';
                        tablee += '<span>+</span>';
                        tablee += '</button>';
						
                        tablee += '<input type="text" name="name" class="quantityy text-center" min="1" readonly value="'+ketqua[i].number+'">';
                        tablee += '<button class="minus-btnn btnD" type="button" name="button">';
                        tablee += '<span>-</span>';
                        tablee += '<input type="hidden" class="idCartt" value="' + ketqua[i].idCart + '">';
                        tablee += '<input type="hidden" class="idBookk" value="' + ketqua[i].idBook + '">';
                        tablee += '</button></div>';
					
                        
                        tablee +=   '</td>';
                        tablee +=   '<td style="font-size: 30px">';           
                        tablee +=   '<button class="btn btn-danger text-white btn-delete"> <i class="fa fa-trash"></i>';
                        tablee +=   '<input type="hidden" class="idCartt" value="'+ketqua[i].idCart+'">';
                        tablee +=   '<input type="hidden" class="idBookk" value="'+ketqua[i].idBook+'">';
                        tablee +=   '</button>';
                        tablee +=   '</button>';
                        tablee +=   '</td>';    
                        tablee +=   '</tr>';
                  	}
                  	
                  	 kq += '<li class="pl-2"><span class="text-secondary">Total: ';
	               	 kq += '<span class="text-danger">' + tong +'</span>';
	               	 kq += '</span></li>';
	               	 kq += '<li class="pl-2">';
	               	 kq += '<a class="text-center text-primary" href="/detailsCart/'+ketqua[0].idCart+'">View Cart</a>';
	               	 kq += '</li>';
	               	 
	               	 $(".itemss").html(kq);
	            	 $(".countCart").text(numCart);
	                 $(".tableBody").html(tablee);
            	 } else {
            		 kq += '<button class="btn btn-dark dropdown-toggle dropCart" data-toggle="dropdown">';
     	       	 	 kq += '<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i> <span class="text-danger countCart">' + numCart +'</span></button>';
            		 
            		 
            		 tablee +=   '<tr>';
                     tablee +=   '<td colspan="6">Not Have Book In Your Cart</td>';
                     tablee +=	 '</tr>';
                     
                     $(".itemss").html(kq);
                	 $(".countCart").text(numCart);
                     $(".tableBody").html(tablee);
                     $(".btnOrder").html('');
            	 }*/
            	 
            	 
            }
       });
	});
	
	$(".order").click(function(){
		//alert("ok");
		//var value = $("input[name='payment']:checked").val();
		
		
		/*var price = $("#price").val();
		$.ajax({
            url: "/pay",
            type: 'POST', 
            contentType: 'text/plain',
            crossDomain: false,
            async:true,
            data:{
            	price: price
            }, success : function(data){
            	window.location.href
            }
        });*/
		var address = $(".diachi").val();
		var idCart = $(".cartID").val();
		var phone = $(".phone").val();
		
		if(address.length > 0){
			$.ajax({
	            url: "/dathang",
	            type: 'GET',
	            dataType: 'text',
	            data:{
	            	address: address,
	            	idCart: idCart,
	            	phone: phone
	            },
	            success : function(ketqua) {
	            	window.location = "//localhost:8888/managerOrder/"+idCart;
	            	
	            }
	        });
			
		} else {
			$(".error").css("display","inline");
		}


	});
	$(".diachi").blur(function(){
		var address = $(this).val();
		if (address.length > 0){
			$(".error").css("display","none");
		}
	});
	
	$(".btn-updateOrder").click(function(){
		
		
		var address = $(".diachi").val();
		var phone = $(".phone").val();
		var idOrder = $(".idOrder").val();
		var idCart = $(".idCart").val();
		if((phone.length == 10) && (address.length != 0)){
			$.ajax({
	            url: "/update/order",
	            type: 'GET',
	            dataType: 'text',
	            data:{
	            	address: address,
	            	phone: phone,
	            	idOrder: idOrder
	            },
	            success : function(ketqua) {
	            	window.location = "//localhost:8888/managerOrder/"+idCart;
	            }
	          });
		}
	});
	
	$(".radio-payment").click(function(){
		var value = $(this).val();
		if (value == "paypal") {
			$(".account-name").css("visibility", "visible");
		} else {
			$(".account-name").css("visibility", "hidden");
			
		}
	});
	
	

});