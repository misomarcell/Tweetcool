<!doctype html>
<html>
<head>
	<meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="./stylesheets/style.css">
    <link href="https://fonts.googleapis.com/css?family=Rubik" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:900" rel="stylesheet">
	<title>Login - Tweetcool</title>
</head>

<body>
    
    <div class="main-container separated">
    	<center><h1>Tweetcool</h1></center>
        <center><div style="margin-top: -10px;">a codecool alternative</div></center>
    </div>
    
	<div class="main-container">
        ${message}
        
        <div class="login-container">
            <center><h2>Login</h2> </center>
            <form action="login" method="POST">
                <input class="text-input login-text" type="email" id="login-email" name="login-email" placeholder="E-mail address"/>
                <input class="text-input login-text" type="password" id="login-pass" name="login-pass" placeholder="Password"/>
                <input class="login-button" type="submit" id="submit" name="submit" value="Login"/>   
            </form>
        </div>
             
       	<div class="register-container">
        	<center><h2>Register</h2> </center>
            <form action="register" method="POST">
                <input class="text-input name-text" type="text" id="first-name" name="first-name" placeholder="First name" autocomplete="off"/>
                <input class="text-input name-text" type="text" id="last-name" name="last-name" placeholder="Last name" autocomplete="off"/>
                <input class="text-input login-text" type="email" id="email" name="email" placeholder="Enter your e-mail address" autocomplete="off"/>
                <input class="text-input login-text" type="password" id="pass" name="pass" placeholder="Password" autocomplete="off"/>
                <input class="text-input login-text" type="password" id="pass2" name="pass2" placeholder="Confirm Password" autocomplete="off"/>
                <input class="login-button" type="submit" id="submit" name="submit" value="Register"/> 
            </form>
       	</div>
        
        <div class="footer-container">
        	Made by Marcell Misó @ 2017
        </div>
    </div>
</body>
</html>
