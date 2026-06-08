package com.example.test

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
public val todo: ImageVector
    get() {
        if (_todo != null) {
            return _todo!!
        }
        _todo =
            ImageVector.Builder(
                name = "todo",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(5f, 19f)
                        verticalLineTo(5f)
                        verticalLineToRelative(9.7f)
                        verticalLineToRelative(1.65f)
                        verticalLineTo(14.23f)
                        verticalLineToRelative(4.22f)
                        verticalLineTo(19f)
                        close()
                        moveToRelative(0f, 2f)
                        quadTo(4.18f, 21f, 3.59f, 20.41f)
                        reflectiveQuadTo(3f, 19f)
                        verticalLineTo(5f)
                        quadTo(3f, 4.17f, 3.59f, 3.59f)
                        reflectiveQuadTo(5f, 3f)
                        horizontalLineTo(19f)
                        quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                        reflectiveQuadTo(21f, 5f)
                        verticalLineToRelative(8f)
                        horizontalLineTo(19f)
                        verticalLineTo(5f)
                        horizontalLineTo(5f)
                        verticalLineTo(19f)
                        horizontalLineToRelative(7f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(5f)
                        close()
                        moveToRelative(12.35f, 1f)
                        lineTo(13.8f, 18.45f)
                        lineToRelative(1.43f, -1.4f)
                        lineToRelative(2.13f, 2.13f)
                        lineTo(21.6f, 14.93f)
                        lineTo(23f, 16.35f)
                        lineTo(17.35f, 22f)
                        close()
                        moveTo(8.71f, 12.71f)
                        quadTo(9f, 12.43f, 9f, 12f)
                        reflectiveQuadTo(8.71f, 11.29f)
                        reflectiveQuadTo(8f, 11f)
                        quadTo(7.58f, 11f, 7.29f, 11.29f)
                        reflectiveQuadTo(7f, 12f)
                        reflectiveQuadToRelative(0.29f, 0.71f)
                        reflectiveQuadTo(8f, 13f)
                        reflectiveQuadTo(8.71f, 12.71f)
                        close()
                        moveToRelative(0f, -4f)
                        quadTo(9f, 8.42f, 9f, 8f)
                        quadTo(9f, 7.57f, 8.71f, 7.29f)
                        reflectiveQuadTo(8f, 7f)
                        quadTo(7.58f, 7f, 7.29f, 7.29f)
                        reflectiveQuadTo(7f, 8f)
                        quadTo(7f, 8.42f, 7.29f, 8.71f)
                        reflectiveQuadTo(8f, 9f)
                        reflectiveQuadTo(8.71f, 8.71f)
                        close()
                        moveTo(11f, 13f)
                        horizontalLineToRelative(6f)
                        verticalLineTo(11f)
                        horizontalLineTo(11f)
                        verticalLineToRelative(2f)
                        close()
                        moveTo(11f, 9f)
                        horizontalLineToRelative(6f)
                        verticalLineTo(7f)
                        horizontalLineTo(11f)
                        verticalLineTo(9f)
                        close()
                    }
                }
                .build()
        return _todo!!
    }

private var _todo: ImageVector? = null
