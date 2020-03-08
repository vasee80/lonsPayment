package com.svt.lonspayapp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.svt.lonspayapp.R;
import com.svt.lonspayapp.databinding.LoginFragmentBinding;
import com.svt.lonspayapp.model.LoginUser;
import com.svt.lonspayapp.viewmodel.LoginViewModel;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    private LoginFragmentBinding binding;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel

        binding = DataBindingUtil.setContentView(this.getActivity(), R.layout.login_fragment);

        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                validateLogin(loginUser);
            }
        });
    }

    private void validateLogin(LoginUser loginUser) {
        if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getEmailId())) {
            binding.txtEmailId.setError(getResources().getString(R.string.enter_email));
            binding.txtEmailId.requestFocus();
        } else if (!Objects.requireNonNull(loginUser).isEmailValid()) {
            binding.txtEmailId.setError(getResources().getString(R.string.enter_valid_email));
            binding.txtEmailId.requestFocus();
        } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getPassword())) {
            binding.txtPassword.setError(getResources().getString(R.string.enter_password));
            binding.txtPassword.requestFocus();
        } else if (!Objects.requireNonNull(loginUser).isPasswordGreaterthan8()) {
            binding.txtPassword.setError(getResources().getString(R.string.enter_valid_password));
            binding.txtPassword.requestFocus();
        }/*else{
            binding.lblError.setText(loginUser.getEmailId()+" : "+loginUser.getPassword());
        }*/
    }
}
