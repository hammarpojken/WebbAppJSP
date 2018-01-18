
function hola() {
	var b = document.getElementById("hej");
	b.innerHTML = "hej";
    $("p").hide();
    console.log("jeksam");
}


function b() {
    $('#delete').on('shown.bs.modal', function (event) {
       var c = event.relatedTarget.parentElement.parentElement.parentElement;
        c.innerHTML = "";
            delete c;
    });
}

function c() {
    $('#edit').on('shown.bs.modal', function (event) {
       var c = event.relatedTarget.parentElement.parentElement.parentElement;
        console.log(c);
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