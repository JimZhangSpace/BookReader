package com.folioreader.model;
import org.litepal.crud.LitePalSupport;

public class BookMark extends LitePalSupport {
    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public int getShowPageNumber() {
        return showPageNumber;
    }

    public void setShowPageNumber(int showPageNumber) {
        this.showPageNumber = showPageNumber;
    }

    private long id;
    //章节索引
    private int chapterIndex;
    //所在章节的页码
    private int pageNumber;
    //所在章节总页码
    private int pageTotal;
    //日期
    private String date;
    //章节标题
    private String chapterTitle;
    //用来显示的页码
    private int showPageNumber;
//    @objc open dynamic var bookId: String!
//    @objc open dynamic var bookmarkId: Int = 0
//
//    @objc open dynamic var chapterIndex: Int = 0
//    @objc open dynamic var pageNumber: Int = 0
//    @objc open dynamic var pageTotal: Int = 0
//    @objc open dynamic var date: Date!
//    @objc open dynamic var chapterTitle : String!
//
//
//    /**显示在书签上面的页码*/
//    @objc open dynamic var showPageNumber: Int = 0


}
