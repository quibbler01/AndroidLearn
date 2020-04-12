package com.easyicon.learnglide.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.easyicon.learnglide.R;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.ui.fragment
 * ClassName:      MyFragment
 * Description:
 * Author:         61444
 * CreateDate:     2020/2/27 22:48
 */
public class MyFragment extends Fragment {

    private Context mContext;
    private FragmentListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof FragmentListener) {
            listener = (FragmentListener) context;
        }
        if (null != listener) {
            listener.notifyActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blank_fragment2_fragment, container, false);

        TextView textView = view.findViewById(R.id.text_view);
//        String s = getString(R.string.string, "Quibbler", 25);

//        textView.setText(s);

        return view;
    }

    public interface FragmentListener {
        void notifyActivity();
    }
}
