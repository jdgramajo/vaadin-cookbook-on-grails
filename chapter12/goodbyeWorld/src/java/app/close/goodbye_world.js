window.app_close_GoodbyeWorld = function() {
	window.onbeforeunload = function() {		
		return 'Goodbye, world!';
	};	
};