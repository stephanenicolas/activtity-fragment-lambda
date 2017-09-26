package com.example.fragmentactivitylambda;

import android.app.Activity;
import java.io.Serializable;

public interface HeadlineListener<T extends Activity> extends Serializable {
  void onArticleSelected(T activity, int position);
}
