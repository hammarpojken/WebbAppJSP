<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.jsp.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
      <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<title>Login</title>
</head>
<body>




<div class="container">
	<div class="row">
		
        
        <div class="col-md-12">
        <h4 id="hej">HospitalHelper</h4>
        <div class="table-responsive">

                
              <table id="mytable" class="table table-bordred table-striped">
                   
                   <thead>
                   
                   <th><input type="checkbox" id="checkall" /></th>
                   <th>First Name</th>
                    <th>Last Name</th>
                     <th>SSN</th>
                     <th>Address</th>
                     <th>Phone</th>
                      <th>Edit</th>
                      
                       <th>Delete</th>
                   </thead>
    <tbody>
    <%
	LoginDao ld = new LoginDao();
	ld.getPatients();
	for(int i = 0; i < ld.data.size(); i++) {
		
		out.println("<tr id =\"tr" + i +"\">");
		out.println("<td><input type=\"checkbox\" class=\"checkthis\" /></td>");
		out.println("<td>"+ ld.data.get(i).getFname() + "</td>");
		out.println("<td>"+ ld.data.get(i).getLname() + "</td>");
		out.println("<td>"+ ld.data.get(i).getSsn() + "</td>");
		out.println("<td>"+ ld.data.get(i).getAdress() + "</td>");
		out.println("<td>"+ ld.data.get(i).getPhone() + "</td>");
		out.println("<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-primary btn-xs\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#edit\" ><span class=\"glyphicon glyphicon-pencil\"></span></button></p></td>");
		out.println("<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button onclick=\"deletePerson(tr" + i +")\" class=\"btn btn-danger btn-xs\" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#delete\" ><span class=\"glyphicon glyphicon-trash\"></span></button></p></td>");
	    out.println("</tr>");
	  
	}
	


%>
     
    
    </tbody>
        
</table>

<div class="clearfix"></div>
<ul class="pagination pull-right">
  <li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
  <li class="active"><a href="#">1</a></li>
  <li><a href="#">2</a></li>
  <li><a href="#">3</a></li>
  <li><a href="#">4</a></li>
  <li><a href="#">5</a></li>
  <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
</ul>
                
            </div>
            
        </div>
	</div>
</div>


<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
    <div class="modal-content">
          <div class="modal-header">
        <button type="button" onclick="" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
      </div>
      <form action="UpdatePeopleDao" method="post">
          <div class="modal-body">
          <div class="form-group">

        <input id="firstname" name="firstName" class="form-control " type="text" placeholder="">
		<input id="ssn" name="ssn" class="form-control " type="hidden" placeholder="">
        </div>
        <div class="form-group">
        
        <input id="lastname" name="lastName" class="form-control " type="text" placeholder="">
        </div>
        <div class="form-group">
        <textarea id="adress" name="adress" rows="2" class="form-control" placeholder=""></textarea>
    
        
        </div>
      </div>
          <div class="modal-footer ">
        <button onclick="" type="submit" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>
      </div>
      	</form>
        </div>
    <!-- /.modal-content --> 
  </div>
      <!-- /.modal-dialog --> 
    </div>
    
    
    
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
    <div class="modal-content">
          <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
      </div>
          <div class="modal-body">
       
       <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
       
      </div>
      <form action="DeletePeopleDao" method="post">
        <div class="modal-footer ">
        <input id="SSN" name="ssn" class="form-control " type="hidden" placeholder="">
        <button id="yesDelete" type="submit" class="btn btn-success" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
      </div>
      </form>
        </div>
    <!-- /.modal-content --> 
  </div>
      <!-- /.modal-dialog --> 
    </div>
    
    <script src="myjs/mytestjs.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>
    $( document ).ready(function(){
        $('#delete').on('show.bs.modal', function (event) {
    	var c = event.relatedTarget.parentElement.parentElement.parentElement.childNodes;
    	
            
    var ssn = document.getElementById("SSN");
        ssn.value = c[7].textContent
            
            console.log(c);
       
    	
    });
    }); 
    
    $( document ).ready(function(){
        $('#edit').on('show.bs.modal', function (event) {
    	var c = event.relatedTarget.parentElement.parentElement.parentElement.childNodes;
    	
    var firstname = document.getElementById("firstname");
        firstname.value = c[3].textContent;
    
    var lastname = document.getElementById("lastname");
        lastname.value = c[5].textContent;
            
    var adress = document.getElementById("adress");
        adress.value = c[9].textContent;
            
    var ssn = document.getElementById("ssn");
        ssn.value = c[7].textContent
            
            console.log(c);
       
    	
    });
    }); 
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script> 
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script> 
</body>
</html>