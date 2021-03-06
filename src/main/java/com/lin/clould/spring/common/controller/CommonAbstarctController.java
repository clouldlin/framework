package com.lin.clould.spring.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lin.clould.spring.common.vo.CommonVO;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
*
* @Class Name :CoAbstarctController.java
* @Description :CoAbstarctController.java
* @Modification Information
* @
* @  수정일            수정자              수정내용
* @ ---------   ---------   -------------------------------
* @ 2013.11.14	정용진		최초생성
* @author CMS
* @since 2013. 11. 14
* @version 1.0
* @see
*
* Copyright (C) by IIT All right reserved.
 */
public class CommonAbstarctController {

	final static Logger logger = LoggerFactory.getLogger(CommonAbstarctController.class);

	/**
	 * PaginationInfo 에 페이지에 대한 정보를 셋팅해 준다.
	 * pageRecourdCount 에 대한 셋팅
	 * @see
	 */
	protected PaginationInfo setPaginationInfo(CommonVO searchVO, Map<String,String> commandMap) {

		/** pageing setting */
			PaginationInfo pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(searchVO.getPageIndex());			//현재 페이지 번호
			pageInfo.setRecordCountPerPage(searchVO.getPageUnit());		//한 페이지에 게시되는 게시물 건수
			pageInfo.setPageSize(searchVO.getPageSize());				//페이징 리스트의 사이즈

			/** pageing setting */
			int firstRecordIndex	 = pageInfo.getFirstRecordIndex();
			int recordCountPerPage 	 = pageInfo.getRecordCountPerPage();
			commandMap.put("firstIndex", firstRecordIndex+"" );
			commandMap.put("lastIndex", (recordCountPerPage+firstRecordIndex)+"" );

			return pageInfo;
	}
}
