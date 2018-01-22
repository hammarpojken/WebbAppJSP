
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


//function deleteSome() {
//    
//    var b = document.getElementsByClassName("checkthis");
//        
//    for(var i = 0; i< b.length; i++) {
//        if(b[i].checked == true) {
//            var a = b[i].parentElement.parentElement.getAttribute("id");
//            var c = document.getElementById(a);
//            c.innerHTML = "";
//            delete c;
//        }
//    }
//}