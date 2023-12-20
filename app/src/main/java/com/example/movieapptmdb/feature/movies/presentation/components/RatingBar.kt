package com.example.movieapptmdb.feature.movies.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.movieapptmdb.R


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    starsModifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {

    val filledStars = kotlin.math.floor(rating).toInt()
    val unfilledStars = (stars - kotlin.math.ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
        if (halfStar) {
            Icon(
                modifier = starsModifier,
                painter = painterResource(id = R.drawable.star_background),
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                modifier = starsModifier,
                painter = painterResource(id = R.drawable.star_foreground),
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}





//@Composable
//fun RatingBar(
//    modifier: Modifier = Modifier,
//    rating: Double,
//    spaceBetween: Dp = 0.dp
//) {
//
//    val image = ImageBitmap.imageResource(id = R.drawable.star_background)
//    val imageFull = ImageBitmap.imageResource(id = R.drawable.star_foreground)
//
//    val totalCount = 5
//
//    val height = LocalDensity.current.run { image.height.toDp() }
//    val width = LocalDensity.current.run { image.width.toDp() }
//    val space = LocalDensity.current.run { spaceBetween.toPx() }
//    val totalWidth = width * totalCount + spaceBetween * (totalCount - 1)
//
//
//    Box(
//        modifier
//            .width(totalWidth)
//            .height(height)
//            .drawBehind {
//                drawRating(rating, image, imageFull, space)
//            })
//}
//
//private fun DrawScope.drawRating(
//    rating: Float,
//    image: ImageBitmap,
//    imageFull: ImageBitmap,
//    space: Float
//) {
//
//    val totalCount = 5
//
//    val imageWidth = image.width.toFloat()
//    val imageHeight = size.height
//
//    val reminder = rating - rating.toInt()
//    val ratingInt = (rating - reminder).toInt()
//
//    for (i in 0 until totalCount) {
//
//        val start = imageWidth * i + space * i
//
//        drawImage(
//            image = image,
//            topLeft = Offset(start, 0f)
//        )
//    }
//
//    drawWithLayer {
//        for (i in 0 until totalCount) {
//            val start = imageWidth * i + space * i
//            // Destination
//            drawImage(
//                image = imageFull,
//                topLeft = Offset(start, 0f)
//            )
//        }
//
//        val end = imageWidth * totalCount + space * (totalCount - 1)
//        val start = rating * imageWidth + ratingInt * space
//        val size = end - start
//
//        // Source
//        drawRect(
//            Color.Transparent,
//            topLeft = Offset(start, 0f),
//            size = Size(size, height = imageHeight),
//            blendMode = BlendMode.SrcIn
//        )
//    }
//}
//
//private fun DrawScope.drawWithLayer(block: DrawScope.() -> Unit) {
//    with(drawContext.canvas.nativeCanvas) {
//        val checkPoint = saveLayer(null, null)
//        block()
//        restoreToCount(checkPoint)
//    }
//}
