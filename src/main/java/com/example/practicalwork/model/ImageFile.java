package com.example.practicalwork.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageFile {
	
	public MultipartFile image; 
	public MultipartFile getImage() {
		return image; 
	}
	public void setImage(MultipartFile image) { 
		this.image = image; 
	}

}
