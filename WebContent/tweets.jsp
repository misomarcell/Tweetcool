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
        
        
        <div>${message}</div>
        
        <div class="post-container">
            <form action="tweets" method="POST">
            	<div class="post-top">
                	<span style="font-weight:600; font-size:14px;">
                    	Tweet as ${name}
                    </span> 
                </div>
                    
            	<input class="tweet-input" type="text" id="tweet" name="tweet" placeholder="Type your post here..." autocomplete="off"/>
            	<input class="tweet-submit" type="submit" value="Post" id="submit" name="submit" />
            </form>
        </div>
    </div>
    
	<!--<div class="post main-container">
		<div class="headline">
			<span class="author">
				<a href="#">
					Author Name
				</a>
			</span> - 
			<span class="date">
				2017.12.15 18:22:55
			</span>
            
            <span class="delete-tweet">
            	<a href=""><i class="fa fa-trash-o" aria-hidden="true"></i></a>
            </span>
		</div>
        
        <div class="post-text">
            Tweet content goes here...
        </div>
	</div>-->
    ${tweets}

</body>
</html>
