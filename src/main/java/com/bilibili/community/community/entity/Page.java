package com.bilibili.community.community.entity;

public class Page {
    private int current = 1;
    private int limit = 10;
    private int rowsTotal;
    private String path;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit > 0 && limit < 100){
            this.limit = limit;
        }
    }

    public int getRowsTotal() {
        return rowsTotal;
    }

    public void setRowsTotal(int rowsTotal) {
        if(rowsTotal >= 0){
            this.rowsTotal = rowsTotal;
        }
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1){
            this.current = current;
        }
    }

    public int getFrom(){
        int from = current - 2;
        return from < 1 ? 1 : from;
    }

    public int getTo(){
        int to = current + 2;
        return to > getTotal() ? getTotal() : to;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getOffset(){
        return (current - 1) * limit;
    }

    public int getTotal(){
        if(rowsTotal % limit == 0){
            return rowsTotal / limit;
        }else{
            return rowsTotal / limit + 1;
        }
    }
}
