<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<style type="text/css">
	#container{
		width:70%;
		margin:0 auto;
	}
	#header{
		width:100%;
		margin-bottom:30px;
	}
	#title{
		width:50%;
		line-height: 20px;
	}
	.btn{
		width:100px;
		height:40px;
		font-size: 1.2em;
		float:right;
		display:block;
		margin-left:10px;
	}
	
</style>
<script type="text/javascript">
	$.noConflict();
	
	var j=jQuery;
	j(function(){
		
	});
</script>
</head>
<body>
	<form id="form1" method="post" action="register">
		<div id="container">
			<h2>글쓰기</h2>
			<div id="header">
				<span>제목:</span>
				<input id="title" type="text" name="btitle">
				<input class="btn" type="submit" value="저장">
				<a href="${pageContext.request.contextPath}"><button type="button" class="btn">뒤로가기</button></a>
			</div>
			<textarea id="editor1" name="bcontent">
			    
			</textarea>
			<script>
				CKEDITOR.replace('bcontent',{filebrowserUploadUrl:"${pageContext.request.contextPath}/imgUpload"});
			</script>
		</div>
		
	</form>
	
	
</body>
</html>