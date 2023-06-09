<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script> -->
<section class="content">
	<div class="container-fluid">
		<div class="row" style="justify-content:center;">
			<div class="col-md-6">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">공지사항 등록</h3>
					</div>
						<form id="frm" name="frm" action="/notice/generalFormPost" method="post" enctype="multipart/form-data">
							<div class="card-body">
								<div class="form-group">
									<label for="boTitle">제목</label>
									<input type="text" class="form-control" id="boTitle" name="boTitle" placeholder="제목넣으쇼" required/>
								</div>
								<div class="form-group">
									<label for="boContent">내용</label>
									<textarea id="boContent" name="boContent" rows="3" cols="100" placeholder="내용없으면 안뎌"></textarea>
								</div>
								<div class="form-group">
									<label for="boWriter">작성자</label>
									<input type="text" class="form-control" id="boWriter" name="boWriter" placeholder="작성자 뉘요" required/>
								</div>
								<div class="form-group">
									<label for="exampleInputFile">File input</label>
									<div class="input-group">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="boFile" name="boFile" multiple>
										<label class="custom-file-label" for="exampleInputFile">Choose file</label>
									</div>
									</div>
									</div>
							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-primary">등록</button>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	CKEDITOR.replace("boContent");

</script>