package com.prepmaster.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import com.prepmaster.app.ui.theme.*

@Composable
fun PrepIllustration(imageType: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color.copy(0.08f), RoundedCornerShape(16.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val boxColor     = color.copy(0.7f)
            val ballColor    = Color(0xFFFF6B6B)
            val arrowColor   = Color(0xFF00E676)
            val groundColor  = color.copy(0.3f)
            val strokeW      = 3f

            // Helper: draw a box
            fun drawBox(x: Float, y: Float, bw: Float, bh: Float) {
                drawRect(color = boxColor, topLeft = Offset(x,y), size = Size(bw,bh),
                    style = Stroke(strokeW))
                drawRect(color = boxColor.copy(0.2f), topLeft = Offset(x,y), size = Size(bw,bh))
            }

            // Helper: draw a ball
            fun drawBall(cx: Float, cy: Float, r: Float) {
                drawCircle(brush = Brush.radialGradient(
                    listOf(Color(0xFFFF9090), Color(0xFFDD3333)),
                    center = Offset(cx - r*0.3f, cy - r*0.3f), radius = r*1.5f),
                    radius = r, center = Offset(cx,cy))
                drawCircle(color = Color.White.copy(0.3f), radius = r*0.3f,
                    center = Offset(cx - r*0.25f, cy - r*0.25f))
            }

            // Helper: draw ground
            fun drawGround(y: Float) {
                drawLine(groundColor, Offset(w*0.05f,y), Offset(w*0.95f,y), strokeW)
            }

            // Helper: draw arrow
            fun drawArrow(x1:Float,y1:Float,x2:Float,y2:Float) {
                drawLine(arrowColor, Offset(x1,y1), Offset(x2,y2), strokeW*1.5f,
                    cap = StrokeCap.Round)
                val angle = kotlin.math.atan2((y2-y1).toDouble(), (x2-x1).toDouble())
                val aLen = 18f
                val aAngle = 0.4f
                drawLine(arrowColor,
                    Offset(x2,y2),
                    Offset(x2 - (aLen * kotlin.math.cos(angle - aAngle)).toFloat(),
                        y2 - (aLen * kotlin.math.sin(angle - aAngle)).toFloat()), strokeW*1.5f)
                drawLine(arrowColor,
                    Offset(x2,y2),
                    Offset(x2 - (aLen * kotlin.math.cos(angle + aAngle)).toFloat(),
                        y2 - (aLen * kotlin.math.sin(angle + aAngle)).toFloat()), strokeW*1.5f)
            }

            when (imageType) {
                "img_in" -> {
                    val bx = w*0.2f; val by = h*0.25f; val bw = w*0.6f; val bh = h*0.55f
                    drawBox(bx, by, bw, bh)
                    drawBall(w/2, by + bh*0.55f, bh*0.2f)
                    drawGround(by + bh + 10)
                }
                "img_on" -> {
                    val bx = w*0.15f; val by = h*0.45f; val bw = w*0.7f; val bh = h*0.35f
                    drawBox(bx, by, bw, bh)
                    drawBall(w/2, by - bh*0.25f, bh*0.22f)
                    drawGround(by + bh + 10)
                }
                "img_under","img_below" -> {
                    val bx = w*0.15f; val by = h*0.2f; val bw = w*0.7f; val bh = h*0.35f
                    drawBox(bx, by, bw, bh)
                    drawBall(w/2, by + bh + bh*0.35f, bh*0.22f)
                    drawGround(by + bh + bh*0.7f + 10)
                }
                "img_over","img_above" -> {
                    val bx = w*0.15f; val by = h*0.5f; val bw = w*0.7f; val bh = h*0.3f
                    drawBox(bx, by, bw, bh)
                    drawBall(w/2, by - bh*0.6f, bh*0.25f)
                    drawGround(by + bh + 10)
                }
                "img_beside","img_next_to","img_by" -> {
                    val bx = w*0.1f; val by = h*0.3f; val bw = w*0.42f; val bh = h*0.45f
                    drawBox(bx, by, bw, bh)
                    drawBall(bx + bw + w*0.15f, by + bh/2, bh*0.22f)
                    drawGround(by + bh + 10)
                }
                "img_between" -> {
                    val bh = h*0.4f; val by = h*0.3f
                    drawBox(w*0.05f, by, w*0.27f, bh)
                    drawBox(w*0.68f, by, w*0.27f, bh)
                    drawBall(w/2, by + bh/2, bh*0.2f)
                    drawGround(by + bh + 10)
                }
                "img_among" -> {
                    val r = h*0.1f; val by = h*0.6f
                    for (i in 0..4) {
                        val angle = i * (2 * Math.PI / 5).toFloat()
                        drawBox(w/2 + (w*0.3f * kotlin.math.cos(angle.toDouble()).toFloat()) - r*1.2f,
                            by/2 + (h*0.28f * kotlin.math.sin(angle.toDouble()).toFloat()) - r,
                            r*2.4f, r*2f)
                    }
                    drawBall(w/2, h/2, r*1.1f)
                }
                "img_behind" -> {
                    // Big box in front, small ball behind
                    val bx = w*0.15f; val by = h*0.3f; val bw = w*0.7f; val bh = h*0.45f
                    drawBall(w/2, by + bh*0.3f, bh*0.15f)
                    drawBox(bx, by, bw, bh)
                    drawGround(by + bh + 10)
                }
                "img_in_front_of" -> {
                    val bx = w*0.15f; val by = h*0.2f; val bw = w*0.7f; val bh = h*0.45f
                    drawBox(bx, by, bw, bh)
                    drawBall(w/2, by + bh + bh*0.3f, bh*0.2f)
                    drawGround(by + bh + bh*0.6f + 10)
                }
                "img_opposite" -> {
                    drawBox(w*0.05f, h*0.3f, w*0.3f, h*0.4f)
                    drawBox(w*0.65f, h*0.3f, w*0.3f, h*0.4f)
                    drawLine(arrowColor, Offset(w*0.38f, h/2), Offset(w*0.62f, h/2), strokeW*1.5f)
                }
                "img_to","img_toward" -> {
                    drawBall(w*0.15f, h/2, h*0.08f)
                    drawArrow(w*0.25f, h/2, w*0.72f, h/2)
                    drawBox(w*0.7f, h*0.3f, w*0.25f, h*0.4f)
                }
                "img_from","img_from_origin" -> {
                    drawBox(w*0.05f, h*0.3f, w*0.25f, h*0.4f)
                    drawBall(w*0.42f, h/2, h*0.08f)
                    drawArrow(w*0.32f, h/2, w*0.75f, h/2)
                    drawBall(w*0.85f, h/2, h*0.08f)
                }
                "img_into" -> {
                    val bx = w*0.35f; val by = h*0.2f; val bw = w*0.55f; val bh = h*0.55f
                    drawBox(bx, by, bw, bh)
                    drawBall(w*0.2f, h*0.45f, h*0.08f)
                    drawArrow(w*0.3f, h*0.45f, bx + bw*0.4f, h*0.55f)
                }
                "img_out_of" -> {
                    val bx = w*0.1f; val by = h*0.2f; val bw = w*0.55f; val bh = h*0.55f
                    drawBox(bx, by, bw, bh)
                    drawBall(w*0.78f, h*0.45f, h*0.08f)
                    drawArrow(bx + bw*0.6f, h*0.5f, w*0.7f, h*0.45f)
                }
                "img_through" -> {
                    drawRect(color=boxColor.copy(0.3f), topLeft=Offset(w*0.3f,h*0.15f), size=Size(w*0.4f,h*0.7f))
                    drawBall(w*0.1f, h/2, h*0.08f)
                    drawArrow(w*0.2f, h/2, w*0.8f, h/2)
                    drawBall(w*0.88f, h/2, h*0.08f)
                }
                "img_across" -> {
                    drawLine(groundColor, Offset(w*0.3f,h*0.45f), Offset(w*0.7f,h*0.45f), strokeW*3)
                    drawBall(w*0.1f, h*0.35f, h*0.08f)
                    drawArrow(w*0.2f, h*0.35f, w*0.88f, h*0.35f)
                    drawBall(w*0.88f, h*0.35f, h*0.08f)
                }
                "img_along" -> {
                    drawLine(groundColor, Offset(w*0.05f,h*0.55f), Offset(w*0.95f,h*0.55f), strokeW*3)
                    drawBall(w*0.15f, h*0.45f, h*0.07f)
                    drawArrow(w*0.25f, h*0.45f, w*0.75f, h*0.45f)
                    drawBall(w*0.82f, h*0.45f, h*0.07f)
                }
                "img_up" -> {
                    drawLine(groundColor, Offset(w*0.05f,h*0.85f), Offset(w*0.95f,h*0.85f), strokeW*3)
                    drawBall(w*0.5f, h*0.75f, h*0.07f)
                    drawArrow(w*0.5f, h*0.65f, w*0.5f, h*0.15f)
                    drawBall(w*0.5f, h*0.1f, h*0.07f)
                }
                "img_down" -> {
                    drawLine(groundColor, Offset(w*0.05f,h*0.85f), Offset(w*0.95f,h*0.85f), strokeW*3)
                    drawBall(w*0.5f, h*0.12f, h*0.07f)
                    drawArrow(w*0.5f, h*0.22f, w*0.5f, h*0.75f)
                    drawBall(w*0.5f, h*0.8f, h*0.07f)
                }
                "img_past" -> {
                    drawBox(w*0.35f, h*0.25f, w*0.3f, h*0.5f)
                    drawBall(w*0.1f, h*0.35f, h*0.08f)
                    drawArrow(w*0.2f, h*0.35f, w*0.9f, h*0.35f)
                    drawBall(w*0.88f, h*0.35f, h*0.08f)
                }
                "img_before" -> {
                    // Ball then star/event
                    drawBall(w*0.15f, h/2, h*0.09f)
                    drawArrow(w*0.27f, h/2, w*0.72f, h/2)
                    // Star shape
                    val cx = w*0.82f; val cy = h/2; val sr = h*0.12f
                    for (i in 0 until 5) {
                        val a = (i * 144 - 90) * Math.PI / 180
                        val a2 = ((i * 144 + 72) - 90) * Math.PI / 180
                        drawLine(arrowColor, Offset(cx + (sr*kotlin.math.cos(a)).toFloat(), cy + (sr*kotlin.math.sin(a)).toFloat()),
                            Offset(cx + (sr*0.4f*kotlin.math.cos(a2)).toFloat(), cy + (sr*0.4f*kotlin.math.sin(a2)).toFloat()), strokeW)
                    }
                }
                "img_after" -> {
                    val cx = w*0.2f; val cy = h/2; val sr = h*0.12f
                    for (i in 0 until 5) {
                        val a = (i * 144 - 90) * Math.PI / 180
                        val a2 = ((i * 144 + 72) - 90) * Math.PI / 180
                        drawLine(arrowColor, Offset(cx + (sr*kotlin.math.cos(a)).toFloat(), cy + (sr*kotlin.math.sin(a)).toFloat()),
                            Offset(cx + (sr*0.4f*kotlin.math.cos(a2)).toFloat(), cy + (sr*0.4f*kotlin.math.sin(a2)).toFloat()), strokeW)
                    }
                    drawArrow(w*0.35f, h/2, w*0.73f, h/2)
                    drawBall(w*0.82f, h/2, h*0.09f)
                }
                "img_at_time","img_on_time","img_in_time" -> {
                    // Clock face
                    val cx = w/2; val cy = h/2; val r = h*0.35f
                    drawCircle(color=boxColor, radius=r, center=Offset(cx,cy), style=Stroke(strokeW))
                    drawCircle(color=boxColor.copy(0.1f), radius=r, center=Offset(cx,cy))
                    // Clock hands
                    drawLine(arrowColor, Offset(cx,cy), Offset(cx, cy-r*0.6f), strokeW*2, cap=StrokeCap.Round)
                    drawLine(arrowColor, Offset(cx,cy), Offset(cx+r*0.45f,cy), strokeW*1.5f, cap=StrokeCap.Round)
                    // Hour marks
                    for (i in 0 until 12) {
                        val a = (i * 30 - 90) * Math.PI / 180
                        val iLen = if (i % 3 == 0) 0.18f else 0.12f
                        drawLine(boxColor,
                            Offset(cx + (r*(1f-iLen)*kotlin.math.cos(a)).toFloat(), cy + (r*(1f-iLen)*kotlin.math.sin(a)).toFloat()),
                            Offset(cx + (r*kotlin.math.cos(a)).toFloat(), cy + (r*kotlin.math.sin(a)).toFloat()), strokeW)
                    }
                    drawCircle(color=arrowColor, radius=4f, center=Offset(cx,cy))
                }
                "img_since","img_for_time","img_until","img_within","img_throughout" -> {
                    // Timeline
                    drawLine(boxColor, Offset(w*0.1f,h*0.5f), Offset(w*0.9f,h*0.5f), strokeW*2)
                    // Dots on timeline
                    listOf(0.15f,0.35f,0.55f,0.75f,0.9f).forEachIndexed { i, x ->
                        drawCircle(if(i<3) arrowColor else boxColor.copy(0.4f), 8f, Offset(w*x, h*0.5f))
                    }
                    drawArrow(w*0.1f,h*0.5f,w*0.92f,h*0.5f)
                    drawBall(w*0.15f,h*0.38f,h*0.07f)
                    drawBall(w*0.75f,h*0.38f,h*0.07f)
                    drawLine(arrowColor, Offset(w*0.15f,h*0.44f), Offset(w*0.75f,h*0.44f), strokeW, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f,4f)))
                }
                "img_with" -> {
                    // Two figures together
                    drawBall(w*0.35f, h*0.35f, h*0.1f)
                    drawLine(boxColor, Offset(w*0.35f,h*0.45f), Offset(w*0.35f,h*0.7f), strokeW*2)
                    drawBall(w*0.65f, h*0.35f, h*0.1f)
                    drawLine(boxColor, Offset(w*0.65f,h*0.45f), Offset(w*0.65f,h*0.7f), strokeW*2)
                    // Connecting hands
                    drawLine(arrowColor, Offset(w*0.42f,h*0.55f), Offset(w*0.58f,h*0.55f), strokeW*3, cap=StrokeCap.Round)
                    drawGround(h*0.73f)
                }
                "img_by_agent","img_by_manner" -> {
                    drawBall(w*0.2f, h*0.4f, h*0.1f)
                    drawArrow(w*0.32f, h*0.4f, w*0.68f, h*0.4f)
                    drawRect(color=arrowColor.copy(0.3f), topLeft=Offset(w*0.55f,h*0.5f), size=Size(w*0.3f,h*0.3f))
                    drawRect(color=arrowColor, topLeft=Offset(w*0.55f,h*0.5f), size=Size(w*0.3f,h*0.3f), style=Stroke(strokeW))
                }
                "img_of" -> {
                    // Ownership link
                    drawBox(w*0.05f, h*0.2f, w*0.35f, h*0.3f)
                    drawBox(w*0.6f, h*0.5f, w*0.35f, h*0.3f)
                    drawLine(arrowColor, Offset(w*0.22f,h*0.5f), Offset(w*0.77f,h*0.5f), strokeW, pathEffect=PathEffect.dashPathEffect(floatArrayOf(10f,5f)))
                }
                "img_in_spite_of","img_despite" -> {
                    // Wall with ball going through
                    for (i in 0..4) drawLine(boxColor.copy(0.6f), Offset(w*0.45f,h*(0.1f+i*0.18f)), Offset(w*0.55f,h*(0.1f+i*0.18f)), strokeW*8)
                    drawBall(w*0.15f,h*0.5f,h*0.08f)
                    drawArrow(w*0.25f,h*0.5f,w*0.85f,h*0.5f)
                    drawBall(w*0.85f,h*0.5f,h*0.08f)
                }
                else -> {
                    // Default: prep label box
                    drawRect(color=color.copy(0.15f), topLeft=Offset(w*0.1f,h*0.2f), size=Size(w*0.8f,h*0.6f),style=Stroke(strokeW))
                    drawBall(w*0.5f,h*0.5f,h*0.15f)
                }
            }
        }
    }
}
