package com.digital.util;

public class Pagination {
	// 分頁所需的屬性， 分別是當前頁，總記錄數，頁面大小，總頁數����
	private int currentPage;
	private int countTotal;
	private int pageSize = 20;
	private int pageTotal;

	private String paginationNavigate;
	private String paginationNavigateOfPost;
	private String paginationNavigateOfPostEn;
	private String paginationNavigateOfPostPo;
	private String paginationNavigateOfPostPoOnly;// 葡文 xupan 只有1、2 下一頁
	private String paginationNavigateOfPostPoOnly2;// 葡文 xupan 只有1、2 下一頁
	private String paginationNavigateOfPostPoOnly3;// 葡文 xupan 只有1、2 下一頁
	private String paginationNavigateOfPostOnly;// xupan 只有1、2 下一頁
	private String paginationNavigateOfPostOnly2;// xupan 只有1、2 下一頁
	private String paginationNavigateOfPostOnly3;// ljl只有1、2 下一頁
	private String paginationNavigateOfPostOnly4;// ljl只有1、2 下一頁
	private String paginationNavigateOfPostOnly5;// 只有1、2 下一頁
	private String paginationNavigateOfPostOnly6;// 只有1、2 下一頁

	private String paginationNavigate2;
	private String paginationNavigateOfPost2;

	private String paginationNavigate3; // 中南區
	private String paginationNavigateOfPost3; // 中南區

	private String paginationNavigateHero;// 群英匯
	private String paginationNavigateOfPostHero;

	public Pagination() {
	};

	public Pagination(int currentPage, int countTotal, int pageSize, String url) {
		this.countTotal = countTotal;
		this.currentPage = currentPage;
		this.pageSize = pageSize;

		setPages(currentPage, pageSize);
		setPaginationNavigate(this.currentPage, this.countTotal, pageSize,
				pageTotal, url);

		setPaginationNavigateOfPost(this.currentPage, this.countTotal,
				pageSize, pageTotal, url);
	}

	public Pagination(int currentPage, int countTotal, int pageSize,
			String url, String style) {
		this.countTotal = countTotal;
		this.currentPage = currentPage;
		this.pageSize = pageSize;

		setPages(currentPage, pageSize);
		if ("admin".equals(style)) {
			setPaginationNavigate(this.currentPage, this.countTotal, pageSize,
					pageTotal, url);
			setPaginationNavigateOfPost(this.currentPage, this.countTotal,
					pageSize, pageTotal, url);
		} else {
			setPaginationNavigateWWW(this.currentPage, this.countTotal,
					pageSize, pageTotal, url);
			setPaginationNavigateOfPost(this.currentPage, this.countTotal,
					pageSize, pageTotal, url);
		}

	}

