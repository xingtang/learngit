package  com.digital.util;

/**
 * 一個網頁中多個分頁使用
 * ljl 2012-12-15
 * 
 */
public class ManyPagination {    
	//分頁所需的屬性， 分別是當前頁，總記錄數，頁面大小，總頁數����
	private int currentPage; 
	private int countTotal;
	private int pageSize = 20;
	private int pageTotal;	
	private String paginationNavigateOfPost;//中文	
	private String paginationNavigateOfPostEn;//英文	
	private String paginationNavigateOfPostPo;//葡文

	
	public ManyPagination(){};

	public ManyPagination(int currentPage, int countTotal, int pageSize, String url,  int pageNumber, String type,int n){
		this.countTotal = countTotal;
		this.currentPage = currentPage;
		this.pageSize = pageSize;	
		setPages(currentPage, pageSize);
		if("get".equalsIgnoreCase(type)){}
		else if("post".equalsIgnoreCase(type)){
			setPaginationNavigateOfPost(this.currentPage,this.countTotal,pageSize,pageTotal, url, pageNumber,n);
			setPaginationNavigateOfPostPo(this.currentPage,this.countTotal,pageSize,pageTotal, url, pageNumber,n);
			setPaginationNavigateOfPostEn(this.currentPage,this.countTotal,pageSize,pageTotal, url, pageNumber,n);		
		}	
	}
	
	/**
	 * 根據傳入得參數，計算出分頁導航欄的相關數據
	 * @param currentPage 當前頁數
	 * @param pageSize  頁面數據數量
	 */
		private void setPages(int currentPage,int pageSize){
			if(currentPage <= 0){
				setCurrentPage(1);
			}
			setPageTotal(countTotal/pageSize + (countTotal%pageSize>0?1:0));
			if(currentPage > pageTotal&& pageTotal != 0){setCurrentPage(pageTotal);}
		}
	
		/**
		 * 用于分類搜索各類分頁：
		 * 
		 * 根據相關參數。得出分頁導航欄 可調節頁數個數。已更正該方法亂碼......
		 * 有查詢條件。用POST的方法提交。
		 * 相應的頁面加上以下JavaScript
		 * doGoPager(n,page){
		 * 			
		 * }
		 * 
		 * 模版用VELOCITY,所以使用方法是 ${pagination.paginationNavigate}
		 * 模版用FreeMarker,所以使用方法是 ${pagination.paginationNavigate}
		 * 如果用struts2 jsp顯示，那麼輸出就是 <s:property value="pageBar"/>   (struts2標籤輸出)
		 * @param currentPage 當前頁數
		 * @param countTotal 記錄總數
		 * @param pageSize   頁面多小
		 * @param pageTotal  頁面總頁數
		 * @param pagerNumber  分頁顯示頁數個數
		 * @param n  一個網頁中第n個模塊分頁處（用于分類搜索各類分頁）
		 */
		public void setPaginationNavigateOfPost(int currentPage, int countTotal, int pageSize, int pageTotal, String url, int pageNumber,int n) {
			StringBuffer pageStr = new StringBuffer();
			pageStr.append("<span class='number'>");
			if(currentPage <= 1){
				pageStr.append("<span title='首頁'><a>首頁</a></span>");
				pageStr.append("<span title='前一頁'><a>前一頁</a></span>");
			}else{
				int page = currentPage - 1;
				pageStr.append("<span title='首頁'><a href='javascript:doGoPage("+n+",1)'>首頁</a></span>");
				pageStr.append("<span title='前一頁'><a href='javascript:doGoPage("+n+","+page+")'>前一頁</a></span>");
			}
			int startPage = 1;
			if(currentPage % pageNumber == 0){
				startPage = currentPage - pageNumber + 1;
			}else{
				startPage = currentPage - currentPage % pageNumber + 1;
			}
			int pageTemp = startPage - 1;
			if(startPage > pageNumber) pageStr.append("<span title='前"+pageNumber+"頁'><a href='javascript:doGoPage("+n+","+ pageTemp +")'>...</a></span>");
			
			for(int i = startPage; i < startPage + pageNumber; i++){
				if(i > this.pageTotal) break;
				if(i == this.currentPage){
					pageStr.append("<span title='第" + i + "頁'><a class='ui-selected-pagination'>" + i + "</a></span>");
				}else{
					pageStr.append("<span title='第" + i + "頁'><a href='javascript:doGoPage("+n+"," + i + ")'>" + i + "</a></span>");
				}
			}
			if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span title='"+pageNumber+"'><a href='javascript:doGoPage("+n+","+(startPage+pageNumber)+")'>...</a></span>");
			
			if(currentPage >= pageTotal){
				pageStr.append("<span title='下一頁'><a>下一頁</a></span>");
				pageStr.append("<span title='最後一頁'><a>最後一頁</a></span>");
			}else{
				int page = currentPage + 1;
				pageStr.append("<span title='下一頁'><a href='javascript:doGoPage("+n+","+page+")'>下一頁</a></span>");
				pageStr.append("<span title='最後一頁'><a href='javascript:doGoPage("+n+","+pageTotal+")'>最後一頁</a></span>");
			}
			pageStr.append("&nbsp;<span class='ui-input-button'><input type='button' value='轉到' onclick='javascript:goPage("+n+")' /></span>");
			pageStr.append("&nbsp;第<input id='pageNum' value='"+currentPage+"' size='2' type='text' style='border: #96A6C5 solid 1px;text-align:center;'>頁");
			pageStr.append("</span><br/>");
			this.paginationNavigateOfPost = pageStr.toString();
		}
	
