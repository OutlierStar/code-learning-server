package com.example.practicalwork.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class FileTreeNode {
    private String name;
    private String path;
    private Long length;
    private Boolean ifDir = false;//是否文件夹
    private List<FileTreeNode> children = new ArrayList<>();

    public void addChild(FileTreeNode treeNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(treeNode);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public void setIfDir(Boolean ifDir) {
        this.ifDir = ifDir;
    }

    public void setChildren(List<FileTreeNode> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Long getLength() {
        return length;
    }

    public Boolean getIfDir() {
        return ifDir;
    }

    public List<FileTreeNode> getChildren() {
        return children;
    }
}
