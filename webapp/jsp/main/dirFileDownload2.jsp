<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%
	
	String filePath = "";
	String fileNm   = request.getParameter("file_nm");
	String fileRealName = fileNm;
	if(fileRealName.length() > 18){
		fileRealName = fileNm.substring(0, fileNm.length()-17);
	}
	String pathKey  = request.getParameter("path_key");
	pathKey = pathKey.replaceAll("\\.","xx").replaceAll("/","xx").replaceAll("\\\\","xx");
	fileNm = fileNm.replaceAll("\\.\\.","xx").replaceAll("/","xx").replaceAll("\\\\","xx");
	
	filePath = "/home/lin/upload_data/" + pathKey + "/" + fileNm; 
	File file = new File(filePath);
	
	if (file.exists()) {
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileRealName, "UTF-8").replaceAll("\\+", " ").replaceAll("%28","(").replaceAll("%29",")")); 
		
		System.out.println("filePath => " + filePath);
	
		int fileSize = (int)file.length();  //파일 크기
		response.setHeader("Content-Length", ""+fileSize);  
	
		//파일 스트림을 저장하기 위한 바이트 배열 생성.  
		byte bytestream[] = new byte[2048]; 
	
		//파일 객체를 스트림으로 불러온다.
		BufferedInputStream Bfin = new BufferedInputStream(new FileInputStream(file)); 
		BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream()); 
	
		int read = 0; 
		while ((read = Bfin.read(bytestream)) != -1){ 
			outs.write(bytestream,0,read);
		} 
	
		outs.close(); 
		Bfin.close();
	} else {
		out.println("<script type='text/javascript'>alert('파일이 존재하지 않습니다');history.back();</script>");
	}

%>