		//英文
		public void setPaginationNavigateOfPostEn(int currentPage, int countTotal, int pageSize, int pageTotal, String url, int pageNumber,int n) {
			StringBuffer pageStr = new StringBuffer();
			pageStr.append("<span class='number'>");
			if(currentPage <= 1){
				pageStr.append("<span title='Home page'><a>Home page</a></span>");
				pageStr.append("<span title='prev page'><a>prev page</a></span>");
			}else{
				int page = currentPage - 1;
				pageStr.append("<span title='Home page'><a href='javascript:doGoPage("+n+",1)'>Home page</a></span>");
				pageStr.append("<span title='Prev page'><a href='javascript:doGoPage("+n+","+page+")'>Prev page</a></span>");
			
			}
			int startPage = 1;
			if(currentPage % pageNumber == 0){
				startPage = currentPage - pageNumber + 1;
			}else{
				startPage = currentPage - currentPage % pageNumber + 1;
			}
			int pageTemp = startPage - 1;
			if(startPage > pageNumber) pageStr.append("<span title='Prev"+pageNumber+"page'><a href='javascript:doGoPage("+n+","+ pageTemp +")'>...</a></span>");
			
			for(int i = startPage; i < startPage + pageNumber; i++){
				if(i > this.pageTotal) break;
				if(i == this.currentPage){
					pageStr.append("<span title='Order" + i + "page'><a class='ui-selected-pagination'>" + i + "</a></span>");
				}else{
					pageStr.append("<span title='Order" + i + "page'><a href='javascript:doGoPage("+n+"," + i + ")'>" + i + "</a></span>");
				}
			}
			if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span title='"+pageNumber+"'><a href='javascript:doGoPage("+n+","+(startPage+pageNumber)+")'>...</a></span>");
			
			if(currentPage >= pageTotal){
				pageStr.append("<span title='Next page'><a>Next page</a></span>");
				pageStr.append("<span title='Last page'><a>Last page</a></span>");
			}else{
				int page = currentPage + 1;
				pageStr.append("<span title='Next page'><a href='javascript:doGoPage("+n+","+page+")'>Next page</a></span>");
				pageStr.append("<span title='Last page'><a href='javascript:doGoPage("+n+","+pageTotal+")'>Last page</a></span>");
			}
			pageStr.append("&nbsp;<span class='ui-input-button'><input type='button' value='Go' onclick='javascript:goPage("+n+")' /></span>");
			pageStr.append("&nbsp;Order<input id='pageNum' value='"+currentPage+"' size='2' type='text' style='border: #96A6C5 solid 1px;text-align:center;'>page");
			pageStr.append("</span><br/>");
			this.paginationNavigateOfPostEn = pageStr.toString();
		}
		
		//葡文一頁中多處有分頁調用
		public void setPaginationNavigateOfPostPo(int currentPage, int countTotal, int pageSize, int pageTotal, String url, int pageNumber, int n) {
			StringBuffer pageStr = new StringBuffer();
			pageStr.append("<span class='number'>");
			if(currentPage <= 1){
				pageStr.append("<span title='<< Anterior'><a><< Anterior</a></span>");
				//pageStr.append("<span title='prev page'><a>prev page</a></span>");
			}else{
				int page = currentPage - 1;
				//pageStr.append("<span title='<< Anterior'><a href='javascript:doGoPage(1)'><< Anterior</a></span>");
				pageStr.append("<span title='<< Anterior'><a href='javascript:doGoPage("+n+","+page+")'><< Anterior</a></span>");
			}
			int startPage = 1;
			if(currentPage % pageNumber == 0){
				startPage = currentPage - pageNumber + 1;
			}else{
				startPage = currentPage - currentPage % pageNumber + 1;
			}
			int pageTemp = startPage - 1;
			if(startPage > pageNumber) pageStr.append("<span title='Prev"+pageNumber+"page'><a href='javascript:doGoPage("+n+","+ pageTemp +")'>...</a></span>");
			
			for(int i = startPage; i < startPage + pageNumber; i++){
				if(i > this.pageTotal) break;
				if(i == this.currentPage){
					pageStr.append("<span title='Order" + i + "page'><a class='ui-selected-pagination'>" + i + "</a></span>");
				}else{
					pageStr.append("<span title='Order" + i + "page'><a href='javascript:doGoPage("+n+"," + i + ")'>" + i + "</a></span>");
				}
			}
			if(this.pageTotal >= startPage + pageNumber) pageStr.append("<span title='"+pageNumber+"'><a href='javascript:doGoPage("+n+","+(startPage+pageNumber)+")'>...</a></span>");
			
			if(currentPage >= pageTotal){
				//pageStr.append("<span title='Next page'><a>Next page</a></span>");
				pageStr.append("<span title='Seguinte >>'><a>Seguinte >></a></span>");
			}else{
				int page = currentPage + 1;
				//pageStr.append("<span title='Next page'><a href='javascript:doGoPage("+page+")'>Next page</a></span>");
				pageStr.append("<span title='Seguinte >>'><a href='javascript:doGoPage("+n+","+page+")'>Seguinte >></a></span>");
			}
			//pageStr.append("&nbsp;<span class='ui-input-button'><input type='button' value='Go' onclick='javascript:goPage()' /></span>");
			//pageStr.append("&nbsp;Order<input id='pageNum' value='"+currentPage+"' size='2' type='text' style='border: #96A6C5 solid 1px;'>page");
			pageStr.append("</span><br/>");
			this.paginationNavigateOfPostPo = pageStr.toString();
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
	public String getPaginationNavigateOfPost() {
		return paginationNavigateOfPost;
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
	
	
}
