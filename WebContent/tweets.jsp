<!doctype html>
<html>
<head>
	<meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="./stylesheets/style.css">
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
        ${message}
        
        <div class="post-container">
            <form action="tweets" method="POST">
            	<div class="post-top">
                	<span style="font-weight:600; font-size:14px;">
                    	Tweet as ${name}
                    </span> 
                    <a href="javascript:logout();">
                    	<span style="font-size:14px; float:right;">
                        	(Kijelentkezés)
                        </span>
                  	</a>
                </div>
                    
            	<input class="tweet-input" type="text" id="tweet" name="tweet" placeholder="Type your post here..." autocomplete="off"/>
            	<input class="tweet-submit" type="submit" value="Post" id="submit" name="submit" />
            </form>
        </div>
    </div>
    
	${tweets}
</body>
</html>
