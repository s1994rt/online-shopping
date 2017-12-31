<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">
	<c:if test="${not empty message}">
	<div class="col-xs-12">
	
	    <div class="alert alert-success alert-dismissible">
	     <button type="button" class="close" data-dismiss="alert">&times;</button>
	          ${message}
	    </div>
	     
	</div>
	</c:if>
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Manage Product</h4>
				</div>

				<div class="panel-body">

					<sf:form class="form-horizontal" modelAttribute="product"
					action="${contextRoot}/manage/products" method="POST" enctype="multipart/form-data">
					
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter Product Name:</label>
							<div class="col-md-8">
								<sf:input type="text" id="name" path="name"
									placeholder="Enter Product Name" class="form-control" /> 
								<sf:errors cssClass="help-block" path="name" element="em"/>	
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter Brand Name:</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Enter Brand Name" class="form-control" />
								<sf:errors cssClass="help-block" path="brand" element="em"/>	 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description:</label>
							<div class="col-md-8">
							   <sf:textarea  path="description" id="description" rows="4" placeholder="Write Description" class="form-control" />
							   <sf:errors cssClass="help-block" path="description" element="em"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="price">Unit Price</label>
							<div class="col-md-8">
							   <sf:input type="text"  path="unitPrice" id="price" rows="4" placeholder="unit price in &#8377;" class="form-control"/>
							   <sf:errors cssClass="help-block" path="unitPrice" element="em"/>
							</div>
						</div>
						<div class="form-group">
						   <label class="control-label col-md-4" for="quantity">Quantity Available:</label>
						   <div class="col-md-8">
						        <sf:input type="text" path="quantity" id="quantity" placeholder="Enter Quantity" class="form-control"/>
						   </div>
						
						</div>
						<div class="form-group">
						    <label class="control-label col-md-4" for="category">Select Category:</label>
						    <div class="col-md-8">
						          <sf:select class="form-control" path="categoryId" id="categoryId"
						          
						          items="${categories}"
						          itemLabel="name"
						          itemValue="id" />
						          
						              
						    </div>
						</div>
						
					   <div class="form-group">
							<label class="control-label col-md-4" for="file">Select File:</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors cssClass="help-block" path="file" element="em"></sf:errors>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit"  id="brandName" class="btn btn-primary"/>
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="supplierId"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
								<sf:hidden path="active"/>
							</div>
						</div>
					</sf:form>
				</div>

			</div>
		</div>

	</div>
</div>