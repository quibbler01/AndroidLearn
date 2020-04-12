package com.easyicon.learnglide.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.easyicon.learnglide.R;
import com.easyicon.learnglide.ui.fragment.BlankFragment;
import com.easyicon.learnglide.ui.fragment.BlankFragment2;
import com.easyicon.learnglide.ui.fragment.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LearnFragmentActivity extends AppCompatActivity implements MyFragment.FragmentListener {

    @BindView(R.id.add_fragment_1)
    Button addFragment1;
    @BindView(R.id.add_fragment_2)
    Button addFragment2;
    @BindView(R.id.del_fragment)
    Button delFragment;

    FragmentManager fragmentManager;
    private boolean isChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_fragment);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        initFragment();
    }


    @OnClick({R.id.add_fragment_1, R.id.add_fragment_2, R.id.del_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_fragment_1:
//                initFragment();
                break;
            case R.id.add_fragment_2:
                changeFragment();
                break;
            case R.id.del_fragment:
                remove();
                break;
        }
    }

    private MyFragment myFragment = new MyFragment();

    private void initFragment() {
        fragmentManager.beginTransaction().replace(R.id.fragment_one, new MyFragment()).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_two, myFragment, "My Fragment Tag").commit();
    }

    private void changeFragment() {
        Log.d("QUIBBLER", "myFragment " + (myFragment == null));
        fragmentManager.beginTransaction()
                .add(R.id.fragment_two, new BlankFragment(), "My Fragment Tag")
                .addToBackStack(BlankFragment.class.getSimpleName())
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_two, new BlankFragment2(), "My Fragment Tag")
                .addToBackStack(BlankFragment.class.getSimpleName())
                .commit();
    }

    private void remove() {
//        fragmentManager.beginTransaction().detach(myFragment).commit();

//        fragmentManager.beginTransaction().addToBackStack()
//        fragmentManager.findFragmentByTag("");
//        Log.d("QUIBBLER", "myFragment " + (myFragment == null));

//        fragmentManager.popBackStackImmediate(BlankFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.popBackStack(BlankFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void notifyActivity() {
        Log.d("QUIBBLER", "Fragment->Activity");
    }
}
