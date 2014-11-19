<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="paging" uri="/WEB-INF/tld/paging-taglib.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" type="text/css" href="/framework/css/common.css" />
<link rel="stylesheet" type="text/css" href="/framework/css/main/main.css" />
<script src="/framework/js/common.js"></script>
<script src="/framework/js/login/login.js"></script>
<script src="/framework/js/main/main.js"></script>
<script type="text/javascript">
/********************************************************************************
 * 기      능   	:  페이징 처리
 * @param obj	:
 * @return  	:
 ********************************************************************************/
 var fn_linkPage = function(pageIndex){
	 var f = document.noticeFrm;
	 f.pageIndex.value = pageIndex;
	 f.action = "/framework/framework/main/main.do";
	 f.submit();
 };
 
 /********************************************************************************
  * 기      능   	:  파일다운로드 처리
  * @param obj	:
  * @return  	:
  ********************************************************************************/
 var file_down = function(fileName) {
 	location.href="/framework/framework/common/download.do?path_key=barcode&file_nm=" + fileName;
 }
 
</script>
</head>
<body>
<div>
<span onclick="file_down(2); return false;" onkeypress="file_down(2); return false;" style="cursor: pointer;"><a href="#">다운로드</a></span>
</div>
<div class="wrapper" style="display: none;">  
	<div class="header">
		<div class="main_title">메인</div>
		<div class="login">
			<form method="post" action="${pageContext.request.contextPath}/framework/login/login.do">
				<input type="text" size="15" name="id" id="id">
				<input type="password" size="15" name="pasword" id="password">
				<input type="submit" id="submit" value="로그인">
				<div><input type="checkbox" id="id_save"><span class="id_save">아이디 저장</span></div>
			</form>
		</div>
	</div> 
	<div class="sidebar"></div>
	<div class="content">
		<div style="background-color: #C4FDFF; width: 30%;">
			<div>
				<form name="noticeFrm" id="noticeFrm" method="get">
					<input type="hidden" name="pageIndex" />
				</form>
				<table summary="순번, 제목, 내용" >
					<caption>메인 공지사항</caption>
					<colgroup>
						<col width="10%" />
						<col width="*" />
						<col width="*" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><label>순번</label></th>
							<th scope="col"><label>제목</label></th>
							<th scope="col"><label>내용</label></th>
						</tr>
					</thead>
					<tbody>
	                    <c:forEach var="list" items="${noticeList}" varStatus="status">
							<tr id="nooticeList_${status.index}">
								<td>${list.seq}</td>
								<td>${list.title}</td>
								<td>${list.content}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty noticeList}">
							<tr>
								<td colspan="3">검색결과가 없습니다.</td>
							</tr>
						</c:if>
			        </tbody>
				</table>
		   	</div>
		   	<!-- paging -->
			<paging:page link="#" 
						 linkMethod="fn_linkPage"	
						 page="${noticePage}" 
						 totalCount="${noticeTotalCount}" 
						 listPerPage="5" 
						 beginLabel="/images/btn/btn_larrow.gif"
						 prevLabel="/images/btn/btn_llarrow.gif"
						 endLabel="/images/btn/btn_rrarrow.gif"
						 nextLabel="/images//btn/btn_rarrow.gif"
						 pageGroupSize="5" 
						 linkType="script" />
			<!-- //paging -->
			<!-- paging -->
			<!-- 	   		
			<div class="paginate" style="padding-top:30px;">
				<a href="#" class="pre_end" onclick="fn_linkPage(1); return false;" onkeypress="fn_linkPage(1); return false;"><img src="/framework/images/btn/btn_larrow.gif" alt="첫페이지" border="0"></a>
				<a href="#" class="pre" onclick="fn_linkPage(1); return false;" onkeypress="fn_linkPage(1); return false;"><img src="/framework/images//btn/btn_llarrow.gif" alt="이전페이지" border="0"></a>
				<strong>1</strong>
				<span onclick="fn_linkPage(2); return false;" onkeypress="fn_linkPage(2); return false;" class="cursor"><a href="#" onclick="return false;">2</a></span>
				<span onclick="fn_linkPage(3); return false;" onkeypress="fn_linkPage(3); return false;" class="cursor"><a href="#" onclick="return false;">3</a></span>
				<span onclick="fn_linkPage(4); return false;" onkeypress="fn_linkPage(4); return false;" class="cursor"><a href="#" onclick="return false;">4</a></span>
				<span onclick="fn_linkPage(5); return false;" onkeypress="fn_linkPage(5); return false;" class="cursor"><a href="#" onclick="return false;">5</a></span>
				<span onclick="fn_linkPage(6); return false;" onkeypress="fn_linkPage(6); return false;" class="cursor"><a href="#" onclick="return false;">6</a></span>
				<span onclick="fn_linkPage(7); return false;" onkeypress="fn_linkPage(7); return false;" class="cursor"><a href="#" onclick="return false;">7</a></span>
				<span onclick="fn_linkPage(8); return false;" onkeypress="fn_linkPage(8); return false;" class="cursor"><a href="#" onclick="return false;">8</a></span>
				<span onclick="fn_linkPage(9); return false;" onkeypress="fn_linkPage(9); return false;" class="cursor"><a href="#" onclick="return false;">9</a></span>
				<span onclick="fn_linkPage(10); return false;" onkeypress="fn_linkPage(10); return false;" class="cursor"><a href="#" onclick="return false;">10</a></span>
				<a href="#" class="next" onclick="fn_linkPage(11); return false;" onkeypress="fn_linkPage(11); return false;"><img src="/framework/images//btn/btn_rrarrow.gif" alt="다음페이지" border="0"></a>
				<a href="#" class="next_end" onclick="fn_linkPage(314); return false;" onkeypress="fn_linkPage(314); return false;"><img src="/framework/images//btn/btn_rarrow.gif" alt="끝페이지" border="0"></a>
		   	</div> 
		   	-->
			<!-- //paging -->
		</div>
	</div>
	<div class="footer"></div>
</div>

</body>
</html>