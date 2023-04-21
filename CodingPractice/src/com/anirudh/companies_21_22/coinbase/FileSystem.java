package com.anirudh.companies_21_22.coinbase;

import java.util.*;

public class FileSystem {

    static class TreeNode {
        final String name;
        final boolean isDir;
        Map<String, TreeNode> children; //empty for file, dir_name -> Node
        String content; //empty for dir
        TreeNode(String name, boolean isDir) {
            this.name = name;
            this.isDir = isDir;
            children = new TreeMap<>();
            content = "";
        }
        //getters and setters
    }

    TreeNode root;

    public FileSystem() {
        root = new TreeNode("/", true);
    }

    private TreeNode getTreeNode(String path) {
        String[] dirs = path.split("/");
        TreeNode curr = root;
        for (String dir : dirs) {
            if(dir.isEmpty())
                continue;
            if (!curr.children.containsKey(dir)) {
                throw new RuntimeException("Directory does not exist");
            }
            curr = curr.children.get(dir);
        }
        return curr;
    }

    private void validatePath(String path) {
        if(!path.startsWith("/"))
            throw new RuntimeException("Invalid path");
    }

    public List<String> ls(String path) {
        validatePath(path);
        TreeNode curr = getTreeNode(path);
        if(curr.isDir) {
            return new ArrayList<>(curr.children.keySet());
        }
        else {
            return Collections.singletonList(curr.name);
        }
    }

    public void mkdir(String path) {
        validatePath(path);
        String[] dirs = path.split("/");
        TreeNode curr = root;
        for(String dir : dirs) {
            if(dir.isEmpty())
                continue;
            curr.children.putIfAbsent(dir, new TreeNode(dir, true));
            curr = curr.children.get(dir);
        }
    }

    public void addContentToFile(String filePath, String content) {
        validatePath(filePath);
        int lastSlashIdx = filePath.lastIndexOf("/");
        TreeNode curr = getTreeNode(filePath.substring(0,lastSlashIdx));
        String fileName = filePath.substring(lastSlashIdx + 1);
        curr.children.putIfAbsent(fileName, new TreeNode(fileName, false));
        curr.children.get(fileName).content = curr.children.get(fileName).content + content;
    }

    public String readContentFromFile(String filePath) {
        validatePath(filePath);
        TreeNode curr = getTreeNode(filePath);
        if(curr.isDir){
            throw new RuntimeException("Not a file");
        }
        return curr.content;
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.ls("/").forEach(System.out::println);
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "helloworld");
        fs.ls("/").forEach(System.out::println);
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
    }
}
