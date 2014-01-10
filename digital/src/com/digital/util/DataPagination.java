package com.digital.util;

public class DataPagination {
	// 分頁所需的屬性， 分別是當前頁，總記錄數，頁面大小，總頁數
	private int currentPage;
	private int countTotal;
	private int pageSize = 20;
	private int pageTotal;

	
	public DataPagination() {
	};

	public DataPagination(int currentPage, int countTotal, int pageSize) {
		this.countTotal = countTotal;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		setPages(currentPage, pageSize);	
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

}
