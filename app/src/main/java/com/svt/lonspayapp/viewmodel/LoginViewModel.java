package com.svt.lonspayapp.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.svt.lonspayapp.model.LoginUser;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> emailIdData = new MutableLiveData<>();
    public MutableLiveData<String> passwordData = new MutableLiveData<>();

    public MutableLiveData<LoginUser> userMutableLiveData;

    public MutableLiveData<LoginUser> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(View view) {
        LoginUser loginUser = new LoginUser(emailIdData.getValue(), passwordData.getValue());

        userMutableLiveData.setValue(loginUser);
    }
}
