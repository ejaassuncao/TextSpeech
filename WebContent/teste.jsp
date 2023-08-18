<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TextSpeech</title>
<head>
<!-- Required meta tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/style.css" crossorigin="anonymous">

<title>TextSpeech</title>
</head>

<body>

	<main role="main" class="container">

	<form>
		<div class="row">
			<div class="col-lg-12 form-group">

				<div class="col-lg-12 form-group">
					<label>Senha:<input type="text" id="senha"
						class="form-control" placeholder="Digite sua senha">
					</label>
				</div>

				<div class="col-lg-12 form-group">
					<label>Guichê:<input type="text" id="guiche"
						class="form-control" placeholder="Digite seu guichê">
					</label>
				</div>

				<div class="col-lg-12 form-group">
					<button type="button" onclick="ChamarSenha()"
						class="btn btn-primary">Play</button>
				</div>

				<div class="col-lg-12 form-group">
					<audio controls id="audio">
						<source src="" type="audio/mpeg">
					</audio>					
				</div>

			</div>
		</div>
	</form>

	</main>
	<footer class="footer">
	<div class="container">
		<span class="text-muted">Onix-Voice</span>
	</div>
	</footer>

	<script type="text/javascript">
		function ChamarSenha() {

			var audio = document.getElementById("audio");
			audio.canplaythrough = function() {
				console.log('carregado audio');
			};

			var senha = document.getElementById("senha").value;
			var guiche = document.getElementById("guiche").value;

			var url = "ServletVoice?senha=" + senha + "&guiche=" + guiche;

			audio.src = url;
			audio.play();

		}
	</script>

</body>
</html>