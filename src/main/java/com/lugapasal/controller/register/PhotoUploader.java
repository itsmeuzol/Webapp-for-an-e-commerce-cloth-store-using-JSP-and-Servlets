package com.lugapasal.controller.register;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.http.Part;

public class PhotoUploader {
	String upPath = "/Users/nitintandukar/eclipse-workspace/LugaPasal/src/main/webapp/images";
	String deletePath = "/Users/nitintandukar/eclipse-workspace/LugaPasal/src/main/webapp/";
	/**
	* Uploads a photo to the upload directory. This method is called by the UploadListener to get the photo from the upload file
	* 
	* @param filePart - The part that contains the
	*/
	public void uploadPhoto(Part filePart) {
     // Get the uploaded image file
        String fileName = getFileName(filePart);
        
        InputStream fileContent;
		try {
			fileContent = filePart.getInputStream();
			 // Save the uploaded image file to the upload directory
	        Files.copy(fileContent, new File(upPath + File.separator + fileName).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	/**
	* Removes a photo from the storage. This does not delete the file from the file system. It is up to the caller to do that.
	* 
	* @param relativePath - The path to the photo relative to the storage
	*/
	public void removePhoto(String relativePath) {
        File file = new File(deletePath, relativePath);
        // Delete the file if it exists and is a file.
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
	/**
	* Returns the filename of the part. It looks for the filename in the content - disposition header and returns it
	* 
	* @param part - the part to check.
	* 
	* @return the filename or null if not found or not a file part ( the part does not have a filename
	*/
	public String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            // Returns the filename from the content.
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
