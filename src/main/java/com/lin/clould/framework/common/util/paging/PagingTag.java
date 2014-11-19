package com.lin.clould.framework.common.util.paging;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * jstl에서 페이징 테그를 쓰기 위한 클래스
 * 
 */
public class PagingTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1508648014477918758L;
	
	/**
	 * @uml.property  name="total"
	 */
	int total; // 총 게시물 수
	/**
	 * @uml.property  name="cpage"
	 */
	int cpage; // 현재 페이지
	/**
	 * @uml.property  name="pageTotal"
	 */
	int pageTotal; // 전체 페이지 수
	/**
	 * @uml.property  name="pageSize"
	 */
	int pageSize; // 페이지 게시물 수
	/**
	 * @uml.property  name="pageGroupSize"
	 */
	int pageGroupSize; // 페이지 리스트 사이즈
	/**
	 * @uml.property  name="pageGroupStart"
	 */
	int pageGroupStart; // 페이지 리스트 그룹시작번
	/**
	 * @uml.property  name="pageGroupEnd"
	 */
	int pageGroupEnd; // 페이지 리스트 그룹 끝번
	/**
	 * @uml.property  name="link"
	 */
	String link;
	/**
	 * @uml.property  name="beginLabel"
	 */
	String beginLabel;
	/**
	 * @uml.property  name="endLabel"
	 */
	String endLabel;
	/**
	 * @uml.property  name="prevLabel"
	 */
	String prevLabel;
	/**
	 * @uml.property  name="nextLabel"
	 */
	String nextLabel;
	/**
	 * @uml.property  name="linkType"
	 */
	String linkType = "url";
	/**
	 * @uml.property  name="linkMethod"
	 */
	String linkMethod = "doPaging";

	public int doStartTag() throws JspException {
		try {
			StringBuffer sbuf = new StringBuffer();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String pageContexts = request.getContextPath();
			
			pageTotal = (int) (total - 1) / pageSize + 1;

			pageGroupStart = (int) ((cpage - 1) / pageGroupSize) * pageGroupSize + 1;
			pageGroupEnd = pageGroupStart + pageGroupSize;
			
			if (pageGroupEnd > pageTotal)
				pageGroupEnd = pageTotal + 1;

			boolean hasPreviousPage = (cpage - 1) > 0;
			boolean hasNextPage = cpage < pageTotal;
			if (cpage != 1) {
				sbuf.append(makeLinkFirst(1, "<img src='"+pageContexts+beginLabel+"' border='0'/>" ));
				sbuf.append("&nbsp;");
			}
			if (hasPreviousPage) {
				sbuf.append("&nbsp;");
				sbuf.append(makeLinkPrev(cpage - 1, "[이전]"));
				sbuf.append("&nbsp;");
			} // end if

			for (int i = pageGroupStart; i < pageGroupEnd; i++) {
				if (i == cpage) {
					if (i == (pageGroupEnd - 1)) {
						sbuf.append("<font color='red'><strong>"+i+"</strong></font>");
					} else {
						sbuf.append("<font color='red'><strong>"+i+"</strong></font>");
					}
					sbuf.append("&nbsp;");
				} else {
					if (i == (pageGroupEnd - 1)) {
						sbuf.append(makeLinkEnd(i, "" + (i) + ""));
					} else {
						sbuf.append(makeLink(i, "" + (i) + ""));
					}
					sbuf.append("&nbsp;");
				} // end if
			} // end for

			if (hasNextPage) {
				//sbuf.append(makeLinkNext(cpage+1, "<img src='"+pageContexts+nextLabel+"' border='0'/>"));
				sbuf.append("&nbsp;");
				sbuf.append(makeLinkNext(cpage+1, "[다음]"));
				sbuf.append("&nbsp;");
			} // end if
			if (cpage != pageTotal) {
				sbuf.append(makeLinkLast(pageTotal,"<img src='"+pageContexts+endLabel+"' border='0'/>"));
			}
			pageContext.getOut().print(sbuf);
		} catch (IOException ioe) {
			throw new JspTagException(
					"Error:  IOException while writing to the user");
		} catch (Exception e) {
			throw new JspTagException(
					"Error:  Exception while writing to the user" + e.getMessage());
		}
		return SKIP_BODY;
	}

	public String makeLinkSelected(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#none' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append("<strong>");
				tmp.append(label);
				tmp.append("</strong>");
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<a href=\"" + URLDecoder.decode(link, "UTF-8") + page + "\">");
				tmp.append("<strong>");
				tmp.append(label);
				tmp.append("</strong>");
				tmp.append("</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}
	
	public String makeLinkSelectedEnd(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#none' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append("<strong>");
				tmp.append(label);
				tmp.append("</strong>");
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<strong>");
				tmp.append(label);
				tmp.append("</strong>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}

	String makeLink(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#none' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append("<span>");
				tmp.append(label);
				tmp.append("</span>");
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<a href=\"" + URLDecoder.decode(link, "UTF-8") + page + "\">");
				tmp.append("<span>");
				tmp.append(label);
				tmp.append("</span>");
				tmp.append("</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}

	String makeLinkEnd(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#none' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append("<span>");
				tmp.append(label);
				tmp.append("</span>");
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<a href=\"" + URLDecoder.decode(link, "UTF-8") + page + "\">");
				tmp.append("<span>");
				tmp.append(label);
				tmp.append("</span>");
				tmp.append("</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}

	String makeLinkPrev(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append(label);
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<a href=\"" + URLDecoder.decode(link, "UTF-8") + page + "\">");
				tmp.append(label);
				tmp.append("</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}

	String makeLinkNext(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#none' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append(label);
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<a href=\"" + URLDecoder.decode(link, "UTF-8") + page + "\">");
				tmp.append(label);
				tmp.append("</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}

	String makeLinkFirst(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#none' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append(label);
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<a href=\"" + URLDecoder.decode(link, "UTF-8") + page + "\">");
				tmp.append(label);
				tmp.append("</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}

	String makeLinkLast(int page, String label) {
		StringBuffer tmp = new StringBuffer();
		try {
			if (linkType.equals("script")) {
				tmp.append("<a href='#none' onclick=\"javascript:" + linkMethod + "(" + page + ")\">");
				tmp.append(label);
				tmp.append("</a>");
			} else if (linkType.equals("url")) {
				tmp.append("<a href=\"" + URLDecoder.decode(link, "UTF-8") + page + "\">");
				tmp.append(label);
				tmp.append("</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp.toString();
	}

	/**
	 * @param value
	 * @uml.property  name="link"
	 */
	public void setLink(String value) {
		try {
			link = URLEncoder.encode(value, "UTF-8") + "&page=";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPage(String page) {
		try {
			if(page.length()>0)
				this.cpage = Integer.parseInt(page);
			else
				this.cpage = 1;
		} catch (Exception e) {
			e.printStackTrace();
			this.cpage = 1;
		}
	}

	public void setTotalCount(String totalCount) {
		try {
			this.total = Integer.parseInt(totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			this.total = 0;
		}
	}

	public void setListPerPage(String listPerPage) {
		try {
			this.pageSize = Integer.parseInt(listPerPage);
		} catch (Exception e) {
			e.printStackTrace();
			this.pageSize = 10;
		}
	}

	/**
	 * @param value
	 * @uml.property  name="prevLabel"
	 */
	public void setPrevLabel(String value) {
		prevLabel = value;
	}

	/**
	 * @param value
	 * @uml.property  name="nextLabel"
	 */
	public void setNextLabel(String value) {
		nextLabel = value;
	}

	/**
	 * @param value
	 * @uml.property  name="beginLabel"
	 */
	public void setBeginLabel(String value) {
		beginLabel = value;
	}

	/**
	 * @param value
	 * @uml.property  name="endLabel"
	 */
	public void setEndLabel(String value) {
		endLabel = value;
	}

	/**
	 * @param value
	 * @uml.property  name="pageGroupSize"
	 */
	public void setPageGroupSize(int value) {
		pageGroupSize = value;
	}

	/**
	 * @param value
	 * @uml.property  name="linkType"
	 */
	public void setLinkType(String value) {
		linkType = value;
	}

	/**
	 * @param value
	 * @uml.property  name="linkMethod"
	 */
	public void setLinkMethod(String value) {
		linkMethod = value;
	}

}
