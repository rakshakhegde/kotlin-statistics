package org.nield.kotlinstatistics

import org.apache.commons.math3.linear.ArrayRealVector
import org.apache.commons.math3.linear.RealVector

data class Vector(private val v: RealVector) {

    constructor(vararg elements: Double): this(ArrayRealVector(elements))
    constructor(elements: Array<Double>): this(ArrayRealVector(elements))

    val dimension get() = v.dimension

    operator fun get(index: Int) = v.getEntry(index)

    operator fun get(index: Int, n: Int) = v.getSubVector(index, n).let { Vector(it) }

    val isNaN get() = v.isNaN
    val isInfinite get() = v.isInfinite

    operator fun plus(vector: Vector) = Vector(v.add(vector.v))
    operator fun plus(d: Double) = Vector(v.mapAdd(d))
    operator fun minus(vector: Vector) = Vector(v.subtract(vector.v))
    operator fun times(vector: Vector) = Vector(v.ebeMultiply(vector.v))
    operator fun times(d: Double) = Vector(v.mapMultiply(d))
    operator fun div(vector: Vector) = Vector(v.ebeDivide(vector.v))
    operator fun div(d: Double) = Vector(v.mapDivide(d))

    fun dotProduct(vector: Vector) = Vector(v.dotProduct(vector.v))

    infix fun dp(vector: Vector) = dotProduct(vector)

    fun copy() = Vector(v.copy())

    override fun toString() = v.toString()
}

fun vectorOf(vararg elements: Double) = Vector(*elements)
fun defaultVectorOf(size: Int = 0, defaultValue: Double = 0.0) =  Vector(ArrayRealVector(size, defaultValue))

fun DoubleArray.toVector() = Vector(*this)
fun Iterable<Double>.toVector() = toList().toDoubleArray().let(::Vector)
fun Sequence<Double>.toVector() = toList().toDoubleArray().let(::Vector)