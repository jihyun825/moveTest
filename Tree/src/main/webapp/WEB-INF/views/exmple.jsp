<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
</head>
<body>
<ul id="treeview">
  <li>Node 1
    <ul>
      <li>Child 1</li>
      <li>Child 2</li>
    </ul>
  </li>
  <li>Node 2</li>
</ul>
</body>
<script>
$('#tree').jstree({ 
	  'core' : {
	    'data' : [
	      { "id" : "ajson1", "parent" : "#", "text" : "Simple root node" },
	      { "id" : "ajson2", "parent" : "#", "text" : "Root node 2" },
	      { "id" : "ajson3", "parent" : "ajson2", "text" : "Child 1" },
	      { "id" : "ajson4", "parent" : "ajson2", "text" : "Child 2" },
	    ]
	  }
	});
</script>
</html>