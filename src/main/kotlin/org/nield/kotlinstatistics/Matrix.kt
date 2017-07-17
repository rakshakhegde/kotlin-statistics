package org.nield.kotlinstatistics

import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.RealMatrix

class Matrix(private val m: RealMatrix) {


    operator fun get(row: Int, col: Int) = m.getEntry(row, col)
    operator fun get(row: Int) = m.getRow(row)

    fun transpose() = m.transpose().let(::Matrix)

    val data get() = m.data

    fun toFormattedString() = data.asSequence().map { it.joinToString(" ")}.joinToString("\r\n")

}

fun <T> Iterable<T>.toMatrix(vararg fieldMappers: (T) -> Double) =
        toList().let {
            val colCount = fieldMappers.size
            val rowCount = it.size

            val m = Array2DRowRealMatrix(rowCount, colCount)

            it.forEachIndexed { rowIndex, item ->
               fieldMappers.forEachIndexed { colIndex, mapper ->
                   m.setEntry(rowIndex, colIndex,mapper(item))
               }
            }

            Matrix(m)
        }

