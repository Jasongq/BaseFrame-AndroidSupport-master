package com.jiuye.baseframe.util.transformations;

/**
 * Copyright (C) 2018 Wasabeef
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

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * @Descropt 裁剪成正方形图片
 * .apply(bitmapTransform(CropSquareTransformation()))
 */
public class CropSquareTransformation extends BitmapTransformation {

  private static final int VERSION = 1;
  private static final String ID =
      "jp.wasabeef.glide.transformations.CropSquareTransformation." + VERSION;

  private int size;

  @Override
  protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool pool,
                             @NonNull Bitmap toTransform, int outWidth, int outHeight) {
    this.size = Math.max(outWidth, outHeight);
    return TransformationUtils.centerCrop(pool, toTransform, size, size);
  }

  @Override
  public String toString() {
    return "CropSquareTransformation(size=" + size + ")";
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof CropSquareTransformation && ((CropSquareTransformation) o).size == size;
  }

  @Override
  public int hashCode() {
    return ID.hashCode() + size * 10;
  }

  @Override
  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    messageDigest.update((ID + size).getBytes(CHARSET));
  }
}
