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
		if (menu == "home")
			break;
		$('#productList').addClass('active');
		$('#c_' + menu).addClass('active');
		break;
	}

	// code for jquery dataTable
	var $table = $('#productListTable');
	// Exeecute the below code only when we have this table

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}

		else {

			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products'
		}

		$table
				.DataTable({
					lengthMenu : [ [ 2, 4, 6, -1 ],
							[ '2 Records', '4 Records', '6 Records', 'All' ] ],
					pageLength : 4,

					ajax : {

						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="dataTableImage" style="width:100px; height:100px;"></img>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data< 1) {
										return '<span style="color:red">Out of stock</span>';
									}
									return data;

								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary" title=" product description"><span class="glyphicon glyphicon-eye-open" ></span></a> &#160';
									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success"  disabled><span class="glyphicon glyphicon-shopping-cart"></span></a>';

									}
									else{
										str+='<a href="'+window.contextRoot+'/carrt/add/'+data+'/product" class="btn btn-success" title="add to cart"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}

									return str;
								}
							}

					]

				});
	}
});