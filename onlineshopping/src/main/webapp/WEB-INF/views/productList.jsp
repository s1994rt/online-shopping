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
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>
					<c:if test="${clickOnCategoryProducts==true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>

		</div>

	</div>


</div>