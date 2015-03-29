(function($, window, document) {
	// The $ is now locally scoped
	
	rt = {
		setLocalStorageItem: function(key, value) {
			localStorage.setItem(key, value);
		},

		getLocalStorageItem: function(key) {
			return localStorage.getItem(key);
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
		
		if (typeof authUser !== 'undefined' && !!authUser) {
			$('#signUpForm').html('<p class="text-white">You are logged in!</p>');
		}
	});
}(window.jQuery, window, document));