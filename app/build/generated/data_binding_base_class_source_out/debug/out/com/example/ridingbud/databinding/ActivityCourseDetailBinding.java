// Generated by view binder compiler. Do not edit!
package com.example.ridingbud.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ridingbud.R;
import com.kakao.vectormap.MapView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCourseDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton btnWriteReview;

  @NonNull
  public final MapView mapView;

  @NonNull
  public final RatingBar ratingBar;

  @NonNull
  public final RecyclerView recyclerViewReviews;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView tvCourseRating;

  @NonNull
  public final TextView tvCourseReviewLabel;

  private ActivityCourseDetailBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton btnWriteReview, @NonNull MapView mapView, @NonNull RatingBar ratingBar,
      @NonNull RecyclerView recyclerViewReviews, @NonNull Toolbar toolbar,
      @NonNull TextView tvCourseRating, @NonNull TextView tvCourseReviewLabel) {
    this.rootView = rootView;
    this.btnWriteReview = btnWriteReview;
    this.mapView = mapView;
    this.ratingBar = ratingBar;
    this.recyclerViewReviews = recyclerViewReviews;
    this.toolbar = toolbar;
    this.tvCourseRating = tvCourseRating;
    this.tvCourseReviewLabel = tvCourseReviewLabel;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCourseDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCourseDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_course_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCourseDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_write_review;
      ImageButton btnWriteReview = ViewBindings.findChildViewById(rootView, id);
      if (btnWriteReview == null) {
        break missingId;
      }

      id = R.id.map_view;
      MapView mapView = ViewBindings.findChildViewById(rootView, id);
      if (mapView == null) {
        break missingId;
      }

      id = R.id.ratingBar;
      RatingBar ratingBar = ViewBindings.findChildViewById(rootView, id);
      if (ratingBar == null) {
        break missingId;
      }

      id = R.id.recyclerView_reviews;
      RecyclerView recyclerViewReviews = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewReviews == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tv_course_rating;
      TextView tvCourseRating = ViewBindings.findChildViewById(rootView, id);
      if (tvCourseRating == null) {
        break missingId;
      }

      id = R.id.tv_course_review_label;
      TextView tvCourseReviewLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvCourseReviewLabel == null) {
        break missingId;
      }

      return new ActivityCourseDetailBinding((ConstraintLayout) rootView, btnWriteReview, mapView,
          ratingBar, recyclerViewReviews, toolbar, tvCourseRating, tvCourseReviewLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
