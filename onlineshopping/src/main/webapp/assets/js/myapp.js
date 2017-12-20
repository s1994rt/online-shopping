$(function() {
	// solving the active prolem of the menu bar
	switch (menu) {
	case 'About':
		$("#about").addClass('active');
		break;
	case 'Contact':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#productList').addClass('active');
		break;
	default:
		$('#productList').addClass('active');
	    $('#c_'+menu).addClass('active');
		break;
	}
});