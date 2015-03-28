(function($, window, document) {
	// The $ is now locally scoped
	
	rt = {
		setLocalStorageItem: function(key, value) {
			localStorage.setItem(key, value);
		},

		getLocalStorageItem: function(key) {
			localStorage.getItem(key);
		},

		removeLocalStorageItem: function(key) {
        	localStorage.removeItem(key);
		},

		getUserData: function(personid) {
			return $.ajax({
				url: './auth',
				type: 'get'
			});
		}
	}

	// Listen for the jQuery ready event on the document
	$(function() {
		//do some stuff when the DOM is ready

		var authUser = rt.getLocalStorageItem('auth-user');
		console.log(authUser);
		
		if (typeof authUser !== "undefined") {
			$('#signUpForm').html('You are logged in!');
		}
	});
}(window.jQuery, window, document));