package com.hairdresser.managers.exception;

import org.springframework.http.HttpStatus;

public class CustomExceptionModel {

	public static class CustomNotFoundException extends CustomException {
		private static final long serialVersionUID = 4658089349978831424L;

		public CustomNotFoundException(String code) {
			super(code, HttpStatus.NOT_FOUND);
		}
	}

	public static class CustomBadRequestException extends CustomException {
		private static final long serialVersionUID = 2700603242765408259L;

		public CustomBadRequestException(String code) {
			super(code, HttpStatus.BAD_REQUEST);
		}
	}
	
	public static class CustomUnathorizedException extends CustomException {
		private static final long serialVersionUID = -1445726245064797107L;

		public CustomUnathorizedException(String code) {
			super(code, HttpStatus.UNAUTHORIZED);
		}
	}
	
	public static class CustomForbiddenException extends CustomException {
		private static final long serialVersionUID = -1445726245064797107L;

		public CustomForbiddenException(String code) {
			super(code, HttpStatus.FORBIDDEN);
		}
	}
	
	public static class CustomNoContentException extends CustomException {
		private static final long serialVersionUID = 4953106025602487100L;

		public CustomNoContentException(String code) {
			super(code, HttpStatus.NO_CONTENT);
		}
	}

	public static class CustomInternalServerErrorException extends CustomException {
		private static final long serialVersionUID = 8001244396944686767L;

		public CustomInternalServerErrorException(String code) {
			super(code, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}