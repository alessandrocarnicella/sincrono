<div class="row">
	<div class="col-12 mb-5">
		<iframe
			src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2972.4074622577473!2d12.509857315304751!3d41.84105577922515!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x13258a3952515311%3A0x9f317c8987c8eebf!2sVia+Mosca%2C+10%2C+00142+Roma+RM!5e0!3m2!1sit!2sit!4v1553607023072"
			width="100%" height="400" frameborder="0" style="border: 0"
			allowfullscreen></iframe>
	</div>
	<div class="col-md-6 grid-margin stretch-card ">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Address</h4>
				<div class="row">
					<div class="col-md-6">
						<address>
							<p class="font-weight-bold">Sincronia</p>
							<p>Via mosca 10</p>
							<p>Roma, RM 00142</p>
							<p>Italia, IT</p>
						</address>
					</div>
					<div class="col-md-6">
						<address class="text-primary">
							<p class="font-weight-bold">E-mail</p>
							<p class="mb-2">nomec443@gmail.com</p>
							<p class="font-weight-bold">Web Address</p>
							<p>www.sincronia.com</p>
						</address>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Contact Us</h4>
				<form class="forms-sample" method="post" action="Contact_Msg">
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text"
							class="form-control" id="nome" name="nome" placeholder="Name" required>
					</div>
					<div class="form-group">
						<label for="cognome">Cognome</label> <input type="text"
							class="form-control" id="cognome" name="cognome" placeholder="Surname"  required>
					</div>
					<div class="form-group">
						<label for="email">Email address</label> <input type="email"
							class="form-control" id="email" name="email" placeholder="Email"  required>
					</div>
					<div class="form-group">
						<label for="exampleTextarea1">Textarea</label>
						<textarea class="form-control" id="msg" name="msg" rows="4"  required></textarea>
					</div>
					<div class="row">
						<div class="col-12">
							<button type="submit" class="btn btn-success mr-2 w-100">Invia
								richiesta</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</div>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function notify_success(msg) {
	$.notify({
		// options
		message: msg 
	},{
		// settings
		type: 'success'
	});

};
</script>

<c:choose>
	<c:when test="${errore_contact eq 1}">
		<script>
			$(window).bind("load",function(){
				setTimeout(notify_success('Success: Email inviata correttamente'),500);
			});
			setTimeout(function(){ 
				location.href = $("#contact_us").attr("href");
				}, 
				2000);
		</script>
		<%
			session.setAttribute("errore_contact", 0);
		%>
	</c:when>
	<c:otherwise>

	</c:otherwise>
</c:choose>