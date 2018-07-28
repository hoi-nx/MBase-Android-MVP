package com.mybase.mvp.interfaces;

/**
 * Created by nguyenxuanhoi2903 on 02/12/2017.
 */

public interface IBaseViewMain extends ILoader{
    void finishGetData(Object object);
    void errorGetData(Throwable throwable);
}
