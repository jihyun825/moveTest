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
<div id="tree">

</div>
</body>
<script>
$(document).ready(function() {
    $('#tree').jstree({
      'core': {
        'data': [
          {
            'text': '경영지원본부',
            'children': [
              { 'text': '총무팀' },
              { 'text': '재경팀' },
              { 'text': '구매팀' }
            ]
          },
          { 'text': '영업본부',
        	  'childern' : [
        		  { 'text': '외근영업팀',
        			  'childern': [
        				  {'text':'1팀'},
        				  {'text':'2팀'}
        			  ]
        		  },
        		  { 'text': '온라인영업팀'},
        		  { 'text': '기업영업팀'}
        	  ]
        	  
          }
        ]
      }
    });
  });
</script>
</html>