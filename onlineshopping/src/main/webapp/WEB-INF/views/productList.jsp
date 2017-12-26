<div class="container">

	<div class="row">
		<!-- sidebar display here -->

		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>
		<!-- actual product display here -->
		<div class="col-md-9">

			<div class="row">
				<div class="col-lg-12">
					<c:if test="${clickOnViewProducts==true}">
						<script>
							window.categoryId = '';
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>
					<c:if test="${clickOnCategoryProducts==true}">
						<script>
							window.categoryId = "${category.id}";
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table id="productListTable"
						class="table table-striped table-borderd">
						<thead>
							<tr>

								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qantity</th>
								<th></th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qantity</th>
								<th></th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>

	</div>


</div>