package com.github.m5.cloud.config.client;

import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.List;

/**
 * @author xiaoyu
 */
public class PageWrapper<T> {
    private int page;
    private int size;
    private long total;
    private int pages;
    private List<T> result;

    private PageWrapper() {
    }

    public static <E> PageWrapper<E> wrapFromES(AggregatedPage<E> page) {
        PageWrapper<E> pageWrapper = new PageWrapper<>();
        pageWrapper.setPage(page.getNumber() + 1);
        pageWrapper.setSize(page.getSize());
        pageWrapper.setTotal(page.getTotalElements());
        pageWrapper.setPages(page.getTotalPages());
        pageWrapper.setResult(page.getContent());
        return pageWrapper;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
