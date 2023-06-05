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
            'text': '대덕인재개발원',
               'children': [
                 { 'text': '취업지원팀',
                    'children':[
                       {'text' : '권용달'},
                       {'text' : '이지영'},
                       {'text' : '이규방'},
                    ]
                  },
                 { 'text': '강사',
                     'children':[
                         {'text' : '하재관'},
                          {'text' : '배미숙'},
                          {'text' : '장승수'},
                          {'text' : '이성엽'},
                          {'text' : '조현준'},
                          {'text' : '송찬중'},
                          {'text' : '송중호'},
                          {'text' : '이유진'},
                          {'text' : '최희연'},
                          {'text' : '장태권'}
                     ]
                  },
                 { 'text': '학생',
                     'children' : [
                        {'text' : '401호'},
                        {'text' : '402호'},
                        {'text' : '403호'},
                        {'text' : '404호'},
                        {'text' : '405호'},
                        {'text' : '406호',
                           'children':[
                              {'text' : '박정수'},
                              {'text' : '신국현'},
                              {'text' : '김지완'},
                              {'text' : '변정민'},
                              {'text' : '전지혜'},
                              {'text' : '이수진'},
                              {'text' : '홍기태'},
                              {'text' : '조성희'},
                              {'text' : '고영우'},
                              {'text' : '이성일'},
                              {'text' : '김동혁'},
                              {'text' : '정은지'},
                              {'text' : '박승우'},
                              {'text' : '김민정'},
                              {'text' : '황지현'},
                              {'text' : '정재균'},
                              {'text' : '배문기'},
                              {'text' : '진현성'},
                              {'text' : '오대환'},
                              {'text' : '김민욱'},
                              {'text' : '이지영'},
                              {'text' : '오미나'},
                              {'text' : '구기현'},
                              {'text' : '전다미'},
                              {'text' : '유이현'},
                              {'text' : '박윤수'},
                              {'text' : '신현근'}
                           ]}
                        ]
                  }
               ]
          },
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
        
        $('#tree').on('changed.jstree', function (e, data) {
            var selectedNodes = data.instance.get_selected(true);
            console.log(selectedNodes);
          });
      
  });
</script>
</html>