package org.spring.boot2.upload.file.storage;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 3881392266165024240L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
