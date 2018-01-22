
function hola() {
	var b = document.getElementById("hej");
	b.innerHTML = "hej";
    $("p").hide();
    console.log("jeksam");
}


function b() {
		$('#delete').on('hidden.bs.modal', function (event) {
            
       var c = event.relatedTarget.parentElement.parentElement.parentElement;
        console.log(c);
    });
}
  


function c() {
    $('#edit').on('show.bs.modal', function (event) {
    	var c = event.relatedTarget.parentElement.parentElement.parentElement.childNodes;
    	
    var a = document.getElementById("firstname");
        a.getAttribute("placeholder").value = c[1];
    	
    });
    
}



function deletePerson(id) {
    $('#yesDelete').on('click', function(event)  {
       var person = document.getElementById("" + id);
        
        id.innerHTML = "";
        delete id;
        $('#delete').modal('hide');
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
        
        
});
}