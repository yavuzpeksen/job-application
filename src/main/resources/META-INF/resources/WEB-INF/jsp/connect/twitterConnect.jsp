<!DOCTYPE html>
<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<body>
		<h3>Connect to Facebook</h3>

		<form action="/connect/twitter" method="POST">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<input type="hidden" name="scope" value="user_posts" />
			<div class="formInfo">
				<p>You aren't connected to Twitter yet. Click the button to connect this application with your Facebook account.</p>
			</div>
			<p><button type="submit">Connect to Twitter</button></p>
		</form>
	</body>
</html>