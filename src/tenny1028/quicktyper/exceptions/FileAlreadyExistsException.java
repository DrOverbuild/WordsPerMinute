/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.exceptions;

/**
 * Created by jasper on 1/31/14.
 */
public class FileAlreadyExistsException extends java.nio.file.FileAlreadyExistsException {
	String nameOfFile;

	public String getNameOfFile() {
		return nameOfFile;
	}

	public FileAlreadyExistsException(String file, String nameOfFile) {
		super(file);
		this.nameOfFile = nameOfFile;
	}

}
