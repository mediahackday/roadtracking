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
 
 		getTimestamp: function() {
			if (!Date.now) {
				Date.now = function now() {
					return new Date().getTime();
				};
			}

			return Date.now();
		},

		getUserData: function(personid) {
			return $.ajax({
				url: './auth',
				type: 'get'
			});
		},

		testGeolocation: function() {
			return 'geolocation' in navigator;
		},

		isAuthExpired: function() {
			var authExpired = false;
			var currentTimestamp = rt.getTimestamp();
			var expiredDate = rt.getLocalStorageItem('auth-expired');

			if (typeof expiredDate !== 'undefined' && !!expiredDate) {
				if (currentTimestamp <= expiredDate) {
					authExpired = true;
				}
			}

			return authExpired;
		}
	}

	$(function() {
		var geolocation = window.navigator.geolocation = {};
		var authUser = rt.getLocalStorageItem('auth-user');
		var authExpired = rt.isAuthExpired();

		if (typeof authUser !== 'undefined' && !!authUser && !authExpired) {
			$('#signUpForm').html('<p class="text-white">You are logged in!</p>');

			if (rt.testGeolocation) {
				navigator.geolocation.watchPosition(function(pos) {
					console.log("I'm located at ", pos.coords.latitude, ' and ', pos.coords.longitude);
					//here can be some callback
				});
			}
		}
	});
}(window.jQuery, window, document));