/**
 *
 */
package com.objectfrontier.training.java.jdbc.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mohammed.mohammed
 * @since  Oct 13, 2018
 */
public class AppException extends RuntimeException {

    List<AppErrorCode> exceptionList = new ArrayList<>();
    Exception cause;

    public AppException(AppErrorCode errorCode) {
        if(exceptionList.isEmpty() || exceptionList.size() == 0) {
            exceptionList.add(errorCode);
        }
    }

    public AppException(AppErrorCode errorCode, Exception cause) {
        super(cause);
        if(exceptionList.isEmpty() || exceptionList.size() == 0) {
            exceptionList.add(errorCode);
            this.cause = cause;
        }
    }

    public AppException(List<AppErrorCode> exceptionList) {
        this.exceptionList = exceptionList;
    }

    public List<AppErrorCode> getExceptionList() {
        return exceptionList;
    }
}
