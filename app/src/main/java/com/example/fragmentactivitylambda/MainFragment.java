package com.example.fragmentactivitylambda;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Illustrates the lambda way of communicating with Activities.
 */
public class MainFragment extends Fragment {
  private static final String BUNDLE_KEY_CLICK_LISTENER = "BUNDLE_KEY_CLICK_LISTENER";

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //we get the click listener from the bundle if present
    //and link it to our button
    if (getArguments().containsKey(BUNDLE_KEY_CLICK_LISTENER)) {
      final Button button = view.findViewById(R.id.button);
      final HeadlineListener headlineListener =
          (HeadlineListener) getArguments().getSerializable(BUNDLE_KEY_CLICK_LISTENER);
      button.setOnClickListener(view1 -> headlineListener.onArticleSelected(getActivity(), 0));
    }
  }

  /**
   * Builds a {@link MainFragment} and put its communication lambda wit the activity
   * in the arguments so that they survive rotation.
   */
  static class Builder {
    private HeadlineListener headlineListener;

    /**
     * This method must define a type generic so that it triggers target type inference.
     * @param headlineListener the listener of clicks on an article.
     * @param <T> the type of the activity that will hold the method reference listener.
     * @return the builder itself for method chaining.
     */
    <T extends Activity> Builder setOnClickListener(HeadlineListener<T> headlineListener) {
      this.headlineListener = headlineListener;
      return this;
    }

    MainFragment build() {
      MainFragment mainFragment = new MainFragment();
      mainFragment.setArguments(createArgs());
      return mainFragment;
    }

    private Bundle createArgs() {
      Bundle bundle = new Bundle();
      if (headlineListener != null) {
        //store the listener in the arguments bundle
        //it is a state less lambda, guaranteed to be serializable
        bundle.putSerializable(BUNDLE_KEY_CLICK_LISTENER, headlineListener);
      }
      return bundle;
    }
  }
}
