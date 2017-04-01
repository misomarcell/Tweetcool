<!doctype html>
<html>
<head>
	<meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="./stylesheets/style.css">
    <link href="https://fonts.googleapis.com/css?family=Rubik" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
	<title>Login - Tweetcool</title>
</head>

<body>
	<div class="main-container">
        ${message}
        
        <div class="login-container">
            <center><h1>Login</h1> </center>
            <form action="LoginSV" method="POST">
                <input class="text-input login-text" type="email" id="login-email" name="login-email" placeholder="E-mail address"/>
                <input class="text-input login-text" type="password" id="login-pass" name="login-pass" placeholder="Password"/>
                <input class="login-button" type="submit" id="submit" name="submit" value="Login"/>   
            </form>
        </div>
             
       	<div class="register-container">
        	<center><h1>Register</h1> </center>
            <form action="RegisterSV" method="POST">
                <input class="text-input name-text" type="text" id="first-name" name="first-name" placeholder="First name"/>
                <input class="text-input name-text" type="text" id="last-name" name="last-name" placeholder="Last name"/>
                <input class="text-input login-text" type="email" id="email" name="email" placeholder="Enter your e-mail address"/>
                <input class="text-input login-text" type="password" id="pass" name="pass" placeholder="Password"/>
                <input class="text-input login-text" type="password" id="pass2" name="pass2" placeholder="Confirm Password"/>
                <input class="login-button" type="submit" id="submit" name="submit" value="Register"/> 
            </form>
       	</div>
        
        <div class="footer-container">
        	Made by Marcell Misó @ 2017
        </div>
    </div>
</body>
</html>
