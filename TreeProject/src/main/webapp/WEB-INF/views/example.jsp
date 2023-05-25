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
<input type="text" id="searchbox" value="" class="input" style="margin:0em auto 1em auto; display:block; padding:4px; border-radius:4px; border:1px solid silver;">
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
	              { 'text': '총무팀',
	            	  'children':[
	            		  {'text' : '황지현'},
	            		  {'text' : '조성희'}
	            	  ]
	            	},
	              { 'text': '재경팀' },
	              { 'text': '구매팀' }
	            ]
          },
          { 'text': '영업본부',
        	  'children' : [
        		  { 'text': '외근영업팀',
        			  'children': [
        				  {'text':'1팀'},
        				  {'text':'2팀'}
        			  ]
        		  },
        		  { 'text': '온라인영업팀'},
        		  { 'text': '기업영업팀'}
        	  ]
          }
        ],
        "check_callback" : true
      },
      'plugins' : [ "contextmenu", "dnd", "search",
    	    "state", "types", "wholerow","sort"],
  	'contextmenu' : {
  		"items" : {
	  			"test" : { //사실상 "test"라는 이름은 변수에 가깝기 때문에 뭐든 상관없다 생각한다.
	          		"separator_before" : false,
	  				"separator_after" : true,
	  				"label" : "채팅하기",
	  				"action" : function(obj){alert('채팅하기')}
	  			},
	  			"test1" : {
	  				"separator_before" : false,
	  				"separator_after" : true,
	  				"label" : "쪽지보내기",
	  				"action" : function(obj){alert('대화하기')}
	  				}
  			}
  		},
  		"search" : {
  		    "show_only_matches" : true,
  		    "show_only_matches_children" : false
  		},
  		
    });
    var to = false;
	     $('#searchbox').keyup(function () {
	       if(to) { clearTimeout(to); }
	       to = setTimeout(function () {
	         var v = $('#searchbox').val();
	         $('#tree').jstree(true).search(v);
	       }, 250);
	     });
		
  });
</script>
</html>