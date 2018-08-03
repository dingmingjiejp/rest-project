package com.trizic.restapi.model;

import java.util.List;

/**
 *  This class represent a pagination List with page number, page size, total number of pages and total number of elements.
 *
 * @param <T>
 */
public class PageList<T> {

  private int pageNumber;

  private int pageSize;

  private int numberOfPages;

  private long totalNumberOfElements;

  private List<T> page;

  public static final int DEFAULT_PAGE_SIZE = 10;

  public static final int DEFAULT_PAGE_NUM = 0;

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public long getTotalNumberOfElements() {
    return totalNumberOfElements;
  }

  public void setTotalNumberOfElements(long totalNumberOfElements) {
    this.totalNumberOfElements = totalNumberOfElements;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public List<T> getPage() {
    return page;
  }

  public void setPage(List<T> page) {
    this.page = page;
  }

  public static <T> PageList<T> of(int pageNumber, int pageSize, int numberOfPages, long totalNumberOfElements, List<T> page) {

    PageList<T> list = new PageList<>();
    list.setPageSize(pageSize);
    list.setPage(page);
    list.setPageNumber(pageNumber);
    list.setNumberOfPages(numberOfPages);
    list.setTotalNumberOfElements(totalNumberOfElements);

    return list;
  }


}