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
	case 'Manage Products':
		$('#manageProducts').addClass('active');
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
											+ '.jpg" class="dataTableImage"></img>';
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
										str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success" title="add to cart"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}

									return str;
								}
							}

					]

				});
	}
	
	
	//dismiss the alert box after 3 seconds
		
		var $alert = $('.alert');
		if($alert.length){
			setTimeout(function(){
				$alert.fadeOut('slow');
			},3000)
		}
		
	//-------------------------------------

	//product data for admin
	
	var $adminProductsTable = $('#adminProductsTable');
	
	if ($adminProductsTable.length){
		var jsonUrl= window.contextRoot +'/json/data/admin/all/products';
		
		$adminProductsTable.DataTable ({
			
			lengthMenu:[
				        [30,40,50,-1],
				        ['30 products','40 products','50 products','All products']
				       ],
			pageLength: 30,
			
			ajax:{
				url: jsonUrl,
				dataSrc: ''
			},
			
			columns: [
				{
					data: 'id'
				},
				{
					data: 'code',
					bSortable : false,
					mRender : function(data,type,row){
						
						return '<img id="adminDataTableImg"  src="'+window.contextRoot+'/resources/images/'+data+'.jpg" style="height:50px; width:50px"/>';
					}
					
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
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
					data : 'unitPrice',
					mRender : function(data, type, row) {
						return '&#8377; ' + data;
					}
				},
				{
					data: 'active',
					bSortable: false,
					mRender: function(data,type,row){
						var str='';
						str+='<label class="switch">';
						if(data){
							str+='<input type="checkbox" checked="checked" value="'+row.id+'"/>';
						}
						else{
							str+='<input type="checkbox"  value="'+row.id+'"/>';
						}
						str+='<div class="slider"></div></label> ';
						return str;
					}
				},
				{
					data: 'id',
					mRender: function(data,type,row){
						var str='';
						str+= '<a href="'+ window.contextRoot +'/manage/'+ data +'/product" class="btn btn-warning">';
		                str+= '<span class="glyphicon glyphicon-pencil"/>';
		                return str;
					}
				}
				
			],
			
			initComplete: function(){
				
				var api=this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					console.log("hi");
					var checkbox = $(this);
					var checked =checkbox.prop('checked');
					var dMsg = (checked)? 'You want to activate the product':
						                  'Yout want to deactivate the product';
					
					var value = checkbox.prop('value');
					bootbox.confirm({
						size: 'medium',
						title: 'Activation And Deactivation',
						message: dMsg,
						callback: function(confirmed){
							if(confirmed){
								var activationUrl = window.contextRoot +'/manage/product/'+value+'/activation';
								
								$.post(activationUrl , function(data){
									bootbox.alert({
										size: 'medium',
										title: 'Information',
										message: data
									});
									
								});
								
								
								}
							
							else{
								checkbox.prop('checked',!checked);
							     }
							}
				
					});
					
				});
			}
			
		});
	}
	
	//-----------------------------------------
	//Valdation for category madal form
	
	var $categoryForm= $('#categoryForm');
	if($categoryForm.length){
		
		$categoryForm.validate({
			
			 rules:{
				  name:{
					  required: true,
					  minlength: 2
				  },
				  
				  description:{
					  required: true
				  }
			 },
			 
			 messages:{
				 
				 name:{
			          required:"Please add the category name!",
			          minlength: "The category name should not be less than 2 characters!"
				 },
				 description:{
					 
					 required:"Please add the description for this category"
				 }
					 
			 },
			 
			 errorElement: 'em',
			 errorPlacement: function(error,element){
				 //add the class of help-block
				 error.addClass("help-block");
				 error.insertAfter(element);
			 }
		})
	}
	
	//---------------
	
});
