package com.example.fragmentactivitylambda;

import android.app.Activity;
import java.io.Serializable;

/**
 * The listener interface which is independent of the activity. 
 * It must have a generic type to trigger target type inference.
 * It is serializable to ensure the listener can survive rotations.
 */
public interface HeadlineListener<T extends Activity> extends Serializable {
  /**
   * Single method to get a functional interface. It uses the activity type 
   * {@code T} to trigger type inference. 
   * @param activity any activity instance.
   * @para position the second param.
   */
  void onArticleSelected(T activity, int position);
}
