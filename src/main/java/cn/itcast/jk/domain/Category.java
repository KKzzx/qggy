package cn.itcast.jk.domain;

import java.io.Serializable;

/**
 * 类别
 *
 * @author Administrator
 */
public class Category implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5437078745633331786L;
    private String id; // 将主键都映射成id
    private String categoryName;
    private String categoryRemark;
    private String categoryState;
    private int categorySort;

    public String getCategoryRemark() {
        return categoryRemark;
    }

    public void setCategoryRemark(String categoryRemark) {
        this.categoryRemark = categoryRemark;
    }

    public String getCategoryState() {
        return categoryState;
    }

    public void setCategoryState(String categoryState) {
        this.categoryState = categoryState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

}
