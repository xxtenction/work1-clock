/*
 * Copyright 2024 Your Company Name
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.work1_clock.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Icons.Filled.Sunny: ImageVector
    get() {
        if (_sunny != null) {
            return _sunny!!
        }
        _sunny = materialIcon(name = "Filled.Sunny") {
            materialPath {
                // 太阳主体
                moveTo(12f, 12f)
                arcToRelative(10f, 10f, 0f, true, true, 0f, 20f)
                arcToRelative(10f, 10f, 0f, true, true, 0f, -20f)

                // 顶部射线
                moveTo(12f, 2f)
                lineTo(12f, 4f)

                // 右侧射线
                moveTo(20f, 12f)
                lineTo(18f, 12f)

                // 底部射线
                moveTo(12f, 20f)
                lineTo(12f, 18f)

                // 左侧射线
                moveTo(4f, 12f)
                lineTo(6f, 12f)

                // 右上射线
                moveTo(18f, 18f)
                lineTo(16f, 20f)

                // 右下射线
                moveTo(18f, 6f)
                lineTo(20f, 4f)

                // 左下射线
                moveTo(6f, 4f)
                lineTo(4f, 6f)

                // 左上射线
                moveTo(6f, 18f)
                lineTo(4f, 20f)
            }
        }
        return _sunny!!
    }

private var _sunny: ImageVector? = null