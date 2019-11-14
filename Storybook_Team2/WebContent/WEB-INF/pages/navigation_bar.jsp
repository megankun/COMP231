<div class="container">
<header class="blog-header py-3">
	<div class="row flex-nowrap justify-content-between align-items-center">
		<div class="col-4 pt-1">
			<a class="text-muted" href="#"></a>
		</div>
		<div class="col-4 text-center">
			<a class="blog-header-logo text-dark" href="#" style="font-size:28pt;"><b>Story Book</b></a>
		</div>
		<div class="col-4 d-flex justify-content-end align-items-center">
			<a class="btn btn-sm btn-outline-secondary" href="signIn.jsp">Logout</a>
		</div>
	</div>
</header>
<div class="nav-scroller py-1 mb-2" style="font-size:16pt;">
	<nav class="nav d-flex justify-content-between">			
		<a class="p-2 text-muted" href="<c:url value="/tutorial?userId=${userId}"/>">Tutorial</a>
	    <a class="p-2 text-muted" href="<c:url value=""/>">Search Stories</a>
	    <a class="p-2 text-muted" href="<c:url value="/episode?userId=${userId}"/>">Episode</a>
	    <a class="p-2 text-muted" href="<c:url value="/userAccountInfo?userId=${userId}"/>">My Account</a>	    
	</nav>
</div>
</div>
<br/>