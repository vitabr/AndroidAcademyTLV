package com.aat.rntv.business;

/**
 * Created by Refael Ozeri on 05/03/2016.
 */
public interface LoginCallback {
  void onSuccess(String response);
  void onError(String e);
}
