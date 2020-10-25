package com.org.api.steplibrary;

public class ResultData {
    private String id;
    private Object category;
    private String name;
    private Object photoUrls;
    private Object tags;
    private String status;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Object getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Object getPhotoUrls(){
        return photoUrls;
    }

    public void setPhotoUrls(String photoUrls){
        this.photoUrls = photoUrls;
    }

    public Object getTags(){
        return tags;
    }

    public void setTags(String tags){
        this.tags = tags;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