	public Pagination(int currentPage, int countTotal, int pageSize,
			String url, int pageNumber, String type) {
		this.countTotal = countTotal;
		this.currentPage = currentPage;
		this.pageSize = pageSize;

		setPages(currentPage, pageSize);
		if ("get".equalsIgnoreCase(type)) {
			setPaginationNavigate(this.currentPage, this.countTotal, pageSize,
					pageTotal, url, pageNumber);
			setPaginationNavigate2(this.currentPage, this.countTotal, pageSize,
					pageTotal, url, pageNumber);
			setPaginationNavigate3(this.currentPage, this.countTotal, pageSize,
					pageTotal, url, pageNumber);
		} else if ("post".equalsIgnoreCase(type)) {
			setPaginationNavigateOfPost(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostOnly(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);// xupan 只有1、2 下一頁
			setPaginationNavigateOfPostOnly2(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);// xupan 只有1、2 下一頁
			setPaginationNavigateOfPostOnly3(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostOnly4(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostOnly5(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostOnly6(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostEn(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostPo(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostPoOnly(this.currentPage,
					this.countTotal, pageSize, pageTotal, url, pageNumber);// xupan
																			// 只有1、2
																			// 下一頁
																			// 葡文
			setPaginationNavigateOfPostPoOnly2(this.currentPage,
					this.countTotal, pageSize, pageTotal, url, pageNumber);// xupan
																			// 只有1、2
																			// 下一頁
																			// 葡文
			setPaginationNavigateOfPostPoOnly3(this.currentPage,
					this.countTotal, pageSize, pageTotal, url, pageNumber);// xupan
																			// 只有1、2
																			// 下一頁
																			// 葡文
			setPaginationNavigateOfPost2(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPost3(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
			setPaginationNavigateOfPostHero(this.currentPage, this.countTotal,
					pageSize, pageTotal, url, pageNumber);
		}

	}

	public Pagination(int currentPage, int countTotal, String url) {
		this.countTotal = countTotal;
		this.currentPage = currentPage;

		setPages(currentPage, pageSize);
		setPaginationNavigate(this.currentPage, this.countTotal, pageSize,
				pageTotal, url);
		setPaginationNavigateOfPost(this.currentPage, this.countTotal,
				pageSize, pageTotal, url);
	}

	public Pagination(int currentPage, int countTotal, String url, String style) {
		this.countTotal = countTotal;
		this.currentPage = currentPage;

		setPages(currentPage, pageSize);
		if ("admin".equals(style)) {
			setPaginationNavigate(this.currentPage, this.countTotal, pageSize,
					pageTotal, url);
			setPaginationNavigateOfPost(this.currentPage, this.countTotal,
					pageSize, pageTotal, url);
		} else {
			setPaginationNavigateWWW(this.currentPage, this.countTotal,
					pageSize, pageTotal, url);
			setPaginationNavigateOfPost(this.currentPage, this.countTotal,
					pageSize, pageTotal, url);
		}
	}

	/**
	 * 根據傳入得參數，計算出分頁導航欄的相關數據
	 * 
	 * @param currentPage
	 *            當前頁數
	 * @param pageSize
	 *            頁面數據數量
	 */
	private void setPages(int currentPage, int pageSize) {
		if (currentPage <= 0) {
			setCurrentPage(1);
		}
		setPageTotal(countTotal / pageSize
				+ (countTotal % pageSize > 0 ? 1 : 0));
		if (currentPage > pageTotal && pageTotal != 0) {
			setCurrentPage(pageTotal);
		}
	}

	/**
	 * 根據相關參數。得出分頁導航欄 直接分頁。沒有查詢條件。用ＧＥＴ的方法提交。 模版用VELOCITY,所以使用方法是
	 * ${pagination.paginationNavigate} 模版用FreeMarker,所以使用方法是
	 * ${pagination.paginationNavigate} 如果用struts2 jsp顯示，那麼輸出就是 <s:property
	 * value="pageBar"/> (struts2標籤輸出)
	 * 
	 * @param currentPage
	 *            當前頁數
	 * @param countTotal
	 *            記錄總數
	 * @param pageSize
	 *            頁面多小
	 * @param pageTotal
	 *            頁面總頁數
	 */
	public void setPaginationNavigate(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='total'>Total: " + countTotal
				+ ". </span> ");
		pageStr.append("<span class='count'>Pages: " + currentPage + " / "
				+ pageTotal + "</span>");
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁����'>&#171;</span>");
			pageStr.append("<span title='前一頁'>&#139;</span>");
		} else {
			int page = currentPage - 1;
			pageStr.append("<span title='首頁����'><a href='" + url
					+ "?currentPage=1'>&#171;</a></span>");
			pageStr.append("<span title='�e�前一頁��'><a href='" + url
					+ "?currentPage=" + page + "'>&#139;</a></span>");

		}
		int startPage = 1;
		if (currentPage % 10 == 0) {
			startPage = currentPage - 9;
		} else {
			startPage = currentPage - currentPage % 10 + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > 10)
			pageStr.append("<span title='前十頁'><a href='" + url
					+ "?currentPage=" + pageTemp + "'>...</a></span>");

		for (int i = startPage; i < startPage + 10; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr
						.append("<span title='第��" + i + "��頁'>" + i
								+ "</span>");
			} else {
				pageStr.append("<span title='第��" + i + "頁��'><a href='" + url
						+ "?currentPage=" + i + "'>" + i + "</a></span>");
			}
		}
		if (this.pageTotal >= startPage + 10)
			pageStr.append("<span title='後10頁'><a href='" + url
					+ "?currentPage=" + (startPage + 10) + "'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁��'>&#155;</span>");
			pageStr.append("<span title='最後一頁��'>&#187;</span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁��'><a href='" + url
					+ "?currentPage=" + page + "'>&#155;</a></span>");
			pageStr.append("<span title='�̫�最後一頁��'><a href='" + url
					+ "?currentPage=" + pageTotal + "'>&#187;</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigate = pageStr.toString();
	}

	/**
	 * 根據相關參數。得出分頁導航欄 有查詢條件。用POST的方法提交。 相應的頁面加上以下JavaScript doGoPager(page){
	 * $("#currentPage").val(page); $("#xxxxForm").submit(); }
	 * 
	 * 模版用VELOCITY,所以使用方法是 ${pagination.paginationNavigate}
	 * 模版用FreeMarker,所以使用方法是 ${pagination.paginationNavigate} 如果用struts2
	 * jsp顯示，那麼輸出就是 <s:property value="pageBar"/> (struts2標籤輸出)
	 * 
	 * @param currentPage
	 *            當前頁數
	 * @param countTotal
	 *            記錄總數
	 * @param pageSize
	 *            頁面多小
	 * @param pageTotal
	 *            頁面總頁數
	 */
	public void setPaginationNavigateOfPost(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='total'>Total: " + countTotal
				+ ". </span> ");
		pageStr.append("<span class='count'>Pages: " + currentPage + " / "
				+ pageTotal + "</span>");
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'>&#171;</span>");
			pageStr.append("<span title='前一頁'>&#139;</span>");
		} else {
			int page = currentPage - 1;
			pageStr
					.append("<span title='首頁'><a href='javascript:doGoPage(1)'>&#171;</a></span>");
			pageStr.append("<span title='前一頁'><a href='javascript:doGoPage("
					+ page + ")'>&#139;</a></span>");

		}
		int startPage = 1;
		if (currentPage % 10 == 0) {
			startPage = currentPage - 9;
		} else {
			startPage = currentPage - currentPage % 10 + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > 10)
			pageStr.append("<span title='前十頁'><a href='javascript:doGoPage("
					+ pageTemp + ")'>...</a></span>");

		for (int i = startPage; i < startPage + 10; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i + "頁'>" + i + "</span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		if (this.pageTotal >= startPage + 10)
			pageStr.append("<span title='後10頁'><a href='javascript:doGoPage("
					+ (startPage + 10) + ")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'>&#155;</span>");
			pageStr.append("<span title='最後一頁'>&#187;</span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href=\"javascript:doGoPage('"
					+ page + "')\";>&#155;</a></span>");
			pageStr.append("<span title='最後一頁'><a href=\"javascript:doGoPage('"
					+ pageTotal + "')\";'>&#187;</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPost = pageStr.toString();
	}

	/**
	 * 根據相關參數。得出分頁導航欄 可調節頁數個數。已更正該方法亂碼...... 有查詢條件。用POST的方法提交。
	 * 相應的頁面加上以下JavaScript doGoPager(page){ $("#currentPage").val(page);
	 * $("#xxxxForm").submit(); }
	 * 
	 * 模版用VELOCITY,所以使用方法是 ${pagination.paginationNavigate}
	 * 模版用FreeMarker,所以使用方法是 ${pagination.paginationNavigate} 如果用struts2
	 * jsp顯示，那麼輸出就是 <s:property value="pageBar"/> (struts2標籤輸出)
	 * 
	 * @param currentPage
	 *            當前頁數
	 * @param countTotal
	 *            記錄總數
	 * @param pageSize
	 *            頁面多小
	 * @param pageTotal
	 *            頁面總頁數
	 * @param pagerNumber
	 *            分頁顯示頁數個數
	 */
	public void setPaginationNavigateOfPost(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'><a>首頁</a></span>");
			pageStr.append("<span title='前一頁'><a>前一頁</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr
					.append("<span title='首頁'><a href='javascript:doGoPage(1)'>首頁</a></span>");
			pageStr.append("<span title='前一頁'><a href='javascript:doGoPage("
					+ page + ")'>前一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			startPage = currentPage - currentPage % pageNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pageNumber)
			pageStr.append("<span title='前" + pageNumber
					+ "頁'><a href='javascript:doGoPage(" + pageTemp
					+ ")'>...</a></span>");

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		if (this.pageTotal >= startPage + pageNumber)
			pageStr.append("<span title='" + pageNumber
					+ "'><a href='javascript:doGoPage("
					+ (startPage + pageNumber) + ")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a>下一頁</a></span>");
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage("
					+ page + ")'>下一頁</a></span>");
			pageStr.append("<span title='最後一頁'><a href='javascript:doGoPage("
					+ pageTotal + ")'>最後一頁</a></span>");
		}
		pageStr
				.append("&nbsp;<span class='ui-input-button'><input type='button' value='轉到' onclick='javascript:doPage()' /></span>");
		pageStr
				.append("&nbsp;第<input id='currentPage2' name='currentPage2'  value='"
						+ currentPage
						+ "' size='2' type='text' style='border: #96A6C5 solid 1px;text-align:center;'>頁");
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPost = pageStr.toString();
	}

	// @xupan 只有1、2 下一頁
	public void setPaginationNavigateOfPostOnly(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage("
					+ page + ")'>下一頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostOnly = pageStr.toString();
	}

	// @xupan 只有1、2 下一頁
	public void setPaginationNavigateOfPostOnly2(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage2(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage2("
					+ page + ")'>下一頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostOnly2 = pageStr.toString();
	}

	// ljl 只有1、2 下一頁
	public void setPaginationNavigateOfPostOnly3(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage3(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage3("
					+ page + ")'>下一頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostOnly3 = pageStr.toString();
	}

	// @ljl 只有1、2 下一頁
	public void setPaginationNavigateOfPostOnly4(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage4(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage4("
					+ page + ")'>下一頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostOnly4 = pageStr.toString();
	}

	// @xupan 只有1、2 下一頁
	public void setPaginationNavigateOfPostOnly5(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage5(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage5("
					+ page + ")'>下一頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostOnly5 = pageStr.toString();
	}

	// @ljl 只有1、2 下一頁
	public void setPaginationNavigateOfPostOnly6(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage2(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage6("
					+ page + ")'>下一頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostOnly6 = pageStr.toString();
	}

	// 英文
	public void setPaginationNavigateOfPostEn(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='Home page'><a>Home page</a></span>");
			pageStr.append("<span title='prev page'><a>prev page</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr
					.append("<span title='Home page'><a href='javascript:doGoPage(1)'>Home page</a></span>");
			pageStr
					.append("<span title='Prev page'><a href='javascript:doGoPage("
							+ page + ")'>Prev page</a></span>");

		}
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			startPage = currentPage - currentPage % pageNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pageNumber)
			pageStr.append("<span title='Prev" + pageNumber
					+ "page'><a href='javascript:doGoPage(" + pageTemp
					+ ")'>...</a></span>");

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='Order" + i
						+ "page'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='Order" + i
						+ "page'><a href='javascript:doGoPage(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		if (this.pageTotal >= startPage + pageNumber)
			pageStr.append("<span title='" + pageNumber
					+ "'><a href='javascript:doGoPage("
					+ (startPage + pageNumber) + ")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='Next page'><a>Next page</a></span>");
			pageStr.append("<span title='Last page'><a>Last page</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr
					.append("<span title='Next page'><a href='javascript:doGoPage("
							+ page + ")'>Next page</a></span>");
			pageStr
					.append("<span title='Last page'><a href='javascript:doGoPage("
							+ pageTotal + ")'>Last page</a></span>");
		}
		pageStr
				.append("&nbsp;<span class='ui-input-button'><input type='button' value='Go' onclick='javascript:goPage()' /></span>");
		pageStr
				.append("&nbsp;Order<input id='pageNum' value='"
						+ currentPage
						+ "' size='2' type='text' style='border: #96A6C5 solid 1px;'>page");
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostEn = pageStr.toString();
	}

	// 葡文
	public void setPaginationNavigateOfPostPo(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr
					.append("<span title='<< Anterior'><a><< Anterior</a></span>");
			// pageStr.append("<span title='prev page'><a>prev
			// page</a></span>");
		} else {
			int page = currentPage - 1;
			// pageStr.append("<span title='<< Anterior'><a
			// href='javascript:doGoPage(1)'><< Anterior</a></span>");
			pageStr
					.append("<span title='<< Anterior'><a href='javascript:doGoPage("
							+ page + ")'><< Anterior</a></span>");
		}
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			startPage = currentPage - currentPage % pageNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pageNumber)
			pageStr.append("<span title='Prev" + pageNumber
					+ "page'><a href='javascript:doGoPage(" + pageTemp
					+ ")'>...</a></span>");

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='Order" + i
						+ "page'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='Order" + i
						+ "page'><a href='javascript:doGoPage(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		if (this.pageTotal >= startPage + pageNumber)
			pageStr.append("<span title='" + pageNumber
					+ "'><a href='javascript:doGoPage("
					+ (startPage + pageNumber) + ")'>...</a></span>");

		if (currentPage >= pageTotal) {
			// pageStr.append("<span title='Next page'><a>Next
			// page</a></span>");
			pageStr
					.append("<span title='Seguinte >>'><a>Seguinte >></a></span>");
		} else {
			int page = currentPage + 1;
			// pageStr.append("<span title='Next page'><a
			// href='javascript:doGoPage("+page+")'>Next page</a></span>");
			pageStr
					.append("<span title='Seguinte >>'><a href='javascript:doGoPage("
							+ page + ")'>Seguinte >></a></span>");
		}
		// pageStr.append("&nbsp;<span class='ui-input-button'><input
		// type='button' value='轉到' onclick='javascript:doPage()' /></span>");
		// pageStr.append("&nbsp;第<input id='currentPage2' name='currentPage2'
		// value='"+currentPage+"' size='2' type='text' style='border: #96A6C5
		// solid 1px;'>頁");

		pageStr
				.append("&nbsp;<span class='ui-input-button'><input type='button' value='Go' onclick='javascript:doPage()' /></span>");
		pageStr
				.append("&nbsp;Order<input id='currentPage2' name='currentPage2' value='"
						+ currentPage
						+ "' size='2' type='text' style='border: #96A6C5 solid 1px;text-align:center;'>page");
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostPo = pageStr.toString();
	}

	// 葡文 只有1.2 下一页 @xupan
	public void setPaginationNavigateOfPostPoOnly(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr
						.append("<span title='﻿Subseção"
								+ i
								+ "Tampadoras de garrafa'><a class='ui-selected-pagination'>"
								+ i + "</a></span>");
			} else {
				pageStr
						.append("<span title='﻿Subseção"
								+ i
								+ "Tampadoras de garrafa'><a href='javascript:doGoPage5("
								+ i + ")'>" + i + "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr
					.append("<span title='﻿A última página'><a>última</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr
					.append("<span title='﻿Próxima página'><a href='javascript:doGoPage("
							+ page + ")'>Seguinte</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostPoOnly = pageStr.toString();
	}

	// 葡文 只有1.2 下一页 @xupan
	public void setPaginationNavigateOfPostPoOnly2(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr
						.append("<span title='﻿Subseção"
								+ i
								+ "Tampadoras de garrafa'><a class='ui-selected-pagination'>"
								+ i + "</a></span>");
			} else {
				pageStr
						.append("<span title='﻿Subseção"
								+ i
								+ "Tampadoras de garrafa'><a href='javascript:doGoPage("
								+ i + ")'>" + i + "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr
					.append("<span title='﻿A última página'><a>última</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr
					.append("<span title='﻿Próxima página'><a href='javascript:doGoPage("
							+ page + ")'>Seguinte</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostPoOnly2 = pageStr.toString();
	}

	// 葡文 只有1.2 下一页 @xupan
	public void setPaginationNavigateOfPostPoOnly3(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			// startPage = currentPage - currentPage % pageNumber + 1;
			startPage = currentPage - pageNumber + 1;
		}
		if (startPage == 0) {
			startPage = 1;
		}

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr
						.append("<span title='﻿Subseção"
								+ i
								+ "Tampadoras de garrafa'><a class='ui-selected-pagination'>"
								+ i + "</a></span>");
			} else {
				pageStr
						.append("<span title='﻿Subseção"
								+ i
								+ "Tampadoras de garrafa'><a href='javascript:doGoPage2("
								+ i + ")'>" + i + "</a></span>");
			}
		}
		// if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span
		// title='"+pageNumber+"'><a
		// href='javascript:doGoPage2("+(startPage+pageNumber)+")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr
					.append("<span title='﻿A última página'><a>última</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr
					.append("<span title='﻿Próxima página'><a href='javascript:doGoPage2("
							+ page + ")'>Seguinte</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPostPoOnly3 = pageStr.toString();
	}

	/**
	 * 前臺分頁導航欄 根據相關參數。得出分頁導航欄 直接分頁。沒有查詢條件。用ＧＥＴ的方法提交。 模版用VELOCITY,所以使用方法是
	 * ${pagination.paginationNavigate} 模版用FreeMarker,所以使用方法是
	 * ${pagination.paginationNavigate} 如果用struts2 jsp顯示，那麼輸出就是 <s:property
	 * value="pageBar"/> (struts2標籤輸出)
	 * 
	 * @param currentPage
	 *            當前頁數
	 * @param countTotal
	 *            記錄總數
	 * @param pageSize
	 *            頁面多小
	 * @param pageTotal
	 *            頁面總頁數
	 */
	public void setPaginationNavigateWWW(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='total'>當前頁: " + currentPage + "/"
				+ pageTotal + ",共有" + countTotal + "條記錄. </span> ");
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'><a>首頁</a></span>");
			pageStr.append("<span title='上一頁'><a>上一頁</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr.append("<span title='首頁'><a href='" + url
					+ "?currentPage=1'>首頁</a></span>");
			pageStr.append("<span title='上一頁'><a href='" + url
					+ "?currentPage=" + page + "'>上一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % 10 == 0) {
			startPage = currentPage - 9;
		} else {
			startPage = currentPage - currentPage % 10 + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > 10)
			pageStr.append("<span title='前十頁'><a href='" + url
					+ "?currentPage=" + pageTemp + "'>...</a></span>");

		for (int i = startPage; i < startPage + 10; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁' class='curnumber'> <a class='selected'>" + i
						+ "</a> </span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁' class='othernumber'> <a href='" + url
						+ "?currentPage=" + i + "'>" + i + "</a> </span>");
			}
		}
		if (this.pageTotal >= startPage + 10)
			pageStr.append("<span title='後10頁'><a href='" + url
					+ "?currentPage=" + (startPage + 10) + "'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a>下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a>尾頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='" + url
					+ "?currentPage=" + page + "'>下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a href='" + url + "?currentPage="
					+ pageTotal + "'>尾頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigate = pageStr.toString();
	}

	/**
	 * 前臺分頁導航欄 簡單版本。 根據相關參數。得出分頁導航欄 直接分頁。沒有查詢條件。用ＧＥＴ的方法提交。 模版用VELOCITY,所以使用方法是
	 * ${pagination.paginationNavigate} 模版用FreeMarker,所以使用方法是
	 * ${pagination.paginationNavigate} 如果用struts2 jsp顯示，那麼輸出就是 <s:property
	 * value="pageBar"/> (struts2標籤輸出)
	 * 
	 * @param currentPage
	 *            當前頁數
	 * @param countTotal
	 *            記錄總數
	 * @param pageSize
	 *            頁面多小
	 * @param pageTotal
	 *            頁面總頁數
	 */
	public void setPaginationNavigateSimple(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {

			pageStr.append("<span title='上一頁'><a>上一頁</a></span>");
		} else {
			int page = currentPage - 1;

			pageStr.append("<span title='上一頁'><a href='" + url
					+ "?currentPage=" + page + "'>上一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % 3 == 0) {
			startPage = currentPage - 2;
		} else {
			startPage = currentPage - currentPage % 3 + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > 3)
			pageStr.append("<span title='前三頁'><a href='" + url
					+ "?currentPage=" + pageTemp + "'>...</a></span>");

		for (int i = startPage; i < startPage + 3; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁' > <a class='selected'>" + i + "</a> </span>");
			} else {
				pageStr.append("<span title='第" + i + "頁' > <a href='" + url
						+ "?currentPage=" + i + "'>" + i + "</a> </span>");
			}
		}
		if (this.pageTotal >= startPage + 3)
			pageStr.append("<span title='後三頁'><a href='" + url
					+ "?currentPage=" + (startPage + 3) + "'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a>下一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='" + url
					+ "?currentPage=" + page + "'>下一頁</a></span>");
		}
		pageStr.append("</span><br/>");
		this.paginationNavigate = pageStr.toString();
	}

	/**
	 * 前臺分頁導航欄 可調節頁數個數。 根據相關參數。得出分頁導航欄 直接分頁。沒有查詢條件。用ＧＥＴ的方法提交。
	 * 模版用VELOCITY,所以使用方法是 ${pagination.paginationNavigate}
	 * 模版用FreeMarker,所以使用方法是 ${pagination.paginationNavigate} 如果用struts2
	 * jsp顯示，那麼輸出就是 <s:property value="pageBar"/> (struts2標籤輸出)
	 * 
	 * @param currentPage
	 *            當前頁數
	 * @param countTotal
	 *            記錄總數
	 * @param pageSize
	 *            頁面多小
	 * @param pageTotal
	 *            頁面總頁數
	 * @param pagerNumber
	 *            分頁顯示頁數個數 可調節頁數個數。
	 */
	public void setPaginationNavigate(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pagerNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='total'>當前頁: " + currentPage + "/"
				+ pageTotal + ",共有" + countTotal + "條記錄. </span> ");
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'><a>首頁</a></span>");
			pageStr.append("<span title='上一頁'><a>上一頁</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr.append("<span title='首頁'><a href='" + url
					+ "?currentPage=1'>首頁</a></span>");
			pageStr.append("<span title='上一頁'><a href='" + url
					+ "?currentPage=" + page + "'>上一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % pagerNumber == 0) {
			startPage = currentPage - pagerNumber + 1;
		} else {
			startPage = currentPage - currentPage % pagerNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pagerNumber)
			pageStr.append("<span title='前" + pagerNumber + "頁'><a href='"
					+ url + "?currentPage=" + pageTemp + "'>...</a></span>");

		for (int i = startPage; i < startPage + pagerNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁' > <a class='selected'>" + i + "</a> </span>");
			} else {
				pageStr.append("<span title='第" + i + "頁' > <a href='" + url
						+ "?currentPage=" + i + "'>" + i + "</a> </span>");
			}
		}
		if (this.pageTotal >= startPage + pagerNumber)
			pageStr.append("<span title='後" + pagerNumber + "頁'><a href='"
					+ url + "?currentPage=" + (startPage + pagerNumber)
					+ "'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a>下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a>尾頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='" + url
					+ "?currentPage=" + page + "'>下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a href='" + url + "?currentPage="
					+ pageTotal + "'>尾頁</a></span>");
		}
		pageStr
				.append("&nbsp;<span class='ui-input-button'><input type='button' value='轉到' onclick='javascript:goPage()' /></span>");
		pageStr
				.append("&nbsp;第<input id='pageNum' value='"
						+ currentPage
						+ "' size='2' type='text' style='border: #96A6C5 solid 1px;text-align:center;'>頁");
		pageStr.append("</span><br/>");
		this.paginationNavigate = pageStr.toString();
	}

	public void setPaginationNavigate2(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pagerNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='total'>當前頁: " + currentPage + "/"
				+ pageTotal + ",共有" + countTotal + "條記錄. </span> ");
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'><a>首頁</a></span>");
			pageStr.append("<span title='上一頁'><a>上一頁</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr.append("<span title='首頁'><a href='" + url
					+ "?currentPage=1'>首頁</a></span>");
			pageStr.append("<span title='上一頁'><a href='" + url
					+ "?currentPage=" + page + "'>上一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % pagerNumber == 0) {
			startPage = currentPage - pagerNumber + 1;
		} else {
			startPage = currentPage - currentPage % pagerNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pagerNumber)
			pageStr.append("<span title='前" + pagerNumber + "頁'><a href='"
					+ url + "?currentPage=" + pageTemp + "'>...</a></span>");

		for (int i = startPage; i < startPage + pagerNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁' > <a class='selected'>" + i + "</a> </span>");
			} else {
				pageStr.append("<span title='第" + i + "頁' > <a href='" + url
						+ "?currentPage=" + i + "'>" + i + "</a> </span>");
			}
		}
		if (this.pageTotal >= startPage + pagerNumber)
			pageStr.append("<span title='後" + pagerNumber + "頁'><a href='"
					+ url + "?currentPage=" + (startPage + pagerNumber)
					+ "'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a>下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a>尾頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='" + url
					+ "?currentPage=" + page + "'>下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a href='" + url + "?currentPage="
					+ pageTotal + "'>尾頁</a></span>");
		}
		pageStr
				.append("&nbsp;<input type='button' onclick='javascript:goPage2()' value='轉到' />");
		pageStr
				.append("&nbsp;第<input id='pageNum2' value='"
						+ currentPage
						+ "' size='2' type='text' style='border: #96A6C5 solid 1px;text-align:center;'>頁");
		pageStr.append("</span><br/>");
		this.paginationNavigate2 = pageStr.toString();
	}

	public void setPaginationNavigate3(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pagerNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='total'>當前頁: " + currentPage + "/"
				+ pageTotal + ",共有" + countTotal + "條記錄. </span> ");
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'><a class='pie'>首頁</a></span>");
			pageStr.append("<span title='上一頁'><a class='pie'>上一頁</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr.append("<span title='首頁'><a href='" + url
					+ "?currentPage=1' class='pie' >首頁</a></span>");
			pageStr.append("<span title='上一頁'><a href='" + url
					+ "?currentPage=" + page + "' class='pie' >上一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % pagerNumber == 0) {
			startPage = currentPage - pagerNumber + 1;
		} else {
			startPage = currentPage - currentPage % pagerNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pagerNumber)
			pageStr.append("<span title='前" + pagerNumber + "頁'><a href='"
					+ url + "?currentPage=" + pageTemp
					+ "' class='pie' >...</a></span>");

		for (int i = startPage; i < startPage + pagerNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁' > <a class='ui-selected-pagination pie'>" + i
						+ "</a> </span>");
			} else {
				pageStr.append("<span title='第" + i + "頁' > <a href='" + url
						+ "?currentPage=" + i + "' class='pie' >" + i
						+ "</a> </span>");
			}
		}
		if (this.pageTotal >= startPage + pagerNumber)
			pageStr.append("<span title='後" + pagerNumber + "頁'><a href='"
					+ url + "?currentPage=" + (startPage + pagerNumber)
					+ "' class='pie' >...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a class='pie' >下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a class='pie' >尾頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='" + url
					+ "?currentPage=" + page + "' class='pie' >下一頁</a></span>");
			pageStr.append("<span title='尾頁'><a href='" + url + "?currentPage="
					+ pageTotal + "' class='pie' >尾頁</a></span>");
		}

		pageStr.append("</span><br/>");
		this.paginationNavigate3 = pageStr.toString();
	}

	public void setPaginationNavigateOfPost2(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'><a>首頁</a></span>");
			pageStr.append("<span title='前一頁'><a>前一頁</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr
					.append("<span title='首頁'><a href='javascript:doGoPage(1)'>首頁</a></span>");
			pageStr.append("<span title='前一頁'><a href='javascript:doGoPage("
					+ page + ")'>前一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			startPage = currentPage - currentPage % pageNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pageNumber)
			pageStr.append("<span title='前" + pageNumber
					+ "頁'><a href='javascript:doGoPage(" + pageTemp
					+ ")'>...</a></span>");

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a href='javascript:doGoPage(" + i + ")'>" + i
						+ "</a></span>");
			}
		}
		if (this.pageTotal >= startPage + pageNumber)
			pageStr.append("<span title='" + pageNumber
					+ "'><a href='javascript:doGoPage("
					+ (startPage + pageNumber) + ")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a>下一頁</a></span>");
			pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr.append("<span title='下一頁'><a href='javascript:doGoPage("
					+ page + ")'>下一頁</a></span>");
			pageStr.append("<span title='最後一頁'><a href='javascript:doGoPage("
					+ pageTotal + ")'>最後一頁</a></span>");
		}

		pageStr
				.append("&nbsp;<span class='ui-input-button'><input type='button' value='轉到' onclick='javascript:goPage2()' /></span>");
		pageStr
				.append("&nbsp;第<input id='pageNum2' value='"
						+ currentPage
						+ "' size='2' type='text'  style='border: #96A6C5 solid 1px;text-align:center;'>頁");
		pageStr.append("</span><br/>");
		this.paginationNavigateOfPost2 = pageStr.toString();
	}

	public void setPaginationNavigateOfPost3(int currentPage, int countTotal,
			int pageSize, int pageTotal, String url, int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		pageStr.append("<span class='number'>");
		if (currentPage <= 1) {
			pageStr.append("<span title='首頁'><a class='pie'>首頁</a></span>");
			pageStr.append("<span title='前一頁'><a class='pie'>前一頁</a></span>");
		} else {
			int page = currentPage - 1;
			pageStr
					.append("<span title='首頁'><a class='pie' href='javascript:doGoPage(1)'>首頁</a></span>");
			pageStr
					.append("<span title='前一頁'><a class='pie' href='javascript:doGoPage("
							+ page + ")'>前一頁</a></span>");

		}
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			startPage = currentPage - currentPage % pageNumber + 1;
		}
		int pageTemp = startPage - 1;
		if (startPage > pageNumber)
			pageStr.append("<span title='前" + pageNumber
					+ "頁'><a class='pie' href='javascript:doGoPage(" + pageTemp
					+ ")' >...</a></span>");

		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='ui-selected-pagination pie'>" + i
						+ "</a></span>");
			} else {
				pageStr.append("<span title='第" + i
						+ "頁'><a class='pie' href='javascript:doGoPage(" + i
						+ ")'>" + i + "</a></span>");
			}
		}
		if (this.pageTotal >= startPage + pageNumber)
			pageStr.append("<span title='" + pageNumber
					+ "'><a class='pie' href='javascript:doGoPage("
					+ (startPage + pageNumber) + ")'>...</a></span>");

		if (currentPage >= pageTotal) {
			pageStr.append("<span title='下一頁'><a class='pie'>下一頁</a></span>");
			pageStr.append("<span title='最後一頁'><a class='pie'>最後一頁</a></span>");
		} else {
			int page = currentPage + 1;
			pageStr
					.append("<span title='下一頁'><a class='pie' href='javascript:doGoPage("
							+ page + ")'>下一頁</a></span>");
			pageStr
					.append("<span title='最後一頁'><a class='pie' href='javascript:doGoPage("
							+ pageTotal + ")'>最後一頁</a></span>");
		}

		pageStr.append("</span><br/>");
		this.paginationNavigateOfPost3 = pageStr.toString();
	}

	public void setPaginationNavigateOfPostHero(int currentPage,
			int countTotal, int pageSize, int pageTotal, String url,
			int pageNumber) {
		StringBuffer pageStr = new StringBuffer();
		if (currentPage <= 1) {
			pageStr
					.append("<a href='#' title='上一頁' class='previous-next-pagination previous-pagination'>&nbsp;</a>");
		} else {
			int page = currentPage - 1;
			pageStr
					.append("<a href='javascript:doGoPage("
							+ page
							+ ")' title='上一頁' class='previous-next-pagination previous-pagination'>&nbsp;</a>");
		}
		int startPage = 1;
		if (currentPage % pageNumber == 0) {
			startPage = currentPage - pageNumber + 1;
		} else {
			startPage = currentPage - currentPage % pageNumber + 1;
		}

		pageStr.append("<span class='ui-pagination-main'>");
		for (int i = startPage; i < startPage + pageNumber; i++) {
			if (i > this.pageTotal)
				break;
			if (i == this.currentPage) {
				pageStr
						.append("<a href='#' class='ui-selected-pagination'>&nbsp;</a>&nbsp;");
			} else {
				pageStr.append("<a href='/association/hero/'>&nbsp;</a>&nbsp;");
			}
		}
		pageStr.append("</span>");
		if (currentPage >= pageTotal) {
			pageStr
					.append("<a href='#' title='下一頁' class='previous-next-pagination next-pagination'>&nbsp;</a>");
		} else {
			int page = currentPage + 1;
			pageStr
					.append("<a href='javascript:doGoPage("
							+ page
							+ ")' title='下一頁' class='previous-next-pagination next-pagination'>&nbsp;</a>");
		}
		this.paginationNavigateOfPostHero = pageStr.toString();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountTotal() {
		return countTotal;
	}

	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public String getPaginationNavigate() {
		return paginationNavigate;
	}

	public String getPaginationNavigateOfPost() {
		return paginationNavigateOfPost;
	}

	public String getPaginationNavigate3() {
		return paginationNavigate3;
	}

	public String getPaginationNavigate2() {
		return paginationNavigate2;
	}

	public String getPaginationNavigateOfPost2() {
		return paginationNavigateOfPost2;
	}

	public String getPaginationNavigateOfPost3() {
		return paginationNavigateOfPost3;
	}

	public String getPaginationNavigateOfPostHero() {
		return paginationNavigateOfPostHero;
	}

	public String getPaginationNavigateOfPostEn() {
		return paginationNavigateOfPostEn;
	}

	public void setPaginationNavigateOfPostEn(String paginationNavigateOfPostEn) {
		this.paginationNavigateOfPostEn = paginationNavigateOfPostEn;
	}

	public String getPaginationNavigateOfPostPo() {
		return paginationNavigateOfPostPo;
	}

	public void setPaginationNavigateOfPostPo(String paginationNavigateOfPostPo) {
		this.paginationNavigateOfPostPo = paginationNavigateOfPostPo;
	}

	public String getPaginationNavigateOfPostOnly() {
		return paginationNavigateOfPostOnly;
	}

	public void setPaginationNavigateOfPostOnly(
			String paginationNavigateOfPostOnly) {
		this.paginationNavigateOfPostOnly = paginationNavigateOfPostOnly;
	}

	public String getPaginationNavigateOfPostOnly2() {
		return paginationNavigateOfPostOnly2;
	}

	public void setPaginationNavigateOfPostOnly2(
			String paginationNavigateOfPostOnly2) {
		this.paginationNavigateOfPostOnly2 = paginationNavigateOfPostOnly2;
	}

	public String getPaginationNavigateOfPostOnly3() {
		return paginationNavigateOfPostOnly3;
	}

	public void setPaginationNavigateOfPostOnly3(
			String paginationNavigateOfPostOnly3) {
		this.paginationNavigateOfPostOnly3 = paginationNavigateOfPostOnly3;
	}

	public String getPaginationNavigateOfPostOnly4() {
		return paginationNavigateOfPostOnly4;
	}

	public void setPaginationNavigateOfPostOnly4(
			String paginationNavigateOfPostOnly4) {
		this.paginationNavigateOfPostOnly4 = paginationNavigateOfPostOnly4;
	}

	public String getPaginationNavigateOfPostOnly5() {
		return paginationNavigateOfPostOnly5;
	}

	public void setPaginationNavigateOfPostOnly5(
			String paginationNavigateOfPostOnly5) {
		this.paginationNavigateOfPostOnly5 = paginationNavigateOfPostOnly5;
	}

	public String getPaginationNavigateOfPostOnly6() {
		return paginationNavigateOfPostOnly6;
	}

	public void setPaginationNavigateOfPostOnly6(
			String paginationNavigateOfPostOnly6) {
		this.paginationNavigateOfPostOnly6 = paginationNavigateOfPostOnly6;
	}

	public String getPaginationNavigateOfPostPoOnly() {
		return paginationNavigateOfPostPoOnly;
	}

	public void setPaginationNavigateOfPostPoOnly(
			String paginationNavigateOfPostPoOnly) {
		this.paginationNavigateOfPostPoOnly = paginationNavigateOfPostPoOnly;
	}

	public String getPaginationNavigateOfPostPoOnly2() {
		return paginationNavigateOfPostPoOnly2;
	}

	public void setPaginationNavigateOfPostPoOnly2(
			String paginationNavigateOfPostPoOnly2) {
		this.paginationNavigateOfPostPoOnly2 = paginationNavigateOfPostPoOnly2;
	}

	public String getPaginationNavigateOfPostPoOnly3() {
		return paginationNavigateOfPostPoOnly3;
	}

	public void setPaginationNavigateOfPostPoOnly3(
			String paginationNavigateOfPostPoOnly3) {
		this.paginationNavigateOfPostPoOnly3 = paginationNavigateOfPostPoOnly3;
	}

}
