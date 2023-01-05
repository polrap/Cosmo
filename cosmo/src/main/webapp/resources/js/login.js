function win_open(){
	
	var width = '650';
    var height = '380';
	var left = Math.ceil(( window.screen.width - width )/2);
    var top = Math.ceil(( window.screen.height - height )/2); 
	
	 window.open('/cosmo/logoutpop', 'popup', 'width='+ width +', height='+ height +', left=' + left + ', top='+ top );
};

function popMoveMain(){
	opener.location.href="/cosmo/logout";
	window.close();
}