package com.ktdsuniversity.edu.bizmatch.board.vo;

public class BoardCommentPaginationVO {
	// 현재 페이지
			private int currPageNo;
			
			// 한 페이지에서 노출시킬 리스트 크기
			private int exposureListSize;
			
			// 페이지 총 갯수
			private int pageCount;
			
			//페이지 아이디 파리미터
			private String searchIdParam; 
			
			// 한 페이지 그룹 내에서 보여질 갯수
			private int pageCountInGroup;
			



			// 총 페이지 그룹 갯수
			private int groupCount;
			
			//현재 페이지 그룹 번호
			private int groupNo;
			
			//페이지 그룹 번호의 시작 페이지 번호
			private int groupStartPageNo;
			
			//페이지 그룹 번호의 끝 페이지 번호
			private int groupEndPageNo;

			// 다음 페이지 그룹의 유무
			private boolean hasNextGroup;
			
			// 이젠 페이지 그룹의 유무
			private boolean hasPrevGroup;
			
			// 다음 그룹의 시작 페이지 번호
			private int nextGroupStartPageNo;
			
			// 이전 그룹의 시작 페이지 번호
			private int prevGroupStartPageNo;

			//생성자
			public BoardCommentPaginationVO() {
				exposureListSize = 10;
				pageCountInGroup = 5;

			}
			
			// 페이지 총 갯수를 구함
			public void setPageCount(int listcount) {
				// 13
				//페이지 총 갯수 = (올림) 리스트의 총 갯수 / 노출시킬 페이지 겟수
				// 7
				this.pageCount = (int) Math.ceil((double)listcount/ this.exposureListSize);
				
				// 7 / 2 ==> 3
				this.groupCount = (int) Math.ceil((double) this.pageCount 
														 / this.pageCountInGroup );
				// 7 / 2 ==> 3
				this.groupNo = this.currPageNo / this.pageCountInGroup;

				// 3 * 2 => 6
				this.groupStartPageNo = this.groupNo * this.pageCountInGroup;
				// (3 + 1) * 2 - 1 = 7 ==> 8page
				this.groupEndPageNo = (this.groupNo + 1) * this.pageCountInGroup - 1;
				
				// 7 > 7 - 1
				if (this.groupEndPageNo > this.pageCount - 1) {
					this.groupEndPageNo = this.pageCount - 1;
				}
				
				this.hasNextGroup = this.groupNo + 1 < this.groupCount;
				this.hasPrevGroup = this.groupNo > 0;

				this.nextGroupStartPageNo = this.groupEndPageNo + 1;
				this.prevGroupStartPageNo = this.groupStartPageNo - this.pageCountInGroup;
				
			}

			
			public String getSearchIdParam() {
				return searchIdParam;
			}

			public void setSearchIdParam(String searchIdParam) {
				this.searchIdParam = searchIdParam;
			}
			
			public int getCurrPageNo() {
				return currPageNo;
			}

			public void setCurrPageNo(int currPageNo) {
				this.currPageNo = currPageNo;
			}

			public int getExposureListSize() {
				return exposureListSize;
			}

			public void setExposureListSize(int exposureListSize) {
				this.exposureListSize = exposureListSize;
			}

			public int getPageCount() {
				return pageCount;
			}


			public int getPageCountInGroup() {
				return pageCountInGroup;
			}

			public void setPageCountInGroup(int pageCountInGroup) {
				this.pageCountInGroup = pageCountInGroup;
			}

			public int getGroupCount() {
				return groupCount;
			}

			public void setGroupCount(int groupCount) {
				this.groupCount = groupCount;
			}

			public int getGroupNo() {
				return groupNo;
			}

			public void setGroupNo(int groupNo) {
				this.groupNo = groupNo;
			}

			public int getGroupStartPageNo() {
				return groupStartPageNo;
			}

			public void setGroupStartPageNo(int groupStartPageNo) {
				this.groupStartPageNo = groupStartPageNo;
			}

			public int getGroupEndPageNo() {
				return groupEndPageNo;
			}

			public void setGroupEndPageNo(int groupEndPageNo) {
				this.groupEndPageNo = groupEndPageNo;
			}

			public boolean isHasNextGroup() {
				return hasNextGroup;
			}

			public void setHasNextGroup(boolean hasNextGroup) {
				this.hasNextGroup = hasNextGroup;
			}

			public boolean isHasPrevGroup() {
				return hasPrevGroup;
			}

			public void setHasPrevGroup(boolean hasPrevGroup) {
				this.hasPrevGroup = hasPrevGroup;
			}

			public int getNextGroupStartPageNo() {
				return nextGroupStartPageNo;
			}

			public void setNextGroupStartPageNo(int nextGroupStartPageNo) {
				this.nextGroupStartPageNo = nextGroupStartPageNo;
			}

			public int getPrevGroupStartPageNo() {
				return prevGroupStartPageNo;
			}

			public void setPrevGroupStartPageNo(int prevGroupStartPageNo) {
				this.prevGroupStartPageNo = prevGroupStartPageNo;
			}
}
