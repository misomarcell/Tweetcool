<!doctype html>
<html>
<head>
	<meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="./stylesheets/style.css">
    <link rel="stylesheet" href="./stylesheets/font-awesome/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Rubik" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
	<title>Tweetcool</title>
</head>

<body>
	<script>
		function logout() {
			document.cookie = 'sessionID=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
			window.location="/Tweetcool/login";
		};
	</script>

	<div class="main-container">
        <div class="main-menu-holder">
        	<a href="./tweets"><div class="menu-item">
                    Tweets
         	</div></a>  
            
            <a href="./profile"><div class="menu-item">
            	Profile
            </div></a>
                  
            <a href="javascript:logout();"><div style="float: right !important;" class="menu-item">
            	Kijelentkezés
            </div></a>
        </div>
        
        <div class="profile-basic-info">
            <div class="profile-picture"><img src="${avatar}" width="100"/></div>
            <div class="name">
                <span><b>${name}</b></span>
                <div>${email}</div>
                <div>${rank}</div>
         	</div>
            <div id="add" style="display:${friend};"><i class="fa fa-plus-circle" aria-hidden="true"></i>  Add as friend</div>
      	</div>
    </div>
	<div class="main-container separated">Tweets from ${firstname}</div>
${tweets}
</body>
</html>
