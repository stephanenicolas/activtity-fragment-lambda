package com.example.fragmentactivitylambda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

/**
 * Illustrates the lambda way of communicating with Fragments.
 */
public class MainActivity extends AppCompatActivity {
  public static final String TAG_FRAGMENT_MAIN = "TAG_FRAGMENT_MAIN";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      //the main advantage of this approach is that we can
      //now clearly see the link between the fragment and the activity
      MainFragment fragment =
          new MainFragment.Builder() //
              .setOnClickListener(MainActivity::onArticleClicked) //
              .build();

      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.container, fragment, TAG_FRAGMENT_MAIN)
          .commit();
    }
  }

  private void onArticleClicked(int position) {
    makeText(this, "Article clicked: " + position, LENGTH_SHORT) //
        .show();
  }
}
