/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dopy.dopy.tayga.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentMoviePosterBinding;
import com.squareup.picasso.Picasso;

/**
 * Fragment implementation created to show a poster inside an ImageView widget.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MoviePosterFragment extends Fragment {

  private String videoPosterThumbnail;
  private String posterTitle;
  FragmentMoviePosterBinding binding;

  /**
   * Override method used to initialize the fragment.
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movie_poster, container, false);
    binding = FragmentMoviePosterBinding.bind(view);
    Picasso.with(getActivity())
            .load(videoPosterThumbnail)
            .placeholder(R.drawable.xmen_placeholder)
            .into(binding.ivThumbnail);
    return view;
  }

  /**
   * Show the poster image in the thumbnailImageView widget.
   */
  public void setPoster(String videoPosterThumbnail) {
    this.videoPosterThumbnail = videoPosterThumbnail;
  }

  /**
   * Store the poster title to show it when the thumbanil view is clicked.
   */
  public void setPosterTitle(String posterTitle) {
    this.posterTitle = posterTitle;
  }

  /**
   * Method triggered when the iv_thumbnail widget is clicked. This method shows a toast with the
   * poster information.
   */
 /* @OnClick(R.id.iv_thumbnail) void onThubmnailClicked() {
    Toast.makeText(getActivity(), posterTitle, Toast.LENGTH_SHORT).show();
  }*/
}