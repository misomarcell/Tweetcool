<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Register - Tweetcool</title>
</head>

<body>
<form action="RegisterSV" method="POST">
	${message}
	<br />
	
	<span>E-mail</span>
    <input type="email" id="email" name="email" placeholder="Enter your e-mail address"/>
    <br />
    
    <span>Password</span>
    <input type="password" id="pass" name="pass" />
    <span>ConfirmPassword</span>
    <input type="password" id="pass2" name="pass2" />
    <br />
    
    <span>First Name</span>
    <input type="text" id="first-name" name="first-name" />
    <span>Last Name</span>
	<input type="text" id="last-name" name="last-name" />
    <br />
    
    <input type="submit" id="submit" name="submit" value="Register"/>
</form>
</body>
</html>
