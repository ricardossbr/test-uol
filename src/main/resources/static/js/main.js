$(document).ready(function(){
	$("input.telefone")
	.mask("(99) 9999-9999?9")
	.focusout(function (event) {  
		var target, phone, element;  
		target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
		phone = target.value.replace(/\D/g, '');
		element = $(target);  
		element.unmask();  
		if(phone.length > 10) {  
			element.mask("(99) 99999-999?9");  
		} else {  
			element.mask("(99) 9999-9999?9");  
		}  
	});
	
	$(".erro").hide();
	$(".vingadores").hide();
	$(".liga").hide();

	$(".radio").click(function(){
		$("#liga-grup").val('');
		if($(".radio").val() == "Liga da Justiça"){
			$(".liga").show();
			$(".vingadores").hide();
		}
	});
	
	$(".radio2").click(function(){
		$("#liga-grup").val('');
		if($(".radio2").val() == "Vingadores"){
			$(".vingadores").show();
			$(".liga").hide();
		}
	});
	
});