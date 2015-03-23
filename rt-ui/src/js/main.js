(function($, window, document) {
	// The $ is now locally scoped
	
	rt = {
		getName: function(personid) {
			return $.ajax({
				url: './auth',
				type: 'get'
			});
		}
	}

	// Listen for the jQuery ready event on the document
	$(function() {
		//do some stuff when the DOM is ready

		$('#signup').submit(function(event) {
			console.log($(this).serialize());

			rt.getName('2342342').done(function(data) {
				console.log(data);
			});

			event.preventDefault();
		});
	});
}(window.jQuery, window, document));