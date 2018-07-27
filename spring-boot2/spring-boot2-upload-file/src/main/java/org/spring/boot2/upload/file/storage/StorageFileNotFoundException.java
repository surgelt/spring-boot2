package org.spring.boot2.upload.file.storage;

public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = 5581934336387360851L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}