/**
 *
 */
package com.objectfrontier.training.ws.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mohammed.mohammed
 * @since  Oct 13, 2018
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    List<AppErrorCode> exceptionList = new ArrayList<>();
    Throwable cause;

    public AppException(AppErrorCode errorCode) {
        if(exceptionList.isEmpty() || exceptionList.size() == 0) {
            exceptionList.add(errorCode);
        }
    }

    public AppException(AppErrorCode errorCode, Throwable cause) {
        super(cause);
        if(exceptionList.isEmpty() || exceptionList.size() == 0) {
            exceptionList.add(errorCode);
            this.cause = cause;
        }
    }

    public AppException(List<AppErrorCode> exceptionList) {
        this.exceptionList = exceptionList;
    }

    public AppException(List<AppErrorCode> exceptionList, Throwable cause) {
        super(cause);
        this.exceptionList = exceptionList;
    }

    public AppException(Throwable cause) {
        this(AppErrorCode.UNKNOWN_ERROR, cause);
    }

    public List<AppErrorCode> getExceptionList() {
        return exceptionList;
    }

    @Override
    public String toString() {
        return String.format("Error : %s", exceptionList);
    }
}
