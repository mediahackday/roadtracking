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

		$('#test').submit(function(event) {
			console.log($(this).serialize());

			rt.getUserData('2342342').done(function(data) {
				console.log(data);
			});

			event.preventDefault();
		});
	});
}(window.jQuery, window, document));