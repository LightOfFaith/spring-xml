<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test GUI</title>
</head>
<body>

	<p>session >>>>>>>>${session }</p>
	<p>application>>>>>>>>${application }</p>
	<p>exception>>>>>>>>${exception }</p>
	<p>page >>>>>>>>${page }</p>
	<p>pageContext >>>>>>>>${pageContext }</p>
	<p>config>>>>>>>>${config }</p>
	<p>pageContext.errorData >>>>>>>>${pageContext.errorData }</p>
	<p>pageContext.exception>>>>>>>>${pageContext.exception }</p>
	<p>pageContext.page>>>>>>>>${pageContext.page }</p>
	<p>pageContext.request >>>>>>>>${pageContext.request }</p>
	<p>pageContext.response>>>>>>>>${pageContext.response }</p>
	<p>pageContext.servletConfig >>>>>>>>${pageContext.servletConfig }</p>
	<p>pageContext.servletContext
		>>>>>>>>${pageContext.servletContext }</p>
	<p>pageContext.session >>>>>>>>${pageContext.session }</p>
	<p>pageContext.response>>>>>>>>${pageContext.variableResolver }</p>
	<p>pageContext.request.contextPath
		>>>>>>>>${pageContext.request.contextPath }</p>

</body>
</html